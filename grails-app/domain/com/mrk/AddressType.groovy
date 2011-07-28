package com.mrk

class AddressType extends Type {
	
	static mapping = {
		discriminator "address_type"
	}
	
    static constraints = {
    }
}
