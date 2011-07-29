package com.testapp
import grails.plugins.springsecurity.Secured

class SecureController {

    def index = {
      render 'Secure access only'
   }

}
