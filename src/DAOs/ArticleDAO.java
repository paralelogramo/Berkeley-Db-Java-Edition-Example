package DAOs;

import Entities.Article;
import com.sleepycat.persist.model.Entity;
import Main.SetupEnvironment;

/**
 *
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912 
 */
public class ArticleDAO {
    
    /**
     * Allows to add an Article to the database.
     * @param a an instance of an Article.
     * @see Article
     */
    public static void addArticle(Article a){
        SetupEnvironment.articlePrimaryIndex.put(a);
    }
    
    /**
     * Allows to retrieve an Article from the database.
     * @param id An Integer that represents the primary key of an Article that you want to get.
     * @see Article
     * @see Entity
     * @return An instance of Article with their fields.
     */
    public static Article getArticle(Integer id){
        return SetupEnvironment.articlePrimaryIndex.get(id);
    }
    
    /**
     * Allows to get an Article from the database.
     * @param id An Integer that represents the primary key of an Article that you want to delete.
     * @see Article
     */
    public static void delArticle(Integer id){
        SetupEnvironment.articlePrimaryIndex.delete(id);
    }
}
