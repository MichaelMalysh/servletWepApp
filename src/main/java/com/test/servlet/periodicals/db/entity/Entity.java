package com.test.servlet.periodicals.db.entity;

import java.io.Serializable;

/**
 * @author Misha Malysh
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -281699323322481008L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}