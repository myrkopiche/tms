<%@ page contentType="text/html"%>
<h3>Hi ${firstname}, ${companyName } invite you to join the company</h3>
<div>
   If you accept the invitation please click here : <a href="${confirmationUrl}?tk=${token}&userId=${userId}&companyId=${companyId}">${confirmationUrl}?tk=${token}&userId=${userId}&companyId=${companyId}</a>
   </br></br>
   If you refuse the invitation please click here: <a href="${cancelUrl}?tk=${token}&userId=${userId}&companyId=${companyId}">${cancelUrl}?tk=${token}&userId=${userId}&companyId=${companyId}</a>
   
</div>