package com.mrk

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.juli.logging.impl.Log4JLogger;
import org.apache.log4j.*



class CompanyUserGroupRelation implements Serializable{
	
	PartyCompany company
	PartyUser user
	static hasMany =[groups:CompanyUserGroup]
	boolean enable
	boolean is_admin
	boolean default_company = false
	
    static constraints = {
    }
	
	
	static def getCurrentCompany(long principalId) {		
		Principal pr = Principal.get(principalId)
		PartyUser user = PartyUser.findByPrincipal(pr)
		def cugr = CompanyUserGroupRelation.findByUserAndDefault_company(user,true)
		if(!cugr)return false
		
		return cugr.company		
	}
	
	
	static Set<CompanyUserGroup> getUserGroups(long principalId,long companyId)
	{
		Principal pr = Principal.get(principalId)
		PartyUser partyUser = PartyUser.findByPrincipal(pr)
		PartyCompany partyCompany = PartyCompany.get(companyId)
		CompanyUserGroupRelation.findAllByUserAndCompany(partyUser,partyCompany).collect { it.groups }.flatten() as Set
		
	}
	
	static List<PartyCompany> getAllUserCompanies(long principalId)		
	{
		Principal pr = Principal.get(principalId)
		PartyUser user = PartyUser.findByPrincipal(pr)	
		CompanyUserGroupRelation.findAllByUserAndEnable(user,true).unique{it.company.id}.collect{it.company} as List
		
	}
	
	
}
