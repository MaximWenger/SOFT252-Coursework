/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accessLogger.gui;

import Buildings.Building;
import Buildings.Campus;
import Buildings.Floor;
import Buildings.Room;
import accessPeople.PeopleFactory;
import accessPeople.Person;
import accessPeople.SwipeCard;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * The UserMainMenu Class provides the Main GUI used by the user with the
 * majority of functions.
 *
 */
public class UserMainMenu extends javax.swing.JFrame {

    private DefaultListModel listModelPeople;
    private DefaultListModel listModelCurrentLog;

    private DefaultComboBoxModel listModelCampus2;

    private DefaultComboBoxModel listModelBuilding2;
    private DefaultComboBoxModel listModelFloor;

    private DefaultComboBoxModel listModelRoom;

    private PeopleFactory personFactory;
    private List<Campus> campusList;

    private Integer SelectedCampus = 0;
    private Integer SelectedBuilding = 0;
    private Integer SelectedFloor = 0;
    private Integer SelectedRoom = 0;
    List<Person> peopleList = new ArrayList<>();
    private JTextArea CurrentLog;

    /**
     * Creates new form UserMainMenu Populates example users.
     */
    public UserMainMenu() {

        listModelCurrentLog = new DefaultListModel();
        listModelFloor = new DefaultComboBoxModel();
        listModelRoom = new DefaultComboBoxModel();
        CurrentLog = new JTextArea();

        peopleList = createPersonTestData();

        listModelPeople = new DefaultListModel();

        for (Integer i = 0; i < peopleList.size(); i++) {
            listModelPeople.addElement(peopleList.get(i).getMySwipeCard().getName());//Current Campus user list on GUI is populated from listModelPeople.
        }

        populateSites();

        initComponents();
    }

    /**
     * Populates Campus, Buildings, Rooms and Floors example data.
     */
    private void populateSites() {
        List<Campus> campusList = new ArrayList<>();
        this.campusList = populateCampus(); //Populates campusList with CampusData

        List<Building> buildingList = new ArrayList<>();
        buildingList = populateBuilding(this.campusList);//Populates buildingList with building data, aswell as campusObject

        Campus c1 = this.campusList.get(0);//Getting default campus
        c1.addBuildingList(buildingList);//adding buildings to default (0) campus

        List<Building> buildings = c1.getBuildingList();//Populating buildings with buildings object, returned from Campus class.

        List<Floor> floorList = new ArrayList<>();
        floorList = populateFloors(buildings);//populating floorList, using building list.

        populateFloorstoBuildings(floorList, buildings);
        List<Room> testRooms = createRoomTestData(floorList);

        populateRoomstoFloors(testRooms, floorList, buildings);

        listModelCampus2 = new DefaultComboBoxModel();
        listModelBuilding2 = new DefaultComboBoxModel();
        listModelFloor = new DefaultComboBoxModel();

        for (Integer i = 0; i < this.campusList.size(); i++) {
            listModelCampus2.addElement(this.campusList.get(i).getName());
        }

    }

    /**
     * Populates floors to rooms. Having confirmed that the room belongs to the
     * same floor.
     *
     * @param testRooms To store a list of rooms and their object data.
     * @param floorList To store a list of floors and their object data.
     * @param buildings To store a list of buildings and their object data.
     */
    private void populateRoomstoFloors(List<Room> testRooms, List<Floor> floorList, List<Building> buildings) {
        for (Integer q = 0; q < testRooms.size(); q++) {
            for (Integer a = 0; a < floorList.size(); a++) {
                for (Integer z = 0; z < buildings.size(); z++) {
                    if (buildings.get(z).getBuildingName() == floorList.get(a).getBuilding().getBuildingName() && buildings.get(z).getBuildingName() == testRooms.get(q).getFloor().getBuilding().getBuildingName()) {
                        buildings.get(z).getFloorList().get(a).setRoomList(testRooms);
                    }
                }
            }
        }
    }

    /**
     * Populates floors to Buildings. Having confirmed that the room belongs to
     * the same floor.
     *
     * @param floorList To store a list of floors and their object data.
     * @param buildings To store a list of buildings and their object data.
     */
    private void populateFloorstoBuildings(List<Floor> floorList, List<Building> buildings) {
        for (Integer i = 0; i < floorList.size(); i++) { //Iterates through the buildings and floors, adding the floors to the correct building.
            for (Integer x = 0; x < buildings.size(); x++) {
                if (floorList.get(i).getBuilding().getBuildingName() == buildings.get(x).getBuildingName()) {
                    buildings.get(x).addFloorList(floorList);
                }
            }
        }
    }

