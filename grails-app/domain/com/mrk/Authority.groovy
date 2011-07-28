package com.mrk

class Authority {

	String authority	
	
	static mapping = {
		discriminator column: [name:'group_type', length:32]
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
