/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessPeople;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *
 * @author Max
 */
public class PeopleFactoryTest {

    private PeopleFactory peoplefactory = new PeopleFactory();

    /**
     *
     */
    public PeopleFactoryTest() {
    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Confirm names are recalled correctly.
     */
    @Test
    public void testCreatePersonNameConfirmNameCorrect() {

        Person person = null;

        String p2 = null;

        List<String> Role = new ArrayList();
        Role.add("Staff");

        person = peoplefactory.createPerson(2, "Joe", Role);

        // System.out.println(person.getMySwipeCard().getName());
        assertEquals("Joe", person.getMySwipeCard().getName());

    }

    /**
     * Confirm roles are not the same.
     */
    @Test
    public void testCreatePersonConfirmCorrectRole() {
        Person person = null;

        List<String> Role = new ArrayList();

        Role.add("Staff");

        person = peoplefactory.createPerson(2, "Joe", Role);

        Person p2 = null;

        List<String> Role2 = new ArrayList();
        Role2.add("Manager");

        p2 = peoplefactory.createPerson(2, "Joe", Role2);

        assertFalse(person.getClass().equals(p2));

    }

    /**
     * Compare class from dynamically typed to statically typed. Confirming
     * correct class type.
     */
    @Test
    public void testCreatePersonDynamicallyStaticallyTyped() {
        Person person1 = null;
        List<String> Role1 = new ArrayList();
        Role1.add("Visitor");
        person1 = peoplefactory.createPerson(2, "Joe", Role1);

        // Checking consistency in our factory class.
        Visitor person2;
        person2 = new Visitor(2, "Joe", Role1);

        assertEquals(person1.getClass().toString(), person2.getClass().toString());

    }

    /**
     * Confirm Visitor IDs are recalled correctly.
     */
    @Test
    public void testCreatePersonConfirmID() {
        Person person1 = null;
        List<String> Role1 = new ArrayList();
        Role1.add("Visitor");
        person1 = peoplefactory.createPerson(2, "Joe", Role1);

        // Checking consistency in our factory class.
        Visitor person2;
        person2 = new Visitor(2, "Joe", Role1);

        assertEquals(person1.getMySwipeCard().getID(), person2.getMySwipeCard().getID());
    }

    /**
     * Confirm Student IDs are recalled correctly.
     */
    @Test
    public void testStudentPersonClass() {
        Person person1 = null;
        List<String> Role1 = new ArrayList();
        Role1.add("Student");
        person1 = peoplefactory.createPerson(2, "Joe", Role1);

        // Checking consistency in our factory class.
        Student person2;
        person2 = new Student(2, "Joe", Role1);

        assertEquals(person1.getClass().toString(), person2.getClass().toString());

    }

    /**
     * Confirm Staff IDs are recalled correctly.
     */
    @Test
    public void testStaffPersonClass() {
        Person person1 = null;
        List<String> Role1 = new ArrayList();
        Role1.add("Staff");
        person1 = peoplefactory.createPerson(2, "Joe", Role1);

        // Checking consistency in our factory class.
        Staff person2;
        person2 = new Staff(2, "Joe", Role1);

        assertEquals(person1.getClass().toString(), person2.getClass().toString());
    }

    /**
     *Confirm ContractCleaner IDs are recalled correctly.
     */
    @Test
    public void testContractCleanerPersonClass() {
        Person person1 = null;
        List<String> Role1 = new ArrayList();
        Role1.add("ContractCleaner");
        person1 = peoplefactory.createPerson(2, "Joe", Role1);

        // Checking consistency in our factory class.
        ContractCleaner person2;
        person2 = new ContractCleaner(2, "Joe", Role1);

        assertEquals(person1.getClass().toString(), person2.getClass().toString());
    }

    /**
     * Confirm Manager IDs are recalled correctly.
     */
    @Test
    public void testManagerPersonClass() {
        Person person1 = null;
        List<String> Role1 = new ArrayList();
        Role1.add("Manager");
        person1 = peoplefactory.createPerson(2, "Joe", Role1);

        // Checking consistency in our factory class.
        Manager person2;
        person2 = new Manager(2, "Joe", Role1);

        assertEquals(person1.getClass().toString(), person2.getClass().toString());
    }

    /**
     * Confirm Security IDs are recalled correctly.
     */
    @Test
    public void testSecuirtyPersonClass() {
        Person person1 = null;
        List<String> Role1 = new ArrayList();
        Role1.add("Security");
        person1 = peoplefactory.createPerson(2, "Joe", Role1);

        // Checking consistency in our factory class.
        Security person2;
        person2 = new Security(2, "Joe", Role1);

        assertEquals(person1.getClass().toString(), person2.getClass().toString());
    }

    /**
     * Confirm EmergencyResponder IDs are recalled correctly.
     */
    @Test
    public void testEmergencyResponderPersonClass() {
        Person person1 = null;
        List<String> Role1 = new ArrayList();
        Role1.add("EmergencyResponder");
        person1 = peoplefactory.createPerson(2, "Joe", Role1);

        // Checking consistency in our factory class.
        EmergencyResponder person2;
        person2 = new EmergencyResponder(2, "Joe", Role1);

        assertEquals(person1.getClass().toString(), person2.getClass().toString());
    }

}
