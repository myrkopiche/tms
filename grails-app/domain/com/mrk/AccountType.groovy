package com.mrk

class AccountType {
	
	String type
	String name
	String description
	
	
    static constraints = {
		type(maxsize:32,nullable:false)
		name(maxsize:128,nullable:false)
		description(maxsize:512,nullable:false)
    }
}
