package com.mrk
//import org.apache.commons.codec.net.BCodec;
//import org.jasypt.util.text.BasicTextEncryptor;


import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class RegistrationController {
	
	def registrationService
	static allowedMethods = [confirmation:'GET']
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def confirmation = { 
		String email = new String(params.email.decodeURL().decodeBase64())
		String token = new String(params.tk.decodeURL().decodeBase64())
		//BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		//textEncryptor.setPassword("helo");
		//String myEncryptedText = textEncryptor.encrypt("mp");
		//println myEncryptedText
	    
		
		
		
		//Registration registration = Registration.findByRegistrationToken(token)
		//Party p1 = Party.get(1)
		
		//Party pu = Party.findByEmail("mp@mr-k.org")
		
		//render "${pu}"
		
	}
}
