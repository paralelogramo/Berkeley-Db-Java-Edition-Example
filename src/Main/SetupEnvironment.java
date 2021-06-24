/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DAOs.ArticleDAO;
import DAOs.EditionDAO;
import DAOs.ResearcherDAO;
import Entities.Article;
import Entities.Author;
import Entities.Conference;
import Entities.Edition;
import Entities.Researcher;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityCursor;
import com.sleepycat.persist.EntityJoin;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.ForwardCursor;
import com.sleepycat.persist.PrimaryIndex;
import com.sleepycat.persist.SecondaryIndex;
import com.sleepycat.persist.StoreConfig;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912  
 */
public class SetupEnvironment {
    
    /* The Entity Store object and the Environment object are the objects 
       that manage the enironment and the connections with the databases.*/
    public static EntityStore store;
    public static Environment env;
    
    
    /* Primary Index and Secondary Index are the objects that manage de indexes
       and get the instances of the respective entity */
    /* Creation of edition indexes objects */
    public static PrimaryIndex<Integer, Edition> editionPrimaryIndex;
    public static SecondaryIndex<Integer, Integer, Edition> editionByYear;
    public static SecondaryIndex<String, Integer, Edition> editionByDate;
    public static SecondaryIndex<String, Integer, Edition> editionByCountry;
    public static SecondaryIndex<Integer, Integer, Edition> ref_Conference;
    
    /* Creation of conference indexes objects */
    public static PrimaryIndex<Integer, Conference> conferencePrimaryIndex;
    public static SecondaryIndex<String, Integer, Conference> conferenceByName;
    
    /* Creation of researcher indexes objects */
    public static PrimaryIndex<Integer, Researcher> researcherPrimaryIndex;
    public static SecondaryIndex<String, Integer, Researcher> researcherByName;
    public static SecondaryIndex<String, Integer, Researcher> researcherBySurname;
    public static SecondaryIndex<String, Integer, Researcher> researcherBySecSurname;
    public static SecondaryIndex<String, Integer, Researcher> researcherByUniversity;
    
    /* Creation of article indexes objects */
    public static PrimaryIndex<Integer, Article> articlePrimaryIndex;
    public static SecondaryIndex<String, Integer, Article> articleByTitle;
    public static SecondaryIndex<Integer, Integer, Article> ref_Edition;
    
    /* Creation of author indexes objects */
    public static PrimaryIndex<Integer, Author> authorPrimaryIndex;
    public static SecondaryIndex<Integer, Integer, Author> ref_Article;
    public static SecondaryIndex<Integer, Integer, Author> ref_Researcher;
    
     /* Special constructor, this ensures the uniqueness of the variables */
    static {
        /* Creation of the directory to store the data */
        String dir = System.getProperty("user.dir");
        File file = new File(dir, "Database");
        file.mkdirs();        
        
        /* Creation of the environment variables where Berkeley works */
        EnvironmentConfig envConfig = new EnvironmentConfig();
        StoreConfig storeConfig = new StoreConfig();        
        
        /* Grant permissions to create and store data */
        envConfig.setAllowCreate(true);
        storeConfig.setAllowCreate(true);
        
        /* Setting environment variables and assigning indexes */
        env = new Environment(file, envConfig);
        SetupEnvironment.store = new EntityStore(SetupEnvironment.env, "EntityStore", storeConfig);
        
        /* Conference Indexes */
        conferencePrimaryIndex = SetupEnvironment.store.getPrimaryIndex(Integer.class, Conference.class);
        conferenceByName = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.conferencePrimaryIndex, String.class, "name");
        
