package com.szakdolgozat.dto;

/**
 * Created by Ákos on 2016.02.28..
 */
public class NamePriceDTO {

    private String name;
    private Integer price;
    private int loyalty;

    public NamePriceDTO(){

    }

    public NamePriceDTO(String name, Integer price, int loyalty){
        this.name=name;
        this.price=price;
        this.loyalty=loyalty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }
}
