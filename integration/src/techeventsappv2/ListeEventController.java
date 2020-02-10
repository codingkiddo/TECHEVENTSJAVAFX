/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techeventsappv2;

import entities.Event;
import entities.Interesse;
import services.ServiceEvent;
import services.ServiceInteresse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ahmed Abdessamed
 */
public class ListeEventController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();
    ObservableList filteredData = FXCollections.observableArrayList();
    
    @FXML
    private TableColumn<?, ?> titreT;
    @FXML
    private TableColumn<?, ?> localisationT;
    @FXML
    private TableColumn<?, ?> hdebutT;
    @FXML
    private TableColumn<?, ?> hfinT;
    @FXML
    private TableView<?> tableT;
    @FXML
    private TableColumn<?, ?> typeT;
    @FXML
    private ChoiceBox<String> item;
    @FXML
    private TextField recherche;
    @FXML
    private Button Brecheche;
    @FXML
    private TableColumn<?, ?> prixT;
    @FXML
    private DatePicker rechdate;
    @FXML
    private Button interesse;
    @FXML
    private TableColumn<?, ?> descT;
    @FXML 
    private TextField idusertxt;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         LoadData();
        item.getSelectionModel().selectLast();
               ServiceEvent   ms =  new ServiceEvent();
               ArrayList Liste = (ArrayList) ms.afficherTouTEvent();
               ObservableList obs =FXCollections.observableArrayList(Liste);
               tableT.setItems(obs);
                 titreT.setCellValueFactory(new PropertyValueFactory<>("titre"));
                 localisationT.setCellValueFactory(new PropertyValueFactory<>("localisation"));
                 
                  hdebutT.setCellValueFactory(new PropertyValueFactory<>("hdebut"));
                   hfinT.setCellValueFactory(new PropertyValueFactory<>("hfin"));
                    prixT.setCellValueFactory(new PropertyValueFactory<>("prix"));
                     typeT.setCellValueFactory(new PropertyValueFactory<>("type"));
                      descT.setCellValueFactory(new PropertyValueFactory<>("description"));
        // TODO
                      idusertxt.setVisible(false);
        
    }    
    

        private void LoadData(){ 
        list.removeAll();
        String a = "type";
        String b = "titre";
        String c = "prix";
        String d= "tous";
        list.addAll(a,b,c,d);
        item.getItems().addAll(list);
        }


    @FXML
    private void rechecher(ActionEvent event) {
      if ( item.getSelectionModel().getSelectedItem() == "titre"){
                             ServiceEvent   ms =  new ServiceEvent();
               ArrayList filteredData = (ArrayList) ms.afficherByTitre(recherche.getText());
               ObservableList obs =FXCollections.observableArrayList(filteredData);
               tableT.setItems(obs);
                 titreT.setCellValueFactory(new PropertyValueFactory<>("titre"));
                 localisationT.setCellValueFactory(new PropertyValueFactory<>("localisation"));
                  hdebutT.setCellValueFactory(new PropertyValueFactory<>("hdebut"));
                   hfinT.setCellValueFactory(new PropertyValueFactory<>("hfin"));
                    prixT.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    descT.setCellValueFactory(new PropertyValueFactory<>("description"));
                
      }

      if ( item.getSelectionModel().getSelectedItem() == "type"){
                             ServiceEvent   ms =  new ServiceEvent();
               ArrayList filteredData = (ArrayList) ms.afficherByType(recherche.getText());
               ObservableList obs =FXCollections.observableArrayList(filteredData);
               tableT.setItems(obs);
                 titreT.setCellValueFactory(new PropertyValueFactory<>("titre"));
                 localisationT.setCellValueFactory(new PropertyValueFactory<>("localisation"));
                  hdebutT.setCellValueFactory(new PropertyValueFactory<>("hdebut"));
                   hfinT.setCellValueFactory(new PropertyValueFactory<>("hfin"));
                    prixT.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    descT.setCellValueFactory(new PropertyValueFactory<>("description"));
      }
      
      
      
      if ( item.getSelectionModel().getSelectedItem() == "prix"){
                             ServiceEvent   ms =  new ServiceEvent();
               ArrayList filteredData = (ArrayList) ms.afficherByPrix(recherche.getText());
               ObservableList obs =FXCollections.observableArrayList(filteredData);
               tableT.setItems(obs);
                 titreT.setCellValueFactory(new PropertyValueFactory<>("titre"));
                 localisationT.setCellValueFactory(new PropertyValueFactory<>("localisation"));
                  hdebutT.setCellValueFactory(new PropertyValueFactory<>("hdebut"));
                   hfinT.setCellValueFactory(new PropertyValueFactory<>("hfin"));
                    prixT.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    descT.setCellValueFactory(new PropertyValueFactory<>("description"));
      }
      
        if ( item.getSelectionModel().getSelectedItem() == "tous"){
          ServiceEvent   ms =  new ServiceEvent();
               ArrayList Liste = (ArrayList) ms.afficherTouTEvent();
               ObservableList obs =FXCollections.observableArrayList(Liste);
               tableT.setItems(obs);
                 titreT.setCellValueFactory(new PropertyValueFactory<>("titre"));
                 localisationT.setCellValueFactory(new PropertyValueFactory<>("localisation"));
                  hdebutT.setCellValueFactory(new PropertyValueFactory<>("hdebut"));
                   hfinT.setCellValueFactory(new PropertyValueFactory<>("hfin"));
                    prixT.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    descT.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        }
    }
