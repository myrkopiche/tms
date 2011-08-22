package com.mrk


import org.jasypt.util.text.BasicTextEncryptor;

import com.sun.tools.javac.comp.Flow;

import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class InvitationController implements Serializable {
	
	def springSecurityService
	def invitationService
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def confirmation = {
		if(params.tk && params.userId && params.companyId)
		{
			invitationService.confirmInvitation(params.tk,params.userId,params.companyId)
			render "Invitation Successfull you are now part of the company"
		}
		else
		{
			render "nothing"
		}
	}
	
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def cancel = {
		println params
		if(params.tk && params.userId && params.companyId)
		{
			invitationService.cancelInvitation(params.tk,params.userId,params.companyId)
			render "You Cancel the invitation, thank you."
		}
		else
		{
			render "nothing"
		}		
	}
	
	
	
}