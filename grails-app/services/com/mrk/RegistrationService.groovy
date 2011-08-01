package com.mrk
import org.springframework.transaction.annotation.Transactional;

class RegistrationService {
	def springSecurityService
   
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
		log.debug("Successfully register user = ${params}");
		
    }
	
	private void sendConfirmationEmail(final Registration registration){
		println("firstname = ${registration.firstname}")
		println("lastname = ${registration.lastname}")
		println("email = ${registration.email}")
		println("password = ${registration.password}")
		
	}
	
}
