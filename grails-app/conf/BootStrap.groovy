import org.hibernate.validator.Email;
import com.mrk.AccountType;
import com.mrk.AddressType;
import com.mrk.Party;
import com.mrk.PartyUser;
import com.mrk.PartyCompany;
import com.mrk.Phone;
import com.mrk.PhoneType;
import com.mrk.Type;
import com.mrk.Address;
import com.mrk.Authority
import com.mrk.User;
import com.mrk.UserAuthority;

class BootStrap {
	def springSecurityService
	
    def init = { servletContext ->
		
		def adminRole = new Authority(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Authority(authority: 'ROLE_USER').save(flush: true)
		String password = springSecurityService.encodePassword('password')
		def testUser = new User(username: 'me', enabled: true, password: password)
		testUser.save(flush: true)
		
		UserAuthority.create testUser, adminRole, true
  
		assert User.count() == 1
		assert Authority.count() == 2
		assert UserAuthority.count() == 1
		
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
		def user1 = new User(username: 'mp', enabled: false, password: password)
		user1.save(flush:true)
		
		def partyUser = new PartyUser(lastname:'Piche',firstname:'Myrko',email:'mp@mr-k.org',principal:user1).save(flush:true)
		
		//afterEmail confirmation enable user
		partyUser.principal.setEnabled true
		partyUser.save()	
		
  
    }
    def destroy = {
    }
}