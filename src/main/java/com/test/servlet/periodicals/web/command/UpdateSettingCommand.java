package com.test.servlet.periodicals.web.command;

import com.test.servlet.periodicals.Path;
import com.test.servlet.periodicals.db.dao.UserDAO;
import com.test.servlet.periodicals.db.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;

/**
 * @author Misha Malysh
 */
public class UpdateSettingCommand implements Command {

    private static final long serialVersionUID = 4963140469750917974L;
    private static final Logger log = LogManager.getLogger();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");

        // UPDATE USER ////////////////////////////////////////////////////////

        User user = (User)request.getSession().getAttribute("user");
        boolean updateUser = false;

        // update first name
        String firstName = request.getParameter("firstName");
        if (firstName != null && !firstName.isEmpty()) {
            user.setFirstName(firstName);
            updateUser = true;
        }

        // update last name
        String lastName = request.getParameter("lastName");
        if (lastName != null && !lastName.isEmpty()) {
            user.setLastName(lastName);
            updateUser = true;
        }

        String localeToSet = request.getParameter("localeToSet");
        if (localeToSet != null && !localeToSet.isEmpty()) {
            HttpSession session = request.getSession();
            Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", localeToSet);
            session.setAttribute("defaultLocale", localeToSet);
            user.setLocalName(localeToSet);
            updateUser = true;
        }

        if (updateUser == true)
            new UserDAO().updateUser(user);


        log.debug("Command finished");
        return Path.PAGE__SETTINGS;
    }
}
