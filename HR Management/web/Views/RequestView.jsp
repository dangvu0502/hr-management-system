<%-- 
    Document   : RequestView
    Created on : Feb 19, 2022, 10:06:15 AM
    Author     : quocb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Request List</title>
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
                                    Request List
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
                                                        <input id="titles" type="text" name="title" class="form-control input-md" style="width: 450px;" placeholder="Enter title to search" onclick="dateHideShow()"/>
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
                                                                        <label class="text-left" for="name" style="width: 150px;">Support Type</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="name" id="name">
                                                                            <option value="">Support Type</option>
                                                                            <c:forEach var="sp" items="${supportName}" >
                                                                                <option value="${sp.name}">${sp.name}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label class="text-left" for="status" style="width: 150px;">Status</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="status" id="status">
                                                                            <option value="0">Choose Status</option>

                                                                            <option value="1">Submitted</option>


                                                                            <option value="2">Assigned</option>


                                                                            <option value="3">Completed</option>


                                                                            <option value="4">Closed</option>

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
                                                <button onclick="window.open('http://localhost:8080/HR_Management/Request/AddRequest', '_blank')" class="btn btn-md btn-primary" style="width: 150px; ">Add new Request</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-body" id="requestTable">
                                        <table class="table table-hover">
                                            <tr>
                                                <th style="width: 10%">Request Date</th>
                                                <th style="width: 13%">Request Title</th>
                                                <th style="width: 16%">Support Type</th>
                                                <th style="width: 13%">In-charge staff</th>
                                                <th style="width: 13%">Status</th>
                                                <th style="width: 13%">Update Date</th>

                                                <th style="width: 13%">Action</th>
                                            </tr>
                                            <c:forEach var="r" items="${requestList}" >
                                                <tr id="${r.support_type_id}">
                                                    <td>${r.request_date}</td>
                                                    <td>${r.title}</td>
                                                    <td>${r.name.name}</td>
                                                    <td>${r.fullname.fullname}</td>

                                                    <td>
                                                        <c:if test="${r.status == 1}">
                                                            <span class="label label-warning">Submitted</span>
                                                        </c:if>
                                                        <c:if test="${r.status == 2}">
                                                            <span class="label label-success">Assigned</span>
                                                        </c:if>
                                                        <c:if test="${r.status == 3}">
                                                            <span class="label label-success">Completed</span>
                                                        </c:if>
                                                        <c:if test="${r.status == 4}">
                                                            <span class="label label-danger">Closed</span>
                                                        </c:if>
                                                    </td>
                                                    <td>${r.update_date}</td>

                                                    <td>
                                                        <a href="#" class="btn btn-md btn-default" onclick="deleteRequest(${r.id})"><i class="fa fa-trash-o"></i></a>

                                                        <a href="#" class="btn btn-md btn-default" onclick="window.open('http://localhost:8080/HR_Management/Request/EditRequest?id=${r.id}', '_blank')" ><i class="fa fa-pencil"></i></a>

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
                                                                var name = document.getElementById('name').value;
                                                                var status = document.getElementById('status').value;
                                                                var titles = document.getElementById('titles').value;
                                                                var link = "http://localhost:8080/HR_Management/Request/RequestList?";
                                                                link += "page=" + pageNumber;
                                                                link += "&";
                                                                link += "fromDate=" + fromDate;
                                                                link += "&";
                                                                link += "toDate=" + toDate;
                                                                link += "&";
                                                                link += "name=" + name;
                                                                link += "&";
                                                                link += "status=" + status;
                                                                link += "&";
                                                                link += "title=" + titles;
                                                                $('#requestTable').load(link + " " + "#requestTable");
                                                            }


                                                            $(document).ready(function () {
                                                                $('#fromDate').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#toDate').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#name').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#status').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#titles').keyup(function () {
                                                                    page(1);
                                                                });

                                                            });

                                                            function deleteRequest(id) {
                                                                var cf = confirm("Are you sure to delete?");
                                                                var pageNumber = document.getElementById('page-active').innerHTML;
                                                                var fromDate = document.getElementById('fromDate').value;
                                                                var toDate = document.getElementById('toDate').value;
                                                                var name = document.getElementById('name').value;
                                                                var status = document.getElementById('status').value;
                                                                var titles = document.getElementById('titles').value;
                                                                if (cf) {
                                                                    //Logic to delete the item
                                                                    $.ajax({

                                                                        type: "POST",

                                                                        url: "http://localhost:8080/HR_Management/Request/DeleteRequest",

                                                                        data: {id: id,
                                                                            fromDate: fromDate,
                                                                            toDate: toDate,
                                                                            name: name,
                                                                            status: status,
                                                                            title: titles,
                                                                            page: pageNumber},

                                                                        success: function (number) {
                                                                            page(number);
                                                                        }

                                                                    });


                                                                }
                                                            }


        </script>
    </body>
</html>

