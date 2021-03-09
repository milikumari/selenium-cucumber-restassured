package com.acceptance.tests.web.testDataModel;

public class BasketItem {

    // all data typ are set as stringa s selenium and cucumber all return them as string
    //
    String quantity;
    String name;
    String size;
    String price;
    String colour;

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        Float total=null;
        try {
             total =   Integer.parseInt(quantity) * Float.parseFloat(price);
             total= (float) Math.round(total * 100) / 100;

        } catch (Exception e) {
       System.err.println(e.getMessage());
        }
        return String.valueOf(total);

    }
}
