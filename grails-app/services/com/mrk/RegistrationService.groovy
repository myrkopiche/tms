package com.mrk

import org.springframework.transaction.annotation.Transactional;

class RegistrationService {
	def springSecurityService
	def tmsEncryptionService
	def partyService
    def emailConfirmationUrl = "http://localhost:8080/tms/registration/confirmation"
	def emailCompanyConfirmationUrl = "http://localhost:8080/tms/registration/companyConfirmation"
	
	@Transactional(readOnly = false)
    public void registerUser(params) {
		log.debug("About to register a new user: ${params}");

		// Create the principal
		def passwordEncrypt = springSecurityService.encodePassword(params.password)
		Principal userInstance = new Principal(params)
		userInstance.setEnabled(false)
		userInstance.setPassword(passwordEncrypt)
		userInstance.setConfirm(passwordEncrypt)
		userInstance.save(flush:true)
		
		PartyUser partyUser = new PartyUser(params)
		partyUser.setPrincipal(userInstance)
		partyUser.save()
		
		Registration reg = new Registration()
		reg.setPartyUser(partyUser)
		reg.setRegistrationToken(UUID.randomUUID().toString())
		reg.save()
			
		//send email
		this.sendConfirmationEmail reg
		
		
		log.debug("Successfully register user = ${params}");
		
    }
	
	@Transactional(readOnly = false)
	public Boolean registerCompany(Principal principal,PartyCompany partycompany,Address address,Phone phone) {
		log.debug("About to register a new company: ${principal}");		
		
		//add address and phone to company
		address.save()
		phone.save()
		partycompany.addToAddresses(address).addToPhones(phone)
		if(partycompany.save(flush:true))
		{
			//get partyUser
			def partyuser = PartyUser.findByPrincipal(principal)
			
			//add user to company
			partyService.addUsersToCompany(partycompany.id,partyuser.id as List,true)
			
			//add company base module rights
			CompanyModuleGroup cmg = CompanyModuleGroup.findByName('GROUP_MODULE_BASE')
			def cmgr = new CompanyModuleGroupRelation(company:partycompany,group:cmg,enable:true).save()
			
			//add admin base group rights
			def cag = CompanyAdminGroup.findAll()
			cag.each{
				cmgr.addToAdminGroups(it)
			}
			
			//create registration form
			
			Registration reg = new Registration()
			reg.setPartyUser(partyuser)
			reg.setRegistrationToken(UUID.randomUUID().toString())
			reg.save()
			
			//send email
			this.sendCompanyConfirmationEmail(reg,partycompany)
			log.debug("Successfully register company = ${partycompany}");
			return true
			
		}
		else
		{
			return false
		}
		
	}	
	
	@Transactional(readOnly = false)
	public void confirmRegistration(String encryptedToken, String encryptedEmail)
	{
		log.debug("Encrypted email: " + encryptedEmail);
		log.debug("Encrypted token: " + encryptedToken);
		
		String email = tmsEncryptionService.decrypt(encryptedEmail).decodeURL()
		String token = tmsEncryptionService.decrypt(encryptedToken).decodeURL()
		
		log.debug('decrypted token and email')
		
		PartyUser pu = PartyUser.findByEmail(email)
		Registration reg = Registration.findByRegistrationTokenAndPartyUser(token,pu)
		Principal principal = reg.partyUser.principal
		principal.setEnabled(true)
		principal.save()
		
		//delete registration
		reg.delete()
		
	}
	
	@Transactional(readOnly = false)
	public void companyConfirmation(String encryptedToken, String encryptedEmail,String encryptCompanyEmail,String companyId)
	{
		log.debug("Encrypted email: " + encryptedEmail);
		log.debug("Encrypted CompanyEmail: " + encryptCompanyEmail);
		log.debug("Encrypted token: " + encryptedToken);
		
		String email = tmsEncryptionService.decrypt(encryptedEmail).decodeURL()
		String companyEmail = tmsEncryptionService.decrypt(encryptCompanyEmail).decodeURL()
		def compId =  tmsEncryptionService.decrypt(companyId).decodeURL()  as Long
		String token = tmsEncryptionService.decrypt(encryptedToken).decodeURL()
		
		//company
		PartyCompany pc = PartyCompany.findByIdAndEmail(compId,companyEmail)
		
		//find company users
		PartyUser compUser = new PartyUser()
		def users = partyService.getAdminUsersForCompany(compId)
		users.each {  
			if(it.email == email)
			{
				compUser = it
				return
			}	
		}
		log.debug("compUser: ${compUser}")
		//find association
		Registration reg = Registration.findByRegistrationTokenAndPartyUser(token,compUser)
		
		if(reg) //si il y a bien registration activé la compagnie
		{
			pc.setEnable(true)
			pc.save()
			reg.delete()
		}
		else
		{
			throw new Exception('This is not a valid registration company, please try again')
		}
		
		log.debug("successfully register company : ${pc}")
		
	}
	
	@Transactional(readOnly = true)
	private void sendCompanyConfirmationEmail(final Registration registration,final PartyCompany partycompany){
		log.debug("sending company confirmation")
		
		def emailEncrypt = tmsEncryptionService.encrypt(registration.partyUser.email).encodeAsURL()
		def companyEmailEncrypt = tmsEncryptionService.encrypt(partycompany.email).encodeAsURL()
		def companyIdEncrypt = tmsEncryptionService.encrypt(partycompany.id.toString()).encodeAsURL()
		def tokenEncrypt = tmsEncryptionService.encrypt(registration.registrationToken).encodeAsURL()
		
		sendMail {
			to "${registration.partyUser.email}"
			subject "Hello ${partycompany.name}"
			body( view:"/mail/registrationCompanyConfirmation",
				model:[name:partycompany.name,token:tokenEncrypt,email:emailEncrypt,emailConfirmationUrl:this.emailCompanyConfirmationUrl,companyEmail:companyEmailEncrypt,companyId:companyIdEncrypt ])
		  }
		  
		
	}
	
	@Transactional(readOnly = true)
	private void sendConfirmationEmail(final Registration registration){
		
		def emailEncrypt = tmsEncryptionService.encrypt(registration.partyUser.email).encodeAsURL()
		def tokenEncrypt = tmsEncryptionService.encrypt(registration.registrationToken).encodeAsURL()	
		//def decodedEmail = new String(emailEncrypt.decodeURL().decodeBase64())	
		
		sendMail {
			to "${registration.partyUser.email}"
			subject "Hello ${registration.partyUser.firstname}"
			body( view:"/mail/registrationConfirmation",  
				model:[firstname:registration.partyUser.firstname,lastname:registration.partyUser.lastname,token:tokenEncrypt,email:emailEncrypt,emailConfirmationUrl:this.emailConfirmationUrl ])
		  }
		
	}
	
}
