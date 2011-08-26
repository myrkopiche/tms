package com.tms.base

class CompanyModuleGroup extends Group {
	
	CompanyAdminGroup admingroup
	
    static mapping = {
		discriminator "COMPANY_MODULE"
	}
	
    static constraints = {
    }
}
