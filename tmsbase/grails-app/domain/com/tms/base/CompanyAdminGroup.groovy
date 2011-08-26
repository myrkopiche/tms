package com.tms.base

import java.io.Serializable;

class CompanyAdminGroup extends Group implements Serializable{
	
	static mapping = {
		discriminator "COMPANY_ADMIN"
	}
	
	static constraints = {
	}
}
