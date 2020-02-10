/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techeventsappv2;

import entities.Event;
import services.ServiceEvent;/*
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;*/
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;


import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static javax.management.Query.value;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ahmed Abdessamed
 */
public class AjouterEventController implements Initializable {

    @FXML
    private TextField nbrplaces;
    @FXML
    private TextField localisation;
   
    @FXML
    private TextField hdebut;
    @FXML
    private TextField hfin;
    @FXML
    private TextField prix;
   
    @FXML
    private TextField titre;
   
   
    @FXML
    private TextField prixgold;
    @FXML
    private TextField prixsilver;
    @FXML
    private TextField prixdiamond;
    @FXML
    private TextField pdiamond;
    @FXML
    private TextField pglod;
    @FXML
    private TextField psilver;
    @FXML
    private DatePicker dateevent;
     
    @FXML
    private TextArea descri;
    @FXML
    private ChoiceBox<?> categoriechoise;
    @FXML
    private ChoiceBox<?> typechoise;
    
    public String UserSaveImagePath = "C:\\Users\\Ahmed Abdessamed\\Documents\\NetBeansProjects\\Tech_event_java\\src\\images\\";
    public String extention = ".png";
 ObservableList list0 = FXCollections.observableArrayList();
 ObservableList list1 = FXCollections.observableArrayList();
ServiceEvent SE = new ServiceEvent();
 
 
    @FXML
    private ImageView affiche;
    
   boolean inscrivalide = true ;
    @FXML
    private ImageView hfinimage;
    @FXML
    private ImageView hdebutimage;
    @FXML
    private ImageView nbrplace;
    @FXML
    private Button BListeEvent;
    @FXML
    private ImageView priximage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          LoadData0();
          LoadData1();
          typechoise.getSelectionModel().selectLast();
       categoriechoise.getSelectionModel().selectLast();
       
        hdebut.textProperty().addListener((observable, oldValue, newValue) -> {
    
        if(!(newValue.matches("[0-2]+[0-9]") && newValue.length()==2)){
         hdebutimage.setVisible(true);
         inscrivalide= false;
        }
         else if(newValue.length()==0){
            hdebutimage.setVisible(true);
            inscrivalide = false;
        }
        else{
         hdebutimage.setVisible(false);
         inscrivalide = true;
        }
        
        });
         hfin.textProperty().addListener((observable, oldValue, newValue) -> {
    
        if(!(newValue.matches("[0-2]+[0-9]") && newValue.length()==2)){
         hfinimage.setVisible(true);
         inscrivalide= false;
        }
         else if(newValue.length()==0){
            hfinimage.setVisible(true);
            inscrivalide = false;
        }
        else{
         hfinimage.setVisible(false);
         inscrivalide = true;
        }
        
        });
        
         prix.textProperty().addListener((observable, oldValue, newValue) -> {
    
        if(!(newValue.matches("[0-9]+") && newValue.length() < 6)){
         priximage.setVisible(true);
         inscrivalide= false;
        }
          else if(newValue.length()==0){
            priximage.setVisible(true);
            inscrivalide = false;
        }
        else{
         priximage.setVisible(false);
         inscrivalide = true;
        }
        /*
        int a = Integer.parseInt(hfin.getText());
         int b = Integer.parseInt(hdebut.getText());
         
         if ( a<b)
         {
          inscrivalide = false;
         hfinimage.setVisible(true);}
         else{
          hdebutimage.setVisible(false);
         inscrivalide = true;
         }
         */
        });   
         nbrplaces.textProperty().addListener((observable, oldValue, newValue) -> {
    
        if(!(newValue.matches("[0-9]+") && newValue.length()<6)){
         nbrplace.setVisible(true);
         inscrivalide= false;
        }
          else if(newValue.length()==0){
            nbrplace.setVisible(true);
            inscrivalide = false;
        }
          
        else{
         nbrplace.setVisible(false);
         inscrivalide = true;
        }
        
        
        
        });   
       
         /*
        titre.textProperty().addListener((observable, oldValue, newValue) -> {
            titre.setStyle("-fx-background-color: "+"white");
        });
       descri.textProperty().addListener((observable, oldValue, newValue) -> {
            descri.setStyle("-fx-background-color: "+"white");
        });
       localisation.textProperty().addListener((observable, oldValue, newValue) -> {
            localisation.setStyle("-fx-background-color: "+"white");
        });
       
       
        psilver.textProperty().addListener((observable, oldValue, newValue) -> {
            psilver.setStyle("-fx-background-color: "+"white");
        });
        
      */
        
