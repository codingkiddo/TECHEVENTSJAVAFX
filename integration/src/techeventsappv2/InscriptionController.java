/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techeventsappv2;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class InscriptionController implements Initializable {
       //Attributs
      @FXML
      TextField prenom;
            @FXML
      TextField nom;
                  @FXML
      TextField email;
                  @FXML
      TextField age;
                  @FXML
      TextField password;
                  @FXML
      TextField passwordconfirm;
    @FXML
    private Button btn;
 
    //Attributs 
 
    //methodes
          @FXML
    private void inscriptionAction(ActionEvent event) throws SQLException {
         String p = prenom.getText();
         String n = nom.getText();
         String e = email.getText();
         int a = Integer.parseInt(age.getText());
         String p1 = password.getText();
         String p2 = passwordconfirm.getText();
              if (p1.equals(p2)) {
                           Utilisateur u = new Utilisateur(p,n,a,e,p1);
         UtilisateurService us = new UtilisateurService();
         us.Signup(u);
              }else{
              System.out.println("mot de passes non correspondantes"+p1+" "+p2);
              }
    }  
    
        @FXML
    private void connexionAction(ActionEvent event) throws IOException {
   Parent home_page_parent = FXMLLoader.load(getClass().getResource("connexion.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
    }
    
    //methodes
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
