package monapp;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "authController")
@SessionScoped
public class AuthenticateController {
	@EJB
	AuthenticateManager am;
	
	public String login(String login, String pwd) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		return am.login(login, Cryptography.crypt(pwd)) ? "userAccount": "logIn"; 
    }
	
	public String log_out(){
    	am.logout();
    	return "hello";
    }
	
	public boolean isLogin(){
		return am.isLogin();
	}
	
	public boolean isNullCV(){
		return !isLogin() || getCV() == null;
	}
	
	public String save() throws SQLException {
        am.updateData();
        return "userAccount";
    }
	
	public void removeAccount(){
		am.removeAccount();
		am.logout();
	}
	
	public Person getUser(){
		return am.getUser();
	}
	
	public CV getCV(){
		CV c = am.getCV();
//		System.out.println(c.getName());
		return c;
	}
	
	
	public void redirect(ComponentSystemEvent event){
		if(! isLogin()){
			FacesContext fc = FacesContext.getCurrentInstance();
			ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
			nav.performNavigation("hello.xhtml");
		}
	}
	
}
