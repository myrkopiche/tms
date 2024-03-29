package com.tms.base

import java.io.Serializable;

import org.jasypt.util.text.BasicTextEncryptor;

class Party implements Serializable{
	
	String email
	static hasMany= [phones:Phone,addresses:Address,websites:Website]
	
	static constraints = {
		email blank: false, nullable:false,email:true,unique:true
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
