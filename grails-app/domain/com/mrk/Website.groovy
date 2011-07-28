package com.mrk

class Website {
	
	String url
	WebsiteType type
	static belongsTo = Party
	
    static constraints = {
		url(maxsize:200)
    }
}
