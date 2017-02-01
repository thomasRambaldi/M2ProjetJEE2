package monapp;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="projetjee2")
public class CV implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
//	@GeneratedValue
	private Integer id = 0;


	@ElementCollection
	ArrayList<Activity> activities;
	
	public CV(){

	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
