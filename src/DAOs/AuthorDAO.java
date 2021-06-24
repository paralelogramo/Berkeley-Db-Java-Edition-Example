package DAOs;

import Entities.Author;
import Main.SetupEnvironment;

/**
 *
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912
 */
public class AuthorDAO {
    
    /**
     * Allows to add an Author to the database.
     * @param a an instance of an Author.
     * @see Author
     */
    public static void addAutor(Author a){
        SetupEnvironment.authorPrimaryIndex.put(a);
    }
    
    /**
     * Allows to retrieve an Author from the database.
     * @param id An Integer that represents the primary key of an Author that you want to get.
     * @see Author
     * @see Entity
     * @return An instance of Author with their fields.
     */
    public static Author getAutor(Integer id){
        return SetupEnvironment.authorPrimaryIndex.get(id);
    }
    
    /**
     * Allows to get an Author from the database.
     * @param id An Integer that represents the primary key of an Author that you want to delete.
     * @see Author
     */
    public static void delAutor(Integer id){
        SetupEnvironment.authorPrimaryIndex.delete(id);
    }
}
