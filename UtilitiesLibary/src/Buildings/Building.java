/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buildings;

import java.util.ArrayList;
import java.util.List;

/**
 * Produces Building class which instantiates Properties.
 *
 */
public class Building implements IBuilding, ISubject, IObservers {

    private List<Floor> FloorList;
    private String BuildingName;
    private String BuildingMode;
    private ArrayList<IObservers> Observers = new ArrayList<>();

    /**
     * Populates current object with provided paramaters and default details,
     * produces List for all floors within building. Registers Building to
     * observers list.
     *
     * @param name String - New Building name
     * @param campus Campus - giving all campus details.
     */
    public Building(String name, Campus campus) {
        this.BuildingName = name;
        this.BuildingMode = "Normal";
        this.FloorList = new ArrayList<>();
        campus.registerObservers(this);
    }

    /**
     * Used to add a floor to an existing building floorlist.
     * @param floors List - Current list of floors.
     */
    public void addFloorList(List<Floor> floors) {
        this.FloorList = floors;
    }

    /**
     * Populates FloorList list with given paramaters. (Producing a list of
     * floors within buildings)
     *
     * @param floorNumb Integer - Used to number the floor.
     * @param building  Building - giving all building details.
     */
    public void makeFloor(Integer floorNumb, Building building) {
        this.FloorList.add(new Floor(floorNumb, building));
    }

    /**
     * Remove floor from list, using given index.
     *
     * @param index Integer - floor index to remove from floorList
     */
    public void removeFloor(Integer index) {
        this.FloorList.remove(index);
    }

    /**
     * Returns whole floor list for current building object.
     * @return FloorList  List - of current floors.
     */
    public List<Floor> getFloorList() {
        return FloorList;
    }
    /**
     * Adds another floor to current building object.
     * @param floor Floor - new floor to add.
     */
    public void addFloor(Floor floor) {
        this.FloorList.add(floor);
    }
    /**
     * Returns current building object name
     * @return  String - current building object name
     */
    public String getBuildingName() {
        return BuildingName;
    }

    /**
     * Returns current building mode for current building object.
     *
     * @return String - building mode for current building object.
     */
    public String getBuildingMode() {
        return BuildingMode;
    }

    /**
     * Sets building mode for current building object.
     *
     * @param BuildingMode String - used to Set building mode for current building object.
     */
    public void setBuildingMode(String BuildingMode) {
        this.BuildingMode = BuildingMode;
    }

    /**
     * Returns current building object name.
     *
     * @return String - current building object name.
     */
    @Override
    public String getName() {
        return this.BuildingName;
    }

    /**
     * Sets building name to given parameter (Allows building name to be
     * changed)
     *
     * @param name String - used to set new name for current building object.
     * 
     */
    @Override
    public void setName(String name) {
        this.BuildingName = name;
    }

    /**
     * Using given parameter, updates all building modes, thus all rooms by
     * extension.
     *
     * @param mode String - New building mode
     */
    @Override
    public void update(String mode) {
        setBuildingMode(mode);
        notifyObservers(mode);
    }

    /**
     * Adds current object to Observers list.
     *
     * @param observer IObservers -.
     */
    @Override
    public void registerObservers(IObservers observer) {
        this.Observers.add(observer);
    }

    /**
     * Changes mode from given parameter for every object within Observers list.
     *
     * @param mode String - New Mode
     */
    @Override
    public void notifyObservers(String mode) {
        Observers.stream().forEach((o) -> {//Retruns a 'Stream' of all elements, to work with forEach.
            o.update(mode);
            
           
        });
    }

}
