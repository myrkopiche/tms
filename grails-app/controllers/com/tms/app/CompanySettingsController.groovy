package com.tms.app

import com.mrk.CompanyUserGroupRelation;

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY','ROLE_COMPANY_ADMIN','ROLE_MODULE_BASE'])
class CompanySettingsController {
	def springSecurityService
	def partyService
	
	@Secured(['ROLE_SETTINGS_COMPANY_VIEW'])
	def index = {
		redirect(action: "list", params: params)
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_VIEW'])
	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def currentCompany = CompanyUserGroupRelation.getCurrentCompany(springSecurityService.principal.id)
		def p = partyService.getUser(springSecurityService.principal.id)
		def userGroups = CompanyUserGroupRelation.getUserGroups(p.id,currentCompany.id)
		def groups = userGroups.toList().list(params)
		println groups
		//[groupList: Principal.list(params), userInstanceTotal: Principal.count()]
	}
	
	
	@Secured(['ROLE_SETTINGS_COMPANY_CREATE'])
	def create = {
	
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_CREATE'])
	def save = {
	
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_VIEW'])
	def show = {
	
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_UPDATE'])
	def edit = {
	
	}
	
	@Secured(['ROLE_SETTINGS_COMPANY_UPDATE'])
	def update = {
	
	}
	
	
	@Secured(['ROLE_SETTINGS_COMPANY_DELETE'])
	def delete = {
	
	}
	
	

	
	
}
