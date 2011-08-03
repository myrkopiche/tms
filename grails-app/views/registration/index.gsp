<%@ page import="com.mrk.Principal" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

			<g:hasErrors>
    			<g:eachError>	
    			<div class="errors">
                	<p>${it.defaultMessage}</p>
            	</div>
    			</g:eachError>
    			
			</g:hasErrors>
			
            <g:hasErrors bean="${pr}">
            	<div class="errors">
                	<g:renderErrors bean="${pr}" as="list" />
            	</div>
            </g:hasErrors>
    
        <g:form controller="registration" action="registrationstep1">
        	<label for="firstname">firstname</label>
        	<input type="text" name="firstname" />
        	<br/>
        	<label for="lastname">lastname</label>
        	<input type="text" name="lastname" />
        	
        	<br/>
        	<label for="email">email</label>
        	<input type="text" name="email" />
	
        	<br/>
        	<label for="username">username</label>
        	<input type="text" name="username" />
        	
        	<br/>
        	<label for="password">password</label>
        	<input type="password" name="password" />
        	
        	<br/>
        	<label for="confirmpassword">Confirm password</label>
        	<input type="password" name="confirmpassword" />
        	
        	<g:submitButton name="Register" value="Register" />
        </g:form>
    </body>
</html>