    /**
     * Produces Campus ArrayList and calls CampusList.
     *
     * @return Returns populated campusList.
     */
    private List<Campus> populateCampus() {

        List<Campus> campusList = new ArrayList<>();

        campusList = createCampusTestData();

        return campusList;
    }

    /**
     * Produces Building ArrayList and calls buildingList.
     *
     * @param campusList Used to populate buildingList with campusList objects.
     * @return Returns populated buildingList.
     */
    private List<Building> populateBuilding(List<Campus> campusList) {

        List<Building> buildingList = new ArrayList<>();
        buildingList = createBuildingTestData(campusList);

        return buildingList;
    }

    /**
     * Produces Floor ArrayList and calls createFloorTestData to populate floor
     * example data.
     *
     * @param buildingList Used to populate floorList with buildingList objects.
     * @return returns populatedfloorList
     */
    private List<Floor> populateFloors(List<Building> buildingList) {

        List<Floor> floorList = new ArrayList<>();

        floorList = createFloorTestData(buildingList);

        return floorList;
    }

    /**
     * Populates peopleList ArrayList with example users data.
     *
     * @return returns populated peopleList
     */
    private List<Person> createPersonTestData() { //Produces Test Data for GUI
        List<Person> peopleList = new ArrayList<>();

        personFactory = new PeopleFactory();

        List<String> role = new ArrayList<>();

        role.add("Staff");

        peopleList.add(personFactory.createPerson(11125, "John Smith", role));
        peopleList.add(personFactory.createPerson(21125, "Paul Smith", role));
        peopleList.add(personFactory.createPerson(31125, "Ringo Smith", role));

        role.clear();

        role.add("Manager");
        peopleList.add(personFactory.createPerson(91125, "Jack Johnson", role));
        peopleList.add(personFactory.createPerson(51622, "Leonardo da Vinci", role));
        peopleList.add(personFactory.createPerson(54125, "Elton John", role));

        role.clear();

        role.add("Student");
        role.add("ContractCleaner");
        peopleList.add(personFactory.createPerson(54126, "Paul Anderson", role));
        peopleList.add(personFactory.createPerson(65478, "Rick Sanchez", role));
        peopleList.add(personFactory.createPerson(21457, "Peter Griffin", role));

        return peopleList; //Returns populated list.
    }

    /**
     * Populates campusList ArrayList with example data.
     *
     * @return returns populated campusList.
     */
    private List<Campus> createCampusTestData() {

        List<Campus> campusList = new ArrayList<>();

        campusList.add(new Campus("Plymouth"));

        return campusList;
    }

    /**
     * Populates buildingList ArrayList with example building data.
     *
     * @param campus used to populate example building data with existing campus
     * data
     * @return returns populated buildingList.
     */
    private List<Building> createBuildingTestData(List<Campus> campus) {

        List<Building> buildingList = new ArrayList<>();

        buildingList.add(new Building("Babbage", campus.get(0)));

        buildingList.add(new Building("Smeaton", campus.get(0)));

        buildingList.add(new Building("Scott", campus.get(0)));

        return buildingList;
    }

    /**
     * Populates floorList ArrayList with example floor data.
     *
     * @param building used to populate floorList data with existing building
     * data.
     * @return returns populated floorlist.
     */
    private List<Floor> createFloorTestData(List<Building> building) {
        List<Floor> floorList = new ArrayList<>();

        floorList.add(new Floor(1, building.get(0)));

        floorList.add(new Floor(2, building.get(0)));

        floorList.add(new Floor(3, building.get(0)));

        floorList.add(new Floor(1, building.get(1)));
        floorList.add(new Floor(2, building.get(1)));
        floorList.add(new Floor(3, building.get(1)));
        floorList.add(new Floor(4, building.get(1)));

        floorList.add(new Floor(1, building.get(2)));
        floorList.add(new Floor(2, building.get(2)));
        floorList.add(new Floor(3, building.get(2)));
        floorList.add(new Floor(4, building.get(2)));
        floorList.add(new Floor(5, building.get(2)));

        return floorList;
    }

