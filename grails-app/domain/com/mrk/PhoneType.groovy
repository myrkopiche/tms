package com.mrk

class PhoneType extends Type {
	
	static mapping = {
		discriminator "phone_type"
	}
	
    static constraints = {
    }
}
