package com.mrk

class Type {
	
	String code
	String label
	String description
	
	
	static mapping = {
		discriminator column: [name:'discriminator', length:50]
	}

	
    static constraints = {
    }
}
