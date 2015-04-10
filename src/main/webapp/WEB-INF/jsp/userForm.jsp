<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tags:page title="User Form" nav="add_user">
  <c:if test="${not empty param.success}">
    <tags:alert type="success" title="Success!" message="Saved user." />
  </c:if>
  <form:form action="user_form.html" commandName="userAndPassword" cssClass="form-horizontal">
    <spring:nestedPath path="user">
      <tags:textInput path="firstName" label="First Name" required="${false}" />
      <tags:textInput path="lastName" label="Last Name" required="${false}" />
      <tags:textInput path="title" label="Title" required="${false}" />
      <tags:textInput path="organization" label="Organization" required="${false}" />
      <tags:textInput path="email" label="Email" required="${false}" autocomplete="off" />
    </spring:nestedPath>
    <c:set var="passwordRequired" value="${not userAndPassword.user.idSet }" />
    <tags:passwordInput path="password" label="Password" autocomplete="off" required="${passwordRequired}" />
    <tags:passwordInput path="passwordVerification" label="Password Verification" autocomplete="off" required="${passwordRequired}" />
    <div class="form-actions">
      <button type="submit" class="btn btn-primary">Save</button>
    </div>
  </form:form>
</tags:page>
