
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
         <g:hasErrors bean="${partycompany}">
            	<div class="errors">
                	<g:renderErrors bean="${partycompany}" as="list" />
            	</div>
         </g:hasErrors>
    	<g:hasErrors bean="${address}">
            	<div class="errors">
                	<g:renderErrors bean="${address}" as="list" />
            	</div>
         </g:hasErrors>
    
		<g:form action="company">
			<fieldset>
    		<legend>User</legend>
    			<div class="prop ${hasErrors(bean:principal,field:'username', 'errors')}">
	        		<label for="username">username</label>
	        		<input type="text" name="username" value="${fieldValue(bean:principal,field:'username')}" />
				</div>
				<br/>
				<div class="prop ${hasErrors(bean:principal,field:'password', 'errors')}">
	        		<label for="password">password</label>
	        		<input type="password" name="password" value="${fieldValue(bean:principal,field:'password')}" />
				</div>
    		</fieldset>


    		<g:submitButton name="step2" value="Next"></g:submitButton>
		</g:form>
    
    </body>
</html>

