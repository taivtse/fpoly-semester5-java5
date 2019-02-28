<%--
  Created by IntelliJ IDEA.
  User: vothanhtai
  Date: 2/8/19
  Time: 19:37
--%>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title><spring:message code="label.index.title"/></title>
    <content tag="specific_css">
        <link rel="stylesheet"
              href="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/css/datatables.css'/>"/>
        <link rel="stylesheet" href="<c:url value='/template/admin/vendor/morris/morris.css'/>"/>
    </content>
    <style>
        .top-image h3 {
            position: absolute;
            left: 50%;
            color: white;
            margin-top: 13px;
            transform: translate(-50%, 0);
        }

        .profile-stats {
            display: flex;
            justify-content: space-around;
        }

        .level1 .top-image h3 {
            font-size: 29px;
        }

        .level1 .stat,
        .level2 .stat {
            font-size: 13px !important;
        }

        .level3 .top-image h3 {
            font-size: 22px;
        }

        .level3 .profile-info h3.name {
            font-size: 19px;
            margin-top: 29px;
            margin-bottom: 0;
        }

        .level3 .profile-info h4.name {
            margin-top: 5px;
            font-size: 17px;
        }

        .level3 .stat {
            font-size: 11px !important;
        }

        .level4 .top-image h3 {
            font-size: 20px;
        }

        .level4 .profile-info h3.name {
            font-size: 17px;
            margin-top: 29px;
            margin-bottom: 0;
        }

        .level4 .profile-info h4.name {
            margin-top: 3px;
            font-size: 15px;
        }

        .level4 .profile-stats li {
            padding: 0 !important;
        }

    </style>
