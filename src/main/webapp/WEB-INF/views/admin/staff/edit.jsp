<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 2/11/19
  Time: 13:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="submitFormUrl" value="/admin/staff/update"></c:url>
<html>
<head>
    <title>
        <fmt:message key="label.staff.${staffDto eq null ? 'add' : 'update'}" bundle="${lang}"/>
    </title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/bootstrap-fileupload/bootstrap-fileupload.min.css'/>"/>
        <link rel="stylesheet" href="<c:url value='/template/admin/vendor/bootstrap-datepicker/css/datepicker3.css'/>"/>
    </content>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><fmt:message key="label.staff.${staffDto eq null ? 'add' : 'update'}" bundle="${lang}"/></h2>
    </header>

    <!-- start: page -->
    <div class="row">
        <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">
                    <h2 class="panel-title"><fmt:message key="label.staff.info" bundle="${lang}"/></h2>
                </header>
                <div class="panel-body">
                    <form class="form-horizontal form-bordered" action="${submitFormUrl}" method="post">
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="name">
                                <fmt:message key="label.staff.name" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="name" value="${staffDto.name}" class="form-control" id="name">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 text-right">
                                <fmt:message key="label.staff.gender" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <div class="radio-custom pr-xl" style="display: inline-block;">
                                    <input type="radio" value="male" ${staffDto.gender eq 'male' ? 'checked' : ''}
                                           id="rdo-male" name="gender" checked>
                                    <label for="rdo-male">
                                        <fmt:message key="label.staff.gender.male" bundle="${lang}"/>
                                    </label>
                                </div>
                                <div class="radio-custom pr-xl" style="display: inline-block;">
                                    <input type="radio" value="female" ${staffDto.gender eq 'female' ? 'checked' : ''}
                                           id="rdo-female" name="gender">
                                    <label for="rdo-female">
                                        <fmt:message key="label.staff.gender.female" bundle="${lang}"/>
                                    </label>
                                </div>
                                <div class="radio-custom" style="display: inline-block;">
                                    <input type="radio" value="other" ${staffDto.gender eq 'other' ? 'checked' : ''}
                                           id="orther" name="gender">
                                    <label for="orther">
                                        <fmt:message key="label.other" bundle="${lang}"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                <fmt:message key="label.staff.photo" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <div class="fileupload fileupload-new" data-provides="fileupload">
                                    <div class="input-append">
                                        <div class="uneditable-input" style="width: 64%;">
                                            <i class="fa fa-file fileupload-exists"></i>
                                            <span class="fileupload-preview"></span>
                                        </div>
                                        <span class="btn btn-default btn-file">
                                            <span class="fileupload-exists">
                                                <fmt:message key="label.change" bundle="${lang}"/>
                                            </span>
                                            <span class="fileupload-new">
                                                <fmt:message key="label.photo.select" bundle="${lang}"/>
                                            </span>
                                            <input type="file" name="photo">
                                        </span>
                                        <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">
                                            <fmt:message key="label.cancel" bundle="${lang}"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                <fmt:message key="label.staff.birthday" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                    <input type="text" name="birthday" value="${staffDto.birthday}"
                                           data-plugin-datepicker class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                <fmt:message key="label.staff.level" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <div data-plugin-spinner data-plugin-options='{ "value":1, "min": 1, "max": 10 }'>
                                    <div class="input-group" style="width:50%;">
                                        <input type="text" name="level" value="${staffDto.level}"
                                               class="spinner-input form-control"
                                               maxlength="2" readonly>
                                        <div class="spinner-buttons input-group-btn">
                                            <button type="button" class="btn btn-default spinner-up">
                                                <i class="fa fa-angle-up"></i>
                                            </button>
                                            <button type="button" class="btn btn-default spinner-down">
                                                <i class="fa fa-angle-down"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="salary">
                                <fmt:message key="label.staff.salary" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <input type="number" name="salary" value="${staffDto.salary}" min="0"
                                       class="form-control" id="salary">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="email">
                                <fmt:message key="label.staff.email" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <input type="email" name="email" value="${staffDto.email}" class="form-control"
                                       id="email">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="phone">
                                <fmt:message key="label.staff.phone" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <input type="tel" name="phone" value="${staffDto.phone}" class="form-control"
                                       id="phone">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="textareaAutosize">
                                <fmt:message key="label.staff.notes" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <textarea name="notes" class="form-control" rows="3" id="textareaAutosize"
                                          data-plugin-textarea-autosize>${staffDto.notes}</textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                <fmt:message key="label.depart" bundle="${lang}"/>
                            </label>
                            <div class="col-md-6">
                                <select data-plugin-selectTwo name="departId" class="form-control populate">
                                    <c:forEach var="departDto" items="${departDtoList}">
                                        <option value="${departDto.id}">${departDto.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="mb-xs mt-xs mr-xs btn btn-primary">Primary</button>
                            <button type="button" class="mb-xs mt-xs mr-xs btn btn-warning">Warning</button>
                        </div>
                    </form>
                </div>
            </section>
        </div>
    </div>
    <!-- end: page -->
</section>
<content tag="specific_script">
    <script src="<c:url value='/template/admin/vendor/bootstrap-fileupload/bootstrap-fileupload.min.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/fuelux/js/spinner.js'/>"></script>
</content>

<content tag="local_script">
</content>
</body>
</html>
