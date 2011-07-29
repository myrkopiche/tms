package com.mrk

class Group {
	String name
	//static hasMany = [principals:Principal,authorities:Authority]
	
	static mapping = {
		discriminator column: [name:'group_type', length:32]
	}
	
    static constraints = {
    }
}
