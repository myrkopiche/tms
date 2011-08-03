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
		println params.tk
		println params.email
		registrationService.confirmRegistration(params.tk,params.email)
		render "The registration is completed, you can now log in the application."	
	}
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def index ={
		
	}
	
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def registrationstep1 ={
		//def reg = new Registration(params.properties)
		params.password = null
		Principal pr = new Principal(params)
		pr.validate()
		PartyUser pu = new PartyUser(params)
	    pu.setPrincipal(pr)		
		pu.validate()
		if( pu.hasErrors() || pr.hasErrors() ) {
			render(view: "index", model:[pu:pu,pr:pr])
		}
		
		
		//render params
		/*
		if (!userInstance.hasErrors()){
			render "errror"
		}
		*/
		
	}
	
	
	
	
}
