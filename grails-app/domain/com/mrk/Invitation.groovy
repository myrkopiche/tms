package com.mrk

class Invitation {
	
	PartyUser user
	PartyCompany company
	Date dateCreated
	
    static constraints = {
		user(nullable:false)
		company(nullable:false)
    }
}
