package com.tms.base

import java.io.Serializable;

class AddressType extends Type implements Serializable{
	
	static mapping = {
		discriminator "address_type"
	}
	
    static constraints = {
    }
	
	static List addressTypeList(){
		return AddressType.findAll("from Type as t where t.class='address_type'") as List
	}
}
