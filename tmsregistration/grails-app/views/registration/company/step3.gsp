<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

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
         
         <g:hasErrors bean="${phone}">
            	<div class="errors">
                	<g:renderErrors bean="${phone}" as="list" />
            	</div>
         </g:hasErrors>
    
		<g:form action="company">
			
		
			<fieldset>
    		<legend>Company</legend>
        	
        	<div class="prop ${hasErrors(bean:partycompany,field:'name', 'errors')}">
        		<label for="username">Company Name</label>
        		<input type="text" name="name"  value="${fieldValue(bean:partycompany,field:'name')}" />
        	</div>
        	
        	<div class="prop ${hasErrors(bean:partycompany,field:'email', 'errors')}">
        		<label for="email">Company Email</label>
        		<input type="text" name="email" value="${fieldValue(bean:partycompany,field:'email')}" />
			</div>
			<br/>
			
        	
        	<g:select name="addresstypeId" from="${addresstype}" 
             value="${addresstypeId}"
          optionKey="id" /> 
        	<br/>
        	<div class="prop ${hasErrors(bean:address,field:'street', 'errors')}">
        		<label for="username">Street</label>
        		<input type="text" name="street"  value="${fieldValue(bean:address,field:'street')}" />
        	</div>
        	
        	<br/>
        	<div class="prop ${hasErrors(bean:address,field:'province_state', 'errors')}">
        		<label for="province_state">Province</label>
        		<input type="text" name="province_state"  value="${fieldValue(bean:address,field:'province_state')}" />
        	</div>
        	
        	<br/>
        	<div class="prop ${hasErrors(bean:address,field:'country', 'errors')}">
        		<label for="country">Country</label>
        		<input type="text" name="country"  value="${fieldValue(bean:address,field:'country')}" />
        	</div>
        	
        	<br/>
        	<div class="prop ${hasErrors(bean:address,field:'zip', 'errors')}">
        		<label for="zip">Postal Code</label>
        		<input type="text" name="zip"  value="${fieldValue(bean:address,field:'zip')}" />
        	</div>
        	
        	<br/>
        	<div class="prop ${hasErrors(bean:address,field:'unit', 'errors')}">
        		<label for="unit">Unit</label>
        		<input type="text" name="unit"  value="${fieldValue(bean:address,field:'unit')}" />
        	</div>
        	
        	<br/>
        	<g:select name="phonetypeId" from="${phonetype}" 
             value="${phonetypeId}"
          optionKey="id" />
        	<br/>
             <br/>
        	<div class="prop ${hasErrors(bean:phone,field:'number', 'errors')}">
        		<label for="local">Local Area</label>
        		<input type="text" name="local" size="3" maxlength="4"  value="${fieldValue(bean:phone,field:'local')}" />
        		<input type="text" name="number" size="10" maxlength="10"  value="${fieldValue(bean:phone,field:'number')}" />
        	</div>
        	
        	</fieldset>
        	
        	
    		<g:submitButton name="step2" value="Back"></g:submitButton>
    		<g:submitButton name="step4" value="Next"></g:submitButton>
    		<g:submitButton name="cancel" value="Cancel"></g:submitButton>
			</g:form>
    
    </body>
</html>
