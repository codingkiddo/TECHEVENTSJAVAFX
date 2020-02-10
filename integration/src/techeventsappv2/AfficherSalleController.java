/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techeventsappv2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.connectionDB;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class AfficherSalleController implements Initializable {
     
    @FXML private ImageView seat1;
    @FXML private ImageView seat2;
    @FXML private ImageView seat3;
    @FXML private ImageView seat4;
    @FXML private ImageView seat5;
    @FXML private ImageView seat6;
    @FXML private ImageView seat7;
    @FXML private ImageView seat8;
    @FXML private ImageView seat9;
    @FXML private ImageView seat10;
    @FXML private ImageView seat11;
    @FXML private ImageView seat12;
    @FXML private ImageView seat13;
    @FXML private ImageView seat14;
    @FXML private ImageView seat15;
    @FXML private ImageView seat16;
    @FXML private ImageView seat17;
    @FXML private ImageView seat18;
    @FXML private ImageView seat19;
    @FXML private ImageView seat20;
    @FXML private ImageView seat21;
    @FXML private ImageView seat22;
    @FXML private ImageView seat23;
    @FXML private ImageView seat24;
    @FXML private ImageView seat25;
    public ArrayList<ImageView> ListImages = new ArrayList<>();
    connectionDB c = connectionDB.getInstance();
    Connection con = c.getConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListImages.add(seat1);
        ListImages.add(seat2);
        ListImages.add(seat3);
        ListImages.add(seat4);
        ListImages.add(seat5);
        ListImages.add(seat6);
        ListImages.add(seat7);
        ListImages.add(seat8);
        ListImages.add(seat9);
        ListImages.add(seat10);
        ListImages.add(seat11);
        ListImages.add(seat12);
        ListImages.add(seat13);
        ListImages.add(seat14);
        ListImages.add(seat15);
        ListImages.add(seat16);
        ListImages.add(seat17);
        ListImages.add(seat18);
        ListImages.add(seat19);
        ListImages.add(seat20);
        ListImages.add(seat21);
        ListImages.add(seat22);
        ListImages.add(seat23);
        ListImages.add(seat24);
        ListImages.add(seat25);
      Image img = new Image(getClass().getResourceAsStream("/images/placeres.png"));
       try {
      PreparedStatement st = con.prepareStatement("select count(seat) from reservation where seat = ?");       
      st.setString(1,"A");
      ResultSet rs = st.executeQuery();
      rs.next();
      int nbrPlacesReservedA = rs.getInt(1);
           for (int i = 1; i <=nbrPlacesReservedA ; i++) {
            ListImages.get(i).setImage(img);
           }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    //Retour lel reservation
       //Affichage Salle
    @FXML
   private void RetourBtnAction(ActionEvent event) throws SQLException, IOException {
   Parent home_page_parent = FXMLLoader.load(getClass().getResource("reservation.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
   }
   //Affichage Salle
    //Retour lel reservation
    
}
