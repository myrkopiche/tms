package com.mrk

class Authority {

	String authority	
	static hasMany= [groups:Group]
	static belongsTo = Group
	
	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
		groups(nullable:true)
	}
}
