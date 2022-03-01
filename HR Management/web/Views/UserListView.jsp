<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="UTF-8">
        <title>Project | List</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="../css/bootstrap.min.css" rel="stylesheet" />
        <!-- font Awesome -->
        <link href="../css/font-awesome.min.css" rel="stylesheet" />
        <!-- Ionicons -->
        <link href="../css/ionicons.min.css" rel="stylesheet" />
        <!-- google font -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' />
        <!-- Theme style -->
        <link href="../css/style.css" rel="stylesheet"/>
        <link href="../css/dialog.css" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="../Views/Home.jsp" class="logo">
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
                                    User List
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
                                                        <div class="btn btn-md btn-default" style="width: 180px; pointer-events: none;"><span>Search</span></div>
                                                        <input id="userSearch" type="text" name="userSearch" class="form-control input-md" style="width: 450px;" placeholder="Enter user name, full name, email, mobile to search" onclick="dateHideShow()"/>
                                                        <br>
                                                        <div id="advanced" style="display: none">
                                                            <br>
                                                            <div class="row">
                                                                <div class="col-lg-2"></div>
                                                                <div class="col-lg-8">
                                                                    <div class="col-md-1"></div>
                                                                </div>
                                                            </div>
                                                            <br>
                                                            <div class="row">
                                                                <div class="col-lg-12">
                                                                    <div class="col-md-4">
                                                                        <label class="text-left" for="groupFilter" style="width: 150px;">Group Code</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="groupFilter" id="groupFilter">
                                                                            <option value="">Choose Group Code</option>
                                                                            <c:forEach var="group" items="${group}">
                                                                                <option value="${group}">${group}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label class="text-left" for="genderFilter" style="width: 150px;">Gender</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="genderFilter" id="genderFilter">
                                                                            <option value="">Choose Status</option>
                                                                            <option value="0">Female</option>
                                                                            <option value="1">Male</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label class="text-left" for="statusFilter" style="width: 150px;">Status</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="statusFilter" id="statusFilter">
                                                                            <option value="-1">Choose Status</option>
                                                                            <option value="0">Deactivate</option>
                                                                            <option value="1">Active</option>
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
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-body" id="projectTable">
                                        <table class="table table-hover">
                                            <tr>
                                                <th style="width: 10%">Group Code</th>
                                                <th style="width: 13%">User Name</th>
                                                <th style="width: 16%">Full Name</th>
                                                <th style="width: 13%">Gender</th>
                                                <th style="width: 13%">Email</th>
                                                <th style="width: 13%">Mobile</th>
                                                <th style="width: 13%">Status</th>
                                                <th style="width: 13%">Action</th>
                                            </tr>
                                            <c:forEach var="user" items="${userList}"  varStatus="theCount">
                                                <tr id="contract${user.group_code}">
                                                    <td>${user.group_code}</td>
                                                    <td>${user.username}</td>
                                                    <td>${user.fullname}</td>
                                                    <td>
                                                        <c:if test="${!user.gender}">
                                                            <span >Female</span>
                                                        </c:if>
                                                        <c:if test="${user.gender}">
                                                            <span >Male</span>
                                                        </c:if>
                                                    </td>
                                                    <td>${user.email}</td>
                                                    <td>${user.mobile}</td>
                                                    <td>
                                                        <c:if test="${!user.status}">
                                                            <span class="badge bg-red">Deactivate</span>
                                                            &nbsp;
<!--                                                            <a href="../UserListController/ChangeStatus?status=0&id=${user.id}&page=${currentNumber}"><span class="glyphicon glyphicon-retweet"></span></a>-->
                                                            <a onclick="changeStatus(${user.id},${currentNumber},0)" href=""><span class="glyphicon glyphicon-retweet"></span></a>
                                                            </c:if>
                                                            <c:if test="${user.status}">
                                                            <span class="badge bg-green">Activate</span>
                                                            &nbsp;
<!--                                                            <a onclick="noti()" href="../UserListController/ChangeStatus?status=1&id=${user.id}&page=${currentNumber}"><span class="glyphicon glyphicon-retweet"></span></a>-->
                                                            <a onclick="changeStatus(${user.id},${currentNumber},1)" href=""><span class="glyphicon glyphicon-retweet"></span></a>
                                                        </c:if>
                                                    </td>
                                                    <td>
