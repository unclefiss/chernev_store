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
                <div class="col-md-4"></div>
                <div class="col-md-4 card">
                    <h2 class="h3 my-3 text-black text-center"><fmt:message key="login.log_in"/></h2>
                    <form method="POST" class="js-form-validate" action="/ChernevOnlineStore/login">
                        <div class="form-group input-group">
                            <span class="input-group-text"> <span class="icon-person col-md-1"></span> </span>
                            <input name="login" class="form-control" placeholder=
                            <fmt:message key="login.login"/> type="text"
                                   data-validate>
                            <span class="some-form__hint"><fmt:message key="login.hint.login"/></span>
                        </div> <!-- form-group// -->
                        <div class="form-group input-group">
                            <span class="input-group-text"> <span class="icon-lock col-md-1"></span> </span>
                            <input name="password" class="form-control" placeholder=
                            <fmt:message key="login.password"/> type="password"
                                   data-validate>
                            <span class="some-form__hint"><fmt:message key="login.hint.password"/></span>
                        </div> <!-- form-group// -->
                        <div class="form-group">
                            <button type="submit"
                                    class="btn btn-primary btn-block submit"><fmt:message key="login.log_in"/></button>
                        </div> <!-- form-group// -->
                        <p class="text-center"><fmt:message key="login.do_you_have"/> <a
                                href="/ChernevOnlineStore/registration"><fmt:message key="login.registration"/></a></p>
                    </form>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
</body>

</html>