/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

/**
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912 
 * @see Entity
 */
@Entity
public class Conference {
    
    /* Primary Key of this entity */
    @PrimaryKey()
    private int id;
    
    /* Secondary Key */
    /* Facilitates the search for this entity through this attribute */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String name;

    /**
     * Special empty construtor, necessary to work with Berkeley DB.
     */
    public Conference() {
    }

    /**
     * Normal Constructor for work in the app.
     * @param id
     * @param nombre
     */
    public Conference(int id, String nombre) {
        this.id = id;
        this.name = nombre;
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
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
