/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buidlings.Logging;

import accessPeople.SwipeCard;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Creates AccessLogger Class. Used to write each room access attempt to a log
 * file.
 *
 */
public class AccessLogger {

    private LocalTime DateTime;
    private Logger _Logger;

    /**
     * 
     */
    public AccessLogger() {
        this._Logger = Logger.getInstance();
        this.DateTime = LocalTime.now();
    }
    
/**
 * Returns DataTime
 * @return LocalTime - DateTime
 */
    public LocalTime getDateTime() {
        return DateTime;
    }
    


    /**
     * CreateAccessLog concatenates all required values into a string and passes
     * them to WriteToAccessLogFile within the Logger Class
     *
     * @param card Provides access to Swipecard Properties.
     * @param floorName Provides access to floorName Properties.
     * @param buildingName Provides access to buildingName Properties.
     * @param roomName Provides access to roomName Properties.
     * @param accessGranted Uses accessGranted method, to return bool value is access is allowed.
     * @return String -  used to populate text log on GUI
     * @throws IOException -
     */
    public String createAccessLog(SwipeCard card, Integer floorName, String buildingName, String roomName, Boolean accessGranted) throws IOException {
        String value = ("UserName:" + card.getName() + " " + "UserID:"+ card.getID() + " " + "Floor:"+ floorName + " " + "Building:" + buildingName + " " + "Room:" + roomName + " " + "AccessGranted:" + accessGranted + " " + "Time:"+ LocalDateTime.now());
        this._Logger.WriteToAccessLogFile(value);
       return value;
    }

}
