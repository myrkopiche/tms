package com.mrk

class CompanyUserGroupRelation {
	
	PartyCompany company
	PartyUser user
	CompanyUserGroup group
	boolean enable
	boolean default_company = false
	
    static constraints = {
    }
}
