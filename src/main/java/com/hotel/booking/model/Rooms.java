package com.hotel.booking.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "rooms")
public class Rooms {
 
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
 
 @Column(name = "roomtype")
 private String roomtype;
 
 @Column(name = "availability")
 private Boolean availability; 

 @ManyToOne
 @JoinColumn
 private User user_id;
 

public User getUser_id() {
    return this.user_id;
}




public void setUser_id(User user_id) {
    this.user_id = user_id;
}



public String getRoomtype() {
    return this.roomtype;
}



public void setRoomtype(String roomtype) {
    this.roomtype = roomtype;
}



public Boolean getAvailability() {
    return this.availability;
}



public void setAvailability(Boolean availability) {
    this.availability = availability;
}



public void setId(int id) {
    this.id = id;
}


public int getId() {
  return id;
 }

 
}
