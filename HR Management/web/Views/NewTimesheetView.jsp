
<%-- 
    Document   : Test
    Created on : Jan 12, 2022, 8:30:25 AM
    Author     : dangGG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Timesheet</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="../css/ionicons.min.css" rel="stylesheet" type="text/css" />

        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="../css/style.css" rel="stylesheet" type="text/css" />


        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>

    <body class="skin-black">
        <div class="wrapper row-offcanvas row-offcanvas-left">

            <!-- Main content -->
            <section class="content">
                <div class="row">
                    <div  class="col-lg-3"></div>

                    <div class="col-lg-6 ">
                        <section class="panel">
                            <header class="panel-heading text-center">
                                <c:choose>
                                    <c:when test="${timesheet == null}">
                                        New Timesheet
                                    </c:when>
                                    <c:otherwise>
                                        Edit Timesheet
                                    </c:otherwise>
                                </c:choose>
                            </header>
                            <div class="panel-body">
                                <c:choose>
                                    <c:when test="${timesheet == null}">
                                        <c:set var = "action"  value = "/HR_Management/Timesheet/NewTimesheet"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var = "action"  value = "/HR_Management/Timesheet/EditTimesheet?id=${timesheet.id}"/>
                                    </c:otherwise>
                                </c:choose>
                                <form action="${action}" method="POST" role="form" onsubmit="return chooseProject && chooseProcess && isValid && cf">
                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8">
                                            <c:if test="${successMessage != null}">
                                                <div class="message alert alert-success" role="alert">
                                                    <p class="mb-0">${successMessage}</p>
                                                </div>
                                                <c:remove var="successMessage" scope="session" /> 
                                            </c:if>
                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="title">Title</label>
                                                    <c:choose>
                                                        <c:when test="${timesheet == null}">
                                                            <input type="text" class="form-control" id="title" name="title" placeholder="Enter timesheet title" required>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="text" class="form-control" id="title" name="title" placeholder="Enter timesheet title" required value="${timesheet.title}">
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                            <div class="row ">
                                                <div class="form-group col-lg-6">
                                                    <label for="date">Date</label>
                                                    <c:choose>
                                                        <c:when test="${timesheet == null}">                                                       
                                                            <input type="date" class="form-control" id="date" name="date" required>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="date" class="form-control" id="date" name="date" required value="${viDate}">
                                                        </c:otherwise>
                                                    </c:choose>

                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label for="duration">Duration (hours : minutes)</label>
                                                    <c:choose>
                                                        <c:when test="${timesheet == null}">
                                                            <input type="text" class="form-control" id="duration" onkeyup="validate()" name="duration" placeholder="Ex: 1:30" required>
                                                            <span id='isValidHour'></span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="text" class="form-control" id="duration"  name="duration" placeholder="Ex: 1:30" required value="${timesheet.duration}">
                                                            <span id='isValidHour'></span>
                                                        </c:otherwise>    
                                                    </c:choose> 
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-lg-6" >
                                                    <label for="project">Project</label>
                                                    <select class="form-control text-bold" aria-label="" id="project" name="project" onchange="val1()">
                                                        
                                                        <c:forEach var="project" items="${projects}">
                                                            <c:choose>
                                                                <c:when test="${timesheet != null && timesheet.project_code == project}">
                                                                    <option value="${project}" selected>${project}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${project}">${project}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>       
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-6" >
                                                    <label for="process">Process</label>
                                                    <select class="form-control text-bold" aria-label="" id="process" name="process" onchange="val2()">
                                                       
                                                        <c:forEach var="process" items="${timesheetProcess}">
                                                            <c:choose>
                                                                <c:when test="${timesheet != null && timesheet.process == process.key}">
                                                                    <option value="${process.key}" selected>${process.value}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${process.key}">${process.value}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <c:if test="${timesheet != null}">
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="work-result">Work result</label><p></p>
                                                        <textarea rows="10" cols="60" id="work-result" name="work-result" style=" resize: vertical;" >${timesheet.work_result}</textarea>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <c:if test="${timesheet == null}">
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="work-result">Work result</label><p></p>
                                                        <textarea rows="10" cols="60" id="work-result" name="work-result" style=" resize: vertical;" ></textarea>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <c:choose>
                                                <c:when test="${timesheet == null}">
                                                    <div class=" form-group col-lg-12 text-center">
                                                        <br>
                                                        <button type="submit" id="submit-btn" class="btn btn-info"  >Add new timesheet</button>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class=" form-group col-lg-12 text-center">
                                                        <br>
                                                        <button type="submit" id="submit-btn" class="btn btn-info" onclick="confirmEdit()">Save</button>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </section>
                    </div>
                </div>
            </section>

        </div>
        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="../js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>

        <!-- Director App -->
        <script src="../js/Director/app.js" type="text/javascript"></script>
        <script src="../js/Director/myScript.js" type="text/javascript"></script>
        <script>
                                                        /** HIDE ALERT**/
                                                        $(document).click(function (e) {
                                                            $('.message').hide();
                                                        });
                                                        /** HIDE ALERT**/

                                                        var chooseProject = false;
                                                        function val1() {
                                                            var value = document.getElementById("project").value;
                                                            if (value != 0)
                                                                chooseProject = true;
                                                            else
                                                                chooseProject = false;
                                                        }

                                                        var chooseProcess = false;
                                                        function val2() {
                                                            var value = document.getElementById("process").value;
                                                            if (value != 0)
                                                                chooseProcess = true;
                                                            else
                                                                chooseProcess = false;
                                                        }

                                                        var isValid = false;
                                                        function validate() {
                                                            var duration = document.getElementById('duration');
                                                            console.log(duration.value.toString());
                                                            var regex = /^[0-9]+:[0-9][0-9]$/;
                                                            if (duration == null || duration.value.toString().length == 0) {
                                                                document.getElementById('isValidHour').innerHTML = '';
                                                            } else {

                                                                if (duration.value.match(regex)) {
                                                                    isValid = true;
                                                                    document.getElementById('isValidHour').style.color = 'green';
                                                                    document.getElementById('isValidHour').innerHTML = 'Valid';
                                                                } else {
                                                                    isValid = false;
                                                                    document.getElementById('isValidHour').style.color = 'red';
                                                                    document.getElementById('isValidHour').innerHTML = 'Not Valid';
                                                                }
                                                            }
                                                        }
                                                        
                                                        var cf = true;
                                                        function confirmEdit(){
                                                            cf = confirm("Confirm Edit");
                                                        }

                                                        $(document).ready(function () {
                                                            validate();
                                                            val1();
                                                            val2();
                                                           
                                                        });
        </script>

    </body>
</html>





























