<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<lesson:page title="student.title.list">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="welcome.do">
                    <spring:message code="common.homepage"/>
                </a>
            </li>
            <li class="active"><spring:message code="student.title.list"/></li>
        </ul><!-- /.breadcrumb -->
    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <h3 class="header smaller lighter blue">
                            <form action="student/student/search.do">
                                <span style="margin-right:20px">学号</span>
                                <input type="text" name="code" style="margin-right:20px">
                                <span style="margin-right:20px"> 姓名</span>
                                <input type="text" name="name" style="margin-right:30px">
                                <input type="submit" value="搜索">
                                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp(分数录入及选课请到相应学生的修改页面操作/选课后再进入修改页面录入分数)
                            </form>
                            <!--新增按键-->
                            <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="student/student/add.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon glyphicon glyphicon-plus"></i>
                                    <spring:message code="button.add"/>
                                </a>
                            </span>
                        </h3>
                        <table id="simple-table" class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><spring:message code="student.code"/></th>
                                <th><spring:message code="student.name"/></th>
                                <th><spring:message code="student.birthday"/></th>
                                <th><spring:message code="student.gender"/></th>
                                    <%--<th class="hidden-md hidden-sm hidden-xs">--%>
                                <th><spring:message code="student.description"/></th>
                                <th><spring:message code="student.course.num"/></th>
                                <th>平均分</th>
                                <th class="center">详情/修改/删除</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${students}" var="student">
                                <tr id="tr-${student.id}">
                                    <td><a href="student/student/listByCode.do?code=${student.code}"><c:out
                                            value="${student.code}"/></a></td>
                                    <td><a href="student/student/listLikeName.do?name=${student.name}"><c:out
                                            value="${student.name}"/></a></td>
                                    <td>${student.birthday}</td>
                                        <%--<td>${student.gender}</td>--%>
                                    <!--性别-->
                                    <c:if test="${student.gender!=1}">
                                        <td><c:out value="女"/></td>
                                    </c:if>
                                    <c:if test="${student.gender==1}">
                                        <td><c:out value="男"/></td>
                                    </c:if>
                                        <%--<td class="hidden-md hidden-sm hidden-xs">--%>
                                    <!--备注-->
                                    <td> ${student.description}</td>
                                    <!--已选课程数-->
                                    <td>
                                        <c:set var="count" value='0'/><c:set var="j" value="1"/>
                                        <c:forEach items="${courses}" var="course">
                                            <c:forEach items="${student.courses}" var="dnf">
                                                <c:if test="${course.id==dnf.id}">
                                                    <c:set var="count" value="${count+j}"/>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                        <c:out value="${count}"/>
                                    </td>
                                    <!--平均分-->
                                    <td>${student.avgScore}</td>
                                    <!--详情/修改/删除按键-->
                                    <td class="center">
                                        <div class="hidden-sm hidden-xs btn-group action-buttons">
                                            <!--详情按键-->
                                            <a href="student/student/detail.do?id=${student.id}"
                                               class="blue2">
                                                <i class="ace-icon glyphicon glyphicon-list bigger-120"></i>
                                            </a>
                                            <!--修改按键-->
                                            <a href="student/student/edit.do?id=${student.id}"
                                               class="green">
                                                <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                            </a>
                                            <!--删除按键-->
                                            <a data-id="${student.id}"
                                               data-url="student/student/save_delete.do"
                                               class="red btn-delete-modal">
                                                <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                            </a>

                                        </div>
                                        <div class="hidden-md hidden-lg">
                                            <div>
                                                <div class="inline pos-rel">
                                                    <button class="btn btn-minier btn-primary dropdown-toggle"
                                                            data-toggle="dropdown" data-position="auto">
                                                        <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                    </button>
                                                    <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                        <li>
                                                            <a class="green bigger-140 show-details-btn">
                                                                <span class="green"><i
                                                                        class="ace-icon fa fa-angle-double-down"></i></span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="student/student/detail.do?id=${student.id}">
                                                                <span class="blue2"><i
                                                                        class="ace-icon glyphicon glyphicon-list bigger-120"></i></span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="student/student/edit.do?id=${student.id}">
                                                                <span class="green"><i
                                                                        class="ace-icon fa fa-pencil-square-o bigger-120"></i></span>
                                                            </a></li>
                                                        <li>
                                                            <a data-id="${student.id}"
                                                               data-url="student/student/save_delete.do"
                                                               class="red btn-delete-modal">
                                                                <span class="red"><i
                                                                        class="ace-icon fa fa-trash-o bigger-120"></i></span>
                                                            </a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                    </td>
                                </tr>
                                <tr class="detail-row" id="tr-detail-${student.id}">
                                    <td colspan="8" id="td-detail-${student.id}">
                                        <div class="table-detail">
                                            <div class="profile-user-info profile-user-info-striped">
                                                <div class="profile-info-row">
                                                    <div class="profile-info-name"><spring:message
                                                            code="mainmenu.description"/></div>
                                                    <div class="profile-info-value">
                                                        <span><c:out value="${student.description}"/></span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div><!-- /.span -->
                </div><!-- /.row -->
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</lesson:page>
