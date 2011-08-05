package com.mrk

import java.io.Serializable;

class Registration implements Serializable{
	String registrationToken
	Date dateCreated
	PartyUser partyUser
	/*
	String username
	String password
	String confirm
	String firstname
	String lastname
	String email
	// transients
	static transients = ['username', 'password','confirm','firstname','lastname','email']
	*/
	
	static mapping = {
		
	}
	
    static constraints = {
		/*firstname blank:false;
		lastname blank:false
		email email:true
		password  blank:false, size:5..15, matches:/[\S]+/, validator:{ val, obj ->
						if (obj.password != obj.confirm)
							return 'user.password.dontmatch'
					}
		
		*/	
    }
}
