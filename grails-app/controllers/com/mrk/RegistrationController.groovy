package com.mrk


import org.jasypt.util.text.BasicTextEncryptor;
import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class RegistrationController {
	
	def registrationService
	def tmsEncryptionService
	
	static allowedMethods = [confirmation:'GET',index:'GET',registrationstep1:'POST']
	
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
		Principal principal = new Principal(params)
		principal.validate()
		PartyUser partyuser = new PartyUser(params)
	    partyuser.setPrincipal(principal)	
		partyuser.validate()
		
		if( partyuser.hasErrors() || principal.hasErrors()   ) {
			render(view: "index", model:[partyuser:partyuser,principal:principal])		
		}
		else
		{
			registrationService.registerUser(params)
		}
		
	}
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def companyRegistration={
		
	}
		
	
}
