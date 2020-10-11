package com.test.servlet.periodicals.db.bean;

import com.test.servlet.periodicals.db.entity.Entity;

/**
 * @author Misha Malysh
 */
public class UserOrderBean extends Entity {

    private static final long serialVersionUID = 1384743770377368695L;

    private long orderId;

    private String userFirstName;

    private String userLastName;

    private int orderBill;

    private String statusName;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getOrderBill() {
        return orderBill;
    }

    public void setOrderBill(int orderBill) {
        this.orderBill = orderBill;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "UserOrderBean{" +
               "orderId=" + orderId +
               ", userFirstName='" + userFirstName + '\'' +
               ", userLastName='" + userLastName + '\'' +
               ", orderBill=" + orderBill +
               ", statusName='" + statusName + '\'' +
               '}';
    }
}
