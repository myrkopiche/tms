package com.mrk

class AdminGroup extends Group {
	
	static mapping = {
		discriminator "ADMIN"
	}
	
    static constraints = {
    }
}
