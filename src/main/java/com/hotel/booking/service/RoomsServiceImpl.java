package com.hotel.booking.service;


import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotel.booking.model.Rooms;
import com.hotel.booking.model.User;
import com.hotel.booking.repository.RoomsRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service("roomsService")
public class RoomsServiceImpl implements RoomsService {
 
 @Autowired
 private RoomsRepository roomsRepository;
 

 @Override
 public Rooms getOneRoom(int id) {
  return roomsRepository.getOne(id);
 }
 
 @Override
 public java.util.List<Rooms> findAllRooms(){
     return roomsRepository.findAll();
 }
 
 @Override
 public long getCount() {
     return roomsRepository.count();
 }

 @Override
 public void updateRooms(Rooms rooms, User user_id) {
  rooms.setAvailability(rooms.getAvailability());
  rooms.setUser_id(user_id);
  roomsRepository.save(rooms);
 }

 @Override
 public int availablityCount() {
     return roomsRepository.countByAvailability(false);
 }



}