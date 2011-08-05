package com.mrk

import java.io.Serializable;

class CompanyUserGroupRelation implements Serializable{
	
	PartyCompany company
	PartyUser user
	CompanyUserGroup group
	boolean enable
	boolean is_admin
	boolean default_company = false
	
    static constraints = {
		group(nullable:true)
    }
}
