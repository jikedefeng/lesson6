<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="lesson" tagdir="/WEB-INF/tags" %>
<lesson:page title="student.title.${cmd}">
 <jsp:attribute name="script">
		<link rel="stylesheet" href="static-resource/ace/assets/css/bootstrap-datepicker3.min.css"/>
		<script src="static-resource/ace/assets/js/bootstrap-datepicker.min.js"></script>
<script src="static-resource/ace/assets/js/jquery.bootstrap-duallistbox.min.js"></script>
        <script type="text/javascript">
            jQuery(function ($) {
                var boolean=true;
                $('.date-picker').datepicker({
                    autoclose: true,
                    format: 'yyyy-mm-dd',
                    todayHighlight: true,
                    zIndex: 9999,
                    'z-index': 9999,
                })
                //show datepicker when clicking on the icon
                    .next().on(ace.click_event, function () {
                    $(this).prev().focus();
                });
                $("#studentCode").focus(function () {
                    $(this).next().remove();
                    $("#sscode").test("请输入数字");
                });
                $("#studentCode").blur(function () {
                    var scode=$("#studentCode").val();
                    if (isNaN(scode)){
                        $("#sscode").text("请输入数字");
                        $(this).css("border-color","red");
                        return boolean=false;
                    }else {
                        $("#sscode").remove();
                        $(this).css("border","#a8d2e7 solid");
                        return boolean=true;
                    }
                });
                $("#score").focus(function () {
                    $(this).next().remove();
                    $("#sscore").test("请输入数字");
                });
                $("#score").blur(function () {
                    var scode=$("#studentCode").val();
                    if (isNaN(scode)){
                        $("#sscore").text("请输入数字");
                        $(this).css("border-color","red");
                        return boolean=false;
                    }else {
                        $("#sscore").remove();
                        $(this).css("border","#a8d2e7 solid");
                        return boolean=true;
                    }
                });
            });
        </script>
     <script>
         var demo1 = $('select[name="reqcourses"]').bootstrapDualListbox();

     </script>
    </jsp:attribute>
    <jsp:attribute name="css">
         <style type="text/css">
             .datepicker {
                 z-index: 9999
             }
         </style>
    </jsp:attribute>

    <jsp:body>

        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="welcome.do">
                        <spring:message code="common.homepage"/>
                    </a>
                </li>

                <li>
                    <a href="student/student/list.do">
                        <spring:message code="student.title.list"/>
                    </a>
                </li>
                <li class="active">
                    <spring:message code="student.title.${cmd}"/>
                </li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <h3 class="header smaller lighter blue"><spring:message code="student.title.${cmd}"/>
                                <span class="hidden-sm hidden-xs btn-group pull-right">
                                <a href="student/student/list.do" class="btn btn-sm btn-primary"><i
                                        class="ace-icon fa fa-angle-left"></i>
                                    <spring:message code="common.back"/>
                                </a>
                            </span>
                            </h3>
                            <form action="student/student/save_${cmd}.do" method="post" class="form-horizontal"
                                  role="form">
                                <input type="hidden" name="cmd" id="cmd" value="${cmd}"/>
                                <input type="hidden" name="id" id="id" value="${student.id}"/>
                                <!--学号-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="account">
                                        <spring:message code="student.code"/>
                                    </label>
                                    <div class="col-sm-6">
                                        <input type="text" autocomplete="off" name="code" value="${student.code}"
                                               class="required form-control " id="studentCode">
                                    </div>
                                    <span id="sscode" class="text_tabledetail2" style="color: #b6010e"></span>
                                </div>
                                <!--班级选择-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="icon">
                                        <spring:message code="student.grade.name"/>
                                    </label>
                                    <div class="col-sm-6">
                                        <select name="classId" id="classId" class="col-xs-12 chosen-select">
                                            <option ${student.classId==1 ? "selected" : ""} value="1"><spring:message
                                                    code="student.grade.1"/></option>
                                            <option ${student.classId==2 ? "selected" : ""} value="2"><spring:message
                                                    code="student.grade.2"/></option>
                                            <option ${student.classId==3 ? "selected" : ""} value="3"><spring:message
                                                    code="student.grade.3"/></option>
                                        </select>
                                    </div>
                                </div>
                                <!--填写姓名-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="account">
                                        <spring:message code="student.name"/>
                                    </label>
                                    <div class="col-sm-6">
                                        <input type="text" autocomplete="off" name="name" value="${student.name}"
                                               class="required form-control">
                                    </div>
                                </div>
                                <!--出生日期选择-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="nameEn">
                                        <spring:message code="student.birthday"/>
                                    </label>
                                    <div class="col-sm-6">
                                        <div class="input-group">
                                            <input type="text"
                                                   class="required form-control date-picker datepicker col-xs-12 col-sm-12"
                                                   autocomplete="off" id="birthday" name="birthday"
                                                   value="${student.birthday}" data-date-format="yyyy-mm-dd"
                                                   class="date-picker">
                                            <span class="input-group-addon"><i
                                                    class="fa fa-calendar bigger-110"></i></span>
                                        </div>
                                    </div>
                                </div>
                                <!--性别选择-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="icon">
                                        <spring:message code="student.gender"/>
                                    </label>
                                    <div class="col-sm-6">
                                        <select name="gender" id="gender" class="col-xs-12 chosen-select">
                                            <option ${student.gender==1 ? "selected" : ""} value="1"><spring:message
                                                    code="student.gender.1"/></option>
                                            <option ${student.gender==2 ? "selected" : ""}value="2"><spring:message
                                                    code="student.gender.2"/></option>
                                        </select>
                                    </div>
                                </div>
                                <!--备注填写-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-right" for="description">
                                        <spring:message code="student.description"/>
                                    </label>

                                    <div class="col-sm-6">
                                        <textarea type="text" autocomplete="off" id="description" name="description"
                                                  class="form-control">${student.description}</textarea>
                                    </div>
                                </div>
                                <!--课程选择-->
                                <div class="form-group">
                                    <label class="col-sm-3 control-label no-padding-top" for="duallist"> 课程类别</label>
                                    <div class="col-sm-6">
                                        未选课程(点击相应课程添加)&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp已选课程(点击相应课程移除)
                                        <select multiple="multiple" size="10" id="duallist" name="reqcourses">
                                            <c:forEach items="${courses}" var="course">
                                                <option value="${course.id}"
                                                        <c:forEach items="${student.courses}" var="dnf">
                                                            <c:if test="${course.id==dnf.id}">
                                                                selected="selected"
                                                            </c:if>
                                                        </c:forEach>
                                                >${course.name}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="hr hr-16 hr-dotted"></div>
                                    </div>
                                </div>
                                <!--平均分-->
                                <div class="center" id="scoreMation"><spring:message code="student.score"/></div>
                                <c:set var="count" value='0'/><c:set var="j" value="1"/>
                                <c:forEach items="${student.courses}" var="ssc">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label no-padding-right" for="account">
                                            <c:out value="${ssc.name}"/>
                                            <c:set var="count" value="${count+j}"/>
                                        </label>
                                        <div class="col-sm-6">
                                            <input type="text" autocomplete="off" name="score" value=""
                                                   class="required form-control" id="score">
                                        </div>
                                        <span id="sscore" class="text_tabledetail2" style="color: #b6010e"></span>
                                    </div>
                                </c:forEach>
                                <%--<!--科目数隐藏域-->--%>
                                <%--<input type="hidden" name="courseNum" value="${count}" id="count"/>--%>
                                <%--<!--平均分隐藏域-->--%>
                                <%--<input type="hidden" name="avgscore" value="${student.avgScore}" id="avgscore"/>--%>
                                <sec:authorize ifAnyGranted="OPT_STUDENT_STUDENT_ADD,OPT_STUDENT_STUDENT_EDIT">
                                    <div class="clearfix form-actions">
                                        <div class="text-center">
                                            <button class="btn btn-info" type="submit">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                <spring:message code="button.submit"/>
                                            </button>
                                        </div>
                                    </div>
                                </sec:authorize>
                            </form>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <!-- PAGE CONTENT ENDS -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div>
    </jsp:body>
</lesson:page>