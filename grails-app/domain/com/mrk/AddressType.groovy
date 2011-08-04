package com.mrk

class AddressType extends Type {
	
	static mapping = {
		discriminator "address_type"
	}
	
    static constraints = {
    }
	
	static List addressTypeList(){
		return AddressType.findAll("from Type as t where t.class='address_type'") as List
		//List adrTypeL = AddressType.list()
		//adrTypeL = AddressType.find("from Type as t where t.class='address_type'")
		//return addrTypeL
	}
}
