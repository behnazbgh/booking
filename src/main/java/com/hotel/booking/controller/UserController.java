package com.hotel.booking.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.booking.model.Rooms;
import com.hotel.booking.model.User;
import com.hotel.booking.service.RoomsService;
import com.hotel.booking.service.RoomsServiceImpl;
import com.hotel.booking.service.UserService;

@Controller
public class UserController {

 @Autowired
 private UserService userService;
 
 @Autowired
 private RoomsService roomsService;
 
 @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView model = new ModelAndView();
  
  model.setViewName("user/login");
  return model;
 }
 
 
 /**
 * @return
 * show sign up
 */
@RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
 public ModelAndView signup() {
  ModelAndView model = new ModelAndView();
  User user = new User();
  model.addObject("user", user);
  model.setViewName("user/signup");
  
  return model;
 }
 
 /** Register new user
 * @param user
 * @param bindingResult
 * @return
 */
@RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
 public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
  ModelAndView model = new ModelAndView();
  User userExists = userService.findUserByEmail(user.getEmail());
  
  if(userExists != null) {
   bindingResult.rejectValue("email", "error.user", "This email already exists!");
  }
  if(bindingResult.hasErrors()) {
   model.setViewName("user/signup");
  } else {
   userService.saveUser(user);
   model.addObject("msg", "User has been registered successfully!");
   model.addObject("user", new User());
   model.setViewName("user/signup");
  }
  
  return model;
 }
 
 /** Show Home page
 * @return
 */
@RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
 public ModelAndView home() {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByEmail(auth.getName());
  List<Rooms> allRooms = roomsService.findAllRooms();
  long roomCount = roomsService.getCount();
  int availCount = roomsService.availablityCount();
  model.addObject("Rooms", allRooms);
  model.addObject("roomCount",roomCount);
  model.addObject("availCount",availCount);
  model.addObject("User", user);
  
  
  model.addObject("userName", user.getFirstname() + " " + user.getLastname());
  model.setViewName("home/home");
  return model;
 }
 
 /** When User Clicks at Book
  * the total available room number will decrease 
 * @param rooms
 * @return
 */
@RequestMapping(value= {"/home/book"}, method=RequestMethod.POST)
 public ModelAndView book(Rooms rooms) {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByEmail(auth.getName());
  List<Rooms> allRooms = roomsService.findAllRooms();
  long roomCount = roomsService.getCount();
  int availCount = roomsService.availablityCount();
  model.addObject("Rooms", allRooms);
  model.addObject("roomCount",roomCount);
  model.addObject("availCount",availCount);
  
  
  if(!rooms.getAvailability()) {
      rooms.setAvailability(true);
      roomsService.updateRooms(rooms, user);
      model.addObject("You have successfully booked the room");
  }
  else {
      model.addObject("The room seems to be already booked .. Apologies .. Refresh and book another room");
  }
  
  
 
  //model.setViewName("home/home");
  return new ModelAndView("redirect:/home/home");
 }
 
 /** When User Chekout 
  * the total number of avaible room will update
  * and increase
 * @param rooms
 * @return
 */
@RequestMapping(value= {"/home/checkout"}, method=RequestMethod.POST)
 public ModelAndView checkout(Rooms rooms) {
  ModelAndView model = new ModelAndView();
  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  User user = userService.findUserByEmail(auth.getName());
  
  List<Rooms> allRooms = roomsService.findAllRooms();
  long roomCount = roomsService.getCount();
  int availCount = roomsService.availablityCount();
  model.addObject("Rooms", allRooms);
  model.addObject("roomCount",roomCount);
  model.addObject("availCount",availCount);
  
  
  
  if(!rooms.getAvailability()) {
    
      model.addObject("The room seems to be not booked previously .. Apologies .. Refresh and try again");
      
  }
  else {
      Rooms exsistingRoom = new Rooms();
      exsistingRoom = roomsService.getOneRoom(rooms.getId()) ;
      
      
      if(exsistingRoom.getUser_id() == user) {
     
   
      rooms.setAvailability(false);
      roomsService.updateRooms(rooms, user);
      
      model.addObject("You have successfully checkedout the room");
   
      }
     else {
         model.addObject("The room doesn't seem is booked by you.. You cannot cancel others bookings");
     }
  }
  //model.setViewName("home/home");
  return new ModelAndView("redirect:/home/home");
 }
 
 
 
 
 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
 public ModelAndView accessDenied() {
  ModelAndView model = new ModelAndView();
  model.setViewName("errors/access_denied");
  return model;
 }
}
