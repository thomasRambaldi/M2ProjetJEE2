package monapp;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AuthenticateManager implements ConnectedUser{

	@Override
	public void login(String login, String pwd) {
	
	}

	@Override
	public void logout() {

	}

	@Override
	public boolean isLogin() {
		
		return false;
	}
	
}
