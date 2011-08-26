
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'companyUserGroup.label', default: 'CompanyUser')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="invite"><g:message code="default.invite.label" default="Invite Users" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'companyUserGroup.id.label', default: 'Id')}" />     
                            <g:sortableColumn property="firstname" title="${message(code: 'companyUserGroup.name.label', default: 'Firstname')}" />
                        	<g:sortableColumn property="latname" title="${message(code: 'companyUserGroup.name.label', default: 'Lastname')}" />
                        	<g:sortableColumn property="invitationUsers" title="${message(code: 'invitationUser.status.label', default: 'Status')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${companyUserInstanceList}" status="i" var="companyUserInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${companyUserInstance.id}">${fieldValue(bean: companyUserInstance, field: "id")}</g:link></td>                
                            <td>${fieldValue(bean: companyUserInstance, field: "firstname")}</td>
                            <td>${fieldValue(bean: companyUserInstance, field: "lastname")}</td>
							<td>
							<g:if test="${invitationUsers.contains(companyUserInstance)}">
								Wainting aprobation
							</g:if>
							<g:else>
								Approved
							</g:else>
								
							</td>
                        </tr>
                    </g:each>
                    </tbody>
                    
                    
                    
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${companyUserInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
