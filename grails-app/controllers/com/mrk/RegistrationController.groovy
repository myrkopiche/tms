package com.mrk


import org.jasypt.util.text.BasicTextEncryptor;
import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class RegistrationController {
	
	def registrationService
	def tmsEncryptionService
	
	static allowedMethods = [confirmation:'GET']
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def confirmation = { 
		println params.tk
		println params.email
		registrationService.confirmRegistration(params.tk,params.email)
		render "The registration is completed, you can now log in the application."	
	}
	
}
