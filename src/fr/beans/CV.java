package fr.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="projetjee2")
public class CV implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private int id;
	
	@ElementCollection
	Set<Activity> activities;
	
	public CV(){
		
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivitees(Set<Activity> activities) {
		this.activities = activities;
	}
}
