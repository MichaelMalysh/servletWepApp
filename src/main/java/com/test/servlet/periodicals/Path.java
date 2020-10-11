package com.test.servlet.periodicals;

/**
 * @author Misha Malysh
 */
public class Path {

    // pages
    public static final String PAGE__LOGIN = "/login.jsp";
    public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    public static final String PAGE__LIST_PUBLLICTIONS = "/WEB-INF/jsp/client/list_publications.jsp";
    public static final String PAGE__LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
    public static final String PAGE__SETTINGS = "/WEB-INF/jsp/setting.jsp";
    public static final String PAGE__MAKE_ORDER = "/WEB-INF/jsp/client/make_order.jsp";

    // commands
    public static final String COMMAND__LIST_ORDERS = "/periodicals?command=listOrders";
    public static final String COMMAND__LIST_PUBLICATIONS = "/periodicals?command=listPublications";
    public static final String COMMAND__MAKE_ORDER = "/periodicals?command=makeOrder";

}

