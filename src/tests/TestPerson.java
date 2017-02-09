package tests;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.beans.Person;
import fr.services.PersonSCRUDManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:tests/spring.xml")
public class TestPerson {

	//  @PersistenceContext(unitName = "myData")
	private static EntityManager em;
	//	@PersistenceContext(unitName = "myData")
	private static EntityManagerFactory emf;

    @Inject
    UserTransaction utx;
    
	@Inject
	PersonSCRUDManager pm;
	
	Person p1 = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("myDS");
		em = emf.createEntityManager();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		em.clear();
		em.close();
		emf.close();
	}

	@Before
	public void setUp() throws Exception {
		p1 = new Person();
	}

	@After
	public void tearDown() throws Exception {
		p1 = null;
	}

	@Test
	public void testGetObjectById_success() {
		Person person = em.find(Person.class, "test@gmail.com");
		Person p = pm.readPerson("test@gmail.com");
		assertNotNull(p);
		assertNotNull(person);
	}
}
