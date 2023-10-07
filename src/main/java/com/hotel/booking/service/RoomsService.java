package com.hotel.booking.service;


import java.util.List;

import com.hotel.booking.model.Rooms;
import com.hotel.booking.model.User;

public interface RoomsService {
 
 public void updateRooms(Rooms rooms, User user_id);

Rooms getOneRoom(int id);

List<Rooms> findAllRooms();

long getCount();

int availablityCount();
}
