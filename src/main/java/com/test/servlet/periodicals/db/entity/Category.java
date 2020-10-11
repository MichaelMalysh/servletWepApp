package com.test.servlet.periodicals.db.entity;

/**
 * @author Misha Malysh
 */
public class Category extends Entity {

    private static final long serialVersionUID = 2012329240115477212L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
               "name='" + name + '\'' +
               '}';
    }
}
