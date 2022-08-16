<?php
$db_name = "buero2";
$mysql_user = "root";
$mysql_password = "";
$server_name = "localhost";

$conn= mysqli_connect($server_name, $mysql_user, $mysql_password, $db_name);
$query = $conn->prepare("select beruf, betriebsart, ort, verfuegbarkeit from joboffer");
$query->execute();
$query->bind_result($beruf, $betriebsart, $ort, $verfuegbarkeit);
$contents = array();
while($query->fetch()) {
	$data = array();
	$data['beruf'] = $beruf;
	$data['betriebsart'] = $betriebsart;
	$data['ort'] = $ort;
	$data['verfuegbarkeit'] = $verfuegbarkeit;
	
	
	array_push($contents, $data);
	
}
echo json_encode($contents);

	
?>