</head>
<body>
<section role="main" class="content-body">
    <header class="page-header">
        <h2><spring:message code="label.board"/></h2>
    </header>

    <!-- start: page -->
    <section class="panel panel-featured">
        <header class="panel-heading">
            <h2 class="panel-title">
                <spring:message code="label.board.personal"/>
            </h2>
        </header>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-12">
                    <div class="row level1" style="border-bottom: 1px solid #dddfe4; display: flex; justify-content: center">
                        <div class="col-xs-5" style="padding: 0 40px">
                            <c:if test="${not empty command.staffSynthesisDtoList[0]}">
                                <section class="panel-group mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center"
                                                style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 13px;
                                        transform: translate(-50%, 0);">1st</h3>
                                            <img src="<c:url value='/template/admin/images/widget-twitter-profile.jpg'/>"
                                                 alt="">
                                        </div>
                                        <div class="profile-info" style="display: flex; flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[0].staffPhoto}'/>"
                                                     alt=""
                                                     style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 180px;
                                             height: 180px">
                                            </div>
                                            <div class="text-center mt-sm">
                                                <h3 class="name">${command.staffSynthesisDtoList[0].staffName}</h3>
                                                <h4 class="name">${command.staffSynthesisDtoList[0].staffCode}</h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[0].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[0].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[0].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                    </div>
                    <div class="row level2" style="border-bottom: 1px solid #dddfe4;">
                        <div class="col-xs-4 col-xs-push-2">
                            <c:if test="${not empty command.staffSynthesisDtoList[1]}">
                                <section class="panel-group mt-sm mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center"
                                                style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 5px;
                                        transform: translate(-50%, 0);">2nd</h3>
                                            <img src="<c:url value='/template/admin/images/widget-twitter-profile.jpg'/>"
                                                 alt="">
                                        </div>
                                        <div class="profile-info" style="display: flex; flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[1].staffPhoto}'/>"
                                                     alt=""
                                                     style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 150px;
                                             height: 150px;
                                             margin-top: -22px;">
                                            </div>
                                            <div class="text-center mt-sm">
                                                <h3 class="name">${command.staffSynthesisDtoList[1].staffName}</h3>
                                                <h4 class="name">${command.staffSynthesisDtoList[1].staffCode}</h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[1].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[1].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[1].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                        <div class="col-xs-4 col-xs-push-2">
                            <c:if test="${not empty command.staffSynthesisDtoList[2]}">
                                <section class="panel-group mt-sm mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center"
                                                style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 5px;
                                        transform: translate(-50%, 0);">3rd</h3>
                                            <img src="<c:url value='/template/admin/images/widget-twitter-profile.jpg'/>"
                                                 alt="">
                                        </div>
                                        <div class="profile-info" style="display: flex; flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[2].staffPhoto}'/>"
                                                     alt=""
                                                     style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 150px;
                                             height: 150px;
                                             margin-top: -22px;">
                                            </div>
                                            <div class="text-center mt-sm">
                                                <h3 class="name">${command.staffSynthesisDtoList[2].staffName}</h3>
                                                <h4 class="name">${command.staffSynthesisDtoList[2].staffCode}</h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[2].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[2].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[2].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                    </div>
                    <div class="row level3" style="border-bottom: 1px solid #dddfe4;">
                        <div class="col-xs-4" style="padding-left: 60px; padding-right: 0">
                            <c:if test="${not empty command.staffSynthesisDtoList[3]}">
                                <section class="panel-group mt-sm mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center" style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 5px;
                                        transform: translate(-50%, 0);">4th</h3>
                                            <img src="/template/admin/images/widget-twitter-profile.jpg" alt="">
                                        </div>
                                        <div class="profile-info"
                                             style="display: flex;flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[3].staffPhoto}'/>"
                                                     alt="" style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 130px;
                                             height: 130px;
                                             margin-top: -22px;">
                                            </div>
                                            <div class="text-center">
                                                <h3 class="name">
                                                        ${command.staffSynthesisDtoList[3].staffName}
                                                </h3>
                                                <h4 class="name">
                                                        ${command.staffSynthesisDtoList[3].staffCode}</h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[3].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[3].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[3].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                        <div class="col-xs-4" style="padding: 0 30px;">
                            <c:if test="${not empty command.staffSynthesisDtoList[4]}">
                                <section class="panel-group mt-sm mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center" style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 5px;
                                        transform: translate(-50%, 0);">5th</h3>
                                            <img src="/template/admin/images/widget-twitter-profile.jpg" alt="">
                                        </div>
                                        <div class="profile-info"
                                             style="display: flex;flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[4].staffPhoto}'/>"
                                                     alt="" style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 130px;
                                             height: 130px;
                                             margin-top: -22px;">
                                            </div>
                                            <div class="text-center">
                                                <h3 class="name">
                                                        ${command.staffSynthesisDtoList[4].staffName}
                                                </h3>
                                                <h4 class="name">
                                                        ${command.staffSynthesisDtoList[4].staffCode}
                                                </h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[4].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[4].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[4].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                        <div class="col-xs-4" style="padding-left: 0; padding-right: 60px;">
                            <c:if test="${not empty command.staffSynthesisDtoList[5]}">
                                <section class="panel-group mt-sm mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center" style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 5px;
                                        transform: translate(-50%, 0);">6th</h3>
                                            <img src="/template/admin/images/widget-twitter-profile.jpg" alt="">
                                        </div>
                                        <div class="profile-info"
                                             style="display: flex;flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[5].staffPhoto}'/>"
                                                     alt="" style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 130px;
                                             height: 130px;
                                             margin-top: -22px;">
                                            </div>
                                            <div class="text-center">
                                                <h3 class="name">
                                                        ${command.staffSynthesisDtoList[5].staffName}
                                                </h3>
                                                <h4 class="name">
                                                        ${command.staffSynthesisDtoList[5].staffCode}
                                                </h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[5].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[5].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[5].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                    </div>
                    <div class="row level4">
                        <div class="col-xs-3">
                            <c:if test="${not empty command.staffSynthesisDtoList[6]}">
                                <section class="panel-group mt-sm mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center" style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 5px;
                                        transform: translate(-50%, 0);">7th</h3>
                                            <img src="/template/admin/images/widget-twitter-profile.jpg" alt=""
                                                 style="height: 37px;">
                                        </div>
                                        <div class="profile-info"
                                             style="display: flex;flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[6].staffPhoto}'/>"
                                                     alt="" style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 110px;
                                             height: 110px;
                                             margin-top: -22px;">
                                            </div>
                                            <div class="text-center">
                                                <h3 class="name">
                                                        ${command.staffSynthesisDtoList[6].staffName}
                                                </h3>
                                                <h4 class="name">
                                                        ${command.staffSynthesisDtoList[6].staffCode}
                                                </h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[6].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[6].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[6].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                        <div class="col-xs-3">
                            <c:if test="${not empty command.staffSynthesisDtoList[7]}">
                                <section class="panel-group mt-sm mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center" style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 5px;
                                        transform: translate(-50%, 0);">8th</h3>
                                            <img src="/template/admin/images/widget-twitter-profile.jpg" alt=""
                                                 style="height: 37px;">
                                        </div>
                                        <div class="profile-info"
                                             style="display: flex;flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[7].staffPhoto}'/>"
                                                     alt="" style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 110px;
                                             height: 110px;
                                             margin-top: -22px;">
                                            </div>
                                            <div class="text-center">
                                                <h3 class="name">
                                                        ${command.staffSynthesisDtoList[7].staffName}
                                                </h3>
                                                <h4 class="name">
                                                        ${command.staffSynthesisDtoList[7].staffCode}
                                                </h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[7].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[7].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[7].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                        <div class="col-xs-3">
                            <c:if test="${not empty command.staffSynthesisDtoList[8]}">
                                <section class="panel-group mt-sm mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center" style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 5px;
                                        transform: translate(-50%, 0);">9th</h3>
                                            <img src="/template/admin/images/widget-twitter-profile.jpg" alt=""
                                                 style="height: 37px;">
                                        </div>
                                        <div class="profile-info"
                                             style="display: flex;flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[8].staffPhoto}'/>"
                                                     alt="" style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 110px;
                                             height: 110px;
                                             margin-top: -22px;">
                                            </div>
                                            <div class="text-center">
                                                <h3 class="name">
                                                        ${command.staffSynthesisDtoList[8].staffName}
                                                </h3>
                                                <h4 class="name">
                                                        ${command.staffSynthesisDtoList[8].staffCode}
                                                </h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[8].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[8].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[8].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                        <div class="col-xs-3">
                            <c:if test="${not empty command.staffSynthesisDtoList[9]}">
                                <section class="panel-group mt-sm mb-sm">
                                    <div class="widget-twitter-profile">
                                        <div class="top-image" style="position: relative">
                                            <h3 class="text-center" style="position: absolute;
                                        left: 50%;
                                        color: white;
                                        margin-top: 5px;
                                        transform: translate(-50%, 0);">10th</h3>
                                            <img src="/template/admin/images/widget-twitter-profile.jpg" alt=""
                                                 style="height: 37px;">
                                        </div>
                                        <div class="profile-info"
                                             style="display: flex;flex-direction: column">
                                            <div class="profile-picture"
                                                 style="display: flex; justify-content: center; margin-right:0px">
                                                <img src="<c:url value='/resource/${command.staffSynthesisDtoList[9].staffPhoto}'/>"
                                                     alt="" style="border-radius: 50%;
                                             object-fit: contain;
                                             width: 110px;
                                             height: 110px;
                                             margin-top: -22px;">
                                            </div>
                                            <div class="text-center">
                                                <h3 class="name">
                                                        ${command.staffSynthesisDtoList[9].staffName}
                                                </h3>
                                                <h4 class="name">
                                                        ${command.staffSynthesisDtoList[9].staffCode}
                                                </h4>
                                            </div>
                                            <ul class="profile-stats">
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.reward"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[9].totalReward}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.punishment"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[9].totalPunishment}</h4>
                                                </li>
                                                <li>
                                                    <h5 class="stat text-uppercase text-center"><spring:message
                                                            code="label.record.total"/></h5>
                                                    <h4 class="count text-center">${command.staffSynthesisDtoList[9].totalResult}</h4>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </section>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="panel panel-featured">
        <header class="panel-heading">
            <h2 class="panel-title">
                <spring:message code="label.board.depart"/>
            </h2>
        </header>
        <div class="panel-body">
            <!-- Morris: Bar -->
            <div class="chart chart-md" id="morrisBar"></div>
            <div class="row pt-xlg">
                <div class="col-xs-12">
                    <table class="table table-bordered table-striped mb-none" id="datatable-default">
                        <thead>
                        <tr>
                            <th><spring:message code="label.numerical.order"/></th>
                            <th><spring:message code="label.depart.name"/></th>
                            <th><spring:message code="label.synthesis.total.reward"/></th>
                            <th><spring:message code="label.synthesis.total.punishment"/></th>
                            <th><spring:message code="label.synthesis.total.result"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="departSynthesisDto" items="${command.departSynthesisDtoList}" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${departSynthesisDto.departName}</td>
                                <td>${departSynthesisDto.totalReward}</td>
                                <td>${departSynthesisDto.totalPunishment}</td>
                                <td>${departSynthesisDto.totalResult}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
    <!-- end: page -->
