package com.mrk

import java.util.List;

class CompanyUserGroup extends Group {
	
	boolean isprivate= false
	PartyCompany company
	static mapping = {
		discriminator "COMPANY_USER"
	}
	
    static constraints = {
		company(nullable:true)
    }
	
	static def getCompanyGroups(PartyCompany company,params = null)
	{		
		def c = CompanyUserGroup.createCriteria()
		def results = c.list(params) {
			like("company", company)
			order("name", "desc")
		}
		return results
		
	}
	
}
