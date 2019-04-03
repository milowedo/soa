package com.agh.soa.interfaces;

import java.util.ArrayList;

public interface ISeatAvailabilityChecker {
    public String showAvailabilityMessage(String type);
    public long showNumberOfAvailableByType(String type);
    public ArrayList<Integer> showAvailableSeatsOfType(String type);
}
