package com.mrk

class Requestmap {

	String url
	String configAttribute

	static mapping = {
		cache true
	}

	static constraints = {
		url blank: false, unique: false
		configAttribute blank: false
	}
}
