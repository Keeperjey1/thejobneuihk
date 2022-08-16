<?php
$getalldata = array();
require "connbuero2.php";
$sql = "SELECT * From users";
$result = mysqli_query($conn, $sql);
if($result->num_rows > 0) {
	$getalldata["userlogs"] = array();
	while($row = mysqli_fetch_array($result)) {
		//$userlogs["ufid"] = $row["id"];
		$userlogs["ufname"] = $row["name"];
		$userlogs["ufsurname"] = $row["surname"];
		$userlogs["ufage"] = $row["age"];
		$userlogs["ufusername"] = $row["username"];
		$userlogs["ufpassword"] = $row["password"];
		array_push($getalldata["userlogs"], $userlogs);
	}
}else{
	$getalldata["success"] = 0;
	$getalldata["msg"] = "No user data.";
}
echo json_encode($getalldata);
?>