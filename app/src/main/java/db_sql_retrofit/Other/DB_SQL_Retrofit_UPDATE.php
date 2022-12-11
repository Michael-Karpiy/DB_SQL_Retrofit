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

$sql = "UPDATE DB_SQL_Retrofit SET id = '$id', name = '$name', description = '$description' WHERE position = '$position';";

$result = mysqli_query($conn, $sql);

if($result){
    echo "Data Updated";
} else {
    echo "Failed";
}

mysqli_close($conn);

?>