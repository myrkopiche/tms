package com.mrk

import org.springframework.transaction.annotation.Transactional;

class RegistrationService {
	def springSecurityService
    def emailConfirmationUrl = "http://localhost:8080/tms/registration/confirmation"
	
	@Transactional(readOnly = false)
    public void registerUser(params) {
		log.debug("About to register a new user: ${params}");
		
		// Create the principal
		Principal userInstance = new Principal(params)
		userInstance.setEnabled(false)
		userInstance.setPassword(springSecurityService.encodePassword(params.password)) 
		userInstance.save()
		
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
	
	public void confirmRegistration(String encryptedToken, String encryptedEmail)
	{
		log.debug("Encrypted email: " + encryptedEmail);
		log.debug("Encrypted token: " + encryptedToken);
		
		String email = new String(encryptedEmail.decodeURL().decodeBase64())
		String token = new String(encryptedToken.decodeURL().decodeBase64())
		
		//Registration registration = Registration.findByRegistrationTokenAndPartyUser()
		
	}
	
	private void sendConfirmationEmail(final Registration registration){
		
		def emailEncrypt = registration.partyUser.email.encodeAsBase64().encodeAsURL()
		def tokenEncrypt = registration.registrationToken.encodeAsBase64().encodeAsURL()	
		//def decodedEmail = new String(emailEncrypt.decodeURL().decodeBase64())	
		
		sendMail {
			to "${registration.partyUser.email}"
			subject "Hello ${registration.partyUser.firstname}"
			body( view:"/mail/registrationConfirmation",  
				model:[firstname:registration.partyUser.firstname,lastname:registration.partyUser.lastname,token:tokenEncrypt,email:emailEncrypt,emailConfirmationUrl:this.emailConfirmationUrl ])
		  }
		
	}
	
}
