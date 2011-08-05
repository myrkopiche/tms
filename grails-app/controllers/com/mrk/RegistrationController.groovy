package com.mrk


import org.jasypt.util.text.BasicTextEncryptor;

import com.sun.tools.javac.comp.Flow;

import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class RegistrationController implements Serializable {
	
	def registrationService
	def tmsEncryptionService
	def springSecurityService
	def partyService
	
	static allowedMethods = [confirmation:'GET',index:'GET',registrationstep1:'POST',company:'GET',registerCompany:'POST']
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def confirmation = { 
		registrationService.confirmRegistration(params.tk,params.email)
		render "The registration is completed, you can now log in the application."	
	}
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def companyConfirmation = {
		registrationService.companyConfirmation(params.tk,params.email,params.companyEmail)
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
	def companyFlow={
		/*List adl = AddressType.addressTypeList()		
		println adl
		[addresstype:adl]*/
		init{
			action{
				Principal principal = new Principal()
				PartyCompany partycompany = new PartyCompany()	
				Address address = new Address()
				Phone phone = new Phone()	
				List addresstype = AddressType.addressTypeList()	
				List phonetype = PhoneType.phoneTypeList()	
				List accounttype = AccountType.accountTypeList()
				[principal: principal,partycompany:partycompany,address:address,phone:phone,addresstype:addresstype,phonetype:phonetype,accounttype:accounttype]
			}
			on("success").to("step1")
		}
		
		step1 {
			on('step2'){
				//validate user password
				flow.principal.properties = params
				String password = springSecurityService.encodePassword(params.password)
				def pr = Principal.findByUsernameAndPassword(params.username,password)
				if(pr)
				{
						flow.principal = pr
						return success()
				}
				else
				{
					flow.principal.errors.rejectValue(null, 'principal.username.password.notfound')
					return error()
				}
					
				
			}.to "step2"
		}
		
		step2{			
			on("cancel").to "init"
			on("step3"){
				def acttype = AccountType.get(params.accountId)	
				[acttype:acttype]
			}.to "step3"
		}
		
		step3{
			on("cancel").to "init"
			on("step2").to "step2"
			on("step4"){
				flow.partycompany.properties = params
				flow.partycompany.accountType = flow.acttype
				flow.addresstypeId = params.addresstypeId
				flow.phonetypeId = params.phonetypeId
				flow.address.properties = params
				flow.address.type =  AddressType.get(params.addresstypeId)	
				flow.phone.properties = params
				flow.phone.type = PhoneType.get(params.phonetypeId)	
				if(!flow.partycompany.validate())return error()
				if(!flow.address.validate())return error()
				if(!flow.phone.validate())return error()
				
			}.to "regComp"
		}
		
		regComp{
			action{
				def valid = registrationService.registerCompany(flow.principal, flow.partycompany)
				if(valid)
				{
					return success()
				}
				else
				{
					return error()
				}
				/*
				flow.partycompany.save()
				def partyuser = PartyUser.findByPrincipal(flow.principal)
				partyService.addUsersToCompany(flow.partycompany.id,partyuser.id as List)
				partyuser.discard()
				log.debug('success')
				*/
			}
			on("success").to("step4")
		}
		
		step4{
			on("success").to "regComp"
			on("back").to "regComp"
		}
		
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
