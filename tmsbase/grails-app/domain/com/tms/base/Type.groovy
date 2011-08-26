package com.tms.base

import java.io.Serializable;

class Type implements Serializable{
	
	String code
	String label
	String description
	
	
	static mapping = {
		discriminator column: [name:'discriminator', length:50]
	}

	
    static constraints = {
    }
}
