<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="UTF-8">
        <title>Group | Add</title>
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
            <a href="Views1/Home.jsp" class="logo">
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
                        <li >
                            <a href="<%= request.getContextPath()%>/Views/Home.jsp">
                                <i class="fa fa-home"></i> <span>Home Page</span>
                            </a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/BlogController">
                                <i class="fa fa-rss"></i> <span>Blog</span>
                            </a>
                        </li>
                        <li>
                            <a href="../Project/List">
                                <i class="fa fa-glass"></i> <span>Project List</span>
                            </a>
                        </li>
                        <li class="active">
                            <a href="../Project/Add">
                                <i class="fa fa-glass"></i> <span>Project Add</span>
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
                        <div  class="col-lg-3"></div>
                        <div class="col-lg-6 ">
                            <section class="panel">
                                <header class="panel-heading text-center">
                                    Add Project
                                </header>
                                <c:if test="${message != null}">
                                    <c:choose>
                                        <c:when test = "${message eq 'Add Project Successfully!!'}">
                                            <div class="error alert alert-success" role="alert">
                                                <p class="mb-0">${message}</p>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="error alert alert-danger" role="alert">
                                                <p class="mb-0">${message}</p>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:remove var="message" scope="session" /> 
                                </c:if>
                                <div class="panel-body">
                                    <form action="../Request/AddRequest" method="POST">

                                        <div class="row">
                                            <div class="col-lg-2"></div>
                                            <div class="col-lg-8">
                                                <div class="row ">
                                                    <div class="form-group col-lg-12">
                                                        <label for="code">Title</label><i style="color: red; font-size: 20px">*</i>
                                                        <input type="text" class="form-control" name="title" required="">
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="request_date">Request Date</label><i style="color: red; font-size: 20px">*</i>
                                                        <input type="date" class="form-control" name="request_date" value="${r.request_date}" required="">
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="support_type_id">Request Name</label>
                                                        <select class="form-control input-md" style="width: 200px;" name="support_type_id" >
                                                            <c:forEach var="sp" items="${listSP}">
                                                                <option value="${sp.id}" >${sp.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="manager">Incharge Staff</label>
                                                        <select class="form-control input-md" style="width: 200px;" name="manager">
                                                            <c:forEach items="${listU}" var="u">
                                                                <option value="${u.id}">${u.username}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <label for="status" >Status</label>
                                                        <input  type="radio" name="status" value="1" style="margin-left: 10px" > <span style="margin-right: 5px" >Submitted</span>
                                                        <input type="radio" name="status" value="2" checked="checked" style="margin-right: 5px"   > Assigned
                                                        <input type="radio" name="status" value="3"  style="margin-right: 5px;" > Completed
                                                        <input type="radio" name="status" value="4"  style="margin-right: 5px;" > Closed
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-lg-12">
                                                        <jsp:useBean id="now" class="java.util.Date" />
                                                        <fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd" />
                                                        <label for="update_date1">Update Date</label>
                                                        <h5 style="color: red"><i>By add new request, start date will set from now on....<br/>Otherwise you can edit it later</i></h5>
                                                        <input type="date" class="form-control" name="update_date1" value="${date}" disabled="">
                                                        <input name="update_date" value="${date}" hidden="">
                                                    </div>
                                                </div>
                                                <div class=" form-group row col-lg-12 text-center">
                                                    <button type="submit" id="submit-btn" class="btn btn-info"  >Add Request</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </section>
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
