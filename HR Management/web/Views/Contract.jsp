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
                        <li class="active">
                            <a href="../Contract/Details">
                                <i class="fa fa-glass"></i> <span>Contract Details</span>
                            </a>
                        </li>
                        <li>
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
                                Contract Details
                            </header>
                            <div class="panel-body" style="width: 50%;">
                                <header class="panel-heading">
                                    Filter
                                </header>
                                <form action="../Contract/Details" method="post">
                                    <div class="form-row">
                                        <div class="form-group col-md-3">
                                            <select class="form-control" name="type">
                                                <option value="fullname" ${setting_type eq 'fullname'?'selected':""} >Full Name</option>
                                                <option value="email" ${setting_type eq 'email'?'selected':""}>Email</option>
                                            </select>                                            
                                        </div>
                                        <div class="form-group col-md-7">
                                            <input class="form-control" type="text" placeholder="Search..." name="txt" value="${txtSearch}">
                                        </div>
                                        <div class="form-group col-md-2">
                                            <input type="submit" class="btn btn-info" value="Search">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="panel-body">
                                <div class="pull-right">
                                    <div class="btn btn-success">
                                        <a href="../Contract/Add" style="color: white;">Add Contract</a>    
                                    </div>
                                </div>
                                <c:if test="${listC==null||listC.size()==0}"><h3 style="color: red">Not found !!</h3></c:if>
                                    <table class="table table-bordered">
                                        <tr ${listC==null||listC.size()==0 ?'hidden':'' }>
                                        <th style="width: 10px">ID</th>
                                        <th>Full name</th>
                                        <th>Email</th>
                                        <th>Start date</th>
                                        <th>End date</th>
                                        <th style="width: 150px;">Status</th>
                                        <th style="width: 150px;">Action</th>
                                    </tr>
                                    <c:forEach items="${listC}" var="c">
                                        <tr>
                                            <td>${c.id}</td>
                                            <td>${c.user_id.fullname}</td>
                                            <td>${c.user_id.email}</td>
                                            <td>${c.startDate}</td>
                                            <td>${c.endDate}</td>
                                            <td>
                                                <c:if test = "${c.status == 0}">
                                                    <span class="badge bg-red">Contract Expired</span>
                                                </c:if>
                                                <c:if test = "${c.status == 1}">
                                                    <span class="badge bg-green">On contract</span>
                                                </c:if>
                                            </td>
                                            <td>
                                                <div style="background-color: #f9d21a;border-radius:25px;text-align: center;padding: 0.4rem;"><a style="color: white;  font-weight:700; " class="edit" href="" onclick="dialogOpen('${c.id}', '${c.user_id.fullname}', '${c.user_id.email}', '${c.startDate}', '${c.endDate}', '${c.status}');
                                                        return false;">Edit</a></div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <div class="table-foot" ${listC==null||listC.size()==0 ?'hidden':'' } > 
                                    <ul class="pagination pagination-sm no-margin pull-right" >
                                        <c:forEach begin="1" end="${endP}" var="p">
                                            <li><a href="../Contract/Details?page=${p}">${p}</a></li>
                                            </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </section><!-- /.content -->
                <!-- /.Dialog -->
                <div id="dialog">
                    <div style="display: flex; justify-content: center;  margin: 2rem;"><img style=" width: 100px; height: 100px;" src="../userimg/${sessionScope.account.avatar}" alt="Avatar" > </div>
                    <div style="display: flex; justify-content: space-between;margin-bottom: 2rem">
                        <div><label>ID</label></div>
                        <div style="margin-left: 4rem;"><input  disabled="true" style="border-radius:8px; " type="text" id="txtId"></div>
                    </div>
                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                        <div><label>Full Name</label></div>
                        <div  style="margin-left: 4rem;"><input style="border-radius:8px; " type="text" id="txtFullName" disabled=""></div>
                    </div>
                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                        <div><label>Email</label></div>
                        <div style="margin-left: 4rem;" ><input style="border-radius:8px; " type="text" id="txtEmail" disabled=""></div>
                    </div>
                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                        <div><label>Start Contract</label></div>
                        <div style="margin-left: 4rem;" ><input style="border-radius:8px; " type="date" id="txtStartDate"></div>
                    </div>
                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                        <div><label>End Contract</label></div>
                        <div style="margin-left: 4rem;" ><input style="border-radius:8px; " type="date" id="txtEndDate"></div>
                    </div>
                    <div style="display: flex;justify-content: space-between;margin-bottom: 2rem">
                        <div><label>Status</label></div>
                        <div style="padding-right: 2rem;" >
                            <c:forEach items="${listC}" var="c">
                                <span class="badge ${c.status == 0?'bg-red':'bg-green'}">${c.status == 0 ? 'Contract Expired':'On contract'}</span>
                            </c:forEach>
                        </div>
                    </div>
                </div>
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
                                                        $(".edit").click(function () {
                                                            $("#dialog").dialog('open');
                                                        });
                                                    });
                                                    function dialogOpen(id, fullname, email, startdate, enddate, status) {
                                                        $('#txtId').val(id);
                                                        $('#txtFullName').val(fullname)
                                                        $('#txtEmail').val(email);
                                                        $('#txtStartDate').val(startdate);
                                                        $('#txtEndDate').val(enddate);
                                                        $("[name=foo]").val([status]);
                                                        $("#dialog").dialog({
                                                            autoOpen: false,
                                                            title: "Edit",
                                                            width: 'auto',
                                                            height: 'auto',
                                                            buttons: {
                                                                Submit: function () {
                                                                    edit(id);
                                                                },
                                                                Close: function () {
                                                                    $(this).dialog('close');
                                                                }
                                                            }
                                                        });
                                                    }
                                                    function edit(id) {
                                                        var username = document.getElementById("txtUserName").value;
                                                        var fullname = document.getElementById("txtFullName").value;
                                                        var password = document.getElementById("txtPassWord").value;
                                                        var status = $('input[name = "foo"]:checked').val();
                                                        var mail = document.getElementById("txtGmail").value;
                                                        var typename = $('#cbbType option:selected').val();
                                                        if (!!username && !!fullname && !!password && !!mail) {
                                                            window.location = "SettingDetailController?typef=edit" + "&id=" + id + "&username=" + username + "&fullname=" + fullname + "&password=" + password + "&status=" + status + "&mail=" + mail + "&typename=" + typename;
                                                            alert('Edit Successfull');
                                                        } else
                                                        {
                                                            alert('Edit Fail');
                                                        }

                                                    }
        </script>
    </body>
</html>
