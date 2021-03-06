<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="UTF-8">
        <title>Director | Simple Tables</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
            <a href="Views/Home.jsp" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                Home
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <!--            <nav class="navbar navbar-static-top" role="navigation">
                             Sidebar toggle button
                            <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </a>
                            <div class="navbar-right">
                                <ul class="nav navbar-nav">
                                     Messages: style can be found in dropdown.less
                                    <li class="dropdown messages-menu">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-envelope"></i>
                                            <span class="label label-success">4</span>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li class="header">You have 4 messages</li>
                                            <li>
                                                 inner menu: contains the actual data 
                                                <ul class="menu">
                                                    <li> start message 
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar3.png" class="img-circle" alt="User Image"/>
                                                            </div>
                                                            <h4>
                                                                Support Team
                                                                <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li> end message 
                                                    <li>
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar2.png" class="img-circle" alt="user image"/>
                                                            </div>
                                                            <h4>
                                                                Director Design Team
                                                                <small><i class="fa fa-clock-o"></i> 2 hours</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar.png" class="img-circle" alt="user image"/>
                                                            </div>
                                                            <h4>
                                                                Developers
                                                                <small><i class="fa fa-clock-o"></i> Today</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar2.png" class="img-circle" alt="user image"/>
                                                            </div>
                                                            <h4>
                                                                Sales Department
                                                                <small><i class="fa fa-clock-o"></i> Yesterday</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">
                                                            <div class="pull-left">
                                                                <img src="img/avatar.png" class="img-circle" alt="user image"/>
                                                            </div>
                                                            <h4>
                                                                Reviewers
                                                                <small><i class="fa fa-clock-o"></i> 2 days</small>
                                                            </h4>
                                                            <p>Why not buy a new awesome theme?</p>
                                                        </a>
                                                    </li>
                                                </ul>
                                            </li>
                                            <li class="footer"><a href="#">See All Messages</a></li>
                                        </ul>
                                    </li>
                                    <li class="dropdown tasks-menu">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-tasks"></i>
                                            <span class="label label-danger">9</span>
                                        </a>
                                        <ul class="dropdown-menu">
            
                                            <li class="header">You have 9 tasks</li>
                                            <li>
                                                 inner menu: contains the actual data 
                                                <ul class="menu">
                                                    <li> Task item 
                                                        <a href="#">
                                                            <h3>
                                                                Design some buttons
                                                                <small class="pull-right">20%</small>
                                                            </h3>
                                                            <div class="progress progress-striped xs">
                                                                <div class="progress-bar progress-bar-success" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                                    <span class="sr-only">20% Complete</span>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </li> end task item 
                                                    <li> Task item 
                                                        <a href="#">
                                                            <h3>
                                                                Create a nice theme
                                                                <small class="pull-right">40%</small>
                                                            </h3>
                                                            <div class="progress progress-striped xs">
                                                                <div class="progress-bar progress-bar-danger" style="width: 40%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                                    <span class="sr-only">40% Complete</span>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </li> end task item 
                                                    <li> Task item 
                                                        <a href="#">
                                                            <h3>
                                                                Some task I need to do
                                                                <small class="pull-right">60%</small>
                                                            </h3>
                                                            <div class="progress progress-striped xs">
                                                                <div class="progress-bar progress-bar-info" style="width: 60%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                                    <span class="sr-only">60% Complete</span>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </li> end task item 
                                                    <li> Task item 
                                                        <a href="#">
                                                            <h3>
                                                                Make beautiful transitions
                                                                <small class="pull-right">80%</small>
                                                            </h3>
                                                            <div class="progress progress-striped xs">
                                                                <div class="progress-bar progress-bar-warning" style="width: 80%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                                                                    <span class="sr-only">80% Complete</span>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </li> end task item 
                                                </ul>
                                            </li>
                                            <li class="footer">
                                                <a href="#">View all tasks</a>
                                            </li>
            
                                        </ul>
                                    </li>
                                     User Account: style can be found in dropdown.less 
                                    <li class="dropdown user user-menu">
            
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="fa fa-user"></i>
                                            <span>Jane Doe <i class="caret"></i></span>
                                        </a>
                                        <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                                            <li class="dropdown-header text-center">Account</li>
            
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-clock-o fa-fw pull-right"></i>
                                                    <span class="badge badge-success pull-right">10</span> Updates</a>
                                                <a href="#">
                                                    <i class="fa fa-envelope-o fa-fw pull-right"></i>
                                                    <span class="badge badge-danger pull-right">5</span> Messages</a>
                                                <a href="#"><i class="fa fa-magnet fa-fw pull-right"></i>
                                                    <span class="badge badge-info pull-right">3</span> Subscriptions</a>
                                                <a href="#"><i class="fa fa-question fa-fw pull-right"></i> <span class=
                                                                                                                  "badge pull-right">11</span> FAQ</a>
                                            </li>
            
                                            <li class="divider"></li>
            
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-user fa-fw pull-right"></i>
                                                    Profile
                                                </a>
                                                <a data-toggle="modal" href="#modal-user-settings">
                                                    <i class="fa fa-cog fa-fw pull-right"></i>
                                                    Settings
                                                </a>
                                            </li>
            
                                            <li class="divider"></li>
            
                                            <li>
                                                <a href="#"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a>
                                            </li>
                                        </ul>
            
                                    </li>
                                </ul>
                            </div>
                        </nav>-->
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Left side column. contains the logo and sidebar -->
            <%@include file="Header/Treebar.jsp" %>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="panel">
                            <header class="panel-heading">
                                Setting List
                            </header>
                            <div class="form-row">
                                <div class="form-group col-md-3">
                                    <div class="panel-body">
                                        <header class="panel-heading">
                                            Filter &nbsp;
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                    Type <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="../SettingController/Filter?input=All">All</a></li>
                                                        <c:forEach items="${listT}" var="t">
                                                        <li>
                                                            <a href="../SettingController/Filter?input=${t}">${t}</a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                                    Status <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="../SettingController/Filter?input=All">All</a></li>
                                                    <li>
                                                        <a href="../SettingController/Filter?input=1">Activate</a>
                                                    </li>
                                                    <li>
                                                        <a href="../SettingController/Filter?input=0">Deactivate</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </header>
                                    </div>
                                </div>
                                <div class="form-group col-md-9">
                                    <div class="panel-body">
                                        <div class="form">
                                            <form action="../SettingController/Search" method="post">
                                                <div class="form-row">
                                                    <div class="form-group col-md-6">
                                                    </div>
                                                    <div class="form-group col-md-5">
                                                        <input class="form-control" type="text" placeholder="Input..." name="input">
                                                    </div>
                                                    <div class="form-group col-md-1">
                                                        <input type="submit" class="btn btn-info" value="Search">
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body">
                                <div class="pull-right">
                                    <div class="btn btn-success">
                                        <a id="opener" href="../SettingController/AddView?" style="color: white;">Add &nbsp;&nbsp;<span class="glyphicon glyphicon-pencil"></span></a>    
                                    </div>
                                </div>
                                <table class="table table-bordered">
                                    <tr>
                                        <th style="width: 50px">ID</th>
                                        <th style="width: 500px">Type</th>
                                        <th style="width: 500px">Value</th>
                                        <th>Order</th>
                                        <th style="width: 150px;">Status</th>
                                        <th style="width: 70px;">Action</th>
                                    </tr>
                                    <c:forEach items="${listS}" var="s">
                                        <tr>
                                            <td>${s.id}</td>
                                            <td>${s.type}</td>
                                            <td>${s.value}</td>
                                            <td>${s.order}</td>
                                            <td>
                                                <c:if test = "${!s.status}">
                                                    <span class="badge bg-red">Deactivate</span>
                                                    &nbsp;
                                                    <a href="../SettingController/Status?status=0&id=${s.id}&page=${page}"><span class="glyphicon glyphicon-retweet"></span></a>
                                                    </c:if>
                                                    <c:if test = "${s.status}">
                                                    <span class="badge bg-green">Activate</span>
                                                    &nbsp;
                                                    <a href="../SettingController/Status?status=1&id=${s.id}&page=${page}"><span class="glyphicon glyphicon-retweet"></span></a>
                                                    </c:if>
                                            </td>
                                            <td>
                                                <a id="delete" onclick="deleteByID('${s.id}');" href="#"><span class="glyphicon glyphicon-trash"></span></a>
                                                &nbsp;&nbsp;
                                                <a class="edit" href="../SettingController/EditView?id=${s.id}&type=${s.type}&value=${s.value}&order=${s.order}&status=${s.status}&note=${s.note}" return false;"><span class="glyphicon glyphicon-edit"></span></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <div class="table-foot">
                                    <ul class="pagination pagination-sm no-margin pull-right">
                                        <c:forEach begin="1" end="${endP}" var="p">
                                                <li><a href="Setting?page=${p}">${p}</a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </section><!-- /.content -->
                <!-- /.Dialog -->
                <!--                <div id="dialog">
                                    <div style="display: flex; justify-content: center;  margin: 2rem;"><img style=" width: 100px; height: 100px;" src="https://www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png" alt="Avatar" > </div>
                                    <div style="display: flex; justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>ID</label></div>
                                        <div style="margin-left: 4rem;"><input  disabled="true" style="border-radius:8px; " type="text" id="txtId"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>UserName</label></div>
                                        <div  style="margin-left: 4rem;"><input style="border-radius:8px; " type="text" id="txtUserName"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>FullName</label></div>
                                        <div  style="margin-left: 4rem;"><input style="border-radius:8px; " type="text" id="txtFullName"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>PassWord</label></div>
                                        <div style="margin-left: 4rem;"><input style="border-radius:8px; " type="password" id="txtPassWord"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>Gmail</label></div>
                                        <div style="margin-left: 4rem;" ><input style="border-radius:8px; " type="text" id="txtGmail"></div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>Status</label></div>
                                        <div style="padding-right: 2rem;" >
                                            <input  type="radio" name="foo" value="1" id="rbStatus" checked="checked"> <span style="margin-right: 2rem;" >Active</span>
                                            <input type="radio" name="foo" value="0" id="rbStatus"> Deactivate
                                        </div>
                                    </div>
                                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                                        <div><label>Type</label></div>
                                        <div style="margin-left: 4rem;" >
                                            <select name="cbbType" id="cbbType" style="width: 215px;height: 30px;border-radius: 8px;">
                                                <option value="0">Admin</option>
                                                <option value="1">HR</option>
                                                <option value="2">Manager</option>
                                                <option value="3">Staff</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>-->
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
                                                        if (confirm("Do you really want to delete setting?")) {
                                                            window.location = "../SettingController/Delete?id=" + id;
                                                        }
                                                    }
        </script>
    </body>
</html>
