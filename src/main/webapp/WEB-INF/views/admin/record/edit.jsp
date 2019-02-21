<%--
  Created by IntelliJ IDEA.
  User: vothanhtai - Date: 2/15/19 - Time: 17:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="submitFormUrl" value="/admin/record"></c:url>
<c:url var="liveSearchUrl" value="/admin/staff/live-search"></c:url>
<html>
<head>
    <title><spring:message code="label.record.page.title"/></title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-ui/jquery-ui.min.css'/>"/>
    </content>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><spring:message code="label.record.page.title"/></h2>
    </header>

    <!-- start: page -->
    <section class="panel">
        <header class="panel-heading">
            <h2 class="panel-title"><spring:message code="label.record.${empty command.pojo.id ? 'insert' : 'update'}"/></h2>
        </header>
        <div class="panel-body">
            <form class="form-horizontal form-bordered" id="command" action="${submitFormUrl}" method="post">
                <input type="hidden" id="staffId" name="staffId" value="${command.pojo.staffDto.id}">
                <c:if test="${not empty command.pojo.id}">
                    <input type="hidden" name="pojo.id" value="${command.pojo.id}">
                </c:if>
                <div class="form-group">
                    <label class="col-md-3 control-label" for="staffCode">
                        <spring:message code="label.staff.code"/>
                    </label>
                    <div class="col-md-6">
                        <input type="text" id="staffCode" class="form-control" value="${command.pojo.staffDto.code}"
                        ${not empty command.pojo.staffDto.code ? 'readonly' : ''}>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label" for="staffName">
                        <spring:message code="label.staff.name"/>
                    </label>
                    <div class="col-md-6">
                        <input type="text" id="staffName" readonly class="form-control"
                               value="${command.pojo.staffDto.name}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label" for="staffDepartName">
                        <spring:message code="label.depart"/>
                    </label>
                    <div class="col-md-6">
                        <input type="text" id="staffDepartName" readonly class="form-control"
                               value="${command.pojo.staffDepartName}">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 text-right">
                        <spring:message code="label.record"/>
                    </label>
                    <div class="col-md-6">
                        <div class="radio-custom pr-xl" style="display: inline-block;">
                            <input type="radio" checked
                                   value="reward"
                                   id="rdo-reward" name="pojo.type"
                            ${command.pojo.type eq 'reward' ? 'checked': ''}>
                            <label for="rdo-reward">
                                <spring:message code="label.record.reward"/>
                            </label>
                        </div>
                        <div class="radio-custom" style="display: inline-block;">
                            <input type="radio"
                                   value="punishment"
                                   id="rdo-punishment" name="pojo.type"
                            ${command.pojo.type eq 'punishment' ? 'checked': ''}>
                            <label for="rdo-punishment">
                                <spring:message code="label.record.punishment"/>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label" for="textareaAutosize">
                        <spring:message code="label.record.reason"/>
                    </label>
                    <div class="col-md-6">
                            <textarea name="pojo.reason" class="form-control" rows="3" id="textareaAutosize"
                                      data-plugin-textarea-autosize>${command.pojo.reason}</textarea>
                    </div>
                </div>

                <div class="form-group text-center">
                    <button type="submit" class="mb-xs mt-xs mr-xs btn btn-primary" id="submitButton" ${empty command.pojo.id ? 'disabled' : ''}>
                        <spring:message code="label.${empty command.pojo.id ? 'insert' : 'update'}"/>
                    </button>
                    <button type="reset" class="mb-xs mt-xs mr-xs btn btn-default">
                        <spring:message code="label.reset"/>
                    </button>
                </div>
            </form>
        </div>
    </section>
    <!-- end: page -->
</section>
<content tag="specific_script">
    <script src="<c:url value='/template/admin/vendor/jquery-ui/jquery-ui.min.js'/>"></script>
</content>
<content tag="local_script">
    <script type="application/javascript">
        $(document).ready(function () {
            <c:if test="${empty command.pojo.id}">
            $('#staffCode').autocomplete({
                source: function (request, response) {
                    var requestUrl = '${pageContext.request.contextPath}${liveSearchUrl}?staffCode={0}'.format(request.term);
                    $.getJSON(requestUrl, function (result) {
                        response($.map(result, function (value, key) {
                            return {
                                label: value.code + ' - ' + value.name,
                                value: value.id
                            }
                        }));
                    });
                },
                minLength: 2,
                select: function (event, ui) {
                    var requestUrl = '${pageContext.request.contextPath}${liveSearchUrl}/info/{0}'.format(ui.item.value);
                    $.getJSON(requestUrl, function (result) {
                        $("#staffId").val(result.id);
                        $("#staffCode").val(result.code);
                        $("#staffName").val(result.name);
                        $("#staffDepartName").val(result.departName);
                    });

                    $("#submitButton").prop('disabled', false);
                }
            });

            $('#staffCode').on('input',function(e){
                $("#submitButton").prop('disabled', true);
            });
            </c:if>
        });
    </script>
</content>
</body>
</html>
