package com.mrk

import org.springframework.transaction.annotation.Transactional;

class RegistrationService {
	def springSecurityService
	def tmsEncryptionService
    def emailConfirmationUrl = "http://localhost:8080/tms/registration/confirmation"
	
	@Transactional(readOnly = false)
    public void registerUser(PartyUser pu) {
		log.debug("About to register a new user: ${pu}");

		// Create the principal
	
		Principal userInstance = new Principal()
		userInstance = pu.principal
		userInstance.setEnabled(false)
		userInstance.setPassword(springSecurityService.encodePassword(pu.principal.password)) 
		userInstance.save()
		
		
		PartyUser partyUser = new PartyUser()
		partyUser = pu
		partyUser.setPrincipal(userInstance)
		partyUser.save()
		
		Registration reg = new Registration()
		reg.setPartyUser(partyUser)
		reg.setRegistrationToken(UUID.randomUUID().toString())
		reg.save()
		
		//send email
		this.sendConfirmationEmail reg

		log.debug("Successfully register user = ${pu}");
		
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
	
		//Registration registration = Registration.findByRegistrationTokenAndPartyUser()
		
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
