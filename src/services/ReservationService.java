/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.connectionDB;


/**
 *
 * @author Yassine
 */
public class ReservationService {
    connectionDB c = connectionDB.getInstance();
    Connection con = c.getConnection();
    public ReservationService(){}
  
   //Ajout reservation
    public void AjouterReservation(Reservation r) throws SQLException{
         Timestamp ts = r.getDateReservation();
         int quantite = r.getQuantite();
         String type = r.getType();
         String seat = r.getSeat();
         int event_id = r.getIdevent();
         int user_id = r.getIduser();
         try{
         String requete = "insert into reservation(quantite,total,type,seat,event_id,user_id,nomReservation)values(?,?,?,?,?,?,?)";
         PreparedStatement st = con.prepareStatement(requete);
         String requete2 ="select prix,titre from event where id = ?";
         PreparedStatement st2 = con.prepareStatement(requete2);
         st2.setInt(1, event_id);
         ResultSet rsprix =st2.executeQuery();
         rsprix.next();
         double prixevent = rsprix.getInt(1);
         String nomReservation =rsprix.getString(2);
         st.setInt(1,quantite);
         st.setDouble(2,quantite*prixevent);
         st.setString(3,type);
         st.setString(4,seat);
         st.setInt(5,event_id);
         st.setInt(6,user_id);
         st.setString(7,nomReservation);
         st.execute();
         }catch(SQLException ex){
            System.out.println(ex.getMessage());
         }
    }
    
    //Affichage panier reservations 
        public ArrayList<Reservation> getAllReservations(int id){
       ArrayList<Reservation> reservations = new ArrayList();
             try {
           PreparedStatement st = con.prepareStatement("");
           ResultSet resultset = st.executeQuery("select * from reservation where user_id ="+id+" ");
           resultset.beforeFirst();
           while(resultset.next()){
           Reservation rs = new Reservation(resultset.getInt(1),resultset.getInt(2),resultset.getInt(3),resultset.getTimestamp(4),resultset.getInt(5),resultset.getDouble(6),resultset.getString(7),resultset.getString(8));
           reservations.add(rs);
           }
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }          
        return reservations;  
    }
    //Affichage panier reservations 
        
   //Modifier Reservation 
     public void modifierReservation(Reservation r) throws SQLException{
        int Qte = r.getQuantite();
        String type = r.getType();
        String seat = r.getSeat();
     String requete = "update reservation SET quantite="+Qte+",type='"+type+"',seat='"+seat+"' where id='"+r.getId()+"' ";
     PreparedStatement st = con.prepareStatement(requete);
        st.execute();
        System.out.println(r);
     }   
    //modifier Reservation   
    
     //Supprimer Reservation
    public void SupprimerReservation(Reservation r) throws SQLException{
     int id = r.getId();
     String requete = "delete from reservation where id='"+id+"' ";
     PreparedStatement st = con.prepareStatement(requete);
        st.execute();
        System.out.println("reservation supprimée !");
     }      
     //Supprimer Reservation 
    
    
    //Affichage Historique rservations 
             public ArrayList<Reservation> getAllPayedReservations(int id){
       ArrayList<Reservation> reservations = new ArrayList();
             try {
           //getAll reservations
           PreparedStatement st = con.prepareStatement("select * from reservation where user_id ="+id+" and event_id = ANY(SELECT id from event) and dateReservation>(SELECT dateevent from event)");
           ResultSet resultset = st.executeQuery();
           resultset.beforeFirst();
           while(resultset.next()){
           Reservation rs = new Reservation(resultset.getInt(1),resultset.getInt(2),resultset.getInt(3),resultset.getTimestamp(4),resultset.getInt(5),resultset.getDouble(6),resultset.getString(7),resultset.getString(8));
           rs.setNomReservation(resultset.getString(10));
           reservations.add(rs);
           }
           
           //getAll Reservations
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }          
        return reservations;  
    }
   
    //Affichage Historique rservations 
    
     //Rechercher Reservation par nom
   public ArrayList<Reservation> SearchPayedReservations(int id,String searchItem){
    ArrayList<Reservation> reservations = new ArrayList();
    try {
       PreparedStatement st = con.prepareStatement("");
       int payer = 1;
       ResultSet resultset = st.executeQuery("select * from reservation where user_id ="+id+" and payer="+payer+" and 	nomReservation LIKE '%"+searchItem+"%'  ");
       resultset.beforeFirst();
       while(resultset.next()){
       Reservation rs = new Reservation(resultset.getInt(1),resultset.getInt(2),resultset.getInt(3),resultset.getTimestamp(4),resultset.getInt(5),resultset.getDouble(6),resultset.getString(7),resultset.getString(8));
       rs.setNomReservation(resultset.getString(10));
       reservations.add(rs);
    }
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }          
        return reservations;  
    }     
     //Rechercher Reservation par nom        
   
 
 
 
}
