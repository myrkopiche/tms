package com.tms.app

import grails.plugins.springsecurity.Secured;

@Secured(['IS_AUTHENTICATED_FULLY'])
class DashboardController {
	def userService
	
	@Secured(['ROLE_DASHBOARD'])
    def index = { 
		userService.setCurrentCompanyAuthorities(2)
	}
}
