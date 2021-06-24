package Entities;

import com.sleepycat.persist.model.DeleteAction;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

/**
 * 
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912
 * @see Entity
 */
@Entity
public class Article {
    
    /* Primary Key of this entity */
    @PrimaryKey
    private int id;
    
    /* Secondary Key */
    /* Facilitates the search for this entity through this attribute */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String title;
    
    /* Secondary Key */
    /* Create a reference to Edition entity */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE, name = "ref_Edition", onRelatedEntityDelete = DeleteAction.CASCADE, relatedEntity = Edition.class)
    private int ref_Edition;

    /**
     * Special empty construtor, necessary to work with Berkeley DB.
     */
    public Article() {
    }

    /**
     * Normal Constructor for work in the app.
     * @param id ID of an Article.
     * @param title The title of the Article.
     * @param ref_Edition Reference an Edition (ID)
     */
    public Article(int id, String title, int ref_Edition) {
        this.id = id;
        this.title = title;
        this.ref_Edition = ref_Edition;
    }

    /**
     *
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public int getRef_Edition() {
        return ref_Edition;
    }

    /**
     *
     * @param ref_Edition
     */
    public void setRef_Edition(int ref_Edition) {
        this.ref_Edition = ref_Edition;
    }
    
    
}