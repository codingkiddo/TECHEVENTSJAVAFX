/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techeventsappv2;

import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class PanierController implements Initializable {

        //Affichage au tableau
    @FXML private TableView<Reservation> paniertable;
    @FXML private TableColumn<Reservation,Timestamp> datereserervationcolumn ;
    @FXML private TableColumn<Reservation,Integer> quantitecolumn;
    @FXML private TableColumn<Reservation,String> typecolumn;
    @FXML private TableColumn<Reservation,String> seatcolumn;
    
    @FXML private TextField txtQte;
    @FXML private TextField txtType;
    @FXML private TextField txtSeat;
    @FXML private TextField idusertxt;

    ReservationService  rs = new ReservationService();
    
    //Affichage au tableau
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datereserervationcolumn.setCellValueFactory(new PropertyValueFactory<Reservation,Timestamp>("dateReservation"));
        quantitecolumn.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("quantite")); 
        typecolumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("type"));
        seatcolumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("seat"));
        //load data
      //  paniertable.setItems(FXCollections.observableArrayList(rs.getAllReservations(1)));         
       //set invisible iduser textfield
      idusertxt.setVisible(false);

       //set invisible iduser textfield
    }  
    
  //get mouse event  
      @FXML
public void clickItem(MouseEvent event)
{
  Reservation r = paniertable.getSelectionModel().getSelectedItem(); 
  txtQte.setText(String.valueOf(r.getQuantite()));
  txtType.setText(r.getType());
  txtSeat.setText(r.getSeat());
}

//Modifier button clicked
 @FXML  private void ModifierButtonAction(ActionEvent event) throws IOException, SQLException {
    
   Reservation r = paniertable.getSelectionModel().getSelectedItem(); 
   r.setQuantite(Integer.parseInt(txtQte.getText()));
   r.setTypeReservation(txtType.getText());
   r.setSeat(txtSeat.getText());
   rs.modifierReservation(r);
   paniertable.setItems(FXCollections.observableArrayList(rs.getAllReservations(Integer.parseInt(idusertxt.getText()))));         
 }
 //Modifier button clicked
 
 //Suppression  button clicked
  @FXML  private void SupprimerButtonAction(ActionEvent event) throws IOException, SQLException {    
    Reservation r = paniertable.getSelectionModel().getSelectedItem(); 
    rs.SupprimerReservation(r);
    paniertable.setItems(FXCollections.observableArrayList(rs.getAllReservations(Integer.parseInt(idusertxt.getText()))));         
 }
  //bouton l'historique
 @FXML private void HistoriqueButtonClicked(ActionEvent event) throws IOException{
   FXMLLoader loader = new FXMLLoader();
   loader.setLocation(getClass().getResource("historique.fxml"));
   Parent home_page_parent = loader.load();
   Scene home_page_scene = new Scene(home_page_parent);
   HistoriqueController Controller = loader.getController();
   Controller.getUserData(Integer.parseInt(idusertxt.getText()));
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
 } 
   //bch nekhou données d'utilisateur 
     public void PaniergetUserData(int id){
       idusertxt.setText(String.valueOf(id));
         paniertable.setItems(FXCollections.observableArrayList(rs.getAllReservations(id)));
     }
   //bch nekhou données d'utilisateur 
  
}
