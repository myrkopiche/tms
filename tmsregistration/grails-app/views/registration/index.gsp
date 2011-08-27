<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

			
            <g:hasErrors bean="${principal}">
            	<div class="errors">
                	<g:renderErrors bean="${principal}" as="list" />
            	</div>
            </g:hasErrors>
            
            <g:hasErrors bean="${partyuser}">
            	<div class="errors">
                	<g:renderErrors bean="${partyuser}" as="list" />
            	</div>
            </g:hasErrors>
            
    
        <g:form controller="registration" action="registrationstep1">
        	<div class="prop ${hasErrors(bean:partyuser,field:'firstname', 'errors')}">
        	<label for="firstname">firstname</label>
        		<input type="text" name="firstname" value="${fieldValue(bean:partyuser,field:'firstname')}" />
        	</div>
        	
        	<br/>
        	<div class="prop ${hasErrors(bean:partyuser,field:'lastname', 'errors')}">
        	<label for="lastname">lastname</label>
        	<input type="text" name="lastname" value="${fieldValue(bean:partyuser,field:'lastname')}" />
        	</div>
        	
        	<br/>
        	<div class="prop ${hasErrors(bean:partyuser,field:'email', 'errors')}">
        		<label for="email">email</label>
        		<input type="text" name="email" value="${fieldValue(bean:partyuser,field:'email')}" />
			</div>
			
        	<br/>
        	<div class="prop ${hasErrors(bean:principal,field:'username', 'errors')}">
        		<label for="username">username</label>
        		<input type="text" name="username"  value="${fieldValue(bean:principal,field:'username')}" />
        	</div>
        	<br/>
        	<div class="prop ${hasErrors(bean:principal,field:'password', 'errors')}">
        		<label for="password">password</label>
        		<input type="password" name="password"/>
        	</div>
        	
        	<br/>
        	<label for="confirmpassword">Confirm password</label>
        	<input type="password" name="confirm" />
        	
        	<g:submitButton name="Register" value="Register" />
        </g:form>
    </body>
</html>
