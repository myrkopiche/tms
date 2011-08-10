<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    <% def currentCompany = 2 %>
    <g:select name="user.company.id"
          from="${companies}"
          value="${currentCompany }"
          optionKey="id"
          optionValue="name" />
    </body>
</html>