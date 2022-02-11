


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
            document.getElementById('checkEqualMessage').style.color = 'green';
            document.getElementById('checkEqualMessage').innerHTML = 'Matching';
            isValid = true;
        } else {
            document.getElementById('checkEqualMessage').style.color = 'red';
            document.getElementById('checkEqualMessage').innerHTML = 'Not Matching';
            isValid = false;
        }
    } else {
        document.getElementById('checkEqualMessage').innerHTML = '';
        isValid = false;
    }
}

function checkMobile() {
  var mobile = document.getElementById('mobile');
  var regex = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
  if(mobile.value.match(regex)) {
    isValid = true;
  }
  else {
    alert("ok");
  }
}

