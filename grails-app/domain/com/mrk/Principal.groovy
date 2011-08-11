package com.mrk

class Principal implements Serializable {

	String username
	String password
	String confirm
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	static hasMany= [groups:Group]
	
	
	static transients = ['confirm']
	
	static constraints = {
		username blank: false, unique: true		
		password(blank: false, nullable: false, size:5..250, validator: {password, obj ->
         def password2 = obj.properties['confirm']
         if(password2 == null) return true // skip matching password validation (only important when setting/resetting pass)
         password2 == password ? true : ['invalid.matchingpasswords']
     })	
		
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Authority> getAuthorities() {
		//println 'hello'	
		//this.groups.collect {it.authorities } as Set
		//get current company
		def currentCompany = CompanyUserGroupRelation.getCurrentCompany(this.id)
		log.debug("in principal currentcompany : ${currentCompany}")	
		if(currentCompany)	
		{	
			CompanyUserGroup userGroups = CompanyUserGroupRelation.getUserGroups(this.id,currentCompany.id)
			def moduleGroups = CompanyModuleGroupRelation.getCompanyGroups(currentCompany.id)
			log.debug("current all groups is : ${moduleGroups}")
			
			def auth = []
			
			//loop in comapny module groups
			moduleGroups.each{
				it.authorities.each{
					auth.add(it)
				}
			}
			
			//loop in company created groups
			userGroups.each{
				it.authorities.each{
					auth.add(it)
				}
			}
			
			//loop in user groups system admin
			this.groups.each{
				it.authorities.each{
					auth.add(it)
				}
			}
			
			
			log.debug("Current authorities: ${auth}")
			return auth as Set
			//GroupAuthority.findAllByGroup(this).collect { it.authority } as Set
		}
		else
		{
			return []
		}
	}
}
