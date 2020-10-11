package com.test.servlet.periodicals.db.dao;

import java.sql.ResultSet;

/**
 * @author Misha Malysh
 */
public interface EntityMapper<T> {
    T mapRow(ResultSet rs);
}
