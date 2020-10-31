<!DOCTYPE>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/script.jspf" %>

<div class="site-wrap">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <span class="icon-error display-3 text-danger"></span>
                    <h2 class="display-3 text-black"><fmt:message key="404"/></h2>
                    <p class="lead mb-5"></p>
                    <p><a href="/ChernevOnlineStore/index.jsp" class="btn btn-sm btn-primary"><fmt:message
                            key="to_shop"/></a></p>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>

</html>