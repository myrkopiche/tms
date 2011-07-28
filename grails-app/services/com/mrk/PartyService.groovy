package com.mrk


import org.springframework.transaction.annotation.Transactional;

class PartyService {
	
	
	@Transactional(readOnly = true)
	public List getAdministrativeCompanies(){
		log.debug("Calling getAdministrativeCompanies()");
		List result = PartyCompany.findAllByAdministrativeCompany(true)
		log.debug("getAdministrativeCompanies() Successful and return ${result} ")
		return result		
	}
	
	public Long saveAdministrativeCompany(PartyCompany company){
		log.debug("Calling saveAdministrativeCompany(PartyCompany company) with company = ${company}");
		company.saveAdministrativeCompany()		
		log.debug("saveAdministrativeCompany() successful and returns id = ${company.getId()}");
		return company.getId();
	}
	
}
