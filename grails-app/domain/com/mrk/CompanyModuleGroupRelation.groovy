package com.mrk

import java.util.Set;

class CompanyModuleGroupRelation {

    PartyCompany company
	CompanyModuleGroup group
	boolean enable
	
    static constraints = {
		group(nullable:true)
    }
	
	static Set<CompanyModuleGroup> getCompanyGroups(long companyId)
	{
		PartyCompany partyCompany = PartyCompany.get(companyId)
		CompanyModuleGroupRelation.findAllByCompany(partyCompany).collect { it.group } as Set
		
	}
	
}
