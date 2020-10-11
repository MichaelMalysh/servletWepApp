package com.test.servlet.periodicals.db.dao;

import com.test.servlet.periodicals.db.DBManager;
import com.test.servlet.periodicals.db.entity.Category;
import com.test.servlet.periodicals.db.entity.Order;
import com.test.servlet.periodicals.db.entity.Publication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Misha Malysh
 */
public class PublicationDAO {

    private static final Logger log = LogManager.getLogger();

    private static final String SQL__FIND_ALL_PUBLICATIONS =
            "SELECT * FROM publication";

    private static final String SQL__FIND_PUBLICATIONS_BY_ORDER =
            "select * from publication where id in (select publication_id from order_has_publication where orders_id=?)";

    private static final String SQL__FIND_ALL_CATEGORIES =
            "SELECT * FROM category";
    private static final String SQL__FIND_PUBLICATION_BY_ID =
            "SELECT * FROM orders WHERE id=?";


    /**
     * Returns all categories.
     *
     * @return List of category entities.
     */
    public List<Category> findCategories() {
        List<Category> categoriesList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CategoryMapper mapper = new CategoryMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_CATEGORIES);
            while (rs.next())
                categoriesList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error("Cannot find category from db", ex);
        } finally {
            if(con != null) {
                DBManager.getInstance().commitAndClose(con);
            }
        }
        return categoriesList;
    }

    /**
     * Returns all publications.
     *
     * @return List of publications entities.
     */
    public List<Publication> findPublications() {
        List<Publication> publicationList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PublicationMapper mapper = new PublicationMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_PUBLICATIONS);
            while (rs.next())
                publicationList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error("Cannot find publications from db", ex);
        } finally {
            if(con != null) {
                DBManager.getInstance().commitAndClose(con);
            }
        }
        return publicationList;
    }

    /**
     * Returns publications of the given order.
     *
     * @param order Order entity.
     * @return List of publications entities.
     */
    public List<Publication> findPublicationsByOrder(Order order) {
        List<Publication> publicationList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PublicationMapper mapper = new PublicationMapper();
            pstmt = con.prepareStatement(SQL__FIND_PUBLICATIONS_BY_ORDER);
            pstmt.setLong(1, order.getId());
            rs = pstmt.executeQuery();
            while (rs.next())
                publicationList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error("Cannot find publications byy order from db", ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationList;
    }

    /**
     * Returns publications with given identifiers.
     *
     * @param ids Identifiers of publications.
     * @return List of publications entities.
     */
    public List<Publication> findPublications(String[] ids) {
        List<Publication> publicationList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PublicationMapper mapper = new PublicationMapper();

            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM publication WHERE id IN (");
            for (String idAsString : ids)
                query.append(idAsString).append(',');
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            stmt = con.createStatement();
            rs = stmt.executeQuery(query.toString());
            while (rs.next())
                publicationList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationList;
    }

    public List<Publication> findPublications(int id) {
        List<Publication> publicationList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PublicationMapper mapper = new PublicationMapper();
            pstmt = con.prepareStatement(SQL__FIND_PUBLICATION_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next())
                publicationList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error("Cannot find order in DB");
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationList;
    }


    /**
     * Extracts a menu item from the result set row.
     */
    private static class PublicationMapper implements EntityMapper<Publication> {

        @Override
        public Publication mapRow(ResultSet rs) {
            try {
                Publication publication = new Publication();
                publication.setId(rs.getLong(Fields.ENTITY__ID));
                publication.setName(rs.getString(Fields.PUBLICATION__NAME));
                publication.setPrice(rs.getInt(Fields.PUBLICATION__PRICE));
                publication.setCategoryId(rs.getLong(Fields.PUBLICATION__CATEGORY_ID));
                return publication;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts a category from the result set row.
     */
    private static class CategoryMapper implements EntityMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs) {
            try {
                Category category = new Category();
                category.setId(rs.getLong(Fields.ENTITY__ID));
                category.setName(rs.getString(Fields.CATEGORY__NAME));
                return category;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
