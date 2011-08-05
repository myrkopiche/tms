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
	public Boolean registerCompany(Principal principal,PartyCompany partycompany) {
		log.debug("About to register a new company: ${principal}");
		
		if(partycompany.save(flush:true))
		{
			//get partyUser
			def partyuser = PartyUser.findByPrincipal(principal)
			
			//add user to company
			partyService.addUsersToCompany(partycompany.id,partyuser.id as List)
			
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
	
	public void confirmRegistration(String encryptedToken, String encryptedEmail)
	{
		log.debug("Encrypted email: " + encryptedEmail);
		log.debug("Encrypted token: " + encryptedToken);
		
		String email = tmsEncryptionService.decrypt(encryptedEmail).decodeURL()
		String token = tmsEncryptionService.decrypt(encryptedToken).decodeURL()
		PartyUser pu = PartyUser.findByEmail(email)
		Registration reg = Registration.findByRegistrationTokenAndPartyUser(token,pu)
		Principal principal = reg.partyUser.principal
		principal.setEnabled(true)
		principal.save()
		
		//delete registration
		reg.delete()
		
	}
	
	public void companyConfirmation(String encryptedToken, String encryptedEmail,String encryptCompanyEmail)
	{
		log.debug("Encrypted email: " + encryptedEmail);
		log.debug("Encrypted CompanyEmail: " + encryptCompanyEmail);
		log.debug("Encrypted token: " + encryptedToken);
		
		String email = tmsEncryptionService.decrypt(encryptedEmail).decodeURL()
		String companyEmail = tmsEncryptionService.decrypt(encryptCompanyEmail).decodeURL()
		String token = tmsEncryptionService.decrypt(encryptedToken).decodeURL()
		
		PartyUser pu = PartyUser.findByEmail(email)
		//PartyCompany pc = PartyCompany
		Registration reg = Registration.findByRegistrationTokenAndPartyUser(token,pu)
		def cug = CompanyUserGroupRelation.findByUserAndCompany(reg.partyuser,)
		
		/*
		Principal principal = reg.partyUser.principal
		principal.setEnabled(true)
		principal.save()
		
		//delete registration
		reg.delete()
		*/
	}
	
	
	private void sendCompanyConfirmationEmail(final Registration registration,final PartyCompany partycompany){
		log.debug("sending company confirmation")
		
		def emailEncrypt = tmsEncryptionService.encrypt(registration.partyUser.email).encodeAsURL()
		def companyEmailEncrypt = tmsEncryptionService.encrypt(partycompany.email).encodeAsURL()
		def tokenEncrypt = tmsEncryptionService.encrypt(registration.registrationToken).encodeAsURL()
		
		sendMail {
			to "${registration.partyUser.email}"
			subject "Hello ${partycompany.name}"
			body( view:"/mail/registrationCompanyConfirmation",
				model:[name:partycompany.name,token:tokenEncrypt,email:emailEncrypt,emailConfirmationUrl:this.emailCompanyConfirmationUrl,companyEmail:companyEmailEncrypt ])
		  }
		  
		
	}
	
	
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
