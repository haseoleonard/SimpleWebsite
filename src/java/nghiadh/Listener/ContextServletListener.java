/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.Listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author haseo
 */
public class ContextServletListener implements ServletContextListener {
    private final String RESOURCE_MAP_PATH = "/WEB-INF/resourceMap.txt";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Locale locale = new Locale("EN", "US");
        try {
//        ResourceBundle resource = ResourceBundle.getBundle("nghiadh.resourcebundle/resource,locale);
            String resourcePath = context.getRealPath(RESOURCE_MAP_PATH);
            Map<String, String> map = getResourceMap(resourcePath);
            context.setAttribute("RESOURCE_MAP", map);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static Map<String, String> getResourceMap(String path) throws FileNotFoundException, IOException {
        Map<String,String> map = new HashMap<>();
        File file = new File(path);
        FileReader fr = null;
        BufferedReader br = null;
        try{
            if(file.exists()&&file.isFile()){
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                while(br.ready()){
                    String line = br.readLine().trim();
                    if(!line.isEmpty()){
                        map.put(line.split("=")[0], line.split("=")[1]);
                    }                    
                }
            }
        }finally{
            if(fr!=null)fr.close();
            if(br!=null)br.close();
        }
        return map;
    }
}
