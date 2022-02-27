<%-- 
    Document   : SupportTypeView
    Created on : Jan 30, 2022, 9:30:43 PM
    Author     : hide
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
                                Support Type Edit
                            </header>
                            <div class="panel-body" style="width: 50%;">
                                <header class="panel-heading">
                                    Edit
                                </header>
                                <div class="panel-body">
                                    <form action="../UserListController/Edit" >
                                        <div class="row ">
                                            <div class="form-group col-lg-12">
                                                <label for="ID">ID</label>
                                                <input type="text" class="form-control" name="spid" readonly="" value="${listS.id}">
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="form-group col-lg-12">
                                                <label for="name">Name</label>
                                                <input type="text" class="form-control" name="name"  value="${listS.name}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="email">Email</label>
                                                <input type="email" class="form-control" name="email"  value="${listS.email}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="description">Description</label>
                                                <input type="text" class="form-control" name="description"  value="${listS.description}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="InCharge">In Charge</label><br>
<!--                                                <input type="text" class="form-control" name="incharge"  value="${listS.in_charge_group}">-->
                                                <select name="incharge" id="incharge" style="width: 215px;height: 30px;border-radius: 8px;">
                                                    <option value="${listS.in_charge_group}">${listS.in_charge_group}</option>
                                                    <c:forEach items="${listCode}" var="c">
                                                        <option value="${c.code}">${c.code}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-lg-12">
                                                <label for="Status">Status</label><br>
                                                <c:choose>
                                                    <c:when test = "${listS.status == false}">
                                                        <input  type="radio" name="foo" value="1" id="rbStatus" > <span style="margin-right: 2rem;" >Active</span>
                                                        <input type="radio" name="foo" value="0" id="rbStatus" checked="checked"> Deactivate
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input  type="radio" name="foo" value="1" id="rbStatus" checked="checked"> <span style="margin-right: 2rem;" >Active</span>
                                                        <input type="radio" name="foo" value="0" id="rbStatus"> Deactivate
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class=" form-group row col-lg-12 text-center">
                                            <button type="submit" id="submit-btn" class="btn btn-info"  >Save Change</button>
                                        </div>
                                    </form>
                                    <!--                                    <form action="../SupportTypeController/Edit" >
                                                                            <div class="row">
                                                                                <div class="col-lg-2"></div>
                                    
                                                                                <div class="col-lg-8">
                                                                                    <div class="row ">
                                                                                        <div class="form-group col-lg-12">
                                                                                            <label for="ID">ID</label>
                                                                                            <input type="text" class="form-control" name="spid" disabled="" value="${listS.id}">
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row ">
                                                                                        <div class="form-group col-lg-12">
                                                                                            <label for="name">Name</label>
                                                                                            <input type="text" class="form-control" name="name"  value="${listS.name}">
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row">
                                                                                        <div class="form-group col-lg-12">
                                                                                            <label for="email">Email</label>
                                                                                            <input type="email" class="form-control" name="email"  value="${listS.email}">
                                                                                        </div>
                                                                                    </div>
                                                                                        <div class="row">
                                                                                        <div class="form-group col-lg-12">
                                                                                            <label for="description">Description</label>
                                                                                            <input type="text" class="form-control" name="description"  value="${listS.description}">
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row">
                                                                                        <div class="form-group col-lg-12">
                                                                                            <label for="InCharge">In Charge</label>
                                                                                            <input type="text" class="form-control" name="InCharge"  value="${listS.in_charge_group}">
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="row">
                                                                                        <div class="form-group col-lg-12">
                                                                                            <label for="Status">Status</label><br>
                                    <c:choose>
                                        <c:when test = "${listS.status == false}">
                                            <input  type="radio" name="foo" value="1" id="rbStatus" > <span style="margin-right: 2rem;" >Active</span>
                                            <input type="radio" name="foo" value="0" id="rbStatus" checked="checked"> Deactivate
                                        </c:when>
                                        <c:otherwise>
                                            <input  type="radio" name="foo" value="1" id="rbStatus" checked="checked"> <span style="margin-right: 2rem;" >Active</span>
                                            <input type="radio" name="foo" value="0" id="rbStatus"> Deactivate
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class=" form-group row col-lg-12 text-center">
                                <button type="submit" id="submit-btn" class="btn btn-info"  >Save Change</button>
                            </div>
                </form>-->
                                </div>
                            </div>
                            <!--                            <div class="panel-body">
                                                            <div style="margin-left: 30rem;" ><div style="display: flex; justify-content: space-between;margin-bottom: 2rem">
                                                                    <div><label>ID</label></div>
                                                                    <div style="margin-left: 4rem;"><input  disabled="true" style="border-radius:8px; " type="text" id="txtId" value="${listS.id}"></div>
                                                                </div>
                                                                <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                                                    <div><label>Name</label></div>
                                                                    <div  style="margin-left: 4rem;"><input style="border-radius:8px; " type="text" id="txtName" value="${listS.name}"></div>
                                                                </div>
                                                                <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                                                    <div><label>Gmail</label></div>
                                                                    <div style="margin-left: 4rem;" ><input style="border-radius:8px; " type="text" id="txtGmail" value="${listS.email}"></div>
                                                                </div>
                                                                <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                                                    <div><label>Description</label></div>
                                                                    <div  style="margin-left: 4rem;"><input style="border-radius:8px; " type="text" id="txtDescription" value="${listS.description}"></div>
                                                                </div>
                                                                <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                                                    <div><label>Status</label></div>
                                                                    <div style="padding-right: 2rem;" >
                                                                        <input  type="radio" name="foo" value="1" id="rbStatus" checked="checked"> <span style="margin-right: 2rem;" >Active</span>
                                                                        <input type="radio" name="foo" value="0" id="rbStatus"> Deactivate
                                                                    </div>
                                                                </div>
                                                                <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                                                    <div><label>In Charge</label></div>
                                                                    <div style="margin-left: 4rem;" >
                                                                        <select name="cbbType" id="cbbInCharge" style="width: 215px;height: 30px;border-radius: 8px;">
                                                                            <option value="0">Admin</option>
                                                                            <option value="1">HR</option>
                                                                            <option value="2">Manager</option>
                                                                            <option value="3">Staff</option>
                                                                        </select>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div> 
                                                                <div><a href="../SupportTypeController/Edit?id=${listS.id}&name=${txtName}&incharge=${listS.in_charge_group}&email=${listS.email}&status=${listS.status}&description=${listS.description}">Edit</a></div>
                                                                <div><a href="../SupportTypeController/SupportType?page=1">Cancer</a></div>
                                                            </div>-->


                            <div class="table-foot">
                                <ul class="pagination pagination-sm no-margin pull-right">

                                </ul>
                            </div>
                        </div>
                    </div>
                    </div>
                </section><!-- /.content -->
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
            function deleteByID(id) {
                if (confirm("Do you really want to delete profile?")) {
                    window.location = "grouplist?typef=delete" + "&id=" + id;
                }
            }
        </script>
    </body>
</html>
