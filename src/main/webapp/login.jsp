<%@ include file="/WEB-INF/jspf/directives/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directives/taglib.jspf" %>

<html>

<c:set var="title" value="Login"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%--Class page corresponds to the '.page' element in included CSS document.--%>
<table id="main-container">

    <%-- HEADER --%>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%-- HEADER --%>

    <tr>
        <td class="content center">

            <%--Defines the web form.--%>
            <form id="login_form" action="periodicals" method="post">

                <input type="hidden" name="command" value="email"/>

                <div><label for="email"><fmt:message key="login_jsp.label.login"/></label>
                <input  type="text" name="email" id="email"/></div>
                <div><label for="password"><fmt:message key="login_jsp.label.password"/></label>
                <input type="password" name="password" id="password"/></div>
                <br/>

                <input type="submit" value='<fmt:message key="login_jsp.button.login"/>'>
            </form>

        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>