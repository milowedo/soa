package com.agh.soa;

import com.agh.soa.entity.Seat;
import com.agh.soa.interfaces.ISeatManagement;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

//Singletonowy komponent EJB ma zawierać
// metody obsługujące zarządzanie miejscami w teatrze.
@SuppressWarnings("ALL")
@Singleton
@Startup
public class SeatManagement implements ISeatManagement {

    private ArrayList<Seat> seatList;

    //Oprócz tego Singleton ma stworzyć liste miejsc z przypisanymi im cenami w momencie stworzenia
    //komponentu.
    @PostConstruct
    public void setupTheatre(){
        seatList = new ArrayList<>();
        int number = 0;
        for (int i = 0; i < 5; i++) {
            seatList.add(new Seat(++number, "Parter",50, false));
            if(i%2==0) seatList.add(new Seat(++number, "Balkon I",70,false));
            seatList.add(new Seat(++number, "Balkon II",30,false));
        }
    }

    //zwraca listę obiektów Seat które zostaną wykorzystane do wskazania użytkownikowi,
    // czy podane miejsce zostało zarezerwowane.
    @Lock(READ)
    public ArrayList<Seat> getSeatList() {
        return seatList;
    }

    //metoda pomocnicza, która zwraca cenę za miejsce jako typ int, co umożliwia
    //szybkie sprawdzenie, czy użytkownika stać na zakup wskazanego miejsca.
    @Lock(READ)
    public int getSeatPrice(int id) {
        return getSeatList().get(id).getPrice();
    }

    //odpowiada za zakup biletu i oznaczenie miejsca jako zarezerwowanego.
    @Lock(WRITE)
    public void buyTicket(int seatId) {
        Seat seat = getSeatList().get(seatId);
        seat.setBooked(true);
    }
}
