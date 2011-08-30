
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'companyUserGroup.label', default: 'CompanyUserGroup')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                        
                            <g:sortableColumn property="name" title="${message(code: 'companyUserGroup.name.label', default: 'Name')}" />
                        
                            <th><g:message code="companyUserGroup.company.label" default="Company" /></th>
                        <sec:ifAnyGranted roles="ROLE_TMS_ADMIN">
                            <g:sortableColumn property="isprivate" title="${message(code: 'companyUserGroup.isprivate.label', default: 'Isprivate')}" />
                        </sec:ifAnyGranted>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${companyUserGroupInstanceList}" status="i" var="companyUserGroupInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${companyUserGroupInstance.id}">${fieldValue(bean: companyUserGroupInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: companyUserGroupInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: companyUserGroupInstance, field: "company")}</td>
                        <sec:ifAnyGranted roles="ROLE_TMS_ADMIN">
                            <td><g:formatBoolean boolean="${companyUserGroupInstance.isprivate}" /></td>
                        </sec:ifAnyGranted>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${companyUserGroupInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
