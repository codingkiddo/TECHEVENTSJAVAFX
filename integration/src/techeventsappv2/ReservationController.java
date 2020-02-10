/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techeventsappv2;

import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ReservationService;
import utils.connectionDB;
import com.itextpdf.io.image.ImageData; 
import com.itextpdf.io.image.ImageDataFactory; 
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Image;  
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ReservationController implements Initializable {
    
    @FXML 
    TextField type;
    @FXML 
    TextField nbrplaces;
    @FXML
    TextField seat;
    @FXML 
    Text totalText;
    @FXML 
    Text nbptext;
    @FXML 
    Text dispo;
    @FXML 
    TextField idusertxt;
        @FXML 
    TextField ideventtxt;
    @FXML 
    Button ticketBtn;
    connectionDB c = connectionDB.getInstance();
    Connection con = c.getConnection();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ticketBtn.setVisible(false);  
    nbrplaces.textProperty().addListener(new ChangeListener<String>() {
    public void changed(ObservableValue<? extends String> observable,
            String oldValue, String newValue) {
      //requete pour avoir le prix
      try{
         String requete2 ="select prix from event where id = "+Integer.parseInt(ideventtxt.getText())+"  ";
         PreparedStatement st2 = con.prepareStatement(requete2);
         ResultSet rsprix =st2.executeQuery();
         rsprix.next();
         double prixevent = rsprix.getInt(1);
         int prixevent2 = (int) prixevent;
         int res = prixevent2*Integer.parseInt(newValue);
          totalText.setText("PRIX TOTAL : "+res+" TND");
    
      }catch(SQLException ex){
        System.out.println(ex.getMessage());
      }
      //requete bch nekhou le prix
    }


       });
       //set invisible iduser textfield
       idusertxt.setVisible(false);
       ideventtxt.setVisible(false);
       //set invisible iduser textfield 
       
    }  
    
    @FXML 
    private void ReservationAction(ActionEvent event) throws SQLException {
     //get value inputs
       String t = type.getText();
       int nbp = Integer.parseInt(nbrplaces.getText());
       String s =  seat.getText();
       PreparedStatement st ;
      //get value inputs
       
      //test Sur Seat A
      if (s.equals("A")) {
     int nbrPlacesAoccupper ;   
     String requeteA = "select sum(quantite) from reservation where event_id = ? and seat = ?";
     st = con.prepareStatement(requeteA);
     st.setInt(1,Integer.parseInt(ideventtxt.getText()));
     st.setString(2,"A");
     ResultSet rsA = st.executeQuery();
     rsA.next();
     nbrPlacesAoccupper = rsA.getInt(1);
          if (nbrPlacesAoccupper == 10) {
            nbptext.setText("Toutes les places A sont déja reservés !");
            }
          else{ 
           int nbpAdispo = 10 - nbrPlacesAoccupper ;
              if(nbp > nbpAdispo){
           nbptext.setText("Il n'ya que !"+nbpAdispo+" place disponibles !");
           nbrplaces.setText(String.valueOf(nbpAdispo));
              }else{
       Reservation r = new Reservation(type.getText(),s,nbp,Integer.parseInt(idusertxt.getText()),Integer.parseInt(ideventtxt.getText()));
       r.setIduser(Integer.parseInt(idusertxt.getText()));
       ReservationService rs = new ReservationService();
       rs.AjouterReservation(r);  
          //redirection vers payement ou ticket ! 
         String requete2 ="select prix from event where id = ?";
         PreparedStatement st2 = con.prepareStatement(requete2);
         st2.setInt(1,Integer.parseInt(ideventtxt.getText()));
         ResultSet rsprix =st2.executeQuery();
         rsprix.next();
         double prixevent = rsprix.getInt(1);
                  if (prixevent == 0) {

                     ticketBtn.setVisible(true);
           
                  }else{
                  
                  }
          //redirection vers payement ou ticket 
              }
           }
        }
     //test Sur Seat A
        
    //test Sur Seat B
        if (s.equals("B")) {
     int nbrPlacesAoccupper ;   
     String requeteA = "select sum(quantite) from reservation where event_id = ? and seat = ?";
     st = con.prepareStatement(requeteA);
     st.setInt(1,1);
     st.setString(2,"B");
     ResultSet rsA = st.executeQuery();
     rsA.next();
     nbrPlacesAoccupper = rsA.getInt(1);
          if (nbrPlacesAoccupper == 10) {
            nbptext.setText("Toutes les places B sont déja reservés !");
            }
          else{ 
           int nbpAdispo = 10 - nbrPlacesAoccupper ;
              if(nbp > nbpAdispo){
           nbptext.setText("Il n'ya que !"+nbpAdispo+" place disponibles !");
           nbrplaces.setText(String.valueOf(nbpAdispo));
              }else{
       Reservation r = new Reservation(type.getText(),s,nbp,1,1);
       r.setIduser(Integer.parseInt(idusertxt.getText()));
       ReservationService rs = new ReservationService();
       rs.AjouterReservation(r);  
       System.out.println("reservation ajouter");
              }
           }
        }
     //test Sur Seat B  
        
     //test Sur Seat C
        if (s.equals("C")) {
     int nbrPlacesAoccupper ;   
     String requeteA = "select sum(quantite) from reservation where event_id = ? and seat = ?";
     st = con.prepareStatement(requeteA);
     st.setInt(1,1);
     st.setString(2,"C");
     ResultSet rsA = st.executeQuery();
     rsA.next();
     nbrPlacesAoccupper = rsA.getInt(1);
          if (nbrPlacesAoccupper == 10) {
            nbptext.setText("Toutes les places C sont déja reservés !");
            }
          else{ 
           int nbpAdispo = 10 - nbrPlacesAoccupper ;
              if(nbp > nbpAdispo){
           nbptext.setText("Il n'ya que !"+nbpAdispo+" place disponibles !");
           nbrplaces.setText(String.valueOf(nbpAdispo));
              }else{
       Reservation r = new Reservation(type.getText(),s,nbp,1,1);
       r.setIduser(Integer.parseInt(idusertxt.getText()));
       ReservationService rs = new ReservationService();
       rs.AjouterReservation(r);  
       System.out.println("reservation ajouter");
              }
           }
        }
     //test Sur Seat C
                     

    }
    
    
    @FXML
   private void PanierBtnAction(ActionEvent event) throws SQLException, IOException {
   FXMLLoader loader = new FXMLLoader();
   loader.setLocation(getClass().getResource("panier.fxml"));
   Parent home_page_parent = loader.load();
   Scene home_page_scene = new Scene(home_page_parent);
   PanierController Controller = loader.getController();
   Controller.PaniergetUserData(Integer.parseInt(idusertxt.getText()));
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
   }
     
   //Rechercher dans l'historique
   
   //Affichage Salle
    @FXML
   private void afficherSalleBtnAction(ActionEvent event) throws SQLException, IOException {
   Parent home_page_parent = FXMLLoader.load(getClass().getResource("afficherSalle.fxml"));
   Scene home_page_scene = new Scene(home_page_parent);
   Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
   appStage.setScene(home_page_scene);
   appStage.show();
   }
   //Affichage Salle
   
   //bch nekhou données d'utilisateur 
     public void getUserData(int iduser,int idevent){
       idusertxt.setText(Integer.toString(iduser));
       ideventtxt.setText(Integer.toString(idevent));
     }
   //bch nekhou données d'utilisateur 
       //send mail
    public  void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
     @FXML
     public void telchagerTicketButtonAction(ActionEvent event) throws FileNotFoundException, MalformedURLException, SQLException{         
      String req1 = "select * from fos_user where id = ?";
      String req2 = "select * from reservation where user_id = ? and event_id = ? ";
      PreparedStatement st = con.prepareStatement(req1);
      PreparedStatement st1 = con.prepareStatement(req2);
      st.setInt(1,Integer.parseInt(idusertxt.getText()));
      st1.setInt(1,Integer.parseInt(idusertxt.getText()));
      //event id 
        st1.setInt(2,Integer.parseInt(ideventtxt.getText()));
      //event id 
      ResultSet rs1 =  st.executeQuery();
       ResultSet rs2 = st1.executeQuery();
       rs1.next();
       rs2.next();
      String dest = "C:\\generare_pdf\\ticket.pdf";       
      PdfWriter writer = new PdfWriter(dest);              
      PdfDocument pdf = new PdfDocument(writer);                  
      Document document = new Document(pdf);                   
      String imFile = "C:\\Users\\Yassine\\Documents\\NetBeansProjects\\techeventsappv2\\src\\images\\TecheventPNG.png";       
      ImageData data = ImageDataFactory.create(imFile);                    
      Image image = new Image(data);                        
      document.add(image);              
      String para1 = "nom :"+rs1.getString(14)+" Prenom "+rs1.getString(15)+" ID Reservation :"+rs2.getInt(1)+" event : "+rs2.getString(10)+" ";
     Paragraph paragraph1 = new Paragraph(para1);             
     document.add(paragraph1);
      // Closing the document       
      document.close();              
      
      System.out.println("Image added"); 
            // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "yassine.bengharsallah@esprit.tn";
        String password = "ommihafsia008";
 
        // message info
        String mailTo = "yassine.bengharsallah@esprit.tn";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";
 
        // attachments
        String[] attachFiles = new String[3];
        attachFiles[0] = "C:\\generare_pdf\\addimage.pdf";
        attachFiles[1] = "C:\\generare_pdf\\ticket.pdf";
        attachFiles[2] = "C:\\generare_pdf\\chillyfacts_test.pdf";
 
        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
   
     }
   
}
