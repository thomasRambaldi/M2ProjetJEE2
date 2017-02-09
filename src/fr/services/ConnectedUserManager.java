package fr.services;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import fr.beans.CV;
import fr.beans.Person;

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
	
	public CV getCV() {
		return user.getCv();
	}

	public void setCV(CV cv) {
		user.setCv(cv);
	}

	public boolean isLogin() {
		return user != null;
	}

}
