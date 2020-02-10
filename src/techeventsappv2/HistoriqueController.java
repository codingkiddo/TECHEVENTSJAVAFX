/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techeventsappv2;

import entities.Reservation;
import java.net.URL;

import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class HistoriqueController implements Initializable {
    @FXML private TableView<Reservation> paniertable;
    @FXML private TableColumn<Reservation,Timestamp> datereserervationcolumn ;
    @FXML private TableColumn<Reservation,Integer> quantitecolumn;
    @FXML private TableColumn<Reservation,String> typecolumn;
    @FXML private TableColumn<Reservation,String> seatcolumn;
    @FXML private TableColumn<Reservation,String> nomReservationcolumn;

    @FXML private TextField searchText;
    @FXML private TextField idusertxt;

    ReservationService  rs = new ReservationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        datereserervationcolumn.setCellValueFactory(new PropertyValueFactory<Reservation,Timestamp>("dateReservation"));
        quantitecolumn.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("quantite")); 
        typecolumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("type"));
        seatcolumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("seat"));
nomReservationcolumn.setCellValueFactory(new PropertyValueFactory<Reservation,String>("nomReservation"));
 System.out.println(idusertxt.getText());

    }   
    public void RechercherReservationParNomAction(ActionEvent event){
    paniertable.setItems(FXCollections.observableArrayList(rs.SearchPayedReservations(Integer.parseInt(idusertxt.getText()),searchText.getText())));            
    }
    //bch nekhou données d'utilisateur 
     public void getUserData(int iduser){
       idusertxt.setText(String.valueOf(iduser));
       //load data
         paniertable.setItems(FXCollections.observableArrayList(rs.getAllPayedReservations(iduser)));    
     }
   //bch nekhou données d'utilisateur 
    
}
