<%@ page contentType="text/html"%>
<h3>Hi ${firstname} ${lastname}, welcome to the Talk System!</h3>
<div>
   Follow this link to complete your registration: <br/> 
   <a href="${emailConfirmationUrl}?tk=${token}&email=${email}">${emailConfirmationUrl}?tk=${token}&email=${email}</a>
</div>