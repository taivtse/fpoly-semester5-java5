<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 2/11/19
  Time: 13:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="submitFormUrl" value="/admin/user"></c:url>

<html>
<head>
    <title>
        <spring:message code="label.user.${empty command.pojo ? 'insert' : 'update'}"/>
    </title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
    </content>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><spring:message code="label.user.${empty command.pojo ? 'insert' : 'update'}"/></h2>
    </header>

    <!-- start: page -->
    <div class="row">
        <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">
                    <h2 class="panel-title"><spring:message code="label.user.info"/></h2>
                </header>
                <div class="panel-body">
                    <form class="form-horizontal form-bordered" id="command" action="${submitFormUrl}" method="post">
                        <c:if test="${not empty command.pojo}">
                            <input type="hidden" name="pojo.id" value="${command.pojo.id}">
                            <input type="hidden" name="pojo.username" value="${command.pojo.username}">
                        </c:if>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="code">
                                <spring:message code="label.user.username"/>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="pojo.username"
                                       <c:if test="${not empty command.pojo}">readonly disabled</c:if>
                                       value="${command.pojo.username}"
                                       class="form-control"
                                       id="code">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="name">
                                <spring:message code="label.user.fullName"/>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="pojo.fullName" value="${command.pojo.fullName}"
                                       class="form-control"
                                       id="name">
                            </div>
                        </div>
                        <div class="form-group text-center">
                            <button type="submit" class="mb-xs mt-xs mr-xs btn btn-primary">
                                <spring:message code="label.${empty command.pojo ? 'insert' : 'update'}"/>
                            </button>
                            <button type="reset" class="mb-xs mt-xs mr-xs btn btn-default">
                                <spring:message code="label.reset"/>
                            </button>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
    <!-- end: page -->
</section>
<content tag="specific_script">

</content>

<content tag="local_script">
</content>
</body>
</html>
