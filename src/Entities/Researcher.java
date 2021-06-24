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
 *
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912
 * @see Entity
 */
@Entity
public class Researcher {
    
    /* Primary Key of this entity */
    @PrimaryKey
    private int id;
    
    /* Secondary Key */
    /* Facilitates the search for this entity through this attribute */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String name;
    
    /* Secondary Key */
    /* Facilitates the search for this entity through this attribute */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String surname;
    
    /* Secondary Key */
    /* Facilitates the search for this entity through this attribute */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String secSurname;
    
    /* Secondary Key */
    /* Facilitates the search for this entity through this attribute */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String university;

    /**
     * Special empty construtor, necessary to work with Berkeley DB.
     */
    public Researcher() {
    }

    /**
     * Normal Constructor for work in the app.
     * @param id
     * @param name
     * @param surname
     * @param secSurname
     * @param university
     */
    public Researcher(int id, String name, String surname, String secSurname, String university) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.secSurname = secSurname;
        this.university = university;
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

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public String getSecSurname() {
        return secSurname;
    }

    /**
     *
     * @param secSurname
     */
    public void setSecSurname(String secSurname) {
        this.secSurname = secSurname;
    }

    /**
     *
     * @return
     */
    public String getUniversity() {
        return university;
    }

    /**
     *
     * @param university
     */
    public void setUniversity(String university) {
        this.university = university;
    }
    
    
}
