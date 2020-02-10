/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Timestamp;

/**
 *
 * @author Yassine
 */
public class Reservation {
      int id;
      int iduser;
      int idevent;
      Timestamp dateReservation;
      int quantite;
      double total;
      String type;
      String seat;
      String nomReservation;

      public Reservation(){}
   //constructor ajout reservation
    public Reservation(String type, String seat, int quantite, int iduser, int idevent) {
        this.type = type;
        this.seat = seat;
        this.quantite = quantite;
        this.iduser = iduser;
        this.idevent = idevent;
    }
   //constructor ajout reservation
  
    //constructor num 3
    public Reservation(int id, int iduser, int idevent, Timestamp dateReservation, int quantite, double total, String type, String seat) {
        this.id = id;
        this.iduser = iduser;
        this.idevent = idevent;
        this.dateReservation = dateReservation;
        this.quantite = quantite;
        this.total = total;
        this.type = type;
        this.seat = seat;
    }   
     //constructor num 3
    
    public Reservation(Timestamp dateReservation, double total, String type, String seat, int quantite, int iduser, int idevent) {
        this.dateReservation = dateReservation;
        this.total = total;
        this.type = type;
        this.seat = seat;
        this.quantite = quantite;
        this.iduser = iduser;
        this.idevent = idevent;
    }
   //constructors
    public int getId() {
        return id;   
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Timestamp dateReservation) {
        this.dateReservation = dateReservation;
    }

    public double gettotal() {
        return total;
    }

    public void setPrix_total(double total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setTypeReservation(String type) {
        this.type = type;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }


   
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNomReservation() {
        return nomReservation;
    }

    public void setNomReservation(String nomReservation) {
        this.nomReservation = nomReservation;
    }
    

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", dateReservation=" + dateReservation + ", total=" + total + ", type=" + type + ", seat=" + seat + ", quantite=" + quantite + ", iduser=" + iduser + ", idevent=" + idevent + '}';
    }
   
    
   
}
