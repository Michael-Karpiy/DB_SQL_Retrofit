<?php

$servername = "";
$username = "";
$password = "";
$db = "";

$conn = new mysqli($servername,$username,$password,$db);

if($conn->connect_error){
	die("connection failed: ".$conn->connect_error);
}

$position = $_POST['position'];
$id = $_POST['id'];
$name = $_POST['name'];
$description = $_POST['description'];

mysqli_query($conn,"Insert into DB_SQL_Retrofit(position,id,name,description)VALUES('$position','$id','$name','$description')");


mysqli_close($conn);

?>