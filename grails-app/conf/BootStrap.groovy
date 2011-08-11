import org.hibernate.validator.Email;
import com.mrk.AccountType;
import com.mrk.AddressType;

import com.mrk.CompanyModuleGroup;
import com.mrk.CompanyModuleGroupRelation;
import com.mrk.CompanyUserGroup;
import com.mrk.Party;
import com.mrk.PartyUser;
import com.mrk.PartyCompany;
import com.mrk.Phone;
import com.mrk.PhoneType;
import com.mrk.Type;
import com.mrk.Address;
import com.mrk.Authority
import com.mrk.Principal;
import com.mrk.GroupAuthority;

class BootStrap {
	
	def springSecurityService
	def partyService
	def userService
	def registrationService
	
	
    def init = { servletContext ->
		
		
		
		//create account type
		def act1 = new AccountType(type:'FREE',name:'Free Account',description:'This type of account is good for trial').save(flush:true)
		new AccountType(type:'SILVER',name:'Silver Account',description:'This type of account is basic account').save(flush:true)
		
		//create address type
		new AddressType(code:'Home', label:'Home Address', description:'Home Address').save(flush:true)
		new AddressType(code:'Billing', label:'Billing Address', description:'Billing Address').save(flush:true)
		
		//create phone type
		new PhoneType(code:'Home',label:'Home Phone',description:'Home Phone' ).save(flush:true)
		
		
		//module rights
		def authm1 = new Authority(authority: 'ROLE_MODULE_BASE').save()
		def authm2 = new Authority(authority: 'ROLE_MODULE_CONTACT').save()
		def module1 = new CompanyModuleGroup(name:'GROUP_MODULE_BASE')
		module1.addToAuthorities(authm1)
		.addToAuthorities(authm2)
		.save()
		
		
		//define authority
		def adminRole = new Authority(authority: 'ROLE_TMS_ADMIN').save()
		def userRole = new Authority(authority: 'ROLE_USER').save()
		def auth0 = new Authority(authority: 'ROLE_COMPANY_VIEW').save()
		def auth1 =new Authority(authority: 'ROLE_COMPANY_CREATE').save()
		def auth2 = new Authority(authority: 'ROLE_COMPANY_UPDATE').save()
		def auth3 = new Authority(authority: 'ROLE_COMPANY_DELETE').save()
		
		
		
		
		//APPLICATION RIGHTS
		def auth4 = new Authority(authority: 'ROLE_DASHBOARD').save()
		
		//create default TMS USER GROUP
		def group1 = new CompanyUserGroup(name:'GROUP_COMPANY_ADMIN')
		group1.addToAuthorities(auth0)
		.addToAuthorities(auth1)
		.addToAuthorities(auth2)
		.addToAuthorities(auth3)
		.save()
		
		def group2 = new CompanyUserGroup(name:'GROUP_COMPANY_USER')
		group2.addToAuthorities(auth4)
		.save()
		
		def group3 = new CompanyUserGroup(name:'GROUP_TMS_ADMIN')
		group3.addToAuthorities(adminRole)
		.save()
		
		
		
		String password = springSecurityService.encodePassword('password')
		def testUser = new Principal(username: 'me', enabled: true, password: password)
		testUser.save(flush: true)
		//testUser.addToGroups(group3)
		
		//GroupAuthority.create group1, auth1, true
		
		def pu = new PartyUser(firstname:'firstname',lastname:'lastname',email:'mp@mr-k.org',principal:testUser)
		pu.save()
		
		
		//create company
		
		def partyCompany1 = new PartyCompany(email:'mp@wlab.ca',name:'wlab',enable:true,accountType:act1).save(flush:true)
		def partyCompany2 = new PartyCompany(email:'info@wlab.ca',name:'mrk',enable:true,accountType:act1).save(flush:true)
		
		def cmgr = new CompanyModuleGroupRelation(company:partyCompany1,group:module1).save()
		
		List userIds = [pu.id]
		partyService.addUsersToCompany(partyCompany1.id,userIds)
		partyService.addUsersToCompany(partyCompany2.id,userIds)
		partyService.updateGroupsForUserCompany(pu.id, partyCompany1.id, [1])
		partyService.updateGroupsForUserCompany(pu.id, partyCompany2.id, [2])
		partyService.setDefaultCompany(pu.id, partyCompany1.id)
		
		
		
		/*
		assert Principal.count() == 1
		assert Authority.count() == 2
		assert PrincipalAuthority.count() == 1
		*/
		/*
		def type1 = new AddressType(code:'Home', label:'Home Address', description:'Home Address')
		type1.save(flush:true)
		
		def adrTypeL = AddressType.list()
		adrTypeL = AddressType.find("from Type as t where t.class='address_type'")
		
		def address1 = new Address(name:'name',province_state:'province_state',country:'country',zip:'zip',unit:'unit',type:type1)
		address1.save(flush:true)
		
		def type2 = new PhoneType(code:'Home',label:'Home Phone',description:'Home Phone' )
		type2.save(flush:true)
		
		def phone1 = new Phone(number:'454-4554',local:'450',type:type2)
		phone1.save(flush:true)	
		
		def phone2 = new Phone(number:'454-4554',local:'450',type:type2)
		phone2.save(flush:true)
		
		
		def party = new Party(email:'mp@mr-k.org').addToPhones(phone1).addToPhones(phone2).addToAddresses(address1).save(flush:true)
		
		//create company
		def accType = new AccountType(type:'FREE',name:'Free Account',description:'This type of account is good for trial').save(flush:true)
		def partyCompany1 = new PartyCompany(email:'mp@wlab.ca',name:'wlab',enable:false,accountType:accType).addToPhones(phone2).addToAddresses(address1).save(flush:true)		
		partyCompany1.saveAdministrativeCompany()
		
		//register User
		password = springSecurityService.encodePassword('password')
		def user1 = new Principal(username: 'mp', enabled: false, password: password)
		user1.save(flush:true)
		
		def partyUser = new PartyUser(lastname:'Piche',firstname:'Myrko',email:'mp@mr-k.org',principal:user1).save(flush:true)
		
		//afterEmail confirmation enable user
		partyUser.principal.setEnabled true
		partyUser.save()	
		
		List userIds = [partyUser.id]
		
		partyService.addUsersToCompany(partyCompany1.id,userIds)
		*/
  
    }
	
    def destroy = {
    }
}