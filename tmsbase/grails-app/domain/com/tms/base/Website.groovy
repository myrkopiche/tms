package com.tms.base

import java.io.Serializable;

class Website implements Serializable{
	
	String url
	WebsiteType type
	static belongsTo = Party
	
    static constraints = {
		url(maxsize:200)
    }
}
