package com.mrk


import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession;
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

class PartyService {
	
	
	@Transactional(readOnly = true)
	public List getUsersForCompany(Long companyId){
		log.debug("Calling getUsersForCompany() with companyId = ${companyId}");
		def company = PartyCompany.get(companyId)
		Map params =  [:]
		params.put "companyId",companyId
		List parties = CompanyUserGroupRelation.findAllByCompany(company)
		List result = []
		parties.each{
			PartyUser party = it.user
			result.add(party)
		}
				
		log.debug("getUsersForCompany() successfull and return users = ${result}");
		return result
	}
	
	@Transactional(readOnly = true)
	public List getAdminUsersForCompany(Long companyId){
		log.debug("Calling getAdminUsersForCompany() with companyId = ${companyId}");
		def company = PartyCompany.get(companyId)
		Map params =  [:]
		params.put "companyId",companyId
		List parties = CompanyUserGroupRelation.findAllByCompanyAndIs_admin(company,true)
		List result = []
		parties.each{
			PartyUser party = it.user
			result.add(party)
		}
				
		log.debug("getAdminUsersForCompany() successfull and return users = ${result}");
		return result
	}
	

	
	@Transactional(readOnly = true)
	public List getAdministrativeCompanies(){
		log.debug("Calling getAdministrativeCompanies()");
		List result = PartyCompany.findAllByAdministrativeCompany(true)
		log.debug("getAdministrativeCompanies() Successful and return ${result} ")
		return result		
	}
	
	@Transactional(readOnly = true)
	public Long saveAdministrativeCompany(PartyCompany company){
		log.debug("Calling saveAdministrativeCompany(PartyCompany company) with company = ${company}");
		company.saveAdministrativeCompany()		
		log.debug("saveAdministrativeCompany() successful and returns id = ${company.getId()}");
		return company.getId();
	}
	
	@Transactional(readOnly = false)
	public void addUsersToCompany(Long companyId, List userIds,boolean is_admin = false){
		log.debug("Calling addUsersToCompany(PartyCompany company) with companyId = ${companyId}");
		PartyCompany company = PartyCompany.get(companyId)
		
		userIds.each{
			def userId = it			
			PartyUser user = PartyUser.get(userId)
			log.debug("user = ${user}");
			log.debug("company = ${company}");
			CompanyUserGroupRelation cug = new CompanyUserGroupRelation()
			cug.setCompany(company)
			cug.setGroup(null) // The user is linked but does not have any permissions
			cug.setIs_admin(is_admin)
			cug.setUser(user)
			cug.setEnable(true)
			cug.setDefault_company(true) //TODO should be true only if it is the first company for this user			
			cug.save()			
		}
		log.debug("addUsersToCompany() successful");
		
		
	}
	
	@Transactional(readOnly = false)
	public void updateGroupsForUserCompany(Long userId, Long companyId, List<Long> groupIds) {
		log.debug('Calling updateGroupsForUserCompany')
		//select cug.group from com.tms.model.jpa.entity.CompanyUserGroupRelation cug where cug.company.id = :companyId and cug.user.id = :userId
		def pu = PartyUser.get(userId)
		def cp = PartyCompany.get(companyId)
		def areEnable = cp.enable
		def existingGroups = CompanyUserGroupRelation.findAllByCompanyAndUser(cp,pu)
		
		//need to remove if not in array of groupIds
		existingGroups.each { extg ->
			
			if(!extg.group) //if group is null remove it
			{
				log.debug("remove null group instance")
				extg.delete()
				
			}
			else
			{
				log.debug("existing groupId = ${extg.group.id}")
				int extGroupId = extg.group.id
				
				if(groupIds.contains(extGroupId))
				{					
					groupIds -=extGroupId
					log.debug("final groupIds is ${groupIds}")
					
				}
				else
				{
					log.debug("groupIds not contain ${extg.group.id}")
					extg.delete()
				}				
			}		
		}
		
		log.debug("final groupIds: ${groupIds}")
		
		groupIds.each{
			CompanyUserGroup group =  CompanyUserGroup.get(it)
			CompanyUserGroupRelation cugr = new CompanyUserGroupRelation();
			cugr.setCompany(cp)
			cugr.setDefault_company(true)
			cugr.setUser(pu)
			cugr.setEnable(areEnable)
			cugr.setGroup(group)	
			cugr.save();
		}
		
		
		log.debug("existing company group for user: ${existingGroups}")
		
		
		log.debug('UpdateGroupsForUserCompany() successful')
	}
	
	
	
	
	/*
	 * Retrieve PartyUser with principal Id
	 */
	@Transactional(readOnly = true)
	public PartyUser getUser(Long principalId)
	{
		Principal principal = Principal.get(principalId)
		PartyUser.findByPrincipal(principal)
	}
	
	
	/*
	* Set in session and database the current company using
	*/
	@Transactional(readOnly = false)
	public void setDefaultCompany(Long userId, Long companyId) {
		log.debug('Calling setDefaultCompany service')
		
		//save default company into database
		def pu = PartyUser.get(userId)
		def cp = PartyCompany.get(companyId)
		//reset company to no default
		def cugr = CompanyUserGroupRelation.findAllByUserAndEnable(pu,true)
		cugr.each { 
			it.setDefault_company(false)
			it.save()
		}
		
		def existingGroup = CompanyUserGroupRelation.findByCompanyAndUser(cp,pu)
		existingGroup.setDefault_company(true)
		existingGroup.save();
				
		log.debug('Calling setDefaultCompany service successful')
	}
	
	
	
	

	
	
	
}
