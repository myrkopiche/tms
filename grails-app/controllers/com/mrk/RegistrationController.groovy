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
		def reg = new Registration(params)
		println(reg)
		render "yeah"
		/*
		if (!userInstance.hasErrors()){
			render "errror"
		}
		*/
		
	}
	
	
	
	
}
