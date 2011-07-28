package com.mrk

class PartyCompany extends Party {

	String name
	boolean administrativeCompany
	boolean enable
	AccountType accountType
	
		
	static mapping = {
        discriminator "company"
    }
	
    static constraints = {
		name(maxsize:1024,nullable:false)
    }
	
	def saveAdministrativeCompany(){
		this.setAdministrativeCompany(true)
		this.setEnable(true)
		this.save()
	}
	
}
