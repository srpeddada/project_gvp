
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<?php
header("Content-Type: application/json");
$uploaded=array();
if(!empty($_FILES["file"]["name"][0])){
    foreach($_FILES["file"]["name"] as $position=>$name){
        if(move_uploaded_file($_FILES["file"]["tmp_name"][$position],"C:\Users\sai\Documents\uploads/".$name )){
          echo "upload done succesfully....:)";  
        } 
    }
}

