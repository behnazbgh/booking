package com.hotel.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.booking.model.Rooms;

@Repository("roomsRepository")
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {

 Rooms findById(int Id);
 
 /** Check how many available room  
 * @param availability
 * @return
 */
int countByAvailability(boolean availability);
}
