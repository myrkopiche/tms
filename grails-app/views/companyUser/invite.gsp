
<%@ page import="com.mrk.CompanyUserGroup" %>
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
            
        </div>
        <div class="body">
            <h1><g:message code="default.invite.label" default="Invite User" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <g:form action="search_user">
                	<label for="firstname">firstname</label>
                	<g:textField name="firstname" value="${params.firstname}" />
                	
                	<label for="firstname">lastname</label>
                	<g:textField name="lastname" value="${params?.lastname}"/>
                	
                	<label for="email">email</label>
                	<g:textField name="email" value="${params.email}"/>
                	<g:submitButton name="search"/>
                </g:form>
                
                <div class="result" style="padding-top:15px">
                	<g:each  in="${results}"  status="i" var="res" >
                		<g:form action="sendInvitation">
                		<div class="${(i % 2) == 0 ? 'odd' : 'even'}" style="padding:15px">${res.firstname } &nbsp; ${res.lastname } <g:hiddenField name="userId" value="${res.id }" /><g:submitButton name="Invite This User"/></div>
                		</g:form>
                	</g:each>
                </div>
                
            </div>
            <g:if test="${resultsTotal}">
            <div class="paginateButtons">
                <g:paginate total="${resultsTotal}" />
            </div>
            </g:if>
            
        </div>
    </body>
</html>
