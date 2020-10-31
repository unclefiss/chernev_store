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
                <div class="col-md-12 mb-0"><a href="index.jsp"><fmt:message key="header.home"/></a> <span
                        class="mx-2 mb-0">/</span> <strong
                        class="text-black"><fmt:message key="registration.registration"/></strong></div>
            </div>
        </div>
    </div>
    <div class="site-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${not empty errors}">
                        <div class="bg-danger card text-light col-md">
                            <c:forEach var="error" items="${errors}">
                              <span>
                                 Input error:
                                 <c:out value="${error}"/>
                              </span>
                            </c:forEach>
                        </div>
                    </c:if>
                    <h2 class="h3 mb-3 text-black"><fmt:message key="registration.registration"/></h2>
                </div>
                <div class="col-md-7">
                    <form method="POST" class="js-form-validate" action="/ChernevOnlineStore/registration"
                          enctype="multipart/form-data">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><fmt:message key="registration.userpic"/></span>
                            </div>
                            <div class="custom-file">
                                <input name="file" type="file" class="custom-file-input" id="fileInput">
                                <label class="custom-file-label" for="fileInput"><fmt:message
                                        key="registration.choose_file"/></label>
                                <div class="invalid-feedback">Example invalid custom file feedback</div>
                            </div>
                        </div>
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <span class="icon-person col-md-1"></span> </span>
                            </div>
                            <c:if test="${empty userDTO}">
                                <input name="first-name" class="form-control"
                                       placeholder="<fmt:message key="registration.f_name" />" type="text"
                                       data-validate>
                                <span class="some-form__hint">Write your first name</span>
                            </c:if>
                            <c:if test="${not empty userDTO}">
                                <input name="first-name" class="form-control"
                                       placeholder="<fmt:message key="registration.f_name" />" type="text"
                                       data-validate value="${userDTO.firstName}">
                                <span class="some-form__hint">Write your first name</span>
                            </c:if>
                        </div>
                        <!-- form-group// -->
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <span class="icon-person col-md-1"></span> </span>
                            </div>
                            <c:if test="${empty userDTO}">
                                <input name="last-name" class="form-control"
                                       placeholder="<fmt:message key="registration.l_name" />" type="text"
                                       data-validate>
                                <span class="some-form__hint">Write your last name</span>
                            </c:if>
                            <c:if test="${not empty userDTO}">
                                <input name="last-name" class="form-control"
                                       placeholder="<fmt:message key="registration.l_name" />" type="text"
                                       data-validate value="${userDTO.lastName}">
                                <span class="some-form__hint">Write your last name</span>
                            </c:if>
                        </div>
                        <!-- form-group// -->
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <span class="icon-at col-md-1"></span> </span>
                            </div>
                            <c:if test="${empty userDTO}">
                                <input name="login" class="form-control"
                                       placeholder="<fmt:message key="registration.login" />" type="text"
                                       data-validate>
                                <span class="some-form__hint">Write your login</span>
                            </c:if>
                            <c:if test="${not empty userDTO}">
                                <input name="login" class="form-control"
                                       placeholder="<fmt:message key="registration.login" />" type="text"
                                       data-validate value="${userDTO.login}">
                                <span class="some-form__hint">Write your login</span>
                            </c:if>
                        </div>
                        <!-- form-group// -->
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <span class="icon-mail_outline col-md-1"></span> </span>
                            </div>
                            <c:if test="${empty userDTO}">
                                <input name="email-address" class="form-control"
                                       placeholder="<fmt:message key="registration.email" />"
                                       type="text" data-validate>
                                <span class="some-form__hint">Please write email in format "example@domain.end"</span>
                            </c:if>
                            <c:if test="${not empty userDTO}">
                                <input name="email-address" class="form-control"
                                       placeholder="<fmt:message key="registration.email" />"
                                       type="text" data-validate value="${userDTO.email}">
                                <span class="some-form__hint">Please write email in format "example@domain.end"</span>
                            </c:if>
                        </div>
                        <!-- form-group// -->
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <span class="icon-phone col-md-1"></span> </span>
                            </div>
                            <c:if test="${empty userDTO}">
                                <input name="phone-number" class="form-control"
                                       placeholder="<fmt:message key="registration.phone" />"
                                       type="tel" data-validate>
                                <span class="some-form__hint">Write your phone number in format "+38 012 345 6789"</span>
                            </c:if>
                            <c:if test="${not empty userDTO}">
                                <input name="phone-number" class="form-control"
                                       placeholder=
                                           <fmt:message key="registration.phone"/>
                                               type="tel" data-validate value="${userDTO.phone}">
                                <span class="some-form__hint">Write your phone number in format "+38 012 345 6789"</span>
                            </c:if>
                        </div>
                        <!-- form-group// -->
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <span class="icon-lock col-md-1"></span> </span>
                            </div>
                            <input name="password" class="form-control"
                                   placeholder="<fmt:message key="registration.create_password" />"
                                   type="password" data-validate>
                        </div>
                        <!-- form-group// -->
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <span class="icon-lock col-md-1"></span> </span>
                            </div>
                            <input name="password-repeat" class="form-control"
                                   placeholder="<fmt:message key="registration.repeat_password" />"
                                   type="password" data-validate>
                            <span class="some-form__hint">Password must be same</span>
                        </div>
                        <!-- form-group// -->
                        <div class="form-group input-group">
                            <img alt="captcha image" src="/ChernevOnlineStore/captcha">
                            <input name="captcha" class="form-control"
                                   placeholder="<fmt:message key="registration.captcha" />" type="text"
                                   data-validate>
                            <input name="captchaId" value="${captchaHidden}" type="hidden">
                            <span class="some-form__hint">Write captcha please</span>
                        </div>
                        <!-- form-group// -->
                        <div class="form-group input-group">
                            <input name="check" class="form-check-input" placeholder=""
                                   type="checkbox">
                            <div class="input-group-prepend">
                                <span class="text-dark"><fmt:message key="registration.by_clicking"/></span>
                            </div>
                        </div>
                        <!-- form-group// -->
                        <div class="form-group">
                            <button type="submit" placeholder="Create account"
                                    class="btn btn-primary btn-block text submit"><fmt:message
                                    key="registration.registration"/></button>
                        </div>
                        <!-- form-group// -->
                        <p class="text-center"><fmt:message key="registration.have_an_account"/> <a
                                href="/ChernevOnlineStore/login"><fmt:message key="registration.log_in"/></a></p>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>