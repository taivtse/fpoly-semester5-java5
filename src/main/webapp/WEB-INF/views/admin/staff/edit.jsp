<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 2/11/19
  Time: 13:50
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="submitFormUrl" value="/admin/staff"></c:url>
<html>
<head>
    <title>
        <spring:message code="label.staff.${empty command.pojo ? 'insert' : 'update'}"/>
    </title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/bootstrap-fileupload/bootstrap-fileupload.min.css'/>"/>
        <link rel="stylesheet" href="<c:url value='/template/admin/vendor/bootstrap-datepicker/css/datepicker3.css'/>"/>
    </content>
    <style>
        #staffPhoto {
            display: none;
        }

        .imagePreviewWrapper {
            width: 150px;
            height: 150px;
            background: url(<c:url value="/template/admin/images/avatar-default.png"></c:url>) center;
            background-size: cover;
            background-color: #efefef;
            border: 2px dashed #909090;
            cursor: pointer;
        }

        #imagePreview {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
    </style>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><spring:message code="label.staff.${empty command.pojo ? 'insert' : 'update'}"/></h2>
    </header>

    <!-- start: page -->
    <div class="row">
        <div class="col-lg-12">
            <section class="panel">
                <header class="panel-heading">
                    <h2 class="panel-title"><spring:message code="label.staff.info"/></h2>
                </header>
                <div class="panel-body">
                    <form class="form-horizontal form-bordered" id="command" action="${submitFormUrl}" method="post"
                          enctype="multipart/form-data">
                        <div class="form-group">
                            <div class="row mb-lg">
                                <div class="col-xs-12" style="display: flex; justify-content: center">
                                    <input type="file" id="staffPhoto" name="pojo.photo">
                                    <div class="imagePreviewWrapper">
                                        <c:if test="${not empty command.pojo.photo}">
                                            <c:url value="/resource/${command.pojo.photo}"
                                                   var="imageUrl"></c:url>
                                        </c:if>
                                        <img src="${imageUrl}" alt="" id="imagePreview">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <label class="col-md-3 control-label">
                                        <spring:message code="label.staff.photo"/>
                                    </label>
                                    <div class="col-md-6">
                                        <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="fa fa-file-image-o"></i>
                                    </span>
                                            <input type="text" value="${fn:substring(command.pojo.photo, fn:indexOf(command.pojo.photo, "/") + 1, fn:length(command.pojo.photo))}" readonly disabled
                                                   class="form-control" id="staffPhotoName">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${not empty command.pojo}">
                            <input type="hidden" name="pojo.id" value="${command.pojo.id}">
                            <input type="hidden" name="pojo.code" value="${command.pojo.code}">
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="code">
                                    <spring:message code="label.staff.code"/>
                                </label>
                                <div class="col-md-6">
                                    <input type="text" readonly disabled value="${command.pojo.code}"
                                           class="form-control"
                                           id="code">
                                </div>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label class="col-md-3 control-label" for="name">
                                <spring:message code="label.staff.name"/>
                            </label>
                            <div class="col-md-6">
                                <input type="text" name="pojo.name" value="${command.pojo.name}" class="form-control"
                                       id="name">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 text-right">
                                <spring:message code="label.staff.gender"/>
                            </label>
                            <div class="col-md-6">
                                <div class="radio-custom pr-xl" style="display: inline-block;">
                                    <input type="radio" value="male" ${command.pojo.gender eq 'male' ? 'checked' : ''}
                                           id="rdo-male" name="pojo.gender" checked>
                                    <label for="rdo-male">
                                        <spring:message code="label.staff.gender.male"/>
                                    </label>
                                </div>
                                <div class="radio-custom pr-xl" style="display: inline-block;">
                                    <input type="radio"
                                           value="female" ${command.pojo.gender eq 'female' ? 'checked' : ''}
                                           id="rdo-female" name="pojo.gender">
                                    <label for="rdo-female">
                                        <spring:message code="label.staff.gender.female"/>
                                    </label>
                                </div>
                                <div class="radio-custom" style="display: inline-block;">
                                    <input type="radio" value="other" ${command.pojo.gender eq 'other' ? 'checked' : ''}
                                           id="rdo-other" name="pojo.gender">
                                    <label for="rdo-other">
                                        <spring:message code="label.other"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                <spring:message code="label.staff.birthday"/>
                            </label>
                            <div class="col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                    <input type="text" autocomplete="off" name="pojo.birthday"
                                           value="<fmt:formatDate value="${command.pojo.birthday}" pattern="dd/MM/yyyy"/>"
                                           data-plugin-datepicker class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                <spring:message code="label.staff.level"/>
                            </label>
                            <div class="col-md-6">
                                <div data-plugin-spinner data-plugin-options='{ "value":1, "min": 1, "max": 10 }'>
                                    <div class="input-group" style="width:50%;">
                                        <input type="text" name="pojo.level" value="${command.pojo.level}"
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
                                <spring:message code="label.staff.salary"/>
                            </label>
                            <div class="col-md-6">
                                <input type="number" name="pojo.salary" value="${command.pojo.salary}" min="0"
                                       pattern="([0-9]{1,3}),([0-9]{1,3}),([0-9]{1,3}),([0-9]{1,3})"
                                       class="form-control" id="salary">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="email">
                                <spring:message code="label.staff.email"/>
                            </label>
                            <div class="col-md-6">
                                <input type="email" name="pojo.email" value="${command.pojo.email}" class="form-control"
                                       id="email">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="phone">
                                <spring:message code="label.staff.phone"/>
                            </label>
                            <div class="col-md-6">
                                <input type="tel" name="pojo.phone" value="${command.pojo.phone}" class="form-control"
                                       id="phone">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label" for="textareaAutosize">
                                <spring:message code="label.staff.notes"/>
                            </label>
                            <div class="col-md-6">
                                <textarea name="pojo.notes" class="form-control" rows="3" id="textareaAutosize"
                                          data-plugin-textarea-autosize>${command.pojo.notes}</textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label">
                                <spring:message code="label.depart"/>
                            </label>
                            <div class="col-md-6">
                                <select data-plugin-selectTwo name="departId" class="form-control populate">
                                    <c:forEach var="departDto" items="${command.departDtoList}">
                                        <option value="${departDto.id}" ${departDto.id eq command.pojo.departDto.id ? 'selected' : ''}>${departDto.name}</option>
                                    </c:forEach>
                                </select>
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
    <script src="<c:url value='/template/admin/vendor/bootstrap-fileupload/bootstrap-fileupload.min.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/fuelux/js/spinner.js'/>"></script>
</content>

<content tag="local_script">
    <script type="application/javascript">
        $(document).ready(function () {
            getImagePreview();
        })

        function getImagePreview() {
            $(".imagePreviewWrapper").click(function () {
                $("#staffPhoto").trigger('click');
            })

            $("#staffPhoto").change(function () {
                readURL(this);
                $("#staffPhotoName").val(this.files[0].name);
            })
        }

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#imagePreview').attr('src', reader.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
</content>
</body>
</html>
