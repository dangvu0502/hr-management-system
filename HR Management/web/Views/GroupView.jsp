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
        <title>Group List</title>
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
                                    Group List
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
                                                        <div class="btn btn-md btn-default" style="width: 150px; pointer-events: none;"><span>Search </span></div>
                                                        <input id="fullname" type="text" name="fullname" class="form-control input-md" style="width: 450px;" placeholder="Enter full name to search" onclick="dateHideShow()"/>
                                                        <br>
                                                        <div id="advanced" style="display: none">
                                                            <br>
                                                            <div class="row">
                                                                <div class="col-lg-1"></div>
                                                                <div class="col-lg-9">
                                                                    <div class="col-md-5" style="margin-left: 10px">
                                                                        <label class="text-left" for="delete" style="width: 150px;">Status</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="delete" id="delete">
                                                                            <option value="-1">Choose Type Contract</option>
                                                                            <option value="0">Deleted</option>
                                                                            <option value="1">Pending</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-md-5" style="margin-left: 10px">
                                                                        <label class="text-left" for="parent_group_code" style="width: 150px;">Parent Group Code</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="parent_group_code" id="parent_group_code">
                                                                            <option value="">Choose Type Contract</option>
                                                                            <c:forEach var="pc" items="${parentG}" >
                                                                                <option value="${pc}">${pc}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-md-1" style="margin-left: 10px">
                                                                        <label class="text-left" for="status" style="width: 150px;">Group Type</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="status" id="status">
                                                                            <option value="-1">Choose Status</option>
                                                                            <option value="0">Non BA</option>
                                                                            <option value="1">BA</option>
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
                                                <button onclick="window.open('http://localhost:8080/HR_Management/Group/GroupAdd', '_blank')" class="btn btn-md btn-primary" style="width: 150px; ">Add New Group</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-body" id="groupTable">
                                        <table class="table table-hover">
                                            <tr>
                                                <th>Group Code</th>
                                                <th>Manager</th>
                                                <th>Group Name</th>
                                                <th>Parent Group Code</th>
                                                <th>Group Type</th>
                                                <th>Update Date</th>
                                                <th>Status</th>
                                                <th>Action</th>
                                            </tr>
                                            <c:forEach var="g" items="${listG}"  varStatus="theCount">
                                                <tr>
                                                    <td>${g.code}</td>
                                                    <td>${g.fullname.fullname}</td>
                                                    <td>${g.name}</td>
                                                    <td>${g.parent_group_code}</td>
                                                    <td>
                                                        <c:if test="${g.status == 0}">
                                                            <span class="label label-warning">Non BA</span>
                                                        </c:if>
                                                        <c:if test="${g.status == 1}">
                                                            <span class="label label-success">BA</span>
                                                        </c:if>
                                                    </td>
                                                    <td>${g.update_date}</td>
                                                    <td>
                                                        <c:if test="${g.delete == 0}">
                                                            <span class="label label-danger">Deleted</span>
                                                        </c:if>
                                                        <c:if test="${g.delete == 1}">
                                                            <span class="label label-success">Pending</span>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test = "${g.delete == 1}">
                                                            <a id="delete" name="" href="../Group/Delete?delete=1&code=${g.code}&page=${currentNumber}"><i class="glyphicon glyphicon-trash" ></i></a>
                                                            </c:if>
                                                            <c:if test = "${g.delete == 0}">
                                                            <a id="delete" href="../Group/Delete?delete=0&code=${g.code}&page=${currentNumber}"><i class="glyphicon glyphicon-refresh" ></i></a>
                                                            </c:if>
                                                        <a style="margin-left: 10px" href="../SupportTypeController/SupportTypeEdit?id=${s.id}&name=${s.name}&incharge=${s.in_charge_group}&email=${s.email}&status=${s.status}&description=${s.description}" <span class="glyphicon glyphicon-edit"></span></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        <div class="table-foot" id="table-foot">
                                            <ul class="pagination pagination-sm no-margin pull-right">
                                                <c:if test="${total !=0 }">
                                                    <c:if test="${currentNumber>1}">
                                                        <li><button  class="btn btn-sm btn-primary" onclick="page(${currentNumber-1})">Back</button></li>
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
                                                        <li ><button  class="btn btn-sm btn-primary" onclick="page(${currentNumber+1})">Next</button></li>  
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
                                                                var parent_group_code = document.getElementById('parent_group_code').value;
                                                                var status = document.getElementById('status').value;
                                                                var deletes = document.getElementById('delete').value;
                                                                var fullname = document.getElementById('fullname').value;
                                                                console.log(fullname);
                                                                var link = "http://localhost:8080/HR_Management/Group/GroupList?";
                                                                link += "page=" + pageNumber;
                                                                link += "&";
                                                                link += "parent_group_code=" + parent_group_code;
                                                                link += "&";
                                                                link += "delete=" + deletes;
                                                                link += "&";
                                                                link += "status=" + status;
                                                                link += "&";
                                                                link += "fullname=" + fullname;
                                                                $('#groupTable').load(link + " " + "#groupTable");
                                                            }

                                                            $(document).ready(function () {
                                                                $('#parent_group_code').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#delete').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#status').change(function () {
                                                                    page(1);
                                                                });
                                                                $('#fullname').keyup(function () {
                                                                    page(1);
                                                                });

                                                            });
                                                            function deleteTimesheet(code) {
                                                                var cf = confirm("Are you sure to delete?");
                                                                var pageNumber = document.getElementById('page-active').innerHTML;
                                                                var parent_group_code = document.getElementById('parent_group_code').value;
                                                                var status = document.getElementById('status').value;
                                                                var deletes = document.getElementById('delete').value;
                                                                var fullname = document.getElementById('fullname').value;
                                                                if (cf) {
                                                                    //Logic to delete the item
                                                                    $.ajax({

                                                                        type: "POST",

                                                                        url: "http://localhost:8080/HR_Management/Timesheet/DeleteTimesheet",

                                                                        data: {code: code,
                                                                            parent_group_code: parent_group_code,
                                                                            toDate: toDate,
                                                                            status: status,
                                                                            deletes: deletes,
                                                                            fullname: fullname,
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

