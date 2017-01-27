package fr.services;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Local;

import fr.beans.Activity;

@Local
public interface CVSCRUD {

	public ArrayList<Activity> searchActivity(String search) throws SQLException;
	public void createActivity(Activity a)    			 	 throws SQLException;
	public Activity readActivity(Activity a)      		 	 throws SQLException;
	public void updateActivity(Activity a, String id)    	 throws SQLException;
	public void deleteActivity(Activity a)    			 	 throws SQLException;

}
