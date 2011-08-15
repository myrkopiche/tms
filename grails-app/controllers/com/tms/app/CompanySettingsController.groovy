package com.tms.app

import com.mrk.CompanyUserGroup;
import com.mrk.CompanyUserGroupRelation;
import com.mrk.PartyCompany;

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY','ROLE_COMPANY_ADMIN','ROLE_MODULE_BASE'])
class CompanySettingsController {
	def springSecurityService
	def partyService
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_VIEW'])
	def index = {
		redirect(action: "listgroup", params: params)
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_VIEW'])
	def listgroup = {
		def currentcompany = CompanyUserGroupRelation.getCurrentCompany(springSecurityService.principal.id)
		params.max = Math.min(params.max ? params.int('max') : 10, 100)	
		
		List<CompanyUserGroup> userGroups = CompanyUserGroup.getCompanyGroups(currentcompany.id,params)

		def userGroupsT 
		if(!userGroups?.count()){
			userGroupsT = 0
		}
		else{
			userGroupsT = userGroups.count()
		}
		
		[groupList: userGroups, groupListTotal: userGroupsT]
		
	}
	
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_CREATE'])
	def creategroup = {
		def groupInstance = new CompanyUserGroup()
		groupInstance.properties = params
		return [groupInstance: groupInstance]
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_CREATE'])
	def savegroup = {
		def currentcompany = CompanyUserGroupRelation.getCurrentCompany(springSecurityService.principal.id)
		params.company = currentcompany
		def groupInstance = new CompanyUserGroup(params)
		if (groupInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), groupInstance.id])}"
			redirect(action: "listgroup")
		}
		else {
			render(view: "creategroup", model: [groupInstance: groupInstance])
		}
	
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_VIEW'])
	def showgroup = {
		def groupInstance = CompanyUserGroup.get(params.id)
		if (!groupInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'Group'), params.id])}"
			redirect(action: "listgroup")
		}
		else {
			[groupInstance: groupInstance]
		}
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_UPDATE'])
	def editgroup = {
	
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_UPDATE'])
	def updategoup = {
	
	}
	
	
	@Secured(['ROLE_SETTINGS_COMPANY_DELETE'])
	def deletegroup = {
	
	}
	
	

	
	
}
