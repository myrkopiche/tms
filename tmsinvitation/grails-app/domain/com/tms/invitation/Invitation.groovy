package com.tms.invitation
import com.tms.base.PartyUser
import com.tms.base.PartyCompany

class Invitation {
	
	PartyUser user
	PartyCompany company
	Date dateCreated
	
    static constraints = {
		user(nullable:false)
		company(nullable:false)
    }
}
