<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="UTF-8">
        <title>Contract | Add</title>
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
            <aside class="left-side sidebar-offcanvas">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="../userimg/${sessionScope.account.avatar}" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>${sessionScope.account.fullname}</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <ul class="sidebar-menu">
                        <li>
                            <a href="../Contract/Details">
                                <i class="fa fa-glass"></i> <span>Contract Details</span>
                            </a>
                        </li>
                        <li class="active">
                            <a href="../Contract/Add">
                                <i class="fa fa-glass"></i> <span>Contract Add</span>
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
                        <div class="panel">
                            <header class="panel-heading">
                                Contract Add
                            </header>
                            <div class="panel-body">
                                <form action="../Contract/Add" method="POST">
                                    <div class="row">
                                        <div class="col-lg-2"></div>
                                        <div class="col-lg-8">
                                            <div class="row ">
                                                <div class="form-group col-lg-12">
                                                    <label for="fullname">Select user:</label>
                                                    <select onchange="this.form.submit()" name="id">
                                                        <option value="-1">----------Select User-----------</option>
                                                        <c:forEach items="${listU}" var="u">
                                                            <option ${u.id == idSelected?'selected':''} value="${u.id}">${u.username} </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <c:if test="${listUser!=null||listUser.size()==1}">
                                                <c:if test="${listContract==null||listContract.size()==0}">
                                                    <h4 style="color: red">This user does not have any contract before, add one!!</h4>
                                                </c:if>
                                            </c:if>
                                            <c:forEach items="${listUser}" var="user">
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="fullname">Full Name</label>
                                                        <input type="text" class="form-control" disabled="" value="${user.fullname}">
                                                    </div>
                                                </div>
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="fullname">User Name</label>
                                                        <input type="text" class="form-control" disabled="" value="${user.username}">
                                                    </div>
                                                </div>
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="fullname">Email</label>
                                                        <input type="text" class="form-control" disabled="" value="${user.email}">
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            <c:forEach items="${listContract}" var="contract">
                                                <input hidden="" name="idc" value="${contract.id}">
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="StartDate">Start Date</label>
                                                        <input type="date" name="StartDate" class="form-control" value="${contract.startDate}">
                                                    </div>
                                                </div>
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="EndDate">End Date</label>
                                                        <input type="date" name="EndDate" class="form-control" value="${contract.endDate}">
                                                    </div>
                                                </div>
                                                <c:if test="${contract.status==0}">
                                                    <h5 style="color: red"><i>This contract has been exprired </br>By add new contract, the current active contract would be archieved and the end-date of that contract would be updated to the date right before the start date of the new contract</i></h5>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${listUser!=null||listUser.size()==1}">
                                                <c:if test="${listContract==null||listContract.size()==0}">
                                                    <input hidden="" name="idc" value="${contract.id}">
                                                    <div class="row ">
                                                        <div class="form-group col-lg-12">
                                                            <label for="StartDate">Start Date</label>
                                                            <input type="date" name="StartDate" class="form-control" value="${contract.startDate}">
                                                        </div>
                                                    </div>
                                                    <div class="row ">
                                                        <div class="form-group col-lg-12">
                                                            <label for="EndDate">End Date</label>
                                                            <input type="date" name="EndDate" class="form-control" value="${contract.endDate}">
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${listUser==null||listUser.size()==0}"><h3 style="color: red">Select user account to add contract !!</h3></c:if>
                                            <div class=" form-group row col-lg-12 text-center" ${listUser==null||listUser.size()==0 ?'hidden':'' }>
                                                <button onclick="addFunction()" type="submit" id="submit-btn" class="btn btn-info">Add</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </section>
                <div class="footer-main">
                    Copyright &copy Director, 2014
                </div>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/lodash.js/0.10.0/lodash.min.js"></script>
    </body>
    <script>
                                                        function addFunction() {
                                                            alert("Successfully!!");
                                                        }
    </script>
</html>
