<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directives/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directives/taglib.jspf" %>

<html>

<c:set var="title" value="Menu" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="make_order" action="periodicals">
<%--                <input type="hidden" name="command" value="makeOrder" />--%>

                <table id="list_order_table">
                    <thead>
                    <tr>
                        <td><fmt:message key="make_order_jsp.table.header.option"/></td>
                        <td><fmt:message key="make_order_jsp.table.header.name"/></td>
                        <td><fmt:message key="make_order_jsp.table.header.price"/></td>
                        <td><fmt:message key="make_order_jsp.table.header.quantity"/></td>
                        <td><fmt:message key="make_order_jsp.table.header.sub_total"/></td>
                    </tr>
                    </thead>
                </table>

            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
