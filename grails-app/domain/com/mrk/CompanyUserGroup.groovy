package com.mrk

class CompanyUserGroup extends Group{
	
	static mapping = {
		discriminator "COMPANY_USER"
	}
	
    static constraints = {
    }
}
