package com.hotel.booking.service;


import com.hotel.booking.model.User;

public interface UserService {
  
 public User findUserByEmail(String email);
 
 public void saveUser(User user);
}
