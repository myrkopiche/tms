package com.mrk

import java.io.Serializable;
import java.util.List;

class AccountType implements Serializable{
	
	String type
	String name
	String description
	
	
    static constraints = {
		type(maxsize:32,nullable:false)
		name(maxsize:128,nullable:false)
		description(maxsize:512,nullable:false)
    }
	
	static List accountTypeList(){
		return AccountType.findAll() as List
	}
	
}
