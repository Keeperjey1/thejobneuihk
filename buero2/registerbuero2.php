<?php
require "connbuero2.php";
// $name = "Jey"; 
// $surname = "Mueller"; 
// $age = "33"; 
// $user_name = "keepe"; 
// $user_pass = "123";

$name = $_POST["name"];
$surname = $_POST["surname"];
$age = $_POST["age"];
$user_name = $_POST["username"]; 
$user_pass = $_POST["password"];

//$user_name = "tomy";
//$user_pass = "123";

$qreg = "insert into users (name, surname, age, username, password)values (
'$name', '$surname', '$age', '$user_name', '$user_pass');";
if(mysqli_query($conn, $qreg)) {
	echo "data inserted";
}else {
	echo "error";
}

// if($conn->query($qreg) == TRUE) {
	// echo "insert successful";
// }else {
	// echo "Error: " . $qreg . "<br>" . $conn->error;
// }
// $conn->close();

?>