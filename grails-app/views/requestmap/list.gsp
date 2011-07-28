
<%@ page import="com.mrk.Requestmap" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'requestmap.label', default: 'Requestmap')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'requestmap.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="url" title="${message(code: 'requestmap.url.label', default: 'Url')}" />
                        
                            <g:sortableColumn property="configAttribute" title="${message(code: 'requestmap.configAttribute.label', default: 'Config Attribute')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${requestmapInstanceList}" status="i" var="requestmapInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${requestmapInstance.id}">${fieldValue(bean: requestmapInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: requestmapInstance, field: "url")}</td>
                        
                            <td>${fieldValue(bean: requestmapInstance, field: "configAttribute")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${requestmapInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
