
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <style>
            * {
                box-sizing: border-box;
            }

            /* Style inputs */
            input[type=text], select, textarea {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                margin-top: 6px;
                margin-bottom: 16px;
                resize: vertical;
            }

            input[type=submit] {
                background-color: #23b7e5;
                color: white;
                padding: 12px 20px;
                border: none;
                cursor: pointer;
            }

            input[type=submit]:hover {
                background-color: #23b7e5;
            }

            /* Style the container/contact section */
            .container {
                border-radius: 5px;
                background-color: #f2f2f2;
                padding: 10px;
            }

            /* Create two columns that float next to eachother */
            .column {
                float: left;
                width: 50%;
                margin-top: 6px;
                padding: 20px;
            }

            .column-hidden {
                float: left;
                width: 25%;
                margin-top: 6px;
                padding: 20px;
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }


            .jander2{
                font-weight: bold;
            }

            /* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
            @media screen and (max-width: 600px) {
                .column, input[type=submit] {
                    width: 100%;
                    margin-top: 0;
                }
            }
        </style>
    </head>
    <body class="bg-light">
        <div class="container">
            <div style="text-align:center">
                <h2>User information</h2>
            </div>
            <div class="row">
                <div class="column-hidden">
                </div>
                <div class="column">
                    <label for="group-code">Group Code</label>
                    <input class="jander2" type="text" id="group-code" value="ok" disabled="">
                    <label for="fullname">Full Name</label>
                    <input class="jander2"  type="text" id="fullname" name="fullname" value="ok" disabled="">
                    <label for="username">User Name</label>
                    <input class="jander2" type="text" id="username" name="username" value="ok" disabled="">
                    <label for="email">Email</label>
                    <input class="jander2" type="text" id="email" name="email" value="ok" disabled="">
                    <label for="mobile">Mobile</label>
                    <input class="jander2" type="text" id="mobile" name="mobile" value="ok" disabled="">
                    <label for="gender">Gender</label>
                    <input  class="jander2" type="text" id="gender" name="gender" value="ok" disabled="">
                    <label for="system-role">System Role</label>
                    <input class="jander2" type="text" id="system-role" name="system-role" value="ok" disabled="">
                    <label for="password">Password</label>
                    <input class="jander2" type="text" id="password" name="password" value="ok" disabled="">
                    <div style="text-align:center;">
                        <input  type="submit" value="Click here to change password">
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

