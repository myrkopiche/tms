package com.mrk

import org.jasypt.util.text.BasicTextEncryptor;

class Party {
	
	String email
	static hasMany= [phones:Phone,addresses:Address,websites:Website]
	
	static constraints = {
		email blank: false, nullable:false,email:true
	}
	
	static mapping = {
		discriminator column: [name:'discriminator', length:50]
		discriminator "party"
	}
	
	def phones() {
		return phones.collect{}
	}
	
	def addresses(){
		return addresses.collect { }
	}
	
	def websites(){
		return websites.collect { }
	}
	
    
}
