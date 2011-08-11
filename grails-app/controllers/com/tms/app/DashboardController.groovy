package com.tms.app

import com.mrk.CompanyAdminGroup;
import com.mrk.CompanyModuleGroupRelation;
import com.mrk.CompanyUserGroup;
import com.mrk.CompanyUserGroupRelation;
import com.mrk.Group;
import com.mrk.PartyCompany;
import com.mrk.PartyService;
import com.mrk.PartyUser;
import com.mrk.Principal;

import grails.plugins.springsecurity.Secured;
import grails.plugins.springsecurity.SpringSecurityService;


@Secured(['IS_AUTHENTICATED_FULLY','ROLE_MODULE_BASE'])
class DashboardController {
	def userService
	def partyService 
	def springSecurityService

	
	@Secured(['ROLE_DASHBOARD'])
    def index = { 
		//userService.setCurrentCompanyAuthorities(2)
	}
	
	@Secured(['ROLE_DASHBOARD_VIEW'])
	def view = {
		Principal user = Principal.get(springSecurityService.principal.id)
		//def currenTCompany = CompanyUserGroupRelation.getCurrentCompany(user.id)
		//def p = partyService.getUser(springSecurityService.principal.id)
		//springSecurityService.reauthenticate user.username
		/*
		def cug = CompanyAdminGroup.findAll()
		def listofcug = cug.collect { it.id }
		def moduleGroups = CompanyModuleGroupRelation.getCompanyGroups(currenTCompany.id)
		moduleGroups.each{
			def cc = CompanyModuleGroupRelation.get(it.id)
			log.debug("authorities for module admin user: ${cc.adminGroups.authorities}")
		}
		log.debug("companuAdminGroup is ${listofcug}")
		
		//	log.debug("company = ${user.authorities}")
		//println springSecurityService.principal.id
		/*
		Principal user = Principal.get(springSecurityService.principal.id)	
		println user
		def p = partyService.getUser(springSecurityService.principal.id)
		def currenTCompany = CompanyUserGroupRelation.getCurrentCompany(user.id)
		log.debug("currentCompany is ${currenTCompany}")
		//List<Long> groups = [2]
		
		//partyService.updateGroupsForUserCompany(p.id, 2, groups)
		
		springSecurityService.reauthenticate user.username		

		
		//log.debug("current company working is ${session['company.current']}")
		
		def companies =  CompanyUserGroupRelation.getAllUserCompanies(springSecurityService.principal.id)
		[companies:companies]
		*/
	}
	
	
}
