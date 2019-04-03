package com.agh.soa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class Seat implements Serializable {
    private int number;
    private String type;
    private int price;
    private boolean isBooked;
}