<!DOCTYPE html>
<html lang="en">

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeCool LMS</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>

<body>

<div class="header">
    <a href="index.html"><h1>CodeCool LMS</h1></a>
</div>

<nav>
    <ul>
        <a href="profile.jsp"><li>Profile</li></a>
        <a href="curriculum-mentor"><li>Curriculum</li></a>
        <a href="solution.html"><li>Solution</li></a>
        <a href="attendance.html"><li>Attendance</li></a>
    </ul>
</nav>

<div class="wrapper">
    <div class="content">
        <div class="container">
            <div class="containerhead">
                <div class="title"><a href="">New Assignment</a></div>
            </div>

                <p>Title:</p>
                <p>${assignmentPage.getTitle()}</p>

                <p>Question: </p>
                <p>${assignmentPage.getQuestion()}</p>

                <form id=formABC action="blablabla" method="POST">
                    <p>Solution: </p>
                    <p><textarea rows="20" cols="20" name="solution" id=btnTest></textarea></p>
                <br><br>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
                <p><input type="submit" id=btnSubmit></p>

            </form>
            <div class="containerfoot"></div>
        </div>
    </div>

    <script>
    $(document).ready(function () {

        $("#formABC").submit(function (e) {

            //stop submitting the form to see the disabled button effect
            e.preventDefault();

            //disable the submit button
            $("#btnSubmit").attr("disabled", true);

            //disable a normal button
            $("#btnTest").attr("disabled", true);

            return true;

        });
    });
</script>
    <div class="sidebar">
        <div class="sbcontainer">
            <div class="containerhead">
                <div class="title">Sidebar title</div>
            </div>

            <div class="desc">
                <p>
                    Text Text Text Text Text Text Text Text Text Text Text Text
                </p>

            </div>

            <ul class="links">
                <div class="linktitle">Navigation</div>
                <li><a href="/">Sidebar link 1</a></li>
                <li><a href="/">Sidebar link 2</a></li>
                <li><a href="/">Sidebar link 3</a></li>
                <li><a href="/">Sidebar link 4</a></li>
            </ul>

        </div>
    </div>

</div>
</body>
</html>
