package com.tms.app

import com.mrk.CompanyUserGroupRelation;
import com.mrk.PartyCompany;
import com.mrk.PartyService;
import com.mrk.PartyUser;
import com.mrk.Principal;

import grails.plugins.springsecurity.Secured;
import grails.plugins.springsecurity.SpringSecurityService;


@Secured(['IS_AUTHENTICATED_FULLY'])
class DashboardController {
	def userService
	def partyService 
	def springSecurityService

	
	@Secured(['ROLE_DASHBOARD'])
    def index = { 
		//userService.setCurrentCompanyAuthorities(2)
	}
	
	
	def view = {
		//println springSecurityService.principal.id
		
		Principal user = Principal.get(springSecurityService.principal.id)	
		println user.getAuthorities()
		def p = PartyUser.findByPrincipal(user)
		def currenTCompany = CompanyUserGroupRelation.getCurrentCompany(user.id)
		log.debug("currentCompany is ${currenTCompany}")
		List<Long> groups = [1,2]
		
		partyService.updateGroupsForUserCompany(p.id, 2, groups)
		springSecurityService.reauthenticate user.username
		

		def companies =  CompanyUserGroupRelation.getAllUserCompanies(springSecurityService.principal.id)
		[companies:companies]
	}
	
	
}