    /**
     * Populates roomList ArrayList with example room data.
     *
     * @param floor used to populated RoomList object with existing floor data.
     * @return returns populated roomList.
     */
    private List<Room> createRoomTestData(List<Floor> floor) {
        List<Room> roomList = new ArrayList<>();

        roomList.add(new Room("101", "StudentLab", floor.get(0)));
        roomList.add(new Room("102", "StudentLab", floor.get(0)));
        roomList.add(new Room("103", "StudentLab", floor.get(0)));

        roomList.add(new Room("104", "LectureHall", floor.get(1)));
        roomList.add(new Room("105", "LectureHall", floor.get(1)));
        roomList.add(new Room("106", "StudentLab", floor.get(1)));

        roomList.add(new Room("107", "SecureRoom", floor.get(2)));
        roomList.add(new Room("108", "StudentLab", floor.get(2)));
        roomList.add(new Room("109", "SecureRoom", floor.get(2)));

        return roomList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel2 = new java.awt.Panel();
        jLabelCampus = new javax.swing.JLabel();
        jLabelBuilding = new javax.swing.JLabel();
        jLabelFloor = new javax.swing.JLabel();
        jLabelRoom = new javax.swing.JLabel();
        jLabelViewAndEditOperatingMode = new javax.swing.JLabel();
        jComboBoxSetNewModeDropDownBuilding = new javax.swing.JComboBox<>();
        jLabelSetNewModeViewAndEdit = new javax.swing.JLabel();
        jButtonSaveNewModeBuilding = new javax.swing.JButton();
        jComboBoxCampusViewEdit = new javax.swing.JComboBox<>();
        jComboBoxFloorViewEdit = new javax.swing.JComboBox<>();
        jComboBoxRoomViewEdit = new javax.swing.JComboBox<>();
        jComboBoxBuildingViewEdit = new javax.swing.JComboBox<>();
        jButton1SimulateUserEntry = new javax.swing.JButton();
        jButtonEditUser = new javax.swing.JButton();
        jLabelCurrentCampusUsers = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListCampusUsers = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxSetModeDropdownRoom = new javax.swing.JComboBox<>();
        jButton2SaveNewModeRoom = new javax.swing.JButton();
        jLabel3BuildingMode = new javax.swing.JLabel();
        jLabel3RoomMode = new javax.swing.JLabel();
        panel3 = new java.awt.Panel();
        jLabelViewCurrentLog = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButtonExit = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemSaveLog = new javax.swing.JMenuItem();
        jMenuItemLoadLog = new javax.swing.JMenuItem();
        jMenuItemSaveCampusDataModel = new javax.swing.JMenuItem();
        jMenuItemLoadCampusDataModel = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabelCampus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelCampus.setText("Campus");

        jLabelBuilding.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelBuilding.setText("Building");

        jLabelFloor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelFloor.setText("Floor");

        jLabelRoom.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelRoom.setText("Room");

        jLabelViewAndEditOperatingMode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelViewAndEditOperatingMode.setText("View & Edit Operating Mode");

        jComboBoxSetNewModeDropDownBuilding.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Emergency" }));

        jLabelSetNewModeViewAndEdit.setText("Set New Mode:");

        jButtonSaveNewModeBuilding.setText("Save Mode");
        jButtonSaveNewModeBuilding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveNewModeBuildingActionPerformed(evt);
            }
        });

        jComboBoxCampusViewEdit.setModel(listModelCampus2);
        jComboBoxCampusViewEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCampusViewEditActionPerformed(evt);
            }
        });

        jComboBoxFloorViewEdit.setModel(listModelFloor);
        jComboBoxFloorViewEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFloorViewEditActionPerformed(evt);
            }
        });

        jComboBoxRoomViewEdit.setModel(listModelRoom);
        jComboBoxRoomViewEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRoomViewEditActionPerformed(evt);
            }
        });

        jComboBoxBuildingViewEdit.setModel(listModelBuilding2);
        jComboBoxBuildingViewEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBuildingViewEditActionPerformed(evt);
            }
        });

        jButton1SimulateUserEntry.setText("Simulate User Entry");
        jButton1SimulateUserEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1SimulateUserEntryActionPerformed(evt);
            }
        });

        jButtonEditUser.setText("Edit User");

        jLabelCurrentCampusUsers.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCurrentCampusUsers.setText("Current Campus Users");

        jLabel4.setText("Select A User");

        ListCampusUsers.setModel(listModelPeople);
        jScrollPane2.setViewportView(ListCampusUsers);

        jLabel1.setText("Current Building Mode:");

        jLabel5.setText("Current Room Mode:");

        jLabel2.setText("Set New Mode:");

        jComboBoxSetModeDropdownRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Emergency" }));

        jButton2SaveNewModeRoom.setText("Save Mode");
        jButton2SaveNewModeRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2SaveNewModeRoomActionPerformed(evt);
            }
        });

        jLabel3BuildingMode.setText("Unknown");

        jLabel3RoomMode.setText("Unknown");

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jButton1SimulateUserEntry)
                        .addGap(155, 155, 155))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                        .addComponent(jButtonEditUser)
                        .addGap(178, 178, 178))))
            .addGroup(panel2Layout.createSequentialGroup()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(jComboBoxFloorViewEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelRoom)
                            .addComponent(jComboBoxRoomViewEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelCurrentCampusUsers)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel4))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(jLabelViewAndEditOperatingMode))
                            .addGroup(panel2Layout.createSequentialGroup()
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabelCampus)
                                                .addComponent(jComboBoxCampusViewEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabelBuilding))
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jComboBoxBuildingViewEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3BuildingMode))))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addComponent(jLabelSetNewModeViewAndEdit)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxSetNewModeDropDownBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonSaveNewModeBuilding)))
                                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(jLabelFloor))
                                    .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3RoomMode))
                                            .addGroup(panel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jComboBoxSetModeDropdownRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2SaveNewModeRoom)))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addComponent(jLabelViewAndEditOperatingMode)
                .addGap(19, 19, 19)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBuilding)
                    .addComponent(jLabelFloor)
                    .addComponent(jLabelRoom)
                    .addComponent(jLabelCampus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxRoomViewEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jComboBoxFloorViewEdit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxCampusViewEdit)
                    .addComponent(jComboBoxBuildingViewEdit))
                .addGap(57, 57, 57)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3BuildingMode)
                    .addComponent(jLabel3RoomMode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSetNewModeViewAndEdit)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxSetNewModeDropDownBuilding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSaveNewModeBuilding)
                        .addComponent(jLabel2)
                        .addComponent(jComboBoxSetModeDropdownRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2SaveNewModeRoom)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addComponent(jLabelCurrentCampusUsers)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jButton1SimulateUserEntry)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonEditUser))
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabelViewCurrentLog.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelViewCurrentLog.setText("View Current Log");

        jList2.setModel(listModelCurrentLog);
        jScrollPane4.setViewportView(jList2);

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jLabelViewCurrentLog))
            .addGroup(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addComponent(jLabelViewCurrentLog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jMenuFile.setText("File");

        jMenuItemSaveLog.setText("Save Log");
        jMenuItemSaveLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveLogActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSaveLog);

        jMenuItemLoadLog.setText("Load Log");
        jMenuFile.add(jMenuItemLoadLog);

        jMenuItemSaveCampusDataModel.setText("Save Campus Data Model");
        jMenuFile.add(jMenuItemSaveCampusDataModel);

        jMenuItemLoadCampusDataModel.setText("Load Campus Data Model");
        jMenuFile.add(jMenuItemLoadCampusDataModel);

        jMenuBar1.add(jMenuFile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(1159, Short.MAX_VALUE)
                        .addComponent(jButtonExit))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(376, 376, 376)
                .addComponent(jButtonExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Used to close the GUI
     *
     * @param evt
     */
    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    /**
     * Populates floor dropdown list, depending on which building has been
     * chosen.
     *
     * @param evt
     */
    private void jComboBoxBuildingViewEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBuildingViewEditActionPerformed

        try {
            Integer i = jComboBoxBuildingViewEdit.getSelectedIndex(); //Get which building has been selected from dropdown list.

            if (i > -1) {
                this.SelectedBuilding = i;
            } else {
                this.SelectedBuilding = 0;
            }

            if (listModelFloor.getSize() > 0) { //clear exising data from floors
                listModelFloor.removeAllElements();
            }

            if (listModelRoom.getSize() > 0) { //clear existing data from rooms.
                listModelRoom.removeAllElements();
            }

            List<Floor> operatingFloors = this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList(); //Get floor List for this building and this building.

            for (int j = 0; j < operatingFloors.size(); j++) {
                if (operatingFloors.get(j).getBuilding().getBuildingName() == jComboBoxBuildingViewEdit.getSelectedItem()) {//If the building name is the same as that chosen, only populate those floors.

                    listModelFloor.addElement(operatingFloors.get(j).getFloorNumber());//Populates floor Combo Box
                    populateModeLabelBuilding();

                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jComboBoxBuildingViewEditActionPerformed

    /**
     * Populates Building mode label with current building mode.
     */
    private void populateModeLabelBuilding() {
        List<Floor> operatingFloors = this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList();

        for (int j = 0; j < operatingFloors.size(); j++) {
            if (operatingFloors.get(j).getBuilding().getBuildingName() == jComboBoxBuildingViewEdit.getSelectedItem()) {//If the building name is the same as that chosen, only populate those floors.

                jLabel3BuildingMode.setText(operatingFloors.get(j).getBuilding().getBuildingMode());//Populates Building mode Label
            }

        }
    }

    /**
     * Populates Room mode label with current room mode
     */
    private void populateModeLabelRoom() {
        List<Room> operatingRooms = this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList().get(this.SelectedRoom).getRoomList();

        for (int j = 0; j < operatingRooms.size(); j++) {
            if (operatingRooms.get(j).getRoomName() == jComboBoxRoomViewEdit.getSelectedItem()) {//If the building name is the same as that chosen, only populate those floors.

                jLabel3RoomMode.setText(operatingRooms.get(j).getRoomMode());//Populates Building mode Label
            }

        }
    }

    private void jComboBoxRoomViewEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRoomViewEditActionPerformed
        populateModeLabelRoom();
    }//GEN-LAST:event_jComboBoxRoomViewEditActionPerformed
    /**
     * Populates room dropdown list, depending on which floor has been chosen.
     *
     * @param evt
     */
    private void jComboBoxFloorViewEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFloorViewEditActionPerformed
        try {
            Integer i = jComboBoxFloorViewEdit.getSelectedIndex(); //Find which floor has been chosen.

            if (i > -1) {
                this.SelectedFloor = i;
            } else {
                this.SelectedFloor = 0;
            }

            if (listModelRoom.getSize() > 0) { //if dropdown list has elements, clear it.
                listModelRoom.removeAllElements();
            }

            List<Room> operatingRoom = this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList().get(this.SelectedFloor).getRoomList();

            for (int j = 0; j < operatingRoom.size(); j++) {
                if (operatingRoom.get(j).getFloor().getFloorNumber() == jComboBoxFloorViewEdit.getSelectedItem()) {//If the floor number is the same as the selected floor, print all rooms which are on that floor. 
                    listModelRoom.addElement(operatingRoom.get(j).getRoomName());

                    populateModeLabelRoom();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jComboBoxFloorViewEditActionPerformed

    /**
     * Populates Building dropdown list from campus chosen.
     *
     * @param evt
     */
    private void jComboBoxCampusViewEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCampusViewEditActionPerformed

        try {
            Integer i = jComboBoxCampusViewEdit.getSelectedIndex();

            if (i > -1) {
                this.SelectedCampus = i;
            } else {
                this.SelectedCampus = 0;
            }

            listModelBuilding2.removeAllElements(); //Clears existing data from buildings.
            listModelFloor.removeAllElements(); //Clears existing data from Floors.
            listModelRoom.removeAllElements(); //Clears existing data from Rooms.

            Campus operatingCampus = this.campusList.get(i);

            List<Building> buildings = campusList.get(i).getBuildingList();

            for (Integer x = 0; x < buildings.size(); x++) {
                listModelBuilding2.addElement(buildings.get(x).getName());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jComboBoxCampusViewEditActionPerformed

    /**
     * Allows a user to save the current Action Log
     *
     * @param evt
     */
    private void jMenuItemSaveLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveLogActionPerformed
        JFileChooser objFileDialogue = new JFileChooser();

        int intDialogueResult = JFileChooser.CANCEL_OPTION;

        intDialogueResult = objFileDialogue.showSaveDialog(this);

        if (intDialogueResult == JFileChooser.APPROVE_OPTION) {
            File objFile = objFileDialogue.getSelectedFile();

            try (ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(objFile)))) {

                objOut.writeObject(listModelPeople);
                JOptionPane.showMessageDialog(this, "Current campus users saved", "Save completed", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jMenuItemSaveLogActionPerformed

    /**
     * Depending on what option has been chosen, updates emergency mode on
     * Building, trickling down to Rooms.
     *
     * @param evt
     */
    private void jButtonSaveNewModeBuildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveNewModeBuildingActionPerformed
        Integer mode = jComboBoxSetNewModeDropDownBuilding.getSelectedIndex();

        if (mode == 0) {
            this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).update("Normal");

        } else {

            this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).update("Emergency");
        }

        populateModeLabelBuilding();

//Set mode to new mode.
    }//GEN-LAST:event_jButtonSaveNewModeBuildingActionPerformed
    /**
     * Sets mode labels on GUI to correct mode, depending on Room /Building
     * mode.
     *
     * @param evt - event driven
     */
    private void jButton2SaveNewModeRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2SaveNewModeRoomActionPerformed
        Integer mode = jComboBoxSetModeDropdownRoom.getSelectedIndex();
        System.out.println(mode);
        if (mode == 0) {
            System.out.println("hhrh");
            this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList().get(this.SelectedFloor).getRoomList().get(this.SelectedRoom).update("Normal");
        } else {
            System.out.println("bbbbb");
            this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList().get(this.SelectedFloor).getRoomList().get(this.SelectedRoom).update("Emergency");
        }
        populateModeLabelRoom();
    }//GEN-LAST:event_jButton2SaveNewModeRoomActionPerformed
    /**
     *  Used to simulate users trying to access doors. 
     * Also populates GUI log file.
     * @param evt
     */
    private void jButton1SimulateUserEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1SimulateUserEntryActionPerformed

        String user = ListCampusUsers.getSelectedValue();

        Campus operatingCampus = this.campusList.get(this.SelectedCampus); // Current Campus

        Building operatingBuilding = this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding);//Current building

        Floor operatingFloor = this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList().get(this.SelectedFloor);//Current floor

        Room operatingRoom = this.campusList.get(this.SelectedCampus).getBuildingList().get(this.SelectedBuilding).getFloorList().get(this.SelectedFloor).getRoomList().get(this.SelectedRoom);

        SwipeCard card = this.peopleList.get(0).getMySwipeCard();

        for (Integer i = 0; i < peopleList.size(); i++) {
            if (this.peopleList.get(i).getMySwipeCard().getName() == user) {
                card = this.peopleList.get(i).getMySwipeCard();
            }
        }

        try {

            Boolean accessGranted = operatingRoom.tryRoomAccess(card); //Populate Log file

            String logValue = operatingRoom.tryRoomAccessString(card);
            
            listModelCurrentLog.addElement(logValue);//Populate Log on GUI

        } catch (Exception e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_jButton1SimulateUserEntryActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMainMenu.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> ListCampusUsers;
    private javax.swing.JButton jButton1SimulateUserEntry;
    private javax.swing.JButton jButton2SaveNewModeRoom;
    private javax.swing.JButton jButtonEditUser;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonSaveNewModeBuilding;
    private javax.swing.JComboBox<String> jComboBoxBuildingViewEdit;
    private javax.swing.JComboBox<String> jComboBoxCampusViewEdit;
    private javax.swing.JComboBox<String> jComboBoxFloorViewEdit;
    private javax.swing.JComboBox<String> jComboBoxRoomViewEdit;
    private javax.swing.JComboBox<String> jComboBoxSetModeDropdownRoom;
    private javax.swing.JComboBox<String> jComboBoxSetNewModeDropDownBuilding;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3BuildingMode;
    private javax.swing.JLabel jLabel3RoomMode;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelBuilding;
    private javax.swing.JLabel jLabelCampus;
    private javax.swing.JLabel jLabelCurrentCampusUsers;
    private javax.swing.JLabel jLabelFloor;
    private javax.swing.JLabel jLabelRoom;
    private javax.swing.JLabel jLabelSetNewModeViewAndEdit;
    private javax.swing.JLabel jLabelViewAndEditOperatingMode;
    private javax.swing.JLabel jLabelViewCurrentLog;
    private javax.swing.JList<String> jList2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemLoadCampusDataModel;
    private javax.swing.JMenuItem jMenuItemLoadLog;
    private javax.swing.JMenuItem jMenuItemSaveCampusDataModel;
    private javax.swing.JMenuItem jMenuItemSaveLog;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    // End of variables declaration//GEN-END:variables
}
