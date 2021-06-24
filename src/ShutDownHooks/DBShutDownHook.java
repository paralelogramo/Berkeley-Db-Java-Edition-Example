/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShutDownHooks;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.persist.EntityStore;

/**
 * @version 0.1
 * @author Vicente Rojas Aranda - DC: PharaJP#1912 
 */
public class DBShutDownHook extends Thread{
    
    
    private Environment env;
    
    private EntityStore store;

    public DBShutDownHook(Environment env, EntityStore store) {
        this.env = env;
        this.store = store;
    }
        
    @Override
    public void run(){
        try {
            if (env != null) {
                store.sync();
                env.cleanLog();
                store.close();
            env.close();
            }
        } catch (DatabaseException e) {
        }
    }
}
