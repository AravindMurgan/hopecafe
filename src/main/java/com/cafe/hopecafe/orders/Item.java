package com.cafe.hopecafe.orders;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class Item {
    private final SimpleStringProperty itemName;
    private final SimpleStringProperty itemPrice;

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    private CheckBox checkBox;

    Item(String itemname, String itemprice,String checkbox) {
        this.itemName = new SimpleStringProperty(itemname);
        this.itemPrice = new SimpleStringProperty(itemprice);
        this.checkBox = new CheckBox();
        this.checkBox.setSelected(false);
        this.checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // You can perform any action here when checkbox state changes
            if (newValue) {
                System.out.println("Checkbox selected for: " + this.getItemName() + " " + this.getItemPrice());
            } else {
                System.out.println("Checkbox deselected for: " + this.getItemName() + " " + this.getItemPrice());
            }
        });

    }



    public String getItemName() {
        return itemName.get();
    }


    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getItemPrice() {
        return itemPrice.get();
    }


    public void setItemPrice(String itemPrice) {
        this.itemPrice.set(itemPrice);
    }







}
