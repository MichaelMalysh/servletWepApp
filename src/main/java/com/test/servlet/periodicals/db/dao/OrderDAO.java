package com.test.servlet.periodicals.db.dao;

import com.test.servlet.periodicals.db.DBManager;
import com.test.servlet.periodicals.db.bean.UserOrderBean;
import com.test.servlet.periodicals.db.entity.Order;
import com.test.servlet.periodicals.db.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Misha Malysh
 */
public class OrderDAO {

    private static final Logger log = LogManager.getLogger();

    private static final String SQL__GET_USER_ORDER_BEANS =
            "SELECT o.id, u.first_name, u.last_name, o.bill, s.name" +
            "	FROM users u, orders o, statuses s" +
            "	WHERE o.users_id=u.id AND o.statuses_id=s.id";

    private static final String SQL__FIND_ALL_ORDERS =
            "SELECT * FROM orders";

    private static final String SQL__FIND_ORDERS_BY_STATUS_AND_USER =
            "SELECT * FROM orders WHERE statuses_id=? AND user_id=?";

    private static final String SQL__FIND_ORDERS_BY_STATUS =
            "SELECT * FROM orders WHERE statuses_id=?";



    /**
     * Returns all categories.
     *
     * @return List of category entities.
     */
    public List<UserOrderBean> getUserOrderBeans() {
        List<UserOrderBean> orderUserBeanList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__GET_USER_ORDER_BEANS);
            UserOrderBeanMapper mapper = new UserOrderBeanMapper();
            while (rs.next())
                orderUserBeanList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            log.error("Cannot find userOrderBean");
            DBManager.getInstance().rollbackAndClose(con);
        }

        return orderUserBeanList;
    }

    /**
     * Returns all orders.
     *
     * @return List of order entities.
     */
    public List<Order> findOrders() {
        List<Order> ordersList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_ORDERS);
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error("Cannot find order in DB");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return ordersList;
    }

    /**
     * Returns orders with the given status.
     *
     * @param statusId
     *            Status identifier.
     * @return List of order entities.
     */
    public List<Order> findOrders(int statusId) {
        List<Order> ordersList = new ArrayList<Order>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();
            pstmt = con.prepareStatement(SQL__FIND_ORDERS_BY_STATUS);
            pstmt.setInt(1, statusId);
            rs = pstmt.executeQuery();
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error("Cannot find order in DB");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return ordersList;
    }

    /**
     * Returns orders with given identifiers.
     *
     * @param ids
     *            Orders identifiers.
     * @return List of order entities.
     */
    public List<Order> findOrders(String[] ids) {
        List<Order> ordersList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();

            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM orders WHERE id IN (");
            for (String idAsString : ids)
                query.append(idAsString).append(',');
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            stmt = con.createStatement();
            rs = stmt.executeQuery(query.toString());
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error("Cannot find order in DB");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return ordersList;
    }


    /**
     * Returns orders of the given user and status
     *
     * @param user
     *            User entity.
     * @param statusId
     *            Status identifier.
     * @return List of order entities.
     */
    public List<Order> findOrders(User user, int statusId) {
        List<Order> ordersList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();
            pstmt = con.prepareStatement(SQL__FIND_ORDERS_BY_STATUS_AND_USER);
            pstmt.setInt(1, statusId);
            pstmt.setLong(2, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error("Cannot find orders or user in DB");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return ordersList;
    }


    /**
     * Extracts a user order bean from the result set row.
     */
    private static class UserOrderBeanMapper implements EntityMapper<UserOrderBean> {

        @Override
        public UserOrderBean mapRow(ResultSet rs) {
            try {
                UserOrderBean bean = new UserOrderBean();
                bean.setId(rs.getLong(Fields.USER_ORDER_BEAN__ORDER_ID));
                bean.setOrderBill(rs.getInt(Fields.USER_ORDER_BEAN__ORDER_BILL));
                bean.setUserFirstName(rs.getString(Fields.USER_ORDER_BEAN__USER_FIRST_NAME));
                bean.setUserLastName(rs.getString(Fields.USER_ORDER_BEAN__USER_LAST_NAME));
                bean.setStatusName(rs.getString(Fields.USER_ORDER_BEAN__STATUS_NAME));
                return bean;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts order from the result set row.
     */
    private static class OrderMapper implements EntityMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs) {
            try {
                Order order = new Order();
                order.setId(rs.getLong(Fields.ENTITY__ID));
                order.setBill(rs.getInt(Fields.ORDER__BILL));
                order.setUserId(rs.getLong(Fields.ORDER__USER_ID));
                order.setStatusId(rs.getInt(Fields.ORDER__STATUS_ID));
                return order;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
