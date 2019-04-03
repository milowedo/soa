package com.agh.soa.interfaces;

import com.agh.soa.entity.Seat;
import com.agh.soa.exceptions.NotEnoughMoneyException;
import com.agh.soa.exceptions.SeatTakenException;

import java.util.List;

public interface IPaymentService {
    public void createCustomer();
    public String bookSeat(int seatId) throws SeatTakenException, NotEnoughMoneyException;
    public List<Seat> getBoughtSeats();
    public int getAccountBalance();
}