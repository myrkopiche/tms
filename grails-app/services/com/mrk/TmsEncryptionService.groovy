package com.mrk

import org.codehaus.groovy.grails.commons.GrailsApplication;
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH;
import org.jasypt.util.text.BasicTextEncryptor;

class TmsEncryptionService {

	def password = CH.config.jasypt.password
    static transactional = false
	
    def encrypt(String text) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(this.password);
		return textEncryptor.encrypt(text)
    }
	
	def decrypt(String text){
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(this.password);
		return textEncryptor.decrypt(text)
	}
	
}
