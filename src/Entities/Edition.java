/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.sleepycat.persist.model.DeleteAction;
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
public class Edition {
    
    /* Primary Key of this entity */
    @PrimaryKey
    private int id;
    
    /* Secondary Key */
    /* Facilitates the search for this entity through this attribute */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private int year;
    
    /* Secondary Key */
    /* Facilitates the search for this entity through this attribute */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String date;
    
    /* Secondary Key */
    /* Facilitates the search for this entity through this attribute */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE)
    private String country;
    
    /* Secondary Key */
    /* Create a reference to Conference entity */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE, name = "ref_Conference", onRelatedEntityDelete = DeleteAction.CASCADE, relatedEntity = Conference.class)
    private int ref_Conference;

    /**
     * Special empty construtor, necessary to work with Berkeley DB.
     */
    public Edition() {
    }

    /**
     * Normal Constructor for work in the app.
     * @param id
     * @param year
     * @param date
     * @param country
     * @param ref_Conference
     */
    public Edition(int id, int year, String date, String country, int ref_Conference) {
        this.id = id;
        this.year = year;
        this.date = date;
        this.country = country;
        this.ref_Conference = ref_Conference;
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
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public int getRef_Conference() {
        return ref_Conference;
    }

    /**
     *
     * @param ref_Conference
     */
    public void setRef_Conference(int ref_Conference) {
        this.ref_Conference = ref_Conference;
    }

    
}
