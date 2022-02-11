


function password_show_hide() {
    var x = document.getElementById("password");
    var y = document.getElementById("confirm-password");
    if (x.type === "password") {
        x.type = "text";
        y.type = "text";
    } else {
        x.type = "password";
        y.type = "password";
    }
}

var isValid = true;
function checkEqual() {
    var x = document.getElementById('password');
    var y = document.getElementById('confirm-password');
    if (x.value.length != 0 && y.value.length != 0) {
        if (x.value == y.value) {
            document.getElementById('message').style.color = 'green';
            document.getElementById('message').innerHTML = 'Matching';
            isValid = true;
        } else {
            document.getElementById('message').style.color = 'red';
            document.getElementById('message').innerHTML = 'Not Matching';
            isValid = false;
        }
    } else {
        document.getElementById('message').innerHTML = '';
        isValid = false;
    }
}


