package com.beer.model;

public final class BeerExpert {
    private static String beerBrand;

    public static void setBeerBrand(String beerType) {
        switch (beerType){
            case "pale": beerBrand = "Barbakan"; break;
            case "dark": beerBrand = "Kozel"; break;
            case "other": beerBrand = "Namysłów"; break;
        }
    }

    public static String getBeerBrand() {
        return beerBrand;
    }
}
