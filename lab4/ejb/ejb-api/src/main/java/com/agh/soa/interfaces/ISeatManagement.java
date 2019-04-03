package com.agh.soa.interfaces;

import com.agh.soa.entity.Seat;
import java.util.ArrayList;

public interface ISeatManagement {
    public void setupTheatre();
    public ArrayList<Seat> getSeatList();
    public int getSeatPrice(int id);
    public void buyTicket(int seatId);
}
