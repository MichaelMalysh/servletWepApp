package com.test.servlet.periodicals.db.dao;

/**
 * @author Misha Malysh
 */
public final class Fields {

    // entities
    public static final String ENTITY__ID = "id";

    public static final String USER__EMAIL = "email";
    public static final String USER__PASSWORD = "password";
    public static final String USER__FIRST_NAME = "first_name";
    public static final String USER__LAST_NAME = "last_name";
    public static final String USER__BUDGET = "budget";
    public static final String USER__LOCALE_NAME = "local_name";
    public static final String USER__ROLE_ID = "roles_id";

    public static final String ORDER__BILL = "bill";
    public static final String ORDER__USER_ID = "users_id";
    public static final String ORDER__STATUS_ID= "statuses_id";

    public static final String CATEGORY__NAME = "name";

    public static final String PUBLICATION__PRICE = "price";
    public static final String PUBLICATION__NAME = "name";
    public static final String PUBLICATION__CATEGORY_ID = "category_id";

    // beans
    public static final String USER_ORDER_BEAN__ORDER_ID = "id";
    public static final String USER_ORDER_BEAN__USER_FIRST_NAME = "first_name";
    public static final String USER_ORDER_BEAN__USER_LAST_NAME = "last_name";
    public static final String USER_ORDER_BEAN__ORDER_BILL = "bill";
    public static final String USER_ORDER_BEAN__STATUS_NAME = "name";



}