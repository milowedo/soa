package com.agh.soa;

import com.agh.soa.entity.Seat;
import com.agh.soa.interfaces.ISeatAvailabilityChecker;
import com.agh.soa.interfaces.ISeatManagement;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;

import static javax.ejb.LockType.READ;

//bezstanowe ziarno sesyjne odpowiedzialne za informacje
// o dostępności poszczególnych miejsc w teatrze
@SuppressWarnings("ALL")
@Stateless
@Remote(ISeatAvailabilityChecker.class)
public class SeatAvailabilityChecker implements ISeatAvailabilityChecker {

    @EJB
    ISeatManagement seatManagement;

//    public String showAvailabilityMessage(String type){
//        return checkAvailabilityByType(type)? "Dostępne" : "Zajęte";
//    }

//    public boolean checkAvailabilityByType(String type){
//        ArrayList<Seat> seats = seatManagement.getSeatList();
//        for (Seat seat : seats) {
//            if (seat.getType().equals(type) && !(seat.isBooked()))
//                return true;
//        }
//        return false;
//    }
//    public long showNumberOfAvailableByType(String type){
//        return  getStreamOfAvailableAndOfTypeSeats(type)
//                .count();
//
//    }
//    public List<Seat> showAvailableSeatsOfType(String type){
//        return getStreamOfAvailableAndOfTypeSeats(type)
//                .collect(Collectors.toList());
//    }
//    public Stream<Seat> getStreamOfAvailableAndOfTypeSeats(String type){
//        return seatManagement.getSeatList()
//                .stream()
//                .filter(seat -> seat.getType().equals(type))
//                .filter(seat -> !(seat.isBooked()));
//    }

    public boolean checkAvailabilityByType(String type){
        ArrayList<Seat> seats = seatManagement.getSeatList();
        for (Seat seat : seats) {
            if (seat.getType().equals(type) && !seat.isBooked()){
                return true;
            }

        }
        return false;
    }
    public String showAvailabilityMessage(String type){
        boolean available = checkAvailabilityByType(type);
        if (available){
            return "Dostępne";
        }
        return "Zajęte";
    }
    public long showNumberOfAvailableByType(String type){
        int result = 0;
        ArrayList<Seat> seats = seatManagement.getSeatList();
        for (Seat seat : seats) {
            if (seat.getType().equals(type) && !seat.isBooked()){
                result++;
            }

        }
        return result;
    }
    public ArrayList<Integer> showAvailableSeatsOfType(String type){
        ArrayList<Integer> seatsIds = new ArrayList<>();
        ArrayList<Seat> seats = seatManagement.getSeatList();
        for (Seat seat : seats) {
            if (seat.getType().equals(type) && !seat.isBooked()){
                seatsIds.add(seat.getNumber());
            }

        }
        return seatsIds;
    }

    @Lock(READ)
    public int getSeatPriceByType(String type) {
        switch (type){
            case "Parter" : return 40;
            case "Balkon I": return 20;
            case "Balkon II": return 10;
        }
        return 0;
    }


}
