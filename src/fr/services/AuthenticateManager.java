package fr.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.beans.CV;
import fr.beans.IPersonSCRUD;
import fr.beans.Person;

@Stateless(name="authManager")
public class AuthenticateManager {
	@EJB
    IPersonSCRUD pm;
	
	@EJB
	ConnectedUserManager cm;
	
	public boolean login(String login, String pwd) {
		Person log = pm.readPerson(login);
    	if(log == null){
    		return false;
    	}
    	if(! log.getPassword().equals(pwd)){
    		return false;
    	}
    	cm.setUser(log);
    	return true;
		
	}

	public void logout() {
		cm.setUser(null);
	}

	public boolean isLogin() {
		return cm.getUser() != null;
	}

	public void removeAccount() {
		pm.deletePerson(cm.getUser());
	}
	
	public Person getUser(){
		return cm.getUser();
	}
	
	public CV getCV(){
		return cm.getCV();
	}
	
	public void updateData() {
		if(isLogin()) pm.updatePerson(getUser()); 
	}

}
