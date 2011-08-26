package com.tms.base

class AdminGroup extends Group {
	
	static mapping = {
		discriminator "ADMIN"
	}
	
    static constraints = {
    }
}
