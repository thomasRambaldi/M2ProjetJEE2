package monapp;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Navigation {

    public String hello() {
        return "hello.xhtml";
    }
    
    public String inscription() {
        return "inscription.xhtml";
    }
    
    public String connect() {
        return "logIn.xhtml";
    }
    
    public String userAccount(){
    	return "userAccount.xhtml";
    }
    
    public String listPersons(){
    	return "persons.xhtml";
    }
    
    public String listCVs(){
    	return "cvs.xhtml";
    }
    
    public String search(){
    	return "resultSearch.xhtml";
    }

}