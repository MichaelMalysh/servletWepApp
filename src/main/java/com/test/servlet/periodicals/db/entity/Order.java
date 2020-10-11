package com.test.servlet.periodicals.db.entity;

/**
 * @author Misha Malysh
 */
public class Order extends  Entity {

    private static final long serialVersionUID = 7546570387360446174L;

    private Integer bill;

    private Long userId;

    private int statusId;

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Order{" +
               "bill=" + bill +
               ", userId=" + userId +
               ", statusId=" + statusId +
               '}';
    }
}
