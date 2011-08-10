package com.mrk

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY'])
class PartyCompanyController {
	def partyService
	def springSecurityService
	static allowedMethods = [setDefaultCompany: "POST"]
    static scaffold = true
	
	
	def setDefaultCompany= {
		def user = partyService.getUser(springSecurityService.principal.id)
		//persist default company in database
		partyService.setDefaultCompany(user.id, params.company.id as int)
		
		//store in session variable
		session['company.current'] =params.company.id
		log.debug("parameter ${params}")
		//log.debug("current session company id ${session['company.current']}")
		springSecurityService.reauthenticate springSecurityService.principal.username
		redirect(controller:"dashboard", action:"view")
	}
	
}
