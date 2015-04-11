<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<tags:page title="${category.name}" nav="categories">
  <ul class="breadcrumb">
    <li class="active">${fn:escapeXml(category.name)}</li>
  </ul>
  <security:authorize ifAllGranted="ROLE_ADMIN">
    <c:url var="editUrl" value="/category_form.html">
      <c:param name="id" value="${category.id}" />
    </c:url>
    <c:url var="deleteUrl" value="/category_delete.html">
      <c:param name="id" value="${category.id}" />
    </c:url>
    <c:url var="categoriesUrl" value="/categories.html"/>
    <div class="pull-right">
      <a class="editUrl btn btn-primary" href="${editUrl}">Edit</a>
      <a class="deleteUrl btn btn-danger" href="${deleteUrl}">Delete</a>
    </div>
  </security:authorize>

  <p>${f:convertToHtmlLineBreaks(category.description)}</p>
  <tags:showForumList forums="${category.forums}"/>

  <security:authorize ifAllGranted="ROLE_ADMIN">
  <script type="text/javascript">
    $(document).ready(function() {
    	executeDeleteAndRedirect(".deleteUrl", "${categoriesUrl}");
    });
  </script>
  </security:authorize>
</tags:page>
