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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Oussema
 */
public class InscriptionsponsorController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private PasswordField mdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void redirectionverslogin(ActionEvent event) throws IOException {
        
           Parent home_page_parent = FXMLLoader.load(getClass().getResource("connexion.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
    }
    @FXML
    private void BoutonloginSponsor(ActionEvent event) throws IOException, SQLException {
     String  n = nom.getText();
     
     String e = email.getText();
     String a = adresse.getText();
   
     int t = Integer.parseInt(tel.getText());  
     String p = mdp.getText();
     Utilisateur u = new Utilisateur();
     u.setNom(n);
     u.setEmail(e);
     u.setAdresse(a);
     u.setTel(t);
     u.setPassword(p);
     UtilisateurService us = new UtilisateurService();
     us.SignupSponsor(u);
     System.out.print("sponsor ajoutée");
    }
    
}
