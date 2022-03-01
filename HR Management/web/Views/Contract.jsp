<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="UTF-8">
        <title>Contract | Details</title>
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
                                    Contract List
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
                                                        <div class="btn btn-md btn-default" style="width: 150px; pointer-events: none;"><span>Search by Full Name</span></div>
                                                        <input id="contractFullName" type="text" name="contractFullName" class="form-control input-md" style="width: 450px;" placeholder="Enter full name to search" onclick="dateHideShow()"/>
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
                                                                        <label class="text-left" for="typeFilter" style="width: 150px;">Type Contract</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="typeFilter" id="typeFilter">
                                                                            <option value="-1">Choose Type Contract</option>
                                                                            <option value="0">Intern</option>
                                                                            <option value="1">Official</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-md-4">
                                                                        <label class="text-left" for="statusFilter" style="width: 150px;">Status</label><br>
                                                                        <select class="form-control input-md" style="width: 200px;" name="statusFilter" id="statusFilter">
                                                                            <option value="-1">Choose Status</option>
                                                                            <option value="0">Expired</option>
                                                                            <option value="1">On Contract</option>
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
                                                <button onclick="window.open('http://localhost:8080/HR_Management/Contract/Add', '_blank')" class="btn btn-md btn-primary" style="width: 150px; ">Add new contract</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-body" id="contractTable">
                                        <table class="table table-hover">
                                            <tr>
                                                <th style="width: 10%">ID</th>
                                                <th style="width: 13%">Full Name</th>
                                                <th style="width: 16%">Email</th>
                                                <th style="width: 13%">Start Date</th>
                                                <th style="width: 13%">End Date</th>
                                                <th style="width: 13%">Status</th>
                                                <th style="width: 13%">Type</th>
                                                <th style="width: 13%">Action</th>
                                            </tr>
                                            <c:forEach var="contract" items="${contractList}"  varStatus="theCount">
                                                <tr id="contract${contract.id}">
                                                    <td>${contract.id}</td>
                                                    <td>${contract.user_id.fullname}</td>
                                                    <td>${contract.user_id.email}</td>
                                                    <td>${contract.startDate}</td>
                                                    <td>${contract.endDate}</td>
                                                    <td>
                                                        <c:if test="${contract.status == 0}">
                                                            <span class="label label-warning">Expired</span>
                                                        </c:if>
                                                        <c:if test="${contract.status == 1}">
                                                            <span class="label label-success">On Contract</span>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${contract.type == 0}">
                                                            <span class="label label-warning">Intern</span>
                                                        </c:if>
                                                        <c:if test="${contract.type == 1}">
                                                            <span class="label label-success">Official</span>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${contract.status != 0}">
                                                            <a href="#" class="btn btn-md btn-default" onclick="window.open('http://localhost:8080/HR_Management/Contract/EditContract?id=${contract.id}', '_blank')" ><i class="fa fa-pencil"></i></a>
                                                            </c:if>
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

            function page(number) {
                var pageNumber = number;
                var fromDate = document.getElementById('fromDate').value;
                var toDate = document.getElementById('toDate').value;
                var type = document.getElementById('typeFilter').value;
                var status = document.getElementById('statusFilter').value;
                var fullname = document.getElementById('contractFullName').value;
                console.log(fullname);
                var link = "http://localhost:8080/HR_Management/Contract/Details?";
                link += "page=" + pageNumber;
                link += "&";
                link += "fromDate=" + fromDate;
                link += "&";
                link += "toDate=" + toDate;
                link += "&";
                link += "type=" + type;
                link += "&";
                link += "status=" + status;
                link += "&";
                link += "fullname=" + fullname;
                $('#contractTable').load(link + " " + "#contractTable");
            }


            $(document).ready(function () {
                $('#fromDate').change(function () {
                    page(1);
                });
                $('#toDate').change(function () {
                    page(1);
                });
                $('#typeFilter').change(function () {
                    page(1);
                });
                $('#statusFilter').change(function () {
                    page(1);
                });
                $('#contractFullName').keyup(function () {
                    page(1);
                });


            });
        </script>
    </body>
</html>