<!--                                                        <a href="" class="btn btn-md btn-default" onclick="changeStatus(${user.id},${currentNumber},1)"  ><i class="fa fa-pencil"></i></a>-->
                                                        <a href="../UserListController/EditView?id=${user.id}" class="btn btn-md btn-default"  ><i class="fa fa-pencil"></i></a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                        <div class="table-foot" id="table-foot">
                                            <ul class="pagination pagination-sm no-margin pull-right">
                                                <c:if test="${total !=0 }">
                                                    <c:if test="${currentNumber>1}">
                                                        <li><button id="page${currentNumber-1}" class="btn btn-sm btn-primary" onclick="page(${currentNumber-1})"><<</button></li>
                                                        </c:if>
                                                        <c:if test="${currentNumber>3}">
                                                        <li><button id="page${1}" class="btn btn-sm btn-default" onclick="page(${1})">1</button></li>
                                                        <li><button id="page${Math.max(end-5,1)}" class="btn btn-sm btn-default" onclick="page(${Math.max(end-5,1)})">...</button></li>
                                                        </c:if>
                                                        <c:forEach begin="${begin}" end="${end}" var="num">
                                                            <c:if test="${num == currentNumber}">
                                                            <li><button id="page${num}" class="btn btn-sm btn-primary" onclick="page(${num})">${num}</button></li>
                                                            </c:if>
                                                            <c:if test="${num != currentNumber}">
                                                            <li><button id="page${num}" class="btn btn-sm btn-default" onclick="page(${num})">${num}</button></li>
                                                            </c:if>    
                                                        </c:forEach>

                                                    <c:if test="${begin+3 < total}">
                                                        <li><button id="page${Math.min(begin+3,total)}" class="btn btn-sm btn-default" onclick="page(${Math.min(begin+3,total)})">...</button></li>
                                                        <li><button id="page${total}" class="btn btn-sm btn-default" onclick="page(${total})">${total}</button></li>
                                                        </c:if>
                                                        <c:if test="${begin+3 >= total && end != total}">
                                                        <li><button id="page${total}" class="btn btn-sm btn-default" onclick="page(${total})">${total}</button></li>
                                                        </c:if>

                                                    <c:if test="${currentNumber < total}">
                                                        <li><button id="page${currentNumber+1}" class="btn btn-sm btn-primary" onclick="page(${currentNumber+1})">>></button></li>  
                                                        </c:if>
                                                    </c:if>
                                            </ul>
                                        </div>
                                    </div>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>
                </section>
                <!-- /.content -->
                <!-- /.Dialog -->
                <div class="footer-main">
                    Copyright &copy Director, 2014
                </div>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->

        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/lodash.js/0.10.0/lodash.min.js"></script>
        <script type="text/javascript">
        </script>
        <script>
            function dateHideShow() {
                var x = document.getElementById("advanced");
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";
                }
            }
            
            function changeStatus(id,number,status) {
                                                                var cf = confirm("Are you sure to change status?");
                                                                if (cf) {
                                                                    //Logic to delete the item
                                                                    $.ajax({
                                                                        type: "POST",
                                                                        url: "http://localhost:8080/HR_Management/UserListController/ChangeStatus?",
                                                                        data: {id: id,
                                                                            status: status,
                                                                            page: number},
                                                                        success: function () {
                                                                            page(number)
                                                                        }
                                                                    });
                                                                }
                                                            }
            


            function page(number) {
                var pageNumber = number;
                var group = document.getElementById('groupFilter').value;
                var gender = document.getElementById('genderFilter').value;
                var status = document.getElementById('statusFilter').value;
                var search = document.getElementById('userSearch').value;
                var link = "http://localhost:8080/HR_Management/UserListController/UserList?";
                link += "page=" + pageNumber;
                link += "&";
                link += "group=" + group;
                link += "&";
                link += "gender=" + gender;
                link += "&";
                link += "status=" + status;
                link += "&";
                link += "search=" + search;
                $('#projectTable').load(link + " " + "#projectTable");
            }


            $(document).ready(function () {
                $('#groupFilter').change(function () {
                    page(1);
                });
                $('#genderFilter').change(function () {
                    page(1);
                });
                $('#statusFilter').change(function () {
                    page(1);
                });
                $('#userSearch').keyup(function () {
                    page(1);
                });


            });

        </script>
    </body>
</html>
