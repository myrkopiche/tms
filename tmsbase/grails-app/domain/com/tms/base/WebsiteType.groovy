package com.tms.base

class WebsiteType extends Type {

	static mapping = {
		discriminator "website_type"
	}
	
    static constraints = {
    }
}
