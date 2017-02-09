package fr.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(schema="projetjee2")
public class CV implements Serializable{

	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO, generator="my_entity_seq_gen")
//	@SequenceGenerator(name="my_entity_seq_gen", sequenceName="MY_ENTITY_SEQ")
//	private long id;
	
//	@Id @GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id")
//	private int id;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotNull(message="Veuillez saisir un nom pour votre CV")
	@Size( min=5, max=200, message = "Le nom du CV doit avoir au moins 5 caract�res" )
	@Pattern( regexp = "^[a-zA-Z0-9\\s]+$" , message = "Merci de saisir un nom de CV valide" )
	private String name;


	@ElementCollection
	private List<Activity> activities;
	
	@OneToOne(mappedBy="cv")
	Person person;
	
	public CV(){

	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
//	EXPERIENCE, FORMATION, HOBBIES, LANGUAGES, OTHER
	public List<Activity> getNature(Nature nature){
		List<Activity> nat = new ArrayList<Activity>();
		if(activities == null)
			return null;
		for(Activity a : activities)
			if(a.getNature() == nature)
				nat.add(a);
		if(nat.isEmpty())
			return null;
		nat.sort(new Comparator<Activity>() {
			@Override
			public int compare(Activity a1, Activity a2) {
				return a1.getYear().intValue() > a2.getYear().intValue() ? -1 : 1;
			}
		});
		return nat;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
