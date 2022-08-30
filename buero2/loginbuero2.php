<?php
// $db = "buero2";
// $user = "test"; 
// $pass = "test"; 
// $host = "localhost";

$db = "buero2";
$user = $_POST["user"];
$pass = $_POST["pass"];
$host = "localhost";
//echo $user, $pass;
$conn = mysqli_connect($host, $user, $pass, $db);
if($conn){
	//echo "connected...!";
	$q = "SELECT * FROM login where username like '$user' and password like '$pass'";
	//$q2 = "insert into login (username, password) values($user, $pass)";
	
	
	$result = mysqli_query($conn, $q);
	//$result = mysqli_query($conn, $q2);
	
	
	
	if(mysqli_num_rows($result) > 0)
	{
		echo "login successfull...!";
	}else {
		echo "login failed....!";
	}
}else {
	echo "Not Connected....!";
}
	

?>