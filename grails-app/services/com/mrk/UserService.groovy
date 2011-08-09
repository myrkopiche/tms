package com.mrk

import org.springframework.transaction.annotation.Transactional;

class UserService {
	
	def springSecurityService
	
	
	@Transactional(readOnly = false)
	public void setCurrentCompanyAuthorities(Long companyId) {
		 
		log.debug("Calling setCurrentCompanyAuthorities with company id: ${companyId}")
		def authentication = springSecurityService.authentication
		def principal = springSecurityService.principal
		generateCompanyAuthorities(companyId);
		/*
		
				UsernamePasswordAuthenticationToken newAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, authentication.getCredentials(), userDetails
								.getAuthorities());
		
				SecurityContextHolder.getContext().setAuthentication(
						newAuthenticationToken);
		
				//log.debug("Current Company changed to {}", companyId);
		*/
	  }
	
	
	@Transactional(readOnly = false)
	private void generateCompanyAuthorities(Long companyId) {
		log.debug("Calling generate Company Authorities with company id: ${companyId}")
		def principal = springSecurityService.principal
		log.debug("principal id: ${principal.id}")
		
		
		//setCurrentCompany to user
			 //user.setCurrentCompanyId(companyId);
		PartyCompany pc = PartyCompany.get(companyId)
		Principal pr = Principal.get(principal.id)
		PartyUser pu = PartyUser.findByPrincipal(pr)
		
		def cugr = CompanyUserGroupRelation.findByCompanyAndUser(pc,pu)
		def companyAuthorities = []
		cugr.each{
			log.debug("group id: ${it.group.id}")
			it.group.authorities.each{
				log.debug("authority id : ${it.id}")
				companyAuthorities.add(it)
			}
		}
		
		log.debug("authorities : ${companyAuthorities}")
		log.debug('Calling generate Company Authorities with company successful')
		
}
	

	
	
	
}