        // TODO
     }  

    
    private void ClearAjout(){
    nbrplaces.clear();
    localisation.clear();
    prixdiamond.clear();
    hdebut.clear();
    hfin.clear();
    prix.clear();
    

    titre.clear();
    descri.clear();
    
    prixgold.clear();
    prixsilver.clear();
    prixdiamond.clear();
    pglod.clear();
    psilver.clear();
    pdiamond.clear();
    
    
    }
    private void LoadData0(){ 
        list0.removeAll();
        String a = "Competition";
        String b = "WorkShop";
        String c = "Event Privé";
        String d= "Marketing";
        list0.addAll(a,b,c,d);
        typechoise.getItems().addAll(list0);
        }   
     private void LoadData1(){ 
        list1.removeAll();
        String a = "DS";
        String b = "cloud";
        String c = "BI";
        String d= "GL";
        list1.addAll(a,b,c,d);
        categoriechoise.getItems().addAll(list1);
        }   
    
    @FXML
    private void AjouterEventtest(ActionEvent event) throws SQLException, IOException {
         String a = dateevent.getValue().toString();
         
         int s1 = Integer.parseInt(prixsilver.getText());
         int s2 = Integer.parseInt(prixgold.getText());
         int s3 = Integer.parseInt(prixdiamond.getText());
         Event o = new Event ();
         String a1 = titre.getText();
         String a2 = descri.getText();
       String a3;
            a3 =(UserSaveImagePath+titre.getText()+extention);
         String a4 = nbrplaces.getText();
         String a5 = localisation.getText();
         String a6 = dateevent.getValue().toString();
        // int a6 =Integer.parseInt(prix.getText());
         String a7 = (hdebut.getText()+"h");
         String a8 = (hfin.getText()+"h");
         
         int a9 =Integer.parseInt(prix.getText());
         //String a9 = dateevent.getValue().toString();
         String a10 = categoriechoise.getValue().toString();
         String a11 = typechoise.getValue().toString();
         String a12 = psilver.getText();
         String a13 = pglod.getText();
         String a14 = pdiamond.getText();
          int a15 = Integer.parseInt(prixsilver.getText());
         int a16 = Integer.parseInt(prixgold.getText());
         int a17 = Integer.parseInt(prixdiamond.getText()); 
    
       
        Event E = new Event (1,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16,a17);
    //  Event E= new Event(titre.getText(),description.getText(),affiche.getText(),nbrplaces.getText(),localisation.getText(),a,hdebut.getText(),hfin.getText(),p1,categorie.getText(),type.getText(),psilver.getText(),pglod.getText(),pdiamond.getText(),s1,s2,s3);
                 //System.out.println(titre.getText());

       
                 


// Event E = new Event (1,titre.getText(),localisation.getText(),hdebut.getText(),hfin.getText(),type.getText());
  /* if(titre.getText().isEmpty()){
            titre.setStyle("-fx-background-color: #"+"ff7d9f");
           
        }
        if(descri.getText().isEmpty()){
            descri.setStyle("-fx-background-color: #"+"ff7d9f");
           
        }
        if(localisation.getText().isEmpty()){
            localisation.setStyle("-fx-background-color: #"+"ff7d9f");
          
        }
        
        
        if(psilver.getText().isEmpty()){
           psilver.setStyle("-fx-background-color: #"+"ff7d9f");
           
        }*/







if (inscrivalide){
    SE.AjouterE(E);
    
     ClearAjout();
               JOptionPane.showMessageDialog(null, "Votre Event est Ajouté avec succés!");

      Parent page = FXMLLoader.load(getClass().getResource("ClubListeEvent.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
               stage.setTitle("Liste d'événements");
                stage.setScene(scene);
                stage.show();
    }

    }
    @FXML
    private void ListeEvent(ActionEvent event) throws IOException {
         
         Parent page = FXMLLoader.load(getClass().getResource("ClubListeEvent.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.hide();
               stage.setTitle("Liste d'événements");
                stage.setScene(scene);
                stage.show();
                
    }

 public void SaveImage(ImageView imageView, String imageName, String path, String extention) throws IOException {

        BufferedImage image = SwingFXUtils.fromFXImage(imageView.getImage(), null);
        File f;
        f = new File(path + imageName + extention);
        f = new File("C:\\Users\\Ahmed Abdessamed\\Documents\\NetBeansProjects\\Tech_event_java\\src\\images\\"+imageName+".png");
        ImageIO.write((RenderedImage) image, "png", f);
    }

    @FXML
    private void EventImagePickerAction(MouseEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            String imageUrl = selectedFile.toURI().toURL().toString();
            Image image = new Image(imageUrl);
            affiche.setImage(image);
    }
    
    
    }
}