        /* Researcher Indexes */
        researcherPrimaryIndex = SetupEnvironment.store.getPrimaryIndex(Integer.class, Researcher.class);
        researcherByName = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.researcherPrimaryIndex, String.class, "name");
        researcherBySurname = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.researcherPrimaryIndex, String.class, "surname");
        researcherBySecSurname = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.researcherPrimaryIndex, String.class, "secSurname");
        researcherByUniversity = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.researcherPrimaryIndex, String.class, "university");
        
        /* Edition Indexes */
        editionPrimaryIndex = SetupEnvironment.store.getPrimaryIndex(Integer.class, Edition.class);
        ref_Conference = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.editionPrimaryIndex, Integer.class, "ref_Conference");
        editionByYear = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.editionPrimaryIndex, Integer.class, "year");
        editionByDate = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.editionPrimaryIndex, String.class, "date");
        editionByCountry = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.editionPrimaryIndex, String.class, "country");
        
        /* Article Indexes */
        articlePrimaryIndex = SetupEnvironment.store.getPrimaryIndex(Integer.class, Article.class);
        articleByTitle = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.articlePrimaryIndex, String.class, "title");
        ref_Edition = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.articlePrimaryIndex, Integer.class, "ref_Edition");
        
        /* Author Indexes */
        authorPrimaryIndex = SetupEnvironment.store.getPrimaryIndex(Integer.class, Author.class);
        ref_Article = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.authorPrimaryIndex, Integer.class, "ref_Article");
        ref_Researcher = SetupEnvironment.store.getSecondaryIndex(SetupEnvironment.authorPrimaryIndex, Integer.class, "ref_Researcher");
        
        /* Se agrega un ShutDown Hook para cerrar el Env y la Store*/
