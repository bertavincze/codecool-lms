 function changeTheme() {
   var x = document.getElementById("themeSelector").value;
    document.getElementById('themer').href = 'resources/css/' + x + ".css";
    setCookie("theme", x, 5);
    };

  function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
  }

function checkCookie() {
      var theme = getCookie("theme");
      if (theme == "" || theme== "finalstyle") {
              document.getElementById('themer').href = 'resources/css/finalstyle.css';
      } else if (theme == "alternative") {
              document.getElementById('themer').href = 'resources/css/alternative.css';
      } else if (theme == "funky") {
              document.getElementById('themer').href = 'resources/css/funky.css';
      }
    }

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
