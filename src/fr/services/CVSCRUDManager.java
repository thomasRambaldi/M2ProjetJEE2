package fr.services;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Stateless;

import fr.beans.Activity;

@Stateless(name = "act", description = "Representation d'une activitee")
public class CVSCRUDManager implements CVSCRUD{

	@Override
	public ArrayList<Activity> searchActivity(String search) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createActivity(Activity a) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Activity readActivity(Activity a) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateActivity(Activity a, String id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteActivity(Activity a) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}