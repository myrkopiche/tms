package com.mrk

import java.io.Serializable;

class PartyCompany extends Party implements Serializable {

	String name
	boolean administrativeCompany
	boolean enable
	AccountType accountType
	
		
	static mapping = {
        discriminator "company"
    }
	
    static constraints = {
		name(maxsize:1024,nullable:false,unique:true,blank:false)
    }
	
	def saveAdministrativeCompany(){
		this.setAdministrativeCompany(true)
		this.setEnable(true)
		this.save()
	}
	
}
