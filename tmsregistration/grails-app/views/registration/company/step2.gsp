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
    ${accounttype}
		<g:form action="company">
			<fieldset>
    		<legend>AccountType</legend>
    			<g:each in="${accounttype}" var="account">
    				<div style="padding-bottom:20px">
     				<p>Name: ${account.name}</p>
     				<p>Description: ${account.description}</p>
     				<g:form action="company">
     					<input type="hidden" name="accountId" value="${account.id}" />
    					<g:submitButton name="step3" value="Choose"></g:submitButton>
					</g:form>
     				</div>
				</g:each>
    		</fieldset>
		</g:form>
    
    </body>
</html>

