package com.mrk

class PartyUser extends Party{
	
	String lastname
	String firstname
	User principal
	
	
	static mapping = {
		discriminator "user"
	}
	
    static constraints = {
		lastname(maxsize:256,nullable:false)
		firstname(maxsize:256,nullable:false)
    }
}
