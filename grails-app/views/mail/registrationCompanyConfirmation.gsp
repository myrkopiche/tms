<%@ page contentType="text/html"%>
<h3>Hi ${name}, welcome to the Talk System!</h3>
<div>
   Follow this link to complete your company registration: <br/> 
   <a href="${emailConfirmationUrl}?tk=${token}&email=${email}&companyEmail=${companyEmail}">${emailConfirmationUrl}?tk=${token}&email=${email}&companyEmail=${companyEmail}</a>
</div>