/*
    private void SupprimerE(ActionEvent event) {
        
        ObservableList<Event> productSelected, allproducts;
         allproducts = (ObservableList<Event>) tableT.getItems();
         productSelected = (ObservableList<Event>) tableT.getSelectionModel().getSelectedItem();
         productSelected.removeAll(allproducts);
    }*/
   
    ServiceInteresse SI = new ServiceInteresse();
    @FXML
    private void interrese(ActionEvent event) throws IOException, SQLException {
           Event r = (Event)(tableT.getSelectionModel().getSelectedItem());
            Interesse I = new Interesse(1,r.getId());
            System.out.println(r.getId());
           JOptionPane.showMessageDialog(null, " l'event est ajouté à votre liste");

           SI.AjouterEInteresse(I);

    }

    @FXML
    private void rechdate(ActionEvent event) {
                           String a = rechdate.getValue().toString();
 ServiceEvent   ms =  new ServiceEvent();
               ArrayList Liste = (ArrayList) ms.afficherByDate(a);
               ObservableList obs =FXCollections.observableArrayList(Liste);
               tableT.setItems(obs);
                 titreT.setCellValueFactory(new PropertyValueFactory<>("titre"));
                 localisationT.setCellValueFactory(new PropertyValueFactory<>("localisation"));
                  hdebutT.setCellValueFactory(new PropertyValueFactory<>("hdebut"));
                   hfinT.setCellValueFactory(new PropertyValueFactory<>("hfin"));
                    prixT.setCellValueFactory(new PropertyValueFactory<>("prix"));
                    descT.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    @FXML
    private void listeEventInteresse(ActionEvent event)  throws IOException {
        
        Parent page = FXMLLoader.load(getClass().getResource("ListeInteresse.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
               stage.setTitle("Liste d'événements");
                stage.setScene(scene);
                stage.show();
    }
    
    //bch nekhou données d'utilisateur 
     public void getUserData(int id){
       idusertxt.setText(Integer.toString(id));
     }
   //bch nekhou données d'utilisateur 
    
   @FXML
   private void ReserverButtonAction(ActionEvent event) throws IOException{
   Event r = (Event)(tableT.getSelectionModel().getSelectedItem());
   FXMLLoader loader = new FXMLLoader();
   loader.setLocation(getClass().getResource("reservation.fxml"));
   Parent home_page_parent = loader.load();
   Scene home_page_scene = new Scene(home_page_parent);
   ReservationController Controller = loader.getController();
   Controller.getUserData(Integer.parseInt(idusertxt.getText()),r.getId());
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
   }
}
