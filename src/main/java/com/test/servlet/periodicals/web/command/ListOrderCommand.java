package com.test.servlet.periodicals.web.command;

import com.test.servlet.periodicals.Path;
import com.test.servlet.periodicals.db.bean.UserOrderBean;
import com.test.servlet.periodicals.db.dao.OrderDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Misha Malysh
 */
public class ListOrderCommand implements Command {

    private static final long serialVersionUID = 7600476477969669543L;
    private static final Logger log = LogManager.getLogger();

    /**
     * Serializable comparator used with TreeMap container. When the servlet
     * container tries to serialize the session it may fail because the session
     * can contain TreeMap object with not serializable comparator.
     *
     * @author D.Kolesnikov
     *
     */
    private static class CompareById implements Comparator<UserOrderBean>, Serializable {
        private static final long serialVersionUID = -1573481565177573283L;

        public int compare(UserOrderBean bean1, UserOrderBean bean2) {
            if (bean1.getId() > bean2.getId())
                return 1;
            else return 0;
        }
    }

    private static Comparator<UserOrderBean> compareById = new CompareById();

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        log.debug("Commands starts");

        List<UserOrderBean> userOrderBeanList = new OrderDAO().getUserOrderBeans();
        log.trace("Found in DB: userOrderBeanList --> " + userOrderBeanList);

        Collections.sort(userOrderBeanList, compareById);

        // put user order beans list to request
        request.setAttribute("userOrderBeanList", userOrderBeanList);
        log.trace("Set the request attribute: userOrderBeanList --> " + userOrderBeanList);

        log.debug("Commands finished");
        return Path.PAGE__LIST_ORDERS;
    }
}
