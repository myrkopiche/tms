

<%@ page import="com.mrk.CompanyUserGroup" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'companyUserGroup.label', default: 'CompanyUserGroup')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${companyUserGroupInstance}">
            <div class="errors">
                <g:renderErrors bean="${companyUserGroupInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${companyUserGroupInstance?.id}" />
                <g:hiddenField name="version" value="${companyUserGroupInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="companyUserGroup.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: companyUserGroupInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${companyUserGroupInstance?.name}" />
                                </td>
                            </tr>
                        
                            
                        
                   
                        
                        <sec:ifAnyGranted roles="ROLE_TMS_ADMIN">
                        
                        	<tr class="prop">
                                <td valign="top" class="name">
                                  <label for="company"><g:message code="companyUserGroup.company.label" default="Company" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: companyUserGroupInstance, field: 'company', 'errors')}">
                                    <g:select name="company.id" from="${com.mrk.PartyCompany.list()}" optionKey="id" value="${companyUserGroupInstance?.company?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="isprivate"><g:message code="companyUserGroup.isprivate.label" default="Isprivate" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: companyUserGroupInstance, field: 'isprivate', 'errors')}">
                                    <g:checkBox name="isprivate" value="${companyUserGroupInstance?.isprivate}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="principals"><g:message code="companyUserGroup.principals.label" default="Principals" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: companyUserGroupInstance, field: 'principals', 'errors')}">
                                    
                                </td>
                            </tr>
                            </sec:ifAnyGranted>
                        
                        </tbody>
                    </table>
                    
                    ${modules}
                    <g:each in="${modules}">   
	           				<div class="module" style="padding-bottom:20px;padding-top:10px;">
	           					<p><h2>Title: ${it.group.name}</h2></p>
	    				  		<g:each in="${it.group.admingroup.authorities}">
	    				  			<p>Role: ${it.authority}</p>
	    				  			<input type="checkbox"
	    				  				name="authorities"
	    				  				value="${it.id}"  	
	    				  				<g:each var="d" in="${companyUserGroupInstance.authorities}" >			  				
	    				  				<g:if test="${d == it}">CHECKED</g:if>
	    				  				</g:each>
	    				  			/>
	    				  			
	    				  			
	    				  		</g:each>
	           			
	           				</div>          			
						</g:each>
                    
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
