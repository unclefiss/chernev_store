<!DOCTYPE>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/script.jspf" %>

<div class="site-wrap">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <div class="bg-light py-3">
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-0"><a href="index.jsp">Home</a> <span class="mx-2 mb-0">/</span> <strong
                        class="text-black">Contact</strong></div>
            </div>
        </div>
    </div>

    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <span class="icon-check_circle display-3 text-success"></span>
                    <h2 class="display-3 text-black"><fmt:message key="success"/></h2>
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