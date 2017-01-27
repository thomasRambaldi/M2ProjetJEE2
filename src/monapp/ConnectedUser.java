package monapp;


import javax.ejb.Local;

@Local
public interface ConnectedUser {
	public void login(String login, String pwd);
	public void logout();
}
