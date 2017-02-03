package monapp;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="projetjee2")
public class CV implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id = 0;
	
	private String name;


	@ElementCollection
	private List<Activity> activities;
	
	public CV(){

	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
