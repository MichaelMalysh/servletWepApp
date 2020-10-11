package com.test.servlet.periodicals.web.command;

import com.test.servlet.periodicals.Path;
import com.test.servlet.periodicals.db.dao.PublicationDAO;
import com.test.servlet.periodicals.db.entity.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Misha Malysh
 */
public class ListPublicationCommand implements Command {


    private static final long serialVersionUID = 1597945441202737516L;

    private static final Logger log = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");

        // get menu items list
        List<Publication> publicationItems = new PublicationDAO().findPublications();
        log.trace("Found in DB: publicationsList --> " + publicationItems);

        // sort menu by category
        Collections.sort(publicationItems, new Comparator<Publication>() {
            public int compare(Publication o1, Publication o2) {
                return (int)(o1.getCategoryId() - o2.getCategoryId());
            }
        });

        // put menu items list to the request
        request.setAttribute("publicationItems", publicationItems);
        log.trace("Set the request attribute: menuItems --> " + publicationItems);

        log.debug("Command finished");
        return Path.PAGE__LIST_PUBLLICTIONS;
    }
}
