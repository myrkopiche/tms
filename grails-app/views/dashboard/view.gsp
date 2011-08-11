<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
       
    </head>
    <body>
    
    <g:form name="companyform" controller="partyCompany" action="setDefaultCompany" method="post">
    <g:select name="company.id"
          from="${companies}"
          value="${session['company.current']}"
          optionKey="id"
          optionValue="name" onChange="this.form.submit();" />
    </g:form>
    
    <div>
    <sec:ifAllGranted roles="ROLE_ADMIN">Create group of permission</sec:ifAllGranted>
    </div>
    </body>
</html>