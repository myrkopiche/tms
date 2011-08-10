package com.mrk

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.juli.logging.impl.Log4JLogger;
import org.apache.log4j.*
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession;
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest;
import org.springframework.web.context.request.RequestContextHolder;


class CompanyUserGroupRelation implements Serializable{
	
	PartyCompany company
	PartyUser user
	CompanyUserGroup group
	boolean enable
	boolean is_admin
	boolean default_company = false
	
    static constraints = {
		group(nullable:true)
    }
	
	
	static PartyCompany getCurrentCompany(long principalId) {		
		Principal pr = Principal.get(principalId)
		PartyUser user = PartyUser.findByPrincipal(pr)
		def cugr = CompanyUserGroupRelation.findByUserAndDefault_company(user,true)
		return cugr.company	
	}
	
	
	static Set<CompanyUserGroup> getUserGroups(long principalId,long companyId)
	{
		Principal pr = Principal.get(principalId)
		PartyUser partyUser = PartyUser.findByPrincipal(pr)
		PartyCompany partyCompany = PartyCompany.get(companyId)
		CompanyUserGroupRelation.findAllByUserAndCompany(partyUser,partyCompany).collect { it.group } as Set
		
	}
	
	static List<PartyCompany> getAllUserCompanies(long principalId)		
	{
		Principal pr = Principal.get(principalId)
		PartyUser user = PartyUser.findByPrincipal(pr)
		//def cugr = CompanyUserGroupRelation.executeQuery("Select distinct company.name from company_user_group_relation cugr where cugr.user = ? ",[user])
		
		CompanyUserGroupRelation.findAllByUserAndEnable(user,true).unique{it.company.id}.collect{it.company} as List
		
	}
	
	
}
