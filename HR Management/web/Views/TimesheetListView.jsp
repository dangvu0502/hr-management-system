<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Timesheet List</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="../css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- google font -->
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
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="index.html" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Home
            </a>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <!--                        <div class="pull-left image">
                                                    <img src="img/avatar3.png" class="img-circle" alt="User Image" />
                                                </div>-->
                        <div class="pull-left info">
                            <p>Hello, Jane</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- search form -->
                    <form action="#" method="get" class="sidebar-form">
                        <div class="input-group">
                            <input type="text" name="q" class="form-control" placeholder="Search..."/>
                            <span class="input-group-btn">
                                <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                    <ul class="sidebar-menu">
                        <li>
                            <a href="index.html">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                        </li>
                        <li>
                            <a href="general.html">
                                <i class="fa fa-gavel"></i> <span>General</span>
                            </a>
                        </li>
                        <li>
                            <a href="basic_form.html">
                                <i class="fa fa-globe"></i> <span>Basic Elements</span>
                            </a>
                        </li>

                        <li class="active">
                            <a href="simple.html">
                                <i class="fa fa-glass"></i> <span>Simple tables</span>
                            </a>
                        </li>

                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel">
                                <header class="panel-heading">
                                    Timesheet List
                                </header>
                                <!-- <div class="box-header"> -->
                                <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                <!-- </div> -->
                                <div class="panel-body table-responsive">
                                    <div class="box-tools m-b-15">
                                        <div class="row">
                                            <div class="col-lg-8">
                                                <form action="Setting" method="post" >
                                                    <div class="input-group">
                                                        <button type="submit" class="btn btn-md btn-primary" style="width: 150px; " disabled>Search by title</button>
                                                        <input type="text" name="table_search" class="form-control input-md" style="width: 450px;" placeholder="Search" onclick="dateHideShow()"/>
                                                        <button type="submit" class="btn btn-md btn-default"><i class="fa fa-search"></i></button>
                                                        <br>
                                                        <div id="advanced" style="display: none">
                                                            <div class="row">
                                                                <div class="col-lg-2"></div>
                                                                <div class="col-lg-8">
                                                                    <div class="col-md-1"></div>
                                                                    <div class="col-md-7">
                                                                        <label class="text-left" for="from-date" style="width: 150px;">From</label><br>
                                                                        <input type="date" class="form-control" id="from-date" style="width: 200px;" name="from-date" placeholder="From">
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label class="text-left" for="to-date" style="width: 150px;">To</label><br>
                                                                        <input type="date" class="form-control" id="to-date" style="width: 200px;" name="to-date" placeholder="From">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-lg-2"></div>
                                                                <div class="col-lg-8">
                                                                    <div class="col-md-1"></div>
                                                                    <div class="col-md-7">
                                                                        <label class="text-left" for="project-filter" style="width: 150px;">Project</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="project-filter" id="project-filter">
                                                                            <option value="project">Project</option>
                                                                            <option value="project">Project</option>
                                                                            <option value="project">Project</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label class="text-left" for="process-filter" style="width: 150px;">Process</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="process-filter" id="process-filter">
                                                                            <c:forEach var="process" items="${timesheetProcess}">
                                                                                <option value="${process.key}">${process.value}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="col-lg-2"></div>
                                            <div class="col-lg-2">
                                                <form action="https://google.com">
                                                    <input class="btn btn-md btn-primary" type="submit" value="Add new timesheet" />
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-body">
                                        <table class="table table-hover" id="timesheet-list">
                                            <tr>
                                                <th style="width: 10%">#</th>
                                                <th style="width: 13%">Timesheet Date</th>
                                                <th style="width: 16%">Timesheet Title</th>
                                                <th style="width: 13%">Project</th>
                                                <th style="width: 13%">Process</th>
                                                <th style="width: 13%">Duration</th>
                                                <th style="width: 13%">Status</th>
                                                <th style="width: 13%"></th>
                                            </tr>
                                            <c:forEach var="timesheet" items="${timesheetList}"  varStatus="theCount">
                                                <tr  onclick="window.open('http://localhost:8080/HR_Management/Timesheet/TimesheeList', '_blank')">
                                                    <td>${theCount.count}</td>
                                                    <td>${timesheet.date}</td>
                                                    <td>${timesheet.title}</td>
                                                    <td>${timesheet.project_code}</td>
                                                    <td>${timesheetProcess[timesheet.process]}</td>
                                                    <td>${timesheet.duration}</td>
                                                    <td>
                                                        <c:if test="${timesheet.status == 1}">
                                                            <span class="label label-warning">${timesheetStatus[timesheet.status]}</span>
                                                        </c:if>
                                                        <c:if test="${timesheet.status == 2}">
                                                            <span class="label label-success">${timesheetStatus[timesheet.status]}</span>
                                                        </c:if>
                                                        <c:if test="${timesheet.status == 3}">
                                                            <span class="label label-danger">${timesheetStatus[timesheet.status]}</span>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <a href="#" class="btn btn-md btn-default"><i class="fa fa-trash-o"></i></a>
                                                            <c:if test="${timesheet.status != 2}">
                                                            <a href="#" class="btn btn-md btn-default"><i class="fa fa-pencil"></i></a>
                                                            </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        <div class="table-foot">
                                            <ul class="pagination pagination-sm no-margin pull-right">
                                                <c:forEach begin="1" end="3" var="p">
                                                    <li><button id="page-number" class="btn btn-sm btn-default">${p}</button></li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>
                </section><!-- /.content -->
                <div class="footer-main">
                    Copyright &copy Director, 2014
                </div>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="../js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="../js/Director/app.js" type="text/javascript"></script>
        <script>
                                                    function dateHideShow() {
                                                        var x = document.getElementById("advanced");
                                                        if (x.style.display === "none") {
                                                            x.style.display = "block";
                                                        } else {
                                                            x.style.display = "none";
                                                        }
                                                    }

                                                    $(document).on("click", "#page-number", function () { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
                                                        $.get("http://localhost:8080/HR_Management/Timesheet/TimesheetList?page=1",function () {
                                                            $( "#timesheet-list" ).load( "http://localhost:8080/HR_Management/Timesheet/TimesheetList?page=1 #timesheet-list" );
                                                        });
                                                        alert("Clicked");
                                                        });
                                             
        </script>
    </body>
</html>
