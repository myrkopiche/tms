package com.tms.base

import java.io.Serializable;

class Group implements Serializable{
	
	String name
	static hasMany = [authorities:Authority,principals:Principal]
	static belongsTo = Principal
	
	static mapping = {
		table 'tmsgroup'
		discriminator column: [name:'group_type', length:32]
	}
	
    static constraints = {
		name(unique:true,maxsize:50,nullable:false,blank:false)
    }
	
	
}
