<?php

$servername = "";
$username = "";
$password = "";
$db = "";

$conn = new mysqli($servername,$username,$password,$db);

if($conn->connect_error){
	die("connection failed: ".$conn->connect_error);
}

$position = $_POST["position"];

$sql = "DELETE FROM DB_SQL_Retrofit WHERE position='$position'";

$result = mysqli_query($conn,$sql);

if($result){
    echo "Data Deleted";
}
else{
    echo "Failed";
}

mysqli_close($conn);

?>