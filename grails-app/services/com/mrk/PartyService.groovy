package com.mrk


import org.springframework.transaction.annotation.Transactional;

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
	/*
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<PartyUserVO> getUsersForCompany(Long companyId) {
		
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put("companyId", companyId);
		
		List<Party> parties = partyDao.findByNamedQueryAndNamedParams(IPartyDao.FIND_USERS_FOR_COMPANY, params);
		
		List<PartyUserVO> result = new ArrayList<PartyUserVO>(parties.size());
		
		for (Iterator<Party> iterator = parties.iterator(); iterator.hasNext();) {
			PartyUser party =  (PartyUser) iterator.next();
			result.add((PartyUserVO) mapper.map(party, PartyUserVO.class));
		}
		
		return result;
		
	}
	*/
	
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
	public void addUsersToCompany(Long companyId, List userIds){
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
			cug.setUser(user)
			cug.setDefault_company(true) //TODO should be true only if it is the first company for this user			
			cug.save()			
		}
		log.debug("addUsersToCompany() successful");

		
		
	}
	
	

	
	
	
}
