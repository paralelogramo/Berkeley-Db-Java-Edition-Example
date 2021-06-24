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
public class Author {
    
    /* Primary Key of Author entity*/
    @PrimaryKey
    int id;
    
    /* Secondary Key */
    /* Create a reference to Article entity */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE, name = "ref_Article", onRelatedEntityDelete = DeleteAction.CASCADE, relatedEntity = Article.class)
    private int ref_Article;
    
    /* Secondary Key */
    /* Create a reference to Researcher entity */
    @SecondaryKey(relate = Relationship.MANY_TO_ONE, name = "ref_Researcher", onRelatedEntityDelete = DeleteAction.CASCADE, relatedEntity = Researcher.class)
    private int ref_Researcher;

    
    /**
     * Special empty construtor, necessary to work with Berkeley DB.
     */
    public Author() {
    }

    /**
     * Normal Constructor for work in the app.
     * @param id
     * @param ref_art
     * @param ref_rsc
     */
    public Author(int id, int ref_art, int ref_rsc) {
        this.id = id;
        this.ref_Article = ref_art;
        this.ref_Researcher = ref_rsc;
    }

    /**
     *
     * @return
     */
    public long getId() {
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
    public int getRef_Article() {
        return ref_Article;
    }

    /**
     *
     * @param ref_Article
     */
    public void setRef_Article(int ref_Article) {
        this.ref_Article = ref_Article;
    }

    /**
     *
     * @return
     */
    public int getRef_Researcher() {
        return ref_Researcher;
    }

    /**
     *
     * @param ref_Researcher
     */
    public void setRef_Researcher(int ref_Researcher) {
        this.ref_Researcher = ref_Researcher;
    }

    @Override
    public String toString() {
        return "Author{" + "idAutor=" + id + ", ref_Article=" + ref_Article + ", ref_Researcher=" + ref_Researcher + '}';
    }
}
