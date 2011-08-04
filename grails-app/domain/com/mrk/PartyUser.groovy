package com.mrk

import java.io.Serializable;

class PartyUser extends Party implements Serializable{
	
	String lastname
	String firstname
	Principal principal
	
	
	static mapping = {
		discriminator "user"
	}
	
    static constraints = {
		lastname(maxsize:256,nullable:false,blank:false)
		firstname(maxsize:256,nullable:false,blank:false)
    }
}
