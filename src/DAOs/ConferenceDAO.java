/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.Conference;
import Main.SetupEnvironment;

/**
 *
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912 
 */
public class ConferenceDAO {
    
    /**
     * Allows to add an Conference to the database.
     * @param a an instance of an Conference.
     * @see Conference
     */
    public static void addConference(Conference a){
        SetupEnvironment.conferencePrimaryIndex.put(a);
    }
    
    /**
     * Allows to retrieve an Conference from the database.
     * @param id An Integer that represents the primary key of an Conference that you want to get.
     * @see Conference
     * @see Entity
     * @return An instance of Conference with their fields.
     */
    public static Conference getConference(Integer id){
        return SetupEnvironment.conferencePrimaryIndex.get(id);
    }
    
    /**
     * Allows to get an Conference from the database.
     * @param id An Integer that represents the primary key of an Conference that you want to delete.
     * @see Conference
     */
    public static void delConference(Integer id){
        SetupEnvironment.conferencePrimaryIndex.delete(id);
    }
}
