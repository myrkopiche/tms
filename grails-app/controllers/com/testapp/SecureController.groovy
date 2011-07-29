package com.testapp
import com.mrk.Principal;
import com.mrk.PartyUser;
import grails.plugins.springsecurity.Secured

class SecureController {
	
	def springSecurityService
	def partyService
	
    def index = {
		Principal user = Principal.get(springSecurityService.principal.id)
		def p = PartyUser.findByPrincipal(user)
		println p
		
      render 'Secure access only'
   }

}
