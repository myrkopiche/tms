package com.mrk
import grails.plugins.springsecurity.Secured

@Secured(['admin.company.view'])
class ExampleController {

    def scaffold = Example
}
