package com.agh.soa;

import com.agh.soa.entity.Seat;
import com.agh.soa.exceptions.NotEnoughMoneyException;
import com.agh.soa.exceptions.SeatTakenException;
import com.agh.soa.interfaces.IPaymentService;
import com.agh.soa.interfaces.ISeatManagement;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.*;

//stanowe ziarno sesyjne działające jako pośrednik systemu płatności
// pozwalające na zakup biletu na określone miejsce
@SuppressWarnings("ALL")
@Stateful
@Remote(IPaymentService.class)
@Data
public class PaymentService implements IPaymentService {
    int money;

    private List<Seat> boughtSeats = new ArrayList<>();

    @EJB
    ISeatManagement seatManagement;

    @PostConstruct
    public void createCustomer() {
        this.money = 240;
    }

    public String bookSeat(int seatNumber) throws SeatTakenException,
            NotEnoughMoneyException {
        seatNumber--;
        Seat seat = seatManagement.getSeatList().get(seatNumber);
        if (seat == null){
            return "Nie ma takiego miejsca";
        }
        if (seat.isBooked()) {
            throw new SeatTakenException(
                    "To miejsce jest już zarezerwowane!");
        }
        if (seat.getPrice() > money) {
            throw new NotEnoughMoneyException(
                    "Nie masz wystarczających środków aby kupić ten bilet!");

        }
        seatManagement.buyTicket(seatNumber);
        money -= seat.getPrice();
        boughtSeats.add(seat);
        return "Bilet kupiony!.";
    }

    public int getAccountBalance(){
        return this.money;
    }
}
