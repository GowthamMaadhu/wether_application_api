<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel='stylesheet' href='css/bootstrap.css'>
<style>
        body {
            background-image: url('images/wether.jpg'); /* Specify the path to your image */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row justify-content-center align-items-center" style="height:100vh;">
        <div class="col-12 col-md-6">
        <h1 align="center" class="border p-3 mx-auto rounded bg-light" style="width: 50%;">Weather Application</h1>
        <form action="wetherservelet" method="post"align="center" class="border p-3 mx-auto rounded bg-light" style="width: 50%;">
            <div class="form-group">
                <label for="city">City</label>
                <input type="text" class="form-control" id="city" name="city" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    </div>
    </div>
</body>
</html>