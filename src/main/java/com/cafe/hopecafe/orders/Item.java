package com.cafe.hopecafe.orders;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * This class creates items and menu.
 *
 * @author Aravind, Guanlin
 * @version 15/04/2024 22:14
 * @since JDK 17
 */

public class Item {
    private final SimpleStringProperty itemName;
    private final SimpleStringProperty itemPrice;

    /**
     * getter for checkBox
     * @return value of checkBox
     */


    public CheckBox getCheckBox() {
        return checkBox;
    }

    /**
     * setter for checkBox
     * @param checkBox pass checkBox constructor
     */


    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    private CheckBox checkBox;

    /**
     * This is the constructor for item class pass in values.
     * @param itemname set item names
     * @param itemprice set item price
     * @param checkbox set checkbox
     */

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

    /**
     * getter for item names
     * @return value of item name
     */

    public String getItemName() {
        return itemName.get();
    }

    /**
     * setter for item names
     * @param itemName pass item names from constructor
     */

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    /**
     * getter for item price
     * @return value of item price
     */

    public String getItemPrice() {
        return itemPrice.get();
    }

    /**
     * setter for item price
     * @param itemPrice pass item price from constructor
     */


    public void setItemPrice(String itemPrice) {
        this.itemPrice.set(itemPrice);
    }







}
