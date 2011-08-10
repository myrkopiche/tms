package com.mrk

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY'])
class PartyCompanyController {
	def partyService
	def springSecurityService
    static scaffold = true
	
	
	def setDefaultCompany= {
		def user = partyService.getUser(springSecurityService.principal.id)
		//persist default company in database
		partyService.setDefaultCompany(user.id, 2)
		
		//store in session variable
		session['company.current'] =2
		
		log.debug("current session company id ${session['company.current']}")
		
		redirect(controller:"dashboard", action:"view")
	}
	
}
