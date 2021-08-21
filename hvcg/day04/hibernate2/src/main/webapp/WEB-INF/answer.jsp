<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Result</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Search Result</h1>
    <table class="table table-sm table-bordered bg-info">
        <thead>
            <tr>
                <th>Student's Id</th>
                <th>Student's Name</th>
                <th>Student's Age</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                out.println(request.getAttribute("answers"));
            %>
            <tr><td></td><td></td><td></td><td></td></tr>
            <tr class="bg-success">
                <td><strong>ADD MORE STUDENT</strong></td>
                <td></td><td></td>
                <td><button class="btn btn-primary ml-3 btn-add">Click to add</button></td>
            </tr>
            <tr class="bg-success">
                <th>Student's Id</th>
                <th>Student's Name</th>
                <th>Student's Age</th>
                <th>Student's  Birthday</th>
            </tr>
            <tr class="bg-success">
                <td>Auto generated id</td>
                <td><input class="w-100" type="text" id="name" name="name" placeholder="Input student's name"></td>
                <td><input class="w-100" type="number" id="age" name="name" placeholder="Input student's age"></td>
                <td><input class="w-100" type="date" id="birthday" name="birthday"></td>
            </tr>
            <tr><td></td><td></td><td></td><td></td></tr>
            <tr class="bg-secondary">
                <td><strong>MODIFY STUDENT INFO</strong></td>
                <td></td><td></td>
                <td><button class="btn btn-warning btn-confirm ml-3">Click to confirm</button></td>
            </tr>
            <tr class="bg-secondary">
                <td id="edit-id">Auto generated id</td>
                <td><input class="w-100" type="text" id="edit-name" name="edit-name" onclick="this.select()"></td>
                <td><input class="w-100" type="number" id="edit-age" name="edit-name" onclick="this.select()"></td>
                <td><input class="w-100" type="date" id="edit-birthday" name="edit-birthday"></td>
            </tr>
        </tbody>
    </table>
</div>
<script>
    $(document).ready(function(){
        //Delete a Student
        $("button.btn-delete").click(function() {
            //Get id of deleted student
            var deleteId = '' + $(this).parent().parent().find("td.std-id").html();

            //Hide deleted student row
            $(this).parent().parent().hide();

            //Delete student in DB _ use JavaScript - Fetch
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "text/plain");

            var requestOptions = {
                method: 'DELETE',
                headers: myHeaders,
                body: deleteId,
                redirect: 'follow'
            };

            fetch("http://localhost:9090/ui-student/", requestOptions)
                .then(response => response.text())
                .then(result => console.log(result))
                .catch(error => console.log('error', error));
        });

        //Add a Student into DB
        $("button.btn-add").click(function() {
            var name = $("input#name").val();
            var age = $("input#age").val();
            var birthday = $("input#birthday").val();

            //Add student to DB _ use JavaScript - Fetch
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            var raw = JSON.stringify({
                "name": name,
                "age": age,
                "birthday": birthday
            });

            var requestOptions = {
                method: 'POST',
                headers: myHeaders,
                body: raw,
                redirect: 'follow'
            };

            fetch("http://localhost:9090/student", requestOptions)
                .then(response => response.text())
                .then(result => {
                    console.log(result);
                    location.reload(true);
                })
                .catch(error => console.log('error', error));
        });

        //Edit a Student
        var $editStdId;
        var $editStdName;
        var $editStdAge;

        var $editId;
        var $editName;
        var $editAge;
        var $editBirthday = $("input#edit-birthday");

        $("button.btn-edit").click(function() {
            $editStdId = $(this).parent().parent().find("td.std-id");
            $editStdName = $(this).parent().parent().find("td.std-name");
            $editStdAge = $(this).parent().parent().find("td.std-age");

            $editId = $("td#edit-id");
            $editName = $("input#edit-name");
            $editAge = $("input#edit-age");

            $editId.text($editStdId.html());
            $editName.val($editStdName.html());
            $editAge.val($editStdAge.html());

            $editName.focus();
            $editName.select();


            // Check birthday ?
            var id = $editStdId.html();
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "text/plain");

            var requestOptions = {
                method: 'PUT',
                headers: myHeaders,
                body: id,
                redirect: 'follow'
            };

            fetch("http://localhost:9090/ui-student", requestOptions)
                .then(response => response.text())
                .then(result => {
                    alert("Birthday: " + result);
                    $editBirthday.val(result);
                })
                .catch(error => console.log('error', error));
        });

        $("button.btn-confirm").click(function() {
            var id = $editId.html();
            var name = $editName.val();
            var age = $editAge.val();
            var birthday = $("input#edit-birthday").val();

            //Add student to DB _ use JavaScript - Fetch
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/json");

            var raw = JSON.stringify({
                "id": id,
                "name": name,
                "age": age,
                "birthday": birthday
            });

            var requestOptions = {
                method: 'POST',
                headers: myHeaders,
                body: raw,
                redirect: 'follow'
            };

            fetch("http://localhost:9090/student", requestOptions)
                .then(response => response.text())
                .then(result => {
                    console.log(result);
                    location.reload(true);
                })
                .catch(error => console.log('error', error));
        });
    });
</script>
</body>
</html>