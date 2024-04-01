<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/static/css/bootstrap.css">
</head>
<body>
<div class="container">

    <div class="row justify-content-center mt-4">
        <form class="col-md-6" action="/student?action=create" method="post">
            <h1 class="">Add new Student</h1>
            <div class="form-group">
                <label >Name</label>
                <input type="text" class="form-control" name="name" placeholder="Enter name" >
            </div>
            <div class="form-group">
                <label >Email Address</label>
                <input type="text" class="form-control" name="email" placeholder="Enter email" >
            </div>
            <div class="form-group">
                <label >Date of Birth</label>
                <input type="text" class="form-control" name="birth"  placeholder="Enter date of birth as DD/MM/YYYY">
            </div>
            <div class="form-group">
                <label >Address</label>
                <input type="text" class="form-control" name="address" placeholder="Enter Address">
            </div>
            <div class="form-group">
                <label >Phone Number</label>
                <input type="text" class="form-control" name="phone" placeholder="Enter phone number">
            </div>

<%--                <input type="text" class="form-control" name="class" >--%>
            <div class="form-group">
            <label >Class</label>
            <select name="class" class="form-control" >
                <option ></option>
                <option>C0123I3</option>
                <option>C0222G3</option>
                <option>C0324H3</option>
                <option>C0422K3</option>
                <option>C0522Ư3</option>
            </select>
            </div>
            <div class="form-group mt-2">
                <button class="btn btn-success" role="button" type="submit">Create</button>
                <a id="" class="btn btn-dark" href="/student" role="button">Cancel</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