</section>
<content tag="specific_script">
    <script src="<c:url value='/template/admin/vendor/jquery-datatables/jquery.dataTables.min.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/jquery-datatables-bs3/assets/js/datatables.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/raphael/raphael.js'/>"></script>
    <script src="<c:url value='/template/admin/vendor/morris/morris.min.js'/>"></script>
</content>

<content tag="local_script">
    <script type="application/javascript">
        $(document).ready(function () {
            var morrisBarData = [];

            <c:forEach var="depart" items="${command.departSynthesisDtoList}">
            morrisBarData.push({
                departName: '${depart.departName}',
                totalReward: ${depart.totalReward},
                totalPunishment: ${depart.totalPunishment},
                totalResult: ${depart.totalResult}
            });
            </c:forEach>

            /*
            Morris: Bar
            */
            Morris.Bar({
                resize: true,
                element: 'morrisBar',
                data: morrisBarData,
                xkey: 'departName',
                ykeys: ['totalResult'],
                labels: ['<spring:message code="label.board.depart"/>', 'Series B'],
                hideHover: true,
                barColors: ['#016BA1'],
                horizontal: true,
                hoverCallback: function(index, options, content) {
                    var data = options.data[index];
                    var htmlContent = "<div class='morris-hover-row-label' style='color: #e63b35;'>" + data.departName + "</div>" +
                        "<div class='morris-hover-point' style='color: #016BA1;'>" +
                        "<spring:message code='label.synthesis.total.reward'/>: " + data.totalReward + "</div>" +
                        "<div class='morris-hover-point' style='color: #016BA1;'>" +
                        "<spring:message code='label.synthesis.total.punishment'/>: " + data.totalPunishment + "</div>" +
                        "<div class='morris-hover-point' style='color: #016BA1;'>" +
                        "<spring:message code='label.synthesis.total.result'/>: " + data.totalResult + "</div>";
                    return(htmlContent);
                },
            });
        })
    </script>
</content>
</body>
</html>
