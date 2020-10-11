package com.test.servlet.periodicals.db.entity;

/**
 * @author Misha Malysh
 */
public class Publication extends Entity{

    private static final long serialVersionUID = -1156416657079139862L;

    private String name;
    private int price;
    private Long categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Publication{" +
               "name='" + name + '\'' +
               ", price=" + price +
               ", categoryId=" + categoryId +
               '}';
    }
}
