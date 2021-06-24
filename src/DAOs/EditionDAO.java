package DAOs;

import Entities.Edition;
import Main.SetupEnvironment;

/**
 *
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912
 */
public class EditionDAO {
    
    /**
     * Allows to add an Edition to the database.
     * @param a an instance of an Edition.
     * @see Edition
     */
    public static void addEdition(Edition a){
         SetupEnvironment.editionPrimaryIndex.put(a);
    }
    
    /**
     * Allows to retrieve an Edition from the database.
     * @param id An Integer that represents the primary key of an Edition that you want to get.
     * @see Edition
     * @see Entity
     * @return An instance of Edition with their fields.
     */
    public static Edition getEdition(Integer id){
        return SetupEnvironment.editionPrimaryIndex.get(id);
    }
    /**
     * Allows to get an Edition from the database.
     * @param id An Integer that represents the primary key of an Edition that you want to delete.
     * @see Edition
     */
    
    public static void delEdition(Integer id){
        SetupEnvironment.editionPrimaryIndex.delete(id);
    }
}