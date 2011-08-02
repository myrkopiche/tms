import org.hibernate.validator.Email;
import com.mrk.AccountType;
import com.mrk.AddressType;

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
	def registrationService
	
    def init = { servletContext ->
		
		
		
		//define authority
		def adminRole = new Authority(authority: 'ROLE_TMS_ADMIN').save()
		def userRole = new Authority(authority: 'ROLE_USER').save()
		def auth0 = new Authority(authority: 'ROLE_COMPANY_VIEW').save()
		def auth1 =new Authority(authority: 'ROLE_COMPANY_CREATE').save()
		def auth2 = new Authority(authority: 'ROLE_COMPANY_UPDATE').save()
		def auth3 = new Authority(authority: 'ROLE_COMPANY_DELETE').save()
		

		def group1 = new CompanyUserGroup(name:'GROUP_COMPANY')
		group1.addToAuthorities(auth1)
		.addToAuthorities(userRole)
		.addToAuthorities(auth0)
		.save()
		
		
		String password = springSecurityService.encodePassword('password')
		def testUser = new Principal(username: 'me', enabled: true, password: password)
		testUser.save(flush: true)
		group1.addToPrincipals(testUser)
		
		GroupAuthority.create group1, auth1, true
		
		//testing registration
		def prs = [:]
		prs.username = 'mp'
		prs.password = 'mp'
		prs.firstname = 'myrko'
		prs.lastname = 'piche'
		prs.email = 'mp@mr-k.org'
		
		registrationService.registerUser(prs)
		
		
		
		/*
		
		//create company
		def accType = new AccountType(type:'FREE',name:'Free Account',description:'This type of account is good for trial').save(flush:true)
		def partyCompany1 = new PartyCompany(email:'mp@wlab.ca',name:'wlab',enable:false,accountType:accType).save(flush:true)
		partyCompany1.saveAdministrativeCompany()
		
		def partyUser = new PartyUser(lastname:'Piche',firstname:'Myrko',email:'mp@mr-k.org',principal:testUser).save(flush:true)
		partyUser.principal.setEnabled true
		partyUser.save()
		
		List userIds = [partyUser.id]
		partyService.addUsersToCompany(partyCompany1.id,userIds)
		
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