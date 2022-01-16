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
        <link href="css/bootstrap.min.css" rel="stylesheet" />
        <!-- font Awesome -->
        <link href="css/font-awesome.min.css" rel="stylesheet" />
        <!-- Ionicons -->
        <link href="css/ionicons.min.css" rel="stylesheet" />
        <!-- google font -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' />
        <!-- Theme style -->
        <link href="css/style.css" rel="stylesheet"/>

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
                Director
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <!-- Messages: style can be found in dropdown.less-->
                        <li class="dropdown messages-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-envelope"></i>
                                <span class="label label-success">4</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="header">You have 4 messages</li>
                                <li>
                                    <!-- inner menu: contains the actual data -->
                                    <ul class="menu">
                                        <li><!-- start message -->
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
                                        </li><!-- end message -->
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
                                    <!-- inner menu: contains the actual data -->
                                    <ul class="menu">
                                        <li><!-- Task item -->
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
                                        </li><!-- end task item -->
                                        <li><!-- Task item -->
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
                                        </li><!-- end task item -->
                                        <li><!-- Task item -->
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
                                        </li><!-- end task item -->
                                        <li><!-- Task item -->
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
                                        </li><!-- end task item -->
                                    </ul>
                                </li>
                                <li class="footer">
                                    <a href="#">View all tasks</a>
                                </li>

                            </ul>
                        </li>
                        <!-- User Account: style can be found in dropdown.less -->
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
            </nav>
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
                        <div class="panel">
                            <header class="panel-heading">
                                Bordered Table
                                <a id="opener" >Add</a>
                            </header>
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <tr>
                                        <th style="width: 10px">ID</th>
                                        <th>Avatar</th>
                                        <th>User Name</th>
                                        <th>Full Name</th>
                                        <th>Type</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                    <c:forEach items="${listE}" var="e">
                                        <tr>
                                            <td>${e.employee_id}</td>
                                            <td><img src="${e.avatar}" width = "80px" height="80px"></td>
                                            <td>${e.username}</td>
                                            <td>${e.fullname}</td>
                                            <td>${e.type_id}</td>
                                            <td>
                                                <c:if test = "${e.status == 0}">
                                                    <span class="badge bg-red">Deactivate</span>
                                                    <div><a href="StatusController?status=${e.status}&id=${e.employee_id}">switch</a></div>
                                                </c:if>
                                                <c:if test = "${e.status == 1}">
                                                    <span class="badge bg-green">Activate</span>
                                                    <div><a href="StatusController?status=${e.status}&id=${e.employee_id}">switch</a></div>
                                                </c:if>
                                            </td>
                                            <td>
                                                <a id="delete" href="#">Delete </a>
                                                <a class="edit" href="" onclick="dialogOpen('${e.username}', '${e.fullname}', '${e.employee_id}', '${e.type_id}', '${e.status}', '${e.email}', '${e.password}');
                                                        return false;">Edit</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <div class="table-foot">
                                    <ul class="pagination pagination-sm no-margin pull-right">
                                        <li><a href="#">&laquo;</a></li>
                                        <li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li>
                                        <li><a href="#">3</a></li>
                                        <li><a href="#">&raquo;</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </section><!-- /.content -->
                <!-- /.Dialog -->
                <table id="dialog" hidden="hidden">
                    <tr>
                        <td><img style="width: 90px; height: 90px;" src="https://www.winhelponline.com/blog/wp-content/uploads/2017/12/user.png" alt="Avatar" class="avatar"> </td>
                    </tr>
                    <tr>
                        <td class="w150">
                            <label>ID</label></td>
                        <td>
                            <input id="txtId" type="text" name="name" class="w170 " required="" autocomplete="off" /></td>
                    </tr>
                    <tr>
                        <td class="w150">
                            <label>User Name:</label></td>
                        <td>
                            <input  id="txtUserName" type="text" name="name" class="w170 " required autocomplete="off" /></td>
                    </tr>
                    <tr>
                        <td class="w150">
                            <label>Full Name:</label></td>
                        <td>
                            <input id="txtFullName" type="text" name="name" class="w170 " required autocomplete="off" /></td>
                    </tr>
                    <tr>
                        <td class="w150">
                            <label>PassWord:</label></td>
                        <td>
                            <input id="txtPassWord" type="password" name="name" class="w170 " required autocomplete="off"  /></td>
                    </tr>
                    <tr>
                        <td class="w150">
                            <label>Gmail:</label></td>
                        <td>
                            <input id="txtGmail" type="text" name="name" class="w170 " required autocomplete="off" /></td>
                    </tr>
                    <td class="w150">
                        <label>Status:</label></td>
                    </td>
                    <td >
                        <input type="radio" name="foo" value="1" id="rbStatus"> Active
                        <input type="radio" name="foo" value="0" id="rbStatus"> Deactivate
                    </td>
                    </tr>
                    <tr>
                        <td class="w150">
                            <label>Type</label></td>
                        </td>
                        <td><select name="cbbType" id="cbbType" class="w170 ">
                                <option value="0">Admin</option>
                                <option value="1">HR</option>
                                <option value="2">Manager</option>
                                <option value="3">Staff</option>
                            </select>
                        </td>
                    </tr>
                </table>
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
                                                    $(function () {
                                                        $("#dialog").dialog({
                                                            autoOpen: false,
                                                            title: "Add",
                                                            width: 'auto',
                                                            height: 'auto',
                                                            buttons: {
                                                                Submit: function () {
                                                                    add();
                                                                },
                                                                Close: function () {
                                                                    $(this).dialog('close');
                                                                }
                                                            }
                                                        });
                                                        $("#opener").click(function () {
                                                            $("#dialog").dialog('open');
                                                        });
                                                        $(".edit").click(function () {
                                                            $("#dialog").dialog('open');
                                                        });
                                                    });

                                                    function dialogOpen(name, fullname, id, type_id, status, email, pw) {
//                                                        var id = e.valueOf().toString();
//                                                        var u = username.toString();
                                                        $('#txtId').val(id);
                                                        $('#txtUserName').val(name);
                                                        $('#txtPassWord').val(pw)
                                                        $('#txtGmail').val(email);
                                                        $('#txtFullName').val(fullname)
                                                        $("#cbbType").val(type_id);
                                                        $("[name=foo]").val([status]);

                                                        $("#dialog").dialog({
                                                            autoOpen: false,
                                                            title: "Edit",
                                                            width: 'auto',
                                                            height: 'auto',
                                                            buttons: {
                                                                Submit: function () {
                                                                    edit(id);
                                                                    alert('Edit Successfull');
                                                                },
                                                                Close: function () {
                                                                    $(this).dialog('close');
                                                                }
                                                            }
                                                        });
                                                    }
                                                    function add() {
                                                        var id = document.getElementById("txtId").value;
                                                        var username = document.getElementById("txtUserName").value;
                                                        var fullname = document.getElementById("txtFullName").value;
                                                        var password = document.getElementById("txtPassWord").value;
                                                        var status = $('input[name = "foo"]:checked').val();
                                                        var mail = document.getElementById("txtGmail").value;
                                                        var typename = $('#cbbType option:selected').val();                             
                                                        if ( !!username && !!fullname && !!password && !!mail) {
                                                            window.location = "SettingDetailController?type=add" + "&id=" + id + "&username=" + username + "&fullname=" + fullname + "&password=" + password + "&status=" + status + "&mail=" + mail + "&typename=" + typename;
                                                            alert('Add Successfull');
                                                        } else
                                                        {
                                                            alert('Add Fail');
                                                        }
                                                    }
                                                    function edit(id) {
//                                                        var id = document.getElementById("txtId").value;
                                                        var username = document.getElementById("txtUserName").value;
                                                        var fullname = document.getElementById("txtFullName").value;
                                                        var password = document.getElementById("txtPassWord").value;
                                                        var status = $('input[name = "foo"]:checked').val();
                                                        var mail = document.getElementById("txtGmail").value;
                                                        var typename = $('#cbbType option:selected').val();
                                                        window.location = "SettingDetailController?type=edit" + "&id=" + id + "&username=" + username + "&fullname=" + fullname + "&password=" + password + "&status=" + status + "&mail=" + mail + "&typename=" + typename;

                                                    }
        </script>
    </body>
</html>
