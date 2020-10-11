package com.test.servlet.periodicals.web.command;

import com.test.servlet.periodicals.Path;
import com.test.servlet.periodicals.db.dao.UserDAO;
import com.test.servlet.periodicals.db.entity.User;
import com.test.servlet.periodicals.db.enums.Role;
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
public class LoginCommand implements Command {

    private static final long serialVersionUID = 6693399049259998945L;

    private static final Logger log = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");

        HttpSession session = request.getSession();

        //obtain login and password from thr request
        String email = request.getParameter("email");
        log.trace("Request parameter email --> " + email);

        String password = request.getParameter("password");

        //error handler
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;
        if(email == null || password == null || email.isEmpty() || password.isEmpty()){
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage -->" + errorMessage);
            return forward;
        }

        User user = new UserDAO().findUserByEmail(email);
        log.trace("Founding user in DB: "+ user);
        if(user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with suck email/login";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage -->" +errorMessage);
            return forward;
        }else {
            Role userRole = Role.getRole(user);
            log.trace("userRole --> "+userRole);

            if(userRole == Role.ADMIN){
                forward = Path.COMMAND__LIST_ORDERS;
            }

            if(userRole == Role.READER){
                forward = Path.COMMAND__LIST_PUBLICATIONS;
            }

            session.setAttribute("user", user);
            log.trace("Set the session attribute user: " + user);

            session.setAttribute("userRole", userRole);
            log.trace("Set the session attribute userRole: "+userRole);

            log.info("User "+user+" logged as "+userRole.toString().toLowerCase());

            //work with locale
            String userLocaleName = user.getLocalName();
            log.trace("userLocalName --> " + userLocaleName);

            if (userLocaleName != null && !userLocaleName.isEmpty()) {
                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);

                session.setAttribute("defaultLocale", userLocaleName);
                log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);

                log.info("Locale for user: defaultLocale --> " + userLocaleName);
            }
        }

        log.debug("Command finished");
        return forward;
    }
}
