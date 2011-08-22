import org.hibernate.validator.Email;
import com.mrk.AccountType;
import com.mrk.AddressType;

import com.mrk.CompanyAdminGroup;
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
		
		
		//create group settings company
		def a0 = new Authority(authority: 'ROLE_SETTINGS_COMPANY_GROUP_VIEW').save()
		def a1 = new Authority(authority: 'ROLE_SETTINGS_COMPANY_GROUP_CREATE').save()
		def a2 = new Authority(authority: 'ROLE_SETTINGS_COMPANY_GROUP_UPDATE').save()
		def a3 = new Authority(authority: 'ROLE_SETTINGS_COMPANY_GROUP_DELETE').save()
		CompanyAdminGroup sg1 = new CompanyAdminGroup(name:'GROUP_MODULE_SETTING_COMPANY_ADMIN').save()
		sg1.addToAuthorities(a0)
		.addToAuthorities(a1)
		.addToAuthorities(a2)
		.addToAuthorities(a3)
		
		
		//module tms
		def authm1 = new Authority(authority: 'ROLE_MODULE_BASE').save()
		def authm2 = new Authority(authority: 'ROLE_MODULE_CONTACT').save()
		new Authority(authority: 'ROLE_MODULE_CUSTOMERS').save()
		new Authority(authority: 'ROLE_MODULE_VENDORS').save()
		new Authority(authority: 'ROLE_MODULE_PROJECTS').save()
		new Authority(authority: 'ROLE_MODULE_INVOICES').save()
		def module1 = new CompanyModuleGroup(name:'GROUP_MODULE_BASE',admingroup:sg1)
		module1.addToAuthorities(authm1)
		.addToAuthorities(authm2)
		.save()
		
		
		
		
		
		
		
		//define company admin roles
		def auth0 = new Authority(authority: 'ROLE_COMPANY_VIEW').save()
		def auth1 =new Authority(authority: 'ROLE_COMPANY_CREATE').save()
		def auth2 = new Authority(authority: 'ROLE_COMPANY_UPDATE').save()
		def auth3 = new Authority(authority: 'ROLE_COMPANY_DELETE').save()
		
		
		//company admin group
		def compAdminGroup1 = new CompanyAdminGroup(name:'GROUP_MODULE_COMPANY_USER_ADMIN').save()
		compAdminGroup1.addToAuthorities(auth0)
		.addToAuthorities(auth1)
		.addToAuthorities(auth2)
		.addToAuthorities(auth3)
		
		//dashboard module
		def auth4 = new Authority(authority: 'ROLE_DASHBOARD').save()
		def compAdminGroup2 = new CompanyAdminGroup(name:'GROUP_MODULE_DASHBOARD_USER_ADMIN').save()
		compAdminGroup1.addToAuthorities(auth4)
		
		//Group created by a company
		def auth5 = new Authority(authority: 'ROLE_DASHBOARD_VIEW').save()
		def compUserGroup1 = new CompanyUserGroup(name:'GROUP_BASE_USER',isprivate:true).save()
		compUserGroup1.addToAuthorities(auth4)
		.addToAuthorities(auth5)
		
		
		//group admin with 1 authority
		def adminRole = new Authority(authority: 'ROLE_COMPANY_ADMIN').save()
		def compUserAdminGroup = new CompanyUserGroup(name:'GROUP_USER_ADMIN',isprivate:true).save()
		compUserAdminGroup.addToAuthorities(adminRole)
				
		//group user with 1 authority
		def userRole = new Authority(authority: 'ROLE_COMPANY_USER').save()
		def compUserUserGroup = new CompanyUserGroup(name:'GROUP_USER_USER',isprivate:true).save()
		compUserUserGroup.addToAuthorities(userRole)
		
		
	
		
		
		String password = springSecurityService.encodePassword('password')
		def testUser = new Principal(username: 'me', enabled: true, password: password)
		testUser.save(flush: true)
		//testUser.addToGroups(group3)		
		
		//GroupAuthority.create group1, auth1, true
		
		def pu = new PartyUser(firstname:'firstname',lastname:'lastname',email:'mp@wlab.ca',principal:testUser)
		pu.save()
		
		
		//add talk user without association
		String password2 = springSecurityService.encodePassword('password')
		def testUser2 = new Principal(username: 'mp', enabled: true, password: password)
		testUser2.save(flush: true)
		def pu2 = new PartyUser(firstname:'Myrko',lastname:'Piche',email:'mp@mr-k.org',principal:testUser)
		pu2.save()
		
		
		//create company		
		def partyCompany1 = new PartyCompany(email:'mpa@wlab.ca',name:'wlab',enable:true,accountType:act1).save(flush:true)
		def partyCompany2 = new PartyCompany(email:'info@wlab.ca',name:'mrk',enable:true,accountType:act1).save(flush:true)
		//add company base module rights
		CompanyModuleGroup cmg = CompanyModuleGroup.findByName('GROUP_MODULE_BASE')
		def cmgr = new CompanyModuleGroupRelation(company:partyCompany1,group:cmg,enable:true).save()
		def cmgr2 = new CompanyModuleGroupRelation(company:partyCompany2,group:cmg,enable:true).save()
		//add admin base group rights
		/*def cag = CompanyAdminGroup.findAll()
		cag.each{
			cmgr.addToAdminGroups(it)
			cmgr2.addToAdminGroups(it)
		}*/
		
		//add user group to company
		//group user with 1 authority
		//new CompanyUserGroup(name:'3D ARTISTS',partyCompany2).save()
		
		List userIds = [pu.id]
		partyService.addUsersToCompany(partyCompany1.id,userIds)
		partyService.addUsersToCompany(partyCompany2.id,userIds)
		def groupsId1 = [compUserGroup1.id,compUserAdminGroup.id]
		def groupsId2 = [compUserGroup1.id,compUserAdminGroup.id]
		partyService.updateGroupsForUserCompany(pu.id, partyCompany1.id, groupsId1)
		partyService.updateGroupsForUserCompany(pu.id, partyCompany2.id, groupsId2)
		//partyService.setDefaultCompany(pu.id, partyCompany1.id)
		
		
		
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