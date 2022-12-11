<?php

$servername = "";
$username = "";
$password = "";
$db = "";

$conn = new mysqli($servername,$username,$password,$db);

if($conn->connect_error){
	die("connection failed: ".$conn->connect_error);
}

$Table = $_GET["Table"];
$Column = $_GET["Column"];
$Name = $_GET["Name"];

if (isset($Name))
{
	$query = "SELECT * FROM $Table WHERE $Column LIKE '%$Name%'";
	$result = mysqli_query($conn, $query);
	$response = array();
	if (mysqli_num_rows($result) > 0)
	{
		while($row = mysqli_fetch_assoc($result))
			$response[] = $row;
	}

	echo json_encode($response);
}
mysqli_close($conn);

?>