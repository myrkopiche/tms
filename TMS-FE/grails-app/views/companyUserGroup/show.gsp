
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'companyUserGroup.label', default: 'CompanyUserGroup')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="companyUserGroup.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: companyUserGroupInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="companyUserGroup.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: companyUserGroupInstance, field: "name")}</td>
                            
                        </tr>
                    
                        
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="companyUserGroup.authorities.label" default="Authorities" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${companyUserGroupInstance.authorities}" var="a">
                                    <li><g:link controller="authority" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                    <sec:ifAnyGranted roles="ROLE_TMS_ADMIN">
                    
                    	<tr class="prop">
                            <td valign="top" class="name"><g:message code="companyUserGroup.company.label" default="Company" /></td>
                            
                            <td valign="top" class="value"><g:link controller="partyCompany" action="show" id="${companyUserGroupInstance?.company?.id}">${companyUserGroupInstance?.company?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="companyUserGroup.isprivate.label" default="Isprivate" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${companyUserGroupInstance?.isprivate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="companyUserGroup.principals.label" default="Principals" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${companyUserGroupInstance.principals}" var="p">
                                    <li><g:link controller="principal" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                       </sec:ifAnyGranted>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${companyUserGroupInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
