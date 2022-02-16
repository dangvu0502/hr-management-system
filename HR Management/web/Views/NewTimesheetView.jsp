
<%-- 
    Document   : Test
    Created on : Jan 12, 2022, 8:30:25 AM
    Author     : dangGG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Timesheet Detail</title>
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
                                New Timesheet
                            </header>
                            <div class="panel-body">
                                <form action="/HR_Management/Timesheet/NewTimesheet" method="POST" role="form" onsubmit="return chooseProject && chooseProcess && isValid">
                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8">

                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="title">Title</label>
                                                    <input type="text" class="form-control" id="title" name="title" placeholder="Enter timesheet title" required>
                                                </div>
                                            </div>
                                            <div class="row ">
                                                <div class="form-group col-lg-6">
                                                    <label for="date">Date</label>
                                                    <input type="date" class="form-control" id="date" name="date"  placeholder="Enter your username" required>

                                                </div>
                                                <div class="form-group col-lg-6">
                                                    <label for="duration">Duration (hour : minute)</label>
                                                    <input type="text" class="form-control" id="duration" onkeyup="validate()" name="duration" placeholder="Ex: 1:30" required>
                                                    <span id='isValidHour'></span> 
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-lg-6" >
                                                    <label for="project">Project</label>
                                                    <select class="form-control text-bold" aria-label="" id="project" name="project" onchange="val1()">
                                                        <option value="0" selected></option>
                                                        <option value="HRM">HRM</option>        
                                                    </select>
                                                </div>
                                                <div class="form-group col-lg-6" >
                                                    <label for="process">Process</label>
                                                    <select class="form-control text-bold" aria-label="" id="process" name="process" onchange="val2()">
                                                        <option value="0" selected></option>
                                                        <c:forEach var="process" items="${timesheetProcess}">
                                                            <option value="${process.key}">${process.value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>



                                            <div class=" form-group col-lg-12 text-center">
                                                <br>
                                                <button type="submit" id="submit-btn" class="btn btn-info"  >Add new timesheet</button>
                                            </div>

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
        </script>

    </body>
</html>





























