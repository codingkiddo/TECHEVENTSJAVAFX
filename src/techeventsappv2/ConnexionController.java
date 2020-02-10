/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techeventsappv2;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UtilisateurService;
import utils.connectionDB;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ConnexionController implements Initializable {
    
    @FXML 
    TextField email;
    @FXML
    TextField pwd;
    connectionDB c = connectionDB.getInstance();
    Connection con = c.getConnection();
     //methodes
              @FXML
    private void connexionAction(ActionEvent event) throws SQLException, IOException{
       UtilisateurService us = new UtilisateurService();
       Utilisateur rs = us.Connexion(email.getText(),pwd.getText());
                  if (rs == null) {
                      System.out.println("bara nayek password ghalet");
                  }else{
                      System.out.println(rs.getRoles() );

                      if (rs.getRoles().equals("participant")) {

//get user id
   PreparedStatement st = con.prepareStatement("select id from fos_user where email = ? and password = ?");             
   st.setString(1,email.getText());
   st.setString(2,pwd.getText());
   ResultSet rs1 = st.executeQuery();
   rs1.next();
   int id = rs1.getInt(1);
     //get user id
   FXMLLoader loader = new FXMLLoader();
   loader.setLocation(getClass().getResource("ListeEvent.fxml"));
   Parent home_page_parent = loader.load();
   Scene home_page_scene = new Scene(home_page_parent);
   ListeEventController Controller = loader.getController();
   Controller.getUserData(id);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
                      }
                      else if (rs.getRoles().equals("club")){
    Parent home_page_parent = FXMLLoader.load(getClass().getResource("ClubListeEvent.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
                      }
         else if (rs.getRoles().equals("sponsor") ) {
    Parent home_page_parent = FXMLLoader.load(getClass().getResource("sponsoracceuil.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
                      }
                              else if (rs.getRoles().equals("admin") ) {
    Parent home_page_parent = FXMLLoader.load(getClass().getResource("adminacceuil.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
                      }
                      
                  }
    }

     //methodes
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoinscription(ActionEvent event) throws IOException {
        
        
         Parent home_page_parent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
    }
    
}
