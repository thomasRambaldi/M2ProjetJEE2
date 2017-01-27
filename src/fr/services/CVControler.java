package fr.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fr.beans.Activity;
import fr.beans.CV;
import fr.beans.Nature;

@ManagedBean(name = "cv")
@SessionScoped
public class CVControler {

	@EJB
	CVSCRUD cvm;
	
    CV theCV = new CV();
	
	 @PostConstruct
	    public void init()  {
	        System.out.println("Create " + this);
	        if (cvm.searchCV().size() == 0) {
	        	CV cv1 = new CV();
	        	ArrayList<Activity> setCV = new ArrayList<>();
	        	Activity act = new Activity();
	        	act.setTitle("Candidature de stage");
	        	act.setNature(Nature.FORMATION);
	        	act.setYear(2010);
	        	act.setWebSite("https://www.linkedin.com/home?trk=nav_responsive_tab_home");
	        	act.setDescription("Site effectué à partir du cahier des charges de la JAM");
	        	setCV.add(act);
	            cvm.createCV(cv1);
	        }
	    }
	 
	  public List<CV> getCVs() throws SQLException {
	        return cvm.searchCV();
	    }

	    public CV getTheCV() {
	        return theCV ;
	    }

	    public String show(Integer idCv) {
	    	theCV = cvm.readCV(idCv);
	        return "showCV";
	    }

	    public String save() throws SQLException {
	        cvm.createCV(theCV);
	        return "showCV";
	    }

	    public String newCV() {
	    	theCV = new CV();
	        return "editCV";
	    }
}
