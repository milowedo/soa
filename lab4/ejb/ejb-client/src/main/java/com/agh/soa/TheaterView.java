package com.agh.soa;

import com.agh.soa.entity.Seat;
import com.agh.soa.exceptions.NotEnoughMoneyException;
import com.agh.soa.exceptions.SeatTakenException;
import com.agh.soa.interfaces.IPaymentService;
import com.agh.soa.interfaces.ISeatAvailabilityChecker;
import lombok.Data;
import lombok.Getter;
import javax.ejb.EJB;
import javax.el.MethodExpression;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
@Data
public class TheaterView implements Serializable {

    @EJB(lookup = "java:global/ejb-impl-1.0-SNAPSHOT/PaymentService")
    IPaymentService paymentService;

    @EJB(lookup = "java:global/ejb-impl-1.0-SNAPSHOT/SeatAvailabilityChecker")
    ISeatAvailabilityChecker seatAvailabilityChecker;

    private List<Seat> seats;
    private List<Seat> boughtSeats;
    private Seat selectedSeat;
    private String selectedType;
    private int seatNumber;

    public void setSelectedType(String selectedType) {
        System.out.println("selected: "+ selectedType);
        this.selectedType = selectedType;
    }

    public List<Seat> getBoughtSeats() {
        return paymentService.getBoughtSeats();
    }

    public long getSeatPriceByType() {
        if(selectedType == null) return 0;
        switch (selectedType){
            case "Parter" : return 50;
            case "Balkon I": return 70;
            case "Balkon II": return 30;
        }return 0;
    }

    public void bookSeat(){
        try {
            this.paymentService.bookSeat(this.seatNumber);
        } catch (SeatTakenException e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Sorry, this seat is already taken."));
        } catch (NotEnoughMoneyException e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "You do not have sufficient funds to buy this seat"));
        }
    }
}
