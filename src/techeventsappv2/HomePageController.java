/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techeventsappv2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oussema
 */
public class HomePageController implements Initializable {

    @FXML
    private Button participant;
    @FXML
    private Button club;
    @FXML
    private Button sponsor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inscriparti(ActionEvent event) throws IOException {
          Parent home_page_parent = FXMLLoader.load(getClass().getResource("inscription.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
    }

    @FXML
    private void inscriclub(ActionEvent event) throws IOException {
           Parent home_page_parent = FXMLLoader.load(getClass().getResource("inscriptionclub.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
    }

    @FXML
    private void inscrispons(ActionEvent event) throws IOException {
           Parent home_page_parent = FXMLLoader.load(getClass().getResource("inscriptionsponsor.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
    }
    }
    

