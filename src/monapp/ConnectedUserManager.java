package monapp;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful(name = "connectedUser")
public class ConnectedUserManager {
	
	private Person user;
	
	@PostConstruct
	public void init(){
		System.out.println("Create " + this);
		user= null;
	}
	
	@Remove
	public void close() {
		System.out.println("Logout " + user.getEmail());
	}
	
	public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}

	public boolean isLogin() {
		return user != null;
	}

}
