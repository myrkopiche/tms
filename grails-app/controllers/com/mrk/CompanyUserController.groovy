package com.mrk

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY','ROLE_COMPANY_ADMIN','ROLE_MODULE_BASE'])
class CompanyUserController {
	def springSecurityService
	def partyService
	def invitationService
	
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
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_CREATE'])
	def invite= {
		
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_CREATE'])
	def search_user= {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def pu = PartyUser.createCriteria()
		def results = pu.list(params) {
			like("firstname",  params.firstname +'%')
			or{
				like("lastname", params.lastname +'%')
			}
			or{
				like("email", params.email + '%')
			}
			
			order("firstname", "desc")
		}
		render(view: "invite", model:[results:results,resultsTotal:results.count()])	
	}
	
	def sendInvitation = {
		PartyCompany currentCompany = CompanyUserGroupRelation.getCurrentCompany(springSecurityService.principal.id)
		def user = PartyUser.get(params.userId)
		if(user)
		{
			invitationService.sendUserInvitation(user.id, currentCompany.id)
			render("send complete")
		}
		else
		{
			redirect(action: "invite")
		}
		
	}
	
	
	
	
	
}
