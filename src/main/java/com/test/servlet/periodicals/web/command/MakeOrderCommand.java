package com.test.servlet.periodicals.web.command;

import com.test.servlet.periodicals.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Misha Malysh
 */
public class MakeOrderCommand implements Command {

    private static final long serialVersionUID = 8358962741299326049L;
    private static final Logger log = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");

        log.debug("Command finished");

        return Path.PAGE__MAKE_ORDER;
    }
}
