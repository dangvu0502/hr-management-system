<%-- 
    Document   : GroupView
    Created on : Jan 30, 2022, 9:30:43 PM
    Author     : quocb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="UTF-8">
        <title>Director | Simple Tables</title>
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
            <a href="<%= request.getContextPath()%>/Views/Home.jsp" class="logo">
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
                            <img src="img/avatar3.png" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>${sessionScope.account.fullname}</p>

                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                       <ul class="sidebar-menu">
                        <li >
                            <a href="../Views/Home.jsp">
                                <i class="fa fa-home"></i> <span>Home Page</span>
                            </a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/BlogController">
                                <i class="fa fa-rss"></i> <span>Blog</span>
                            </a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/SettingController/Setting">
                                <i class="fa fa-gear"></i> <span>Setting List</span>


                       <ul class="sidebar-menu">
                        <li >
                            <a href="../Views/Home.jsp">
                                <i class="fa fa-home"></i> <span>Home Page</span>
                            </a>
                        </li>

                        <li>
                            <a href="<%= request.getContextPath()%>/BlogController">
                                <i class="fa fa-rss"></i> <span>Blog</span>
                            </a>
                        </li>
                        <li>
                            <a href="<%= request.getContextPath()%>/SettingController/Setting">
                                <i class="fa fa-gear"></i> <span>Setting List</span>
                            </a>
                        </li>


                        <li >
                            <a href="<%= request.getContextPath()%>/Group/GroupList">
                                <i class="fa fa-user"></i> <span>Group List</span>
                            </a>
                        </li>
                        <li class="active">
                            <a href="<%= request.getContextPath()%>/SupportTypeController/SupportType">
                                <i class="fa fa-user"></i> <span>Support Type</span>
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
                                Setting List
                            </header>
                            <div class="panel-body" style="width: 50%;">
                                <header class="panel-heading">
                                    Filter
                                </header>
                                <form action="GroupList" method="post">
                                    <div class="form-row">
                                        <div class="form-group col-md-3">
                                            <select class="form-control" name="type" value ="${txtS}" >
                                                <option value="code">Code</option>
                                                <option value="name">Name</option>
                                                <option value="fullname">Full Name</option>
                                                <option value="email">Email</option>
                                                <option value="type_name">Type</option>
                                                <option value="status">Status</option>
                                            </select>                                            
                                        </div>
                                        <div class="form-group col-md-7">
                                            <input class="form-control" type="text" value="${txtS}" placeholder="Input..." name="input">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <input type="submit" name="input" class="btn btn-info" value="Search">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="panel-body">
                                <div class="pull-right">
                                    <div class="btn btn-success">
                                        <a id="opener" href="../Group/GroupViewAdd" style="color: white;">Add &nbsp;&nbsp;<span class="glyphicon glyphicon-pencil"></span></a>    
                                    </div>
                                </div>
                                <table class="table table-bordered">
                                    <tr>
                                        <th style="width: 10px">Code</th>
                                        <th>Manager_id</th>
                                        <th>Name</th>
                                        <th>Status</th>
                                        <th>Description</th>
                                        <th>Parent_group_code</th>
                                        <th>Update Date</th>

                                    </tr>
                                    <c:forEach items="${listG}" var="g">
                                        <tr>
                                            <td>${g.code}</td>
                                            <td>${g.manager}</td>
                                            <td>${g.name}</td>
                                            <td>
                                                <c:if test = "${g.status}"> <span>Non-BA</span></c:if>
                                                <c:if test = "${!g.status}"><span>BA</span></c:if>
                                                </td>


                                                <td>${g.description}</td>
                                            <td>${g.parent_group_code}</td>
                                            <td>${g.update_date}</td>
                                            <td>
                                                &nbsp;&nbsp;
                                                <c:if test = "${g.delete}"> <a id="delete" name="" href="../Group/Delete?delete=1&id=${g.id}&page=${page}"><span class="glyphicon glyphicon-trash" ></span></a></c:if>
                                                <c:if test = "${!g.delete}"><a id="delete" href="../Group/Delete?delete=0&id=${g.id}&page=${page}"><span class="glyphicon glyphicon-refresh" ></span></a></c:if>
                                                    &nbsp;&nbsp;
                                                    <a class="edit" href="../Group/GroupViewEdit?id=${g.id}&code=${g.code}&manager=${g.manager}&name=${g.name}&status=${g.status}&description=${g.description}&parent_group_code=${g.parent_group_code}&update_date=${g.update_date}" ><span class="glyphicon glyphicon-edit"></span></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <div class="table-foot">
                                    <ul class="pagination pagination-sm no-margin pull-right">
                                        <c:forEach begin="1" end="${endP}" var="p">
                                            <li><a href="../Group/GroupList?page=${p}">${p}</a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </section><!-- /.content -->
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
    </body>
</html>
