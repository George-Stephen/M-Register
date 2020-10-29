
package com.iconic.services.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("number_of_cards")
    @Expose
    private String numberOfCards;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("ordered_by")
    @Expose
    private String orderedBy;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Order() {
    }

    /**
     * 
     * @param orderedBy
     * @param destination
     * @param numberOfCards
     */
    public Order(String numberOfCards, String destination, String orderedBy) {
        super();
        this.numberOfCards = numberOfCards;
        this.destination = destination;
        this.orderedBy = orderedBy;
    }

    public String getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(String numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

}
