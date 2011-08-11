package com.mrk

class CompanyAdminGroup extends Group{

	static mapping = {
		discriminator "COMPANY_ADMIN"
	}
	
	static constraints = {
	}
}
