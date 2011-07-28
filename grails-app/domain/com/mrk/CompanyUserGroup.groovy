package com.mrk

class CompanyUserGroup extends Authority{
	
	static mapping = {
		discriminator "COMPANY_USER"
	}
	
    static constraints = {
    }
}
