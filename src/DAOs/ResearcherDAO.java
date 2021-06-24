/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.Researcher;
import Main.SetupEnvironment;

/**
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912 
 */
public class ResearcherDAO {
    
    /**
     * Allows to add an Researcher to the database.
     * @param a an instance of an Researcher.
     * @see Researcher
     */
    public static void addResearcher(Researcher a){
        SetupEnvironment.researcherPrimaryIndex.putNoOverwrite(a);
    }
    
    /**
     * Allows to retrieve an Researcher from the database.
     * @param id An Integer that represents the primary key of an Researcher that you want to get.
     * @see Researcher
     * @see Entity
     * @return An instance of Researcher with their fields.
     */
    public static Researcher getResearcher(Integer id){
        return SetupEnvironment.researcherPrimaryIndex.get(id);
    }
    
    /**
     * Allows to get an Researcher from the database.
     * @param id An Integer that represents the primary key of an Researcher that you want to delete.
     * @see Researcher
     */
    public static void delResearcher(Integer id){
        SetupEnvironment.researcherPrimaryIndex.delete(id);
    }
}
