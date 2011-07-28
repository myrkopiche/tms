package com.mrk

class Phone {
    String number
	String local
	PhoneType type
	//Party party
	static belongsTo = Party
	
	
    static constraints = {		
		number(maxSize:32)
		local(maxSize:32)		
    }
	
}
