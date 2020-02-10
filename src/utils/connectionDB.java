/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Yassine
 */
public class connectionDB {
     Connection cn;
    static connectionDB instance;
    String login = "root";
    String url="jdbc:mysql://localhost:3306/techevent";
    String mdp="";
    private connectionDB(){
      // public -> private   variable static de nom de class 3/ methode getInstance
        try{
           cn = DriverManager.getConnection(url,login,mdp);
          System.out.println("connexion etablie");
        }catch(SQLException ex){
           System.out.println("connexion echec");
        }
    
    }
    public Connection getConnection(){
      return cn;
    }
    public static connectionDB getInstance(){
      if(instance == null){
          instance =  new connectionDB();
      }
                return instance;
    }
    
}
