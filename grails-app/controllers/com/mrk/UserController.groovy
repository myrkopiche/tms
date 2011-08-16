package com.mrk

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY','ROLE_COMPANY_ADMIN','ROLE_MODULE_BASE'])
class UserController {
	def springSecurityService
	def partyService
	
   @Secured(['ROLE_SETTINGS_COMPANY_GROUP_VIEW'])
    def index = {
        redirect(action: "list", params: params)
    }
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_VIEW'])
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		PartyCompany currentCompany = CompanyUserGroupRelation.getCurrentCompany(springSecurityService.principal.id)
		
		def users =  partyService.getUsersForCompany(currentCompany.id)

		[companyUserInstanceList: users, companyUserInstanceTotal: users.count()]
	}
}
