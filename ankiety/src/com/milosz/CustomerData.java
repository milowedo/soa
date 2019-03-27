package com.milosz;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "CustomerData")
@Data
@SessionScoped
public class CustomerData {
    private String name = null;
    private String email = null;
    private Integer age = null;
    private String gender = null;
    private String education = null;
    private Integer height = null;

    private Integer bust = null;
    private String cup = null;
    private Integer waist = null;
    private Integer hips = null;
    private Integer chest = null;

    private Integer waist_m = null;
    private boolean wasSent = false;

    private String amountForClothes = null;
    private String howOften = null;
    private List<String> colour = new ArrayList<>();
    private List<String> typeOfClothing = new ArrayList<>();

    public void send() {
        this.wasSent = true;
    }

    public String finish() {
        return "podsumowanie";
    }
}
