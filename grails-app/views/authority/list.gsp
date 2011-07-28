
<%@ page import="com.mrk.Authority" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'authority.label', default: 'Authority')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'authority.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="authority" title="${message(code: 'authority.authority.label', default: 'Authority')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${authorityInstanceList}" status="i" var="authorityInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${authorityInstance.id}">${fieldValue(bean: authorityInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: authorityInstance, field: "authority")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${authorityInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
