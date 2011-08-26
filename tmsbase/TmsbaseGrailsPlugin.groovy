import javax.media.j3d.EnvironmentSet;

import grails.util.Environment;

import org.codehaus.groovy.grails.commons.GrailsApplication;
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

class TmsbaseGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Your name"
    def authorEmail = ""
    def title = "Plugin summary/headline"
    def description = '''\\
Brief description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/tmsbase"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
		def config =  application.config;
		config.jasypt.password = "tR54S98UJIO90IKOKKL-09-1222-09-01"
		config.jasypt.keyObtentionIterations = 1000
		config.grails.mail.host= "smtp.gmail.com"
		config.grails.mail.port = 465
		config.grails.mail.username = "a@gmail.com"
		config.grails.mail.password = "wlabwlab"
		config.grails.mail.props = ["mail.smtp.auth":"true",
					   "mail.smtp.socketFactory.port":"465",
					   "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
					   "mail.smtp.socketFactory.fallback":"false"]
		
    }


    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
		// TODO Implement post initialization spring config (optional)
		SpringSecurityUtils.securityConfig.userLookup.userDomainClassName = 'com.tms.base.Principal'
		SpringSecurityUtils.securityConfig.userLookup.authorityJoinClassName = 'com.tms.base.GroupAuthority'
		SpringSecurityUtils.securityConfig.authority.className = 'com.tms.base.Authority'
		//SpringSecurityUtils.securityConfig.requestMap.className = 'com.mrk.Requestmap'
		SpringSecurityUtils.securityConfig.securityConfigType = 'Annotation'
		//SpringSecurityUtils.securityConfig.rejectIfNoRule = true		
		
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
