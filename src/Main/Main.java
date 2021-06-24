/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DAOs.ArticleDAO;
import DAOs.AuthorDAO;
import DAOs.ConferenceDAO;
import DAOs.EditionDAO;
import DAOs.ResearcherDAO;
import Entities.Article;
import Entities.Author;
import Entities.Conference;
import Entities.Edition;
import Entities.Researcher;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* Ask if the directory called Database exist */
        if (new File("Database").exists()) {
            /* If exists do nothing*/
        }
        else{
            /* I doesn't exists create the databases */
            uploadResearcherDB();
            uploadConferenceDB();
            uploadEditionDB();
            uploadArticleDB();
            uploadAuthorDB();
        }
                
        /* Show the examples queries and ask to the user what info want to get */
        System.out.println("Queries");
        System.out.println("");
        System.out.println("1.- Get the titles of the articles written by Janice Hornedo Vallecillos.");
        System.out.println("2.- Get the count of articles of every university.");
        System.out.println("3.- Get the count of published articles between 2001 and 2002.");
        System.out.print("-> ");
        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
        
        /* Switch with the choicen process */
        switch (op) {
            case 1:
                System.out.println("");
                SetupEnvironment.getArticlesJanice();
                break;
            case 2:
                System.out.println("");
                SetupEnvironment.getCountArticulesByUniversity();
                break;
            case 3:
                System.out.println("");
                SetupEnvironment.getArticlesBetween2001_2002();
                break;
        }
    }
    
    /**
     * Take the file researcher and upload the data to the database
     */
    public static void uploadResearcherDB(){
        try {
            /* Standar file reader */
            File file = new File("researcher.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Researcher i;
            while ((line = br.readLine()) != null) {
                /* Split the line with ';' */
                String[] args = line.split(";");
                /* Create a new researcher */
                i = new Researcher(Integer.valueOf(args[0]), args[1], args[2], args[3], args[4]);
                /* With the DAO Object of research add to the database the new researcher */
                ResearcherDAO.addResearcher(i);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ex) {            
        }
    }
    
    /**
     * Take the file conference and upload the data to the database
     */
    public static void uploadConferenceDB(){
        try {
            /* Standar file reader */
            File file = new File("conference.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Conference c;
            while ((line = br.readLine()) != null) {
                /* Split the line with ';' */
                String[] args = line.split(";");
                /* Create a new conference */
                c = new Conference(Integer.valueOf(args[0]), args[1]);
                /* With the DAO Object of conference add to the database the new conference */
                ConferenceDAO.addConference(c);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ex) {            
        }
    }
    
    /**
     * Take the file edition and upload the data to the database
     */
    public static void uploadEditionDB(){
        try {
            /* Standar file reader */
            File file = new File("edition.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Edition e;
            while ((line = br.readLine()) != null) {
                /* Split the line with ';' */
                String[] args = line.split(";");
                /* Create a new edition */
                e = new Edition(Integer.valueOf(args[0]), Integer.valueOf(args[2]), args[3], args[4], Integer.valueOf(args[1]));
                /* With the DAO Object of edition add to the database the new edition */
                EditionDAO.addEdition(e);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ex) {
        }
    }
    
    /**
     * Take the file article and upload the data to the database
     */
    public static void uploadArticleDB(){
        try {
            /* Standar file reader */
            File file = new File("article.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Article a;
            while ((line = br.readLine()) != null) {
                /* Split the line with ';' */
                String[] args = line.split(";");
                /* Create a new article */
                a = new Article(Integer.valueOf(args[0]), args[1], Integer.valueOf(args[2]));
                /* With the DAO Object of article add to the database the new article */
                ArticleDAO.addArticle(a);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ex) {            
        }
    }
    
    /**
     * Take the file author and upload the data to the database
     */
    public static void uploadAuthorDB(){
        try {
            /* Standar file reader */
            File file = new File("author.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Author a;
            int i = 0;
            while ((line = br.readLine()) != null) {
                /* Split the line with ';' */
                String[] args = line.split(";");
                /* Create a new author */
                a = new Author(i, Integer.valueOf(args[0]), Integer.valueOf(args[1]));
                /* With the DAO Object of author add to the database the new author */
                AuthorDAO.addAutor(a);
                /* As the id isn't come in the file needs to
                   create one and add 1 with any iteration */
                i++;
            }
        } catch (FileNotFoundException e) {
        } catch (IOException ex) {            
        }
    }
}
