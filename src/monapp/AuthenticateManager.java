package monapp;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless(name="authManager")
public class AuthenticateManager {
	@EJB
    IPersonSCRUD pm;
	
	@EJB
	ConnectedUserManager cm;
	
	public boolean login(String login, String pwd) {
		Person log = pm.readPerson(login);
    	if(log.equals(null)){
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

	public void updateData() {
		if(isLogin()) pm.updatePerson(getUser()); 
	}

}
