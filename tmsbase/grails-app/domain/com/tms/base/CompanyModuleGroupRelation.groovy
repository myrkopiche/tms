package com.tms.base

import java.io.Serializable;
import java.util.Set;

class CompanyModuleGroupRelation implements Serializable{

    PartyCompany company
	CompanyModuleGroup group
	boolean enable
	
    static constraints = {
		group(nullable:true)
    }
	
	static Set<CompanyModuleGroup> getCompanyGroups(long companyId)
	{
		PartyCompany partyCompany = PartyCompany.get(companyId)
		CompanyModuleGroupRelation.findAllByCompanyAndEnable(partyCompany,true).collect { it.group } as Set
		
	}
	
	static def getCompanyAdminGroups(long companyId)
	{
		PartyCompany partyCompany = PartyCompany.get(companyId)
		CompanyModuleGroupRelation.findAllByCompanyAndEnable(partyCompany,true).collect { it.group.admingroup}.flatten()
	}
	
	
}
