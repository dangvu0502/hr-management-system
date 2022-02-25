<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Timesheet Review</title>
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
                                                                        <label class="text-left" for="usernameFilter" style="width: 150px;">Username</label><br>
                                                                        <input id="usernameFilter" class="form-control input-md" style="width: 200px;" type=text list="usernames" >
                                                                        <datalist id="usernames" hidden>

                                                                            <c:forEach var="user" items="${users}">
                                                                                <option value="${user.username}">${user.username}</option>
                                                                            </c:forEach>
                                                                        </datalist>
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
                                        <table class="table table-hover" id="table-content">
                                            <tr>
                                                <th style="width: 8%">ID</th>
                                                <th></th>
                                                <th style="width: 10%">Username</th>
                                                <th style="width: 13%">Timesheet Date</th>
                                                <th style="width: 16%">Timesheet Title</th>
                                                <th style="width: 13%">Project</th>
                                                <th style="width: 10%">Process</th>
                                                <th style="width: 10%">Duration</th>
                                                <th style="width: 10%">Status</th>
                                                <th style="width: 13%"></th>
                                            </tr>
                                        </table>


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
                                                                var fromDate = document.getElementById('fromDate').value;
                                                                var toDate = document.getElementById('toDate').value;
                                                                var process = document.getElementById('processFilter').value;
                                                                var project = document.getElementById('projectFilter').value;
                                                                var title = document.getElementById('timesheetTitle').value;
                                                                var username = document.getElementById('usernameFilter').value;
                                                                console.log(username);
                                                                $.ajax({

                                                                    type: "POST",
                                                                    data: {
                                                                        page: number,
                                                                        title: title,
                                                                        username: username,
                                                                        fromDate: fromDate,
                                                                        toDate: toDate,
                                                                        process: process,
                                                                        project: project
                                                                    },
                                                                    url: "http://localhost:8080/HR_Management/Timesheet/GetAllTimeSheetInGroup",
                                                                    success: function (responseJson) {

                                                                        if (responseJson != null) {
                                                                            $("#timesheetTable").find("tr:gt(0)").remove();
                                                                            var table = $("#table-content");
                                                                            $.each(responseJson, function (key, value) {
                                                                                console.log(value['id']);
                                                                                var rowNew = "";
                                                                                rowNew += `<tr>`;
                                                                                rowNew += `<td style=" cursor: pointer;" data-toggle="modal" data-target="#exampleModalCenter` + (value['id']) + `quickView" >` + value['id'] + `</td>`;
                                                                                rowNew += `<td> <div class="modal fade" id="exampleModalCenter` + (value['id']) + `quickView" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">`;
                                                                                rowNew += ` 
                                                <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                            </div>
                                                            <div class="modal-body">
                                                                <section class="panel">
                                                                    <header class="panel-heading text-center">
                                                                        ` + value['title'] + `
                                                                    </header>
                                                                    <div class="panel-body">
                                                                        <div class="row">
                                                                            <div class="col-lg-1"></div>
                                                                            <div class="col-lg-8">
                                                                                <div class="row">
                                                                                    <div class="form-group col-lg-12">
                                                                                        <p class="text-bold" >Project: &nbsp ` + value['project_code'] + `</p>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="form-group col-lg-7">
                                                                                        <p class="text-bold" >Process: &nbsp ` + value['process_value'] + `</p>
                                                                                    </div>
                                                                                    <div class="form-group col-lg-5">
                                                                                        <p class="text-bold" >Date: &nbsp ` + value['date'].split("-").reverse().join("-") + `</p>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="form-group col-lg-7">`;
                                                                                if (value['status_value'] == 'rejected')
                                                                                    rowNew += `<p class="text-bold" style="pointer-events: none;">Status: &nbsp <span  class="text-danger">` + value['status_value'] + `</span></p>`;
                                                                                else if (value['status_value'] == 'approved')
                                                                                    rowNew += `<p class="text-bold" style="pointer-events: none;" >Status: &nbsp <span class="text-success">` + value['status_value'] + `</span></p>`;
                                                                                else
                                                                                    rowNew += `<p class="text-bold" style="pointer-events: none;">Status: &nbsp <span class="text-warning">` + value['status_value'] + `</span></p>`;
                                                                                rowNew += `</div>
                                                                                <div class="form-group col-lg-5">
                                                                                        <p class="text-bold" >Duration: &nbsp` + value['duration'] + `</p>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row ">
                                                                                    <div class="form-group col-lg-12">
                                                                                        <label for="work-result">Work result</label><p></p>
                                                                                        <textarea rows="10" cols="60" id="work-result" name="work-result" style=" resize: vertical;" disabled>` + (value['work_result'] == null ? "" : value['work_result']) + `</textarea>
                                                                                    </div>
                                                                                    <div class="form-group col-lg-12">`;
                                                                                if (value['status_value'] == 'rejected')
                                                                                    rowNew += `<label for="reject-reason">Reject reason</label><p></p>
                                                                                            <textarea rows="5" cols="60" id="reject-reason" name="reject-reason" style="  resize: vertical; " disabled>` + (value['reject_reason'] == null ? "" : value['reject_reason']) + `</textarea>`;
                                                                                rowNew += `
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>                                                                     
                                                                </section>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal" >Close</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div> </td>`;
                                                                                rowNew += `<td>` + value['username'] + `</td>`;
                                                                                rowNew += `<td>` + value['date'].split("-").reverse().join("-") + `</td>`;
                                                                                rowNew += `<td>` + value['title'] + `</td>`;
                                                                                rowNew += `<td>` + value['project_code'] + `</td>`;
                                                                                rowNew += `<td>` + value['process_value'] + `</td>`;
                                                                                rowNew += `<td>` + value['duration'] + `</td>`;
                                                                                if (value['status_value'] == 'approved') {
                                                                                    rowNew += `<td>` + `<span class="label label-success">approved</span>` + `</td>`;
                                                                                    rowNew += `<td>
                                                                        <a href="#" class="btn btn-md btn-default"  data-toggle="modal" data-target="#exampleModalCenter` + value['id'] + `"  title="reject"><i class="fa fa-ban"></i></a>
                                                    <div class="modal fade" id="exampleModalCenter` + value['id'] + `" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalCenterTitle">Rejected reason</h5>

                                                                </div>
                                                                <div class="modal-body">
                                                                    <textarea rows="5" cols="75" id="reject-reason` + value['id'] + `" name="reject-reason" style="resize: vertical; " required></textarea>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                    <button type="button" class="btn btn-danger"  onclick="rejectTimesheet(` + value['id'] + `)" data-dismiss="modal" >Reject</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div> <td>`;
                                                                                } else if (value['status_value'] == 'rejected') {
                                                                                    rowNew += `<td>` + `<span class="label label-danger">rejected</span>` + `</td>`;
                                                                                    rowNew += `<td><a href="#" class="btn btn-md btn-default" onclick="approveTimesheet(` + value['id'] + `)" ><i class="fa fa-check"></i></a></td>`;
                                                                                } else {
                                                                                    rowNew += `<td>` + `<span class="label label-warning">submitted</span>` + `</td>`;
                                                                                    rowNew += `<td>
                                                    <a href="#" class="btn btn-md btn-default"  data-toggle="modal" data-target="#exampleModalCenter` + value['id'] + `"  title="reject"><i class="fa fa-ban"></i></a>
                                                    <div class="modal fade" id="exampleModalCenter` + value['id'] + `" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalCenterTitle">Rejected reason</h5>

                                                                </div>
                                                                <div class="modal-body">
                                                                    <textarea rows="5" cols="75" id="reject-reason` + value['id'] + `" name="reject-reason" style="resize: vertical; " required></textarea>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                    <button type="button" class="btn btn-danger" onclick="rejectTimesheet(` + value['id'] + `)" data-dismiss="modal">Reject</button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>                                                 
                                                        <a href="#" class="btn btn-md btn-default" onclick="approveTimesheet(` + value['id'] + `)" ><i class="fa fa-check"></i></a>
                                                                              </td>`;
                                                                                }

                                                                                rowNew += `</tr>`;
                                                                                table.append(rowNew);
                                                                            });
                                                                        }
                                                                    }

                                                                });
                                                            }

                                                            function rejectTimesheet(id) {

                                                                var reject_reason = document.getElementById('reject-reason' + id).value;
                                                                var check = false;
                                                                if (reject_reason == "") {
                                                                    alert("Reject reason must not be empty");
                                                                } else {
                                                                    $.ajax({

                                                                        type: "Post",

                                                                        url: "http://localhost:8080/HR_Management/Timesheet/Reject",

                                                                        data: {id: id,
                                                                            reject_reason: reject_reason},

                                                                        success: function () {
                                                                            setTimeout(function () {
                                                                                page(1);
                                                                            }, 1000);
                                                                        }

                                                                    });
                                                                }



                                                            }

                                                            function approveTimesheet(id) {
                                                                $.ajax({

                                                                    type: "Post",

                                                                    url: "http://localhost:8080/HR_Management/Timesheet/Approve",

                                                                    data: {id: id},

                                                                    success: function () {
                                                                        page(1);
                                                                    }

                                                                });
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
                                                                $('#usernameFilter').keyup(function () {
                                                                    page(1);
                                                                });
                                                                page(1);
                                                            });




        </script>
    </body>
</html>
