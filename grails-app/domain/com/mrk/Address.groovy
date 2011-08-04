package com.mrk

import java.io.Serializable;

class Address implements Serializable{
	String street
	String province_state
	String country
	String zip
	String unit
	//static hasMany = [type:Type]
	AddressType type
	//Party party
	static belongsTo = Party
	
    static constraints = {
		
		street(maxSize:1024,blank:false)
		province_state(maxSize:256,blank:false)
		country(maxSize:256,blank:false)
		zip(maxSize:64,blank:false)
		unit(maxSize:128)
    }
}