//        DBShutDownHook shutdownhook = new DBShutDownHook(SetupEnvironment.env, SetupEnvironment.store);
//        Runtime.getRuntime().addShutdownHook(shutdownhook);
    }
    
    /**
     * Static void method that show every Article written by Janice Hornedo Vallecillos
     */
    public static void getArticlesJanice(){
        System.out.println("Search Started");
        
        /* Create the EntityJoin object with the primary index of Research*/
        EntityJoin join = new EntityJoin(researcherPrimaryIndex);
        
        /* Adds the conditions to the EntityJoin object 
           in this case the name needs to be 'Janice'
           the surname needs to be 'Hornedo' and
           the second surname needs to be 'Vallecillos'*/
        join.addCondition(researcherByName, "Janice");
        join.addCondition(researcherBySurname, "Hornedo");
        join.addCondition(researcherBySecSurname, "Vallecillos");
        
        /* Retrieve the entities that comply with the conditions*/
        ForwardCursor cursor = join.entities();
        
        /* Cast the object to Researcher and initialize
           the variables id and count to get the total sum */
        Researcher i = (Researcher) cursor.next();
        int id = i.getId(), count = 0;
        
        /* Close the cursor
           [Note] If the cursor doesn't close, will generate problems
           with the query's Thread because the Database access will close
           but the cursor is still running getting the info */
        cursor.close();
        
        /* We got the id of 'Janice Hornedo Vallecillos' so we need
           to get all the Articles that has the id as reference
           so create a new EntityJoin but this time with the
           Primary Index of Author*/
        join = new EntityJoin(authorPrimaryIndex);
        
        /* Add the condition that the reference of the researcher
           is the id we got before*/
        join.addCondition(ref_Researcher, id);
        
        /* Get all the entities that comply with the new conditions */
        cursor = join.entities();
        System.out.println("Articles written by Janice Hornedo Vallecillos");
        
        /* Sweeping all entities and retrieve the object */
        for (Object o : cursor) {
            
            /*Cast the object to Author and get the reference to an Article*/
            Author a = (Author) o;
            int ref_art = a.getRef_Article();
            
            /* With the DAO object of Article retrieve the Article with
               the id that got before and print it */
            Article art = ArticleDAO.getArticle(ref_art);
            System.out.println("- "+art.getTitle());
            count++;
        }
        
        /* Close the cursor
           [Note] If the cursor doesn't close, will generate problems
           with the query's Thread because the Database access will close
           but the cursor is still running getting the info */
        cursor.close();
        
        /* Finally close the store and clean and close the environment object */
        try {            
            SetupEnvironment.store.close();
            SetupEnvironment.env.cleanLog();
            SetupEnvironment.env.close();
        } catch (DatabaseException e) {
        }
        
        /* Just for know the totally count */
        System.out.println("Totally count: "+count);
    }
    
    /**
     * Static void method that show the sum of the Articles owned by every university
     */
    public static void  getCountArticulesByUniversity(){
        System.out.println("Search Started");
        
        /* First of all, create a hashmap to save the university and the sum
           of the articles that has any university */
        HashMap<String, Integer> universities = new HashMap<>();
        
        /* Create a EntityCursor to retrieve all the Author entities */
        EntityCursor cursor = SetupEnvironment.authorPrimaryIndex.entities();
        
        /* Sweeping all objects in the cursor */
        for (Object o : cursor) {
            
            /* Cast the object to Author and get the reference
               to the Researcher that wrote it */
            Author a = (Author) o;
            int ref_inv = a.getRef_Researcher();
            
            /* With this reference retrieve the researcher with the
               DAO object of Researcher*/
            Researcher i = ResearcherDAO.getResearcher(ref_inv);
            
            /* Ask if the university is registered in the hasmap */
            if (universities.get(i.getUniversity())==null) {
                /* If isn't registered, registered in the hashmap*/
                universities.put(i.getUniversity(), 1);
            }
            else{
                /* Else get the actual count and sum 1 to this count */
                universities.put(i.getUniversity(), universities.get(i.getUniversity())+1);
            }
        }
        
        /* Close the cursor
           [Note] If the cursor doesn't close, will generate problems
           with the query's Thread because the Database access will close
           but the cursor is still running getting the info */
        cursor.close();
        
        /* Sweeping the hashmap and print the info */
        for (Map.Entry<String, Integer> entry : universities.entrySet()) {
            String key = entry.getKey();
            System.out.print(key+": ");
            Integer value = entry.getValue();
            System.out.println(value);
        }
        
        /* Finally close the store and clean and close the environment object */
        try {            
            SetupEnvironment.store.close();
            SetupEnvironment.env.cleanLog();
            SetupEnvironment.env.close();
        } catch (DatabaseException e) {
        }
        
        /* just for know the totally count */
        System.out.println("Totally count: "+universities.size());
    }
    
    /**
     * Static void method that show the Articles written between the years 2001 and 2002
     */
    public static void getArticlesBetween2001_2002(){
        System.out.println("Search Started");
        
        /* Create the EntityCursor object and get the entities*/
        EntityCursor cursor = SetupEnvironment.articlePrimaryIndex.entities();
        
        /* Initialize the count in 0 */
        int count = 0;
        
        /* Sweeping all objects in the cursor */
        for (Object o : cursor) {
            /* Cast the object to an Article */
            Article a = (Article) o;
            
            /* With the reference to an Edition in the Article Object
               and the DAO object of Edition get the respective edition */
            Edition e = EditionDAO.getEdition(a.getRef_Edition());
            
            /* Ask if the Edition isn't null */
            if (e!=null) {
                /* Ask if the year is 2001 or 2002 */
                if (e.getYear()==2001 || e.getYear()==2002) {
                    /* if comply the conditions add 1 to the count */
                    count++;
                }
            }
        }
        
        /* Close the cursor
           [Note] If the cursor doesn't close, will generate problems
           with the query's Thread because the Database access will close
           but the cursor is still running getting the info */
        cursor.close();
        
        /* Print the finally count */
        System.out.println("Totally count: "+count);
        
        /* Finally close the store and clean and close the environment object */
        try {            
            SetupEnvironment.store.close();
            SetupEnvironment.env.cleanLog();
            SetupEnvironment.env.close();
        } catch (DatabaseException e) {            
        }
    }
}
