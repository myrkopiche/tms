package com.mrk


import org.jasypt.util.text.BasicTextEncryptor;
import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class RegistrationController {
	
	def registrationService
	def tmsEncryptionService
	def springSecurityService
	
	static allowedMethods = [confirmation:'GET',index:'GET',registrationstep1:'POST',company:'GET',registerCompany:'POST']
	
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
	def company={
		List adl = AddressType.addressTypeList()		
		println adl
		[addresstype:adl]
	}
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def registerCompany={
		Principal principal = new Principal()
		String password = springSecurityService.encodePassword(params.password)
		def pr = Principal.findByUsernameAndPassword(params.username,password)
		
		if(pr)
		{
			PartyCompany partyCompany = new PartyCompany(params)
			partyCompany.validate()
			
			Address adr = new Address(params)
			adr.validate()
			
			if( partyCompany.hasErrors() || adr.hasErrors()    ) {
				render(view: "company", model:[principal:pr,address:adr,partycompany:partyCompany])
			}
		}
		else //User not found need to register before
		{
			principal.errors.rejectValue('username', 'principal.username.password.notfound') 
			//principal.errors.rejectValue('pa', 'principal.username.password.notfound')
			render(view: "company", model:[principal:principal])
		}
		
		
		
	}
	
	
		
	
}
