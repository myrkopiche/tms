package com.mrk

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY','ROLE_COMPANY_ADMIN','ROLE_MODULE_BASE'])
class CompanyUserGroupController {
	def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_VIEW'])
    def index = {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_VIEW'])
    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		PartyCompany company = CompanyUserGroupRelation.getCurrentCompany(springSecurityService.principal.id)		
		def groups =  CompanyUserGroup.getCompanyGroups(company,params)				
        [companyUserGroupInstanceList: groups, companyUserGroupInstanceTotal: groups.count()]
    }
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_CREATE'])
    def create = {
        def companyUserGroupInstance = new CompanyUserGroup()
        companyUserGroupInstance.properties = params
		
		//modules
		PartyCompany company = CompanyUserGroupRelation.getCurrentCompany(springSecurityService.principal.id)
		def modules = CompanyModuleGroupRelation.findAllByCompanyAndEnable(company,true)
        return [companyUserGroupInstance: companyUserGroupInstance,modules:modules]
    }
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_CREATE'])
    def save = {
		def currentcompany = CompanyUserGroupRelation.getCurrentCompany(springSecurityService.principal.id)
		params.company = currentcompany
        def companyUserGroupInstance = new CompanyUserGroup(params)
        if (companyUserGroupInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'companyUserGroup.label', default: 'CompanyUserGroup'), companyUserGroupInstance.id])}"
            redirect(action: "show", id: companyUserGroupInstance.id)
        }
        else {
            render(view: "create", model: [companyUserGroupInstance: companyUserGroupInstance])
        }
    }
	

	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_VIEW'])
    def show = {
        def companyUserGroupInstance = CompanyUserGroup.get(params.id)
        if (!companyUserGroupInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'companyUserGroup.label', default: 'CompanyUserGroup'), params.id])}"
            redirect(action: "list")
        }
        else {
            [companyUserGroupInstance: companyUserGroupInstance]
        }
    }
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_UPDATE'])
    def edit = {
        def companyUserGroupInstance = CompanyUserGroup.get(params.id)
        if (!companyUserGroupInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'companyUserGroup.label', default: 'CompanyUserGroup'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [companyUserGroupInstance: companyUserGroupInstance]
        }
    }
	
	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_UPDATE'])
    def update = {
        def companyUserGroupInstance = CompanyUserGroup.get(params.id)
        if (companyUserGroupInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (companyUserGroupInstance.version > version) {
                    
                    companyUserGroupInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'companyUserGroup.label', default: 'CompanyUserGroup')] as Object[], "Another user has updated this CompanyUserGroup while you were editing")
                    render(view: "edit", model: [companyUserGroupInstance: companyUserGroupInstance])
                    return
                }
            }
            companyUserGroupInstance.properties = params
            if (!companyUserGroupInstance.hasErrors() && companyUserGroupInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'companyUserGroup.label', default: 'CompanyUserGroup'), companyUserGroupInstance.id])}"
                redirect(action: "show", id: companyUserGroupInstance.id)
            }
            else {
                render(view: "edit", model: [companyUserGroupInstance: companyUserGroupInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'companyUserGroup.label', default: 'CompanyUserGroup'), params.id])}"
            redirect(action: "list")
        }
    }

	@Secured(['ROLE_SETTINGS_COMPANY_GROUP_DELETE'])
    def delete = {
        def companyUserGroupInstance = CompanyUserGroup.get(params.id)
        if (companyUserGroupInstance) {
            try {
                companyUserGroupInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'companyUserGroup.label', default: 'CompanyUserGroup'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'companyUserGroup.label', default: 'CompanyUserGroup'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'companyUserGroup.label', default: 'CompanyUserGroup'), params.id])}"
            redirect(action: "list")
        }
    }
}
