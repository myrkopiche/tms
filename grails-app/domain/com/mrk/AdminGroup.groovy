package com.mrk

class AdminGroup extends Authority {
	
	static mapping = {
		discriminator "ADMIN"
	}
	
    static constraints = {
    }
}
