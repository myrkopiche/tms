package com.mrk

class Address {
	String name
	String province_state
	String country
	String zip
	String unit
	//static hasMany = [type:Type]
	AddressType type
	//Party party
	static belongsTo = Party
	
    static constraints = {
		
		name(maxSize:1024)
		province_state(maxSize:256)
		country(maxSize:256)
		zip(maxSize:64)
		unit(maxSize:128)
    }
}
