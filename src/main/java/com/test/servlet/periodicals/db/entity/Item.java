package com.test.servlet.periodicals.db.entity;

/**
 * @author Misha Malysh
 */
public class Item {

    private Publication publication = new Publication();
    private int quantity;

    public Item(Publication publication, int quantity) {
        this.publication = publication;
        this.quantity = quantity;
    }

    public Item() {
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
