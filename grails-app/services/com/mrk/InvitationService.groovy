package com.mrk
import org.springframework.transaction.annotation.Transactional;

class InvitationService {

	def tmsEncryptionService
	def userInviationConfirmationUrl = "http://localhost:8080/tms/invitation/confirmation"
	def userInviationCancelUrl = "http://localhost:8080/tms/invitation/cancel"
	
	/*
	* Invite person to company
	*/
   @Transactional(readOnly = false)
   public void sendUserInvitation(Long userId, Long companyId){
	   log.debug("Calling sendUserInvitation with userid = ${userId} and companyId = ${companyId}")
	   PartyUser user = PartyUser.get(userId)
	   PartyCompany company = PartyCompany.get(companyId)
	   
	   //create a registration validation
	   Registration reg = new Registration()
	   reg.setPartyUser(user)
	   reg.setRegistrationToken(UUID.randomUUID().toString())
	   reg.save()
	   
	   this.sendUserInvitationEmail(reg, company)
	   log.debug("Successfully sendUserInvitation")
   }
   
   
   /*
	* Send Invitation email to user
	*/
   @Transactional(readOnly = true)
   private void sendUserInvitationEmail(final Registration registration,final PartyCompany company){
	   
	   def userIdEncrypt = tmsEncryptionService.encrypt(registration.partyUser.id.toString()).encodeAsURL()
	   def companyIdEncrypt = tmsEncryptionService.encrypt(company.id.toString()).encodeAsURL()
	   def tokenEncrypt = tmsEncryptionService.encrypt(registration.registrationToken).encodeAsURL()
	   //def decodedEmail = new String(emailEncrypt.decodeURL().decodeBase64())
	   
	   sendMail {
		   to "${registration.partyUser.email}"
		   subject "Hello ${registration.partyUser.firstname}"
		   body( view:"/mail/userInvitation",
			   model:[firstname:registration.partyUser.firstname,lastname:registration.partyUser.lastname,token:tokenEncrypt,userId:userIdEncrypt,confirmationUrl:this.userInviationConfirmationUrl,cancelUrl:this.userInviationCancelUrl, companyName:company.name,companyId:companyIdEncrypt ])
		 }
	   
   }
   
   @Transactional(readOnly = false)
   public void confirmInvitation(String encryptedToken,String encryptUserId,String encryptCompanyId)
   {
	   log.debug("Calling confirInvitation")
	   String token = tmsEncryptionService.decrypt(encryptedToken).decodeURL()
	   def compId =  tmsEncryptionService.decrypt(encryptCompanyId).decodeURL()  as Long
	   def userId =  tmsEncryptionService.decrypt(encryptUserId).decodeURL()  as Long
	   PartyUser user = PartyUser.get(userId)
	   PartyCompany company = PartyCompany.get(compId)
	   Registration reg = Registration.findByRegistrationTokenAndPartyUser(token,user)
	   if(reg) //found registration
	   {
		   this.addUsersToCompany(company.id, user.id as List)
		   reg.delete()
		   log.debug("successfully confirmInvitaion")
	   }
	   else
	   {
		   throw new Exception('This is not a valid invitation, please try again')
	   }
	   
   }
   
   @Transactional(readOnly = false)
   public void cancelInvitation(String encryptedToken,String encryptUserId,String encryptCompanyId)
   {
	   log.debug("Calling Cancel Invitation")
	   String token = tmsEncryptionService.decrypt(encryptedToken).decodeURL()
	   def compId =  tmsEncryptionService.decrypt(encryptCompanyId).decodeURL()  as Long
	   def userId =  tmsEncryptionService.decrypt(encryptUserId).decodeURL()  as Long
	   PartyUser user = PartyUser.get(userId)
	   
	   Registration reg = Registration.findByRegistrationTokenAndPartyUser(token,user)
	   if(reg){
		   reg.delete()
		   log.debug("Successfully Cancel Invitation")
	   }
	   else
	   {
		   throw new Exception('This is not a valid invitation, please try again')
	   }
	   
	   
	   
   }
   
    
}
