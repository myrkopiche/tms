package com.mrk


import org.jasypt.util.text.BasicTextEncryptor;
import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class RegistrationController {
	
	def registrationService
	def tmsEncryptionService
	
	static allowedMethods = [confirmation:'GET',index:'GET']
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def confirmation = { 
		registrationService.confirmRegistration(params.tk,params.email)
		render "The registration is completed, you can now log in the application."	
	}
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def index ={

	}
	
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def registrationstep1 ={
		Principal principal = new Principal(params['principal'])
		principal.validate()
		PartyUser partyuser = new PartyUser(params['partyuser'])
	    partyuser.setPrincipal(principal)	
		partyuser.validate()
		
		println partyuser.principal.confirm
		
		if( partyuser.hasErrors() || partyuser.principal.hasErrors()   ) {
			render(view: "index", model:[partyuser:partyuser,principal:principal])		
		}
		else
		{
			//registrationService.registerUser(partyuser)
		}
		
	}
		
	
}
