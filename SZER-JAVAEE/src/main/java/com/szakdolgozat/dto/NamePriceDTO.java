package com.szakdolgozat.dto;

/**
 * Created by Ákos on 2016.02.28..
 */
public class NamePriceDTO {

    private String name;
    private Integer price;

    public NamePriceDTO(){

    }

    public NamePriceDTO(String name, Integer price){
        this.name=name;
        this.price=price;
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
}
