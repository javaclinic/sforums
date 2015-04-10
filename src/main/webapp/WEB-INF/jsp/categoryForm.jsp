<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tags:page title="Category Form" nav="add_category">
  <c:if test="${not empty param.success}">
    <tags:alert type="success" title="Success!" message="Saved category." />
  </c:if>
  <form:form action="category_form.html" commandName="category" cssClass="form-horizontal">
    <tags:showFormErrors name="category" />
    <tags:textInput path="name" label="Name" required="${true}" />
    <tags:textAreaInput path="description" label="Description" required="${false}" />
    <div class="form-actions">
      <button type="submit" class="btn btn-primary">Save</button>
    </div>
  </form:form>
</tags:page>
