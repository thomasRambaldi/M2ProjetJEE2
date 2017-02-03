package monapp;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful(name = "persLog", description = "Representation d'une personne logger")
public class AuthenticateManager implements ConnectedUser{

	@PersistenceContext(unitName = "myData")
	EntityManager em;

	@Override
	public void login(String login, String pwd) {
		Person thePerson = em.find(Person.class, login);

		if( ! login.equals(thePerson.getEmail())){
			System.out.println("LOGIN INCORRECT / (OU MDP)");
			return ;
		}
		
		try {
			thePerson.setPassword(pwd); // <---- a voir si c'est utile
			thePerson.crypterPWD();
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		if( ! pwd.equals(thePerson.getPassword())){
			System.out.println("MOT DE PASSE INCORRECT (ou login)");
			return ;
		}
	}

	@Override
	public void logout() {

	}

	@Override
	public boolean isLogin() {

		return false;
	}

}
