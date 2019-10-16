/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

import Buildings.Room;
import Buildings.Building;
import Buildings.Campus;
import Buildings.Floor;
import accessPeople.ContractCleaner;
import accessPeople.EmergencyResponder;
import accessPeople.Manager;
import accessPeople.Security;
import accessPeople.Staff;
import accessPeople.Student;
import accessPeople.Visitor;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for Room class.
 * 
 */
public class RoomTest {

    private Campus campus;
    private Building building;
    private Floor floor;
    private Room roomLH;
    private Room roomSR;
    private Student student;
    private Visitor visitor;
    private LocalTime localTime;
    private Staff staff;
    private ContractCleaner contractCleaner;
    private Manager manager;
    private Security security;
    private EmergencyResponder emergencyResponder;

    /**
     * Populating building objects with test values.
     */
    public RoomTest() {

        this.campus = new Campus("Plymouth");

        this.building = new Building("Babbage", this.campus);

        this.floor = new Floor(3, this.building);

        this.roomLH = new Room("BGB", "LectureHall", this.floor);//Lecture Hall

        this.roomSR = new Room("BGB", "SecureRoom", this.floor);//Secure Room

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
     * Setting LocalTime paramater with localTime.
     */
    @Before
    public void setUp() {
        this.localTime = LocalTime.now();
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     *
     */
    @Test
    public void testSetRoomMode() {
    }

    /**
     * Confirming no room is added before being explicitly told to do so.
     */
    @Test
    public void testIfRoomOnBuildingFalse() {
        // This should return false.
        if (this.building.getFloorList().size() == 1) {
            assertTrue(false);
        } else {
            assertTrue(true);
        }

    }

    /**
     * Confirming addFloor correctly adds 1 floor.
     */
    @Test
    public void TestIfRoomOnBuildingTrue() {

        this.building.addFloor(this.floor);

        // This should return true.
        if (this.building.getFloorList().size() == 1) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    /**
     * Confirming default room mode is normal.
     */
    @Test
    public void testGetRoomMode() {
        assertEquals(this.roomLH.getRoomMode(), "Normal");
    }

    /**
     * Confirming Room type recall is correct.
     */
    @Test
    public void testGetRoomType() {
        assertEquals(this.roomLH.getRoomType(), "LectureHall");
    }

    /**
     * Confirming Room name is correct.
     */
    @Test
    public void testGetRoomName() {
        assertEquals(this.roomLH.getRoomName(), "BGB");
    }

    /**
     * Confirms that a Student can/cannot access Lecture Hall dependent on local
     * time.
     *
     * @throws Exception
     */
    @Test

    public void testTryRoomAccessStudent() throws Exception {

        List<String> Role = new ArrayList();

        Role.add("Student");

        this.student = new Student(3232, "Joe", Role);

        LocalTime EndTime;
        LocalTime StartTime;
        EndTime = LocalTime.of(22, 00);
        StartTime = LocalTime.of(8, 30);

        if (localTime.isAfter(EndTime) && localTime.isBefore(StartTime)) {
            assertFalse(this.roomLH.tryRoomAccess(this.student.getMySwipeCard()));
        } else {
            assertTrue(this.roomLH.tryRoomAccess(this.student.getMySwipeCard()));
        }

    }
    /**
     * Confirms that a Student can/cannot access Secure Room.
     * @throws Exception 
     */
    @Test
    public void testTrySecureRoomAccessStudent() throws Exception {

        List<String> Role = new ArrayList();

        Role.add("Student");

        this.student = new Student(3232, "Joe", Role);

        assertFalse(this.roomSR.tryRoomAccess(this.student.getMySwipeCard()));
    }

    /**
     * Confirms that a Visitors can/cannot access Lecture Hall dependent on
     * local time.
     *
     * @throws Exception
     */
    @Test
    public void testTryRoomAccessVisitor() throws Exception {

        List<String> Role = new ArrayList();

        Role.add("Visitor");

        this.visitor = new Visitor(333, "Max", Role);

        LocalTime EndTime;
        LocalTime StartTime;
        EndTime = LocalTime.of(22, 00);
        StartTime = LocalTime.of(8, 30);

        if (localTime.isAfter(EndTime) || localTime.isBefore(StartTime)) {
            assertFalse(this.roomLH.tryRoomAccess(this.visitor.getMySwipeCard()));
        } else {
            assertTrue(this.roomLH.tryRoomAccess(this.visitor.getMySwipeCard()));
        }
    }
    /**
     * Confirms that a Visitor cannot access Secure Room.
     * @throws Exception 
     */
    @Test
    public void testTrySecureRoomAccessVisitor() throws Exception {
        List<String> Role = new ArrayList();

        Role.add("Visitor");

        this.visitor = new Visitor(333, "Max", Role);
        assertFalse(this.roomSR.tryRoomAccess(this.visitor.getMySwipeCard()));
    }

    /**
     * Confirms that Staff can/cannot access Lecture Hall dependent on local
     * time.
     *
     * @throws Exception
     */
    @Test
    public void testTryRoomAccessStaff() throws Exception {//Uses LectureHall

        List<String> Role = new ArrayList();

        Role.add("Staff");

        this.staff = new Staff(333, "Max", Role);

        LocalTime EndTime;
        LocalTime StartTime;
        EndTime = LocalTime.of(23, 59, 59);
        StartTime = LocalTime.of(8, 30);

        if (localTime.isAfter(EndTime) && localTime.isBefore(StartTime)) {
            assertFalse(this.roomLH.tryRoomAccess(this.staff.getMySwipeCard()));
        } else {
            assertTrue(this.roomLH.tryRoomAccess(this.staff.getMySwipeCard()));
        }

    }
    /**
     * Confirms that a Staff can/cannot access Secure Room dependent on localTime.
     * @throws Exception 
     */
    @Test
    public void testTrySecureRoomAccessStaff() throws Exception { //Uses Secure Room
        List<String> Role = new ArrayList();

        Role.add("Staff");

        this.staff = new Staff(333, "Max", Role);

        LocalTime EndTime;
        LocalTime StartTime;
        EndTime = LocalTime.of(23, 59, 59);
        StartTime = LocalTime.of(8, 30);

        if (localTime.isAfter(EndTime) || localTime.isBefore(StartTime)) {
            assertFalse(this.roomSR.tryRoomAccess(this.staff.getMySwipeCard()));
        } else {
            assertTrue(this.roomSR.tryRoomAccess(this.staff.getMySwipeCard()));
        }
    }

    /**
     * Confirms that a ContractCleaner can/cannot access Lecture Hall dependent
     * on local time.
     *
     * @throws Exception
     */
    @Test
    public void testTryRoomAccessContractCleaner() throws Exception {

        List<String> Role = new ArrayList();

        Role.add("ContractCleaner"); //Testing with ContactCleaner

        this.contractCleaner = new ContractCleaner(333, "Max", Role);//Produce new ContractCleaner object, held by contactCleaner

        LocalTime EndTime;
        LocalTime StartTime;
        EndTime = LocalTime.of(10, 30);
        StartTime = LocalTime.of(5, 30);

        if (localTime.isAfter(EndTime) && localTime.isBefore(StartTime)) { //If the current Time is after EndTime and the current time is before the StartTime. Assume it's false.

            assertFalse(this.roomLH.tryRoomAccess(this.contractCleaner.getMySwipeCard()));

        }

        EndTime = LocalTime.of(22, 30);
        StartTime = LocalTime.of(17, 30);

        if (localTime.isAfter(EndTime) && localTime.isBefore(StartTime)) { //If the current Time is after new EndTime and the current time is before the new StartTime. Assume it's false.

            assertFalse(this.roomLH.tryRoomAccess(this.contractCleaner.getMySwipeCard()));

        } else {

            assertTrue(this.roomLH.tryRoomAccess(this.contractCleaner.getMySwipeCard()));//Assume it's true, as the time is not before the startTime or after the End time in both other If statementds.
        }

    }
    /**
     * Confirms that a ContractCleaner cannot access Secure Room.
     * @throws Exception 
     */
    @Test
    public void testTrySecureRoomAccessContractCleaner() throws Exception {//Uses SecureRoom
        List<String> Role = new ArrayList();

        Role.add("ContractCleaner");

        this.visitor = new Visitor(333, "Max", Role);
        assertFalse(this.roomSR.tryRoomAccess(this.visitor.getMySwipeCard()));
    }

    /**
     * Confirms that a Manager can access Secure Room.
     * @throws Exception 
     */
    @Test
    public void testTrySecureRoomAccessManager() throws Exception {//Uses SecureRoom

        List<String> Role = new ArrayList();

        Role.add("Manager");

        this.manager = new Manager(333, "Max", Role);

        assertTrue(this.roomSR.tryRoomAccess(this.manager.getMySwipeCard()));
    }
    /**
     * Confirms that a Security can access Secure Room.
     * @throws Exception 
     */
    @Test
    public void testTrySecureRoomAccessSecurity() throws Exception {//Uses SecureRoom

        List<String> Role = new ArrayList();

        Role.add("Security");

        this.security = new Security(333, "Max", Role);

        assertTrue(this.roomSR.tryRoomAccess(this.security.getMySwipeCard()));
    }
    
    /**
     * Confirms that a EmergencyResponder cannot access Secure Room with default mode.
     * @throws Exception 
     */
    @Test
    public void testTrySecureRoomAccessEmergencyResponder() throws Exception {//Uses SecureRoom

        List<String> Role = new ArrayList();

        Role.add("EmergencyResponder");

        this.emergencyResponder = new EmergencyResponder(333, "Max", Role);

        assertFalse(this.roomSR.tryRoomAccess(this.emergencyResponder.getMySwipeCard()));
    }

    /**
     *
     */
    @Test
    public void testUpdate() {
    }

}
