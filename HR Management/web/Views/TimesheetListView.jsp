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
           <%@include file="Header/Treebar.jsp" %>
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
                                                        <br>
                                                        <div id="advanced" style="display: none">
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
                                            <div class="col-lg-2"></div>
                                            <div class="col-lg-2">
                                                <button onclick="window.open('http://localhost:8080/HR_Management/Timesheet/NewTimesheet', '_blank')" class="btn btn-md btn-primary" style="width: 150px; ">Add new timesheet</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-body" id="timesheetTable">
                                        <table class="table table-hover">
                                            <tr>
                                                <th style="width: 10%">ID</th>
                                                <th style="width: 13%">Timesheet Date</th>
                                                <th style="width: 16%">Timesheet Title</th>
                                                <th style="width: 13%">Project</th>
                                                <th style="width: 13%">Process</th>
                                                <th style="width: 13%">Duration</th>
                                                <th style="width: 13%">Status</th>
                                                <th style="width: 13%"></th>
                                            </tr>
                                            <c:forEach var="timesheet" items="${timesheetList}"  varStatus="theCount">
                                                <tr id="timesheet${timesheet.id}">
                                                    <td style=" cursor: pointer;" data-toggle="modal" data-target="#exampleModalCenter${timesheet.id}">${timesheet.id}</td>
                                                <div class="modal fade" id="exampleModalCenter${timesheet.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                            </div>
                                                            <div class="modal-body">
                                                                <section class="panel">
                                                                    <header class="panel-heading text-center">
                                                                        ${timesheet.title}
                                                                    </header>
                                                                    <div class="panel-body">

                                                                        <div class="row">
                                                                            <div class="col-lg-1"></div>
                                                                            <div class="col-lg-8">
                                                                                <div class="row">
                                                                                    <div class="form-group col-lg-12">
                                                                                        <p class="text-bold" >Project: &nbsp ${timesheet.project_code}</p>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="form-group col-lg-7">
                                                                                        <p class="text-bold" >Process: &nbsp ${timesheetProcess[timesheet.process]}</p>
                                                                                    </div>
                                                                                    <div class="form-group col-lg-5">
                                                                                        <p class="text-bold" >Date: &nbsp ${timesheet.date}</p>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row">
                                                                                    <div class="form-group col-lg-7">
                                                                                        <c:if test="${timesheet.status == 1}">
                                                                                            <p class="text-bold" style="pointer-events: none;">Status: &nbsp <span  class="text-warning">${timesheetStatus[timesheet.status]}</span></p>
                                                                                            </c:if>
                                                                                            <c:if test="${timesheet.status == 2}">
                                                                                            <p class="text-bold" style="pointer-events: none;" >Status: &nbsp <span class="text-success">${timesheetStatus[timesheet.status]}</span></p>
                                                                                            </c:if>
                                                                                            <c:if test="${timesheet.status == 3}">
                                                                                            <p class="text-bold" style="pointer-events: none;">Status: &nbsp <span class="text-danger">${timesheetStatus[timesheet.status]}</span></p>
                                                                                            </c:if>
                                                                                    </div>
                                                                                    <div class="form-group col-lg-5">
                                                                                        <p class="text-bold" >Duration: &nbsp ${timesheet.duration}</p>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="row ">
                                                                                    <div class="form-group col-lg-12">
                                                                                        <label for="work-result">Work result</label><p></p>
                                                                                        <textarea rows="10" cols="60" id="work-result" name="work-result" style=" resize: vertical;" disabled>${timesheet.work_result}</textarea>
                                                                                    </div>
                                                                                    <div class="form-group col-lg-12">
                                                                                        <c:if test="${timesheet.status == 3}">
                                                                                            <label for="reject-reason">Reject reason</label><p></p>
                                                                                            <textarea rows="5" cols="60" id="reject-reason" name="reject-reason" style="  resize: vertical; " disabled>${timesheet.reject_reason}</textarea>
                                                                                        </c:if>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>

                                                                </section>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
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
                                                    <a href="#" class="btn btn-md btn-default" onclick="deleteTimesheet(${timesheet.id})"><i class="fa fa-trash-o"></i></a>
                                                        <c:if test="${timesheet.status != 2}">
                                                        <a href="#" class="btn btn-md btn-default" onclick="window.open('http://localhost:8080/HR_Management/Timesheet/EditTimesheet?id=${timesheet.id}', '_blank')" ><i class="fa fa-pencil"></i></a>
                                                        </c:if>
                                                </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        <div class="table-foot" id="table-foot">
                                            <ul class="pagination pagination-sm no-margin pull-right">
                                                <c:if test="${total !=0 }">
                                                    <c:if test="${currentNumber>1}">
                                                        <li><button  class="btn btn-sm btn-primary" onclick="page(${currentNumber-1})"><<</button></li>
                                                        </c:if>
                                                        <c:if test="${currentNumber>3}">
                                                        <li><button class="btn btn-sm btn-default" onclick="page(${1})">1</button></li>
                                                        <li><button  class="btn btn-sm btn-default" onclick="page(${Math.max(end-5,1)})">...</button></li>
                                                        </c:if>
                                                        <c:forEach begin="${begin}" end="${end}" var="num">
                                                            <c:if test="${num == currentNumber}">
                                                            <li><button id="page-active" class="btn btn-sm btn-primary" onclick="page(${num})">${num}</button></li>
                                                            </c:if>
                                                            <c:if test="${num != currentNumber}">
                                                            <li><button  class="btn btn-sm btn-default" onclick="page(${num})">${num}</button></li>
                                                            </c:if>    
                                                        </c:forEach>

                                                    <c:if test="${begin+3 < total}">
                                                        <li><button  class="btn btn-sm btn-default" onclick="page(${begin+3})">...</button></li>
                                                        <li><button  class="btn btn-sm btn-default" onclick="page(${total})">${total}</button></li>
                                                        </c:if>
                                                        <c:if test="${begin+3 >= total && end != total}">
                                                        <li><button  class="btn btn-sm btn-default" onclick="page(${total})">${total}</button></li>
                                                        </c:if>

                                                    <c:if test="${currentNumber < total}">
                                                        <li><button  class="btn btn-sm btn-primary" onclick="page(${currentNumber+1})">>></button></li>  
                                                        </c:if>
                                                    </c:if>
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

//                                                                setInterval(function () {
//                                                                    var number = document.getElementById('page-active').innerHTML;
//                                                                    console.log(number);
//                                                                    page(number);
//                                                                }, 10000);
                                                            });




        </script>
    </body>
</html>
