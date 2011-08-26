package com.tms.base

import java.io.Serializable;
import java.util.List;

class PhoneType extends Type implements Serializable{
	
	static mapping = {
		discriminator "phone_type"
	}
	
    static constraints = {
    }
	
	static List phoneTypeList(){
		return PhoneType.findAll("from Type as t where t.class='phone_type'") as List
	}
	
}
