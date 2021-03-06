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
                                                <form action="" method="post" >
                                                    <div class="input-group">
                                                        <div class="btn btn-md btn-default" style="width: 150px; pointer-events: none;"><span>Search by Title</span></div>
                                                        <input id="timesheetTitle" type="text" name="timesheetTitle" class="form-control input-md" style="width: 450px;" placeholder="Enter title to search" onclick="dateHideShow()"/>
                                                        <div id="advanced" style="display: none">
                                                            <br>
                                                            <div class="row">
                                                                <div class="col-lg-2"></div>
                                                                <div class="col-lg-8">
                                                                    <div class="col-md-1"></div>
                                                                    <div class="col-md-7">
                                                                       <label class="text-left" for="projectFilter" style="width: 150px;">Username</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="projectFilter" id="projectFilter">
                                                                            <option value="">Choose Username</option>
                                                                            <c:forEach var="project" items="${projects}">
                                                                                <option value="${project}">${project}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <br>
                                                            <div class="row">
                                                                <div class="col-lg-2"></div>
                                                                <div class="col-lg-8">
                                                                    <div class="col-md-1"></div>
                                                                    <div class="col-md-7">
                                                                        <label class="text-left" for="fromDate" style="width: 150px;">From</label><br>
                                                                        <input type="date" class="form-control" id="fromDate" style="width: 200px;" name="fromDate">
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label class="text-left" for="toDate" style="width: 150px;">To</label><br>
                                                                        <input type="date" class="form-control" id="toDate" style="width: 200px;" name="toDate">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <br>
                                                            <div class="row">
                                                                <div class="col-lg-2"></div>
                                                                <div class="col-lg-8">
                                                                    <div class="col-md-1"></div>
                                                                    <div class="col-md-7">
                                                                        <label class="text-left" for="projectFilter" style="width: 150px;">Project</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="projectFilter" id="projectFilter">
                                                                            <option value="">Choose Project</option>
                                                                            <c:forEach var="project" items="${projects}">
                                                                                <option value="${project}">${project}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label class="text-left" for="processFilter" style="width: 150px;">Process</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="processFilter" id="processFilter">
                                                                            <option value="0">Choose Process</option>
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
                                            
                                        </div>
                                    </div>
                                    <div class="panel-body" id="timesheetTable">
                                        <table class="table table-hover">
                                            <tr>
                                                <th style="width: 10%">ID</th>
                                                <th style="width: 10%">User name</th>
                                                <th style="width: 13%">Timesheet Date</th>
                                                <th style="width: 16%">Timesheet Title</th>
                                                <th style="width: 13%">Project</th>
                                                <th style="width: 10%">Process</th>
                                                <th style="width: 10%">Duration</th>
                                                <th style="width: 10%">Status</th>
                                                <th style="width: 13%"></th>
                                            </tr>

                                            <tr id="timesheet${timesheet.id}">
                                                <td style=" cursor: pointer;" onclick="window.open('http://localhost:8080/HR_Management/Timesheet/TimesheetDetail?id=${timesheet.id}', '_blank')" >1</td>
                                                <td>DangGG</td>
                                                <td>05-02-2001</td>
                                                <td>First timesheet</td>
                                                <td>HRM</td>
                                                <td>design</td>
                                                <td>3:00</td>
                                                <td>
                                                    <c:if test="${timesheet.status == 1}">
                                                        <span class="label label-warning">${timesheetStatus[timesheet.status]}</span>
                                                    </c:if>

                                                    <span class="label label-success">submitted</span>

                                                    <c:if test="${timesheet.status == 3}">
                                                        <span class="label label-danger">${timesheetStatus[timesheet.status]}</span>
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <a href="#" class="btn btn-md btn-default"  data-toggle="modal" data-target="#exampleModalCenter"  title="reject"><i class="fa fa-ban"></i></a>
                                                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalCenterTitle">Rejected reason</h5>
                                                                 
                                                                </div>
                                                                <div class="modal-body">
                                                                    <textarea rows="5" cols="75" id="reject-reason" name="reject-reason" style="resize: vertical; ">${timesheet.reject_reason}</textarea>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                    <button type="button" class="btn btn-danger">Reject</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <c:if test="${timesheet.status != 2}">
                                                        <a href="#" class="btn btn-md btn-default" onclick="window.open('http://localhost:8080/HR_Management/Timesheet/EditTimesheet?id=${timesheet.id}', '_blank')" ><i class="fa fa-check"></i></a>
                                                        </c:if>


                                                </td>
                                            </tr>

                                        </table>
                                        <!-- Modal -->

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

                                                            function page(number) {
                                                                var pageNumber = number;
                                                                var fromDate = document.getElementById('fromDate').value;
                                                                var toDate = document.getElementById('toDate').value;
                                                                var process = document.getElementById('processFilter').value;
                                                                var project = document.getElementById('projectFilter').value;
                                                                var title = document.getElementById('timesheetTitle').value;
                                                                var link = "http://localhost:8080/HR_Management/Timesheet/TimesheetList?";
                                                                link += "page=" + pageNumber;
                                                                link += "&";
                                                                link += "fromDate=" + fromDate;
                                                                link += "&";
                                                                link += "toDate=" + toDate;
                                                                link += "&";
                                                                link += "process=" + process;
                                                                link += "&";
                                                                link += "project=" + project;
                                                                link += "&";
                                                                link += "title=" + title;
                                                                $('#timesheetTable').load(link + " " + "#timesheetTable");
                                                            }

                                                            function deleteTimesheet(id) {
                                                                var cf = confirm("Are you sure to delete?");
                                                                var pageNumber = document.getElementById('page-active').innerHTML;
                                                                var fromDate = document.getElementById('fromDate').value;
                                                                var toDate = document.getElementById('toDate').value;
                                                                var process = document.getElementById('processFilter').value;
                                                                var project = document.getElementById('projectFilter').value;
                                                                var title = document.getElementById('timesheetTitle').value;
                                                                if (cf) {
                                                                    //Logic to delete the item
                                                                    $.ajax({

                                                                        type: "POST",

                                                                        url: "http://localhost:8080/HR_Management/Timesheet/DeleteTimesheet",

                                                                        data: {id: id,
                                                                            fromDate: fromDate,
                                                                            toDate: toDate,
                                                                            process: process,
                                                                            project: project,
                                                                            title: title,
                                                                            page: pageNumber},

                                                                        success: function (number) {
                                                                            page(number);
                                                                        }

                                                                    });


                                                                }
                                                            }


                                                            $(document).ready(function () {
                                                                $('#fromDate').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#toDate').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#processFilter').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#projectFilter').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#timesheetTitle').keyup(function () {
                                                                    page(1);
                                                                });

                                                                $('#myModal').on('shown.bs.modal', function () {
                                                                    $('#myInput').trigger('focus')
                                                                })


                                                            });




        </script>
    </body>
</html>
