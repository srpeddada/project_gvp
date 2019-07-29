<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<?php
session_start();
if(!isset($_SESSION['user'])){
    header("location: index.php");
}
?>

<html>
    <head>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/drop.css"/>
    </head>
    <body style="text-align:center;background-image: url(pic2_1.jpg);">
        <div class="header">
  <img src="banner.jpg" style="width: 1400px;" />
</div>

<div id="navbar">
  <a href="#home" class="fa fa-home" style="font-size:20px;padding: 21px 15px; "> </a>
  <a href="index.php?logout=true" style="font-size:20px;padding: 15px 13px;float: right;">logout</a>
  <a href="#" style="font-size:20px;padding: 15px 13px;float: right;"><?php echo $_SESSION["user"]?></a>
  <a href="#con" style="font-size:20px;padding: 15px 13px;">contact</a>
 
</div><br><br>
        <form action="http://localhost:8080/projectsri/login1" method="get" >
        <input type="text" name="faculty" placeholder="Faculty name"  required="required" pattern="[a-zA-Z]{3,}"><br><br>
    <select id="country" name="country">
      <option value="australia">CSE</option>
      <option value="canada">ECE</option>
      <option value="usa">IT</option>
    </select>
        <br><br>
        <input type="text" name="faculty1" placeholder="File name"  required="required" pattern="[a-zA-Z]{3,}">
    <center>
        <div class="dropzone" id="dropzone">drop your files hear....!!!</div><br>
        <div id="uploads" style="width:25%"></div><br>
    </center>
        <input type="submit" value="submit if uploaded"><br><br><br>
        </form>
        <script>
            
           (function(){
            var drop =document.getElementById("dropzone");
            var upload=function(files){
                var formData= new FormData(),
                        sr=new XMLHttpRequest(),x;
                for(x=0;x<files.length;x++){
                    formData.append("file[]",files[x]);
                }
                sr.onload=function(){
                var data=this.responseText;
                document.getElementById("uploads").innerHTML=data;}
                sr.open("post","drop.php"); 
                sr.send(formData);
            };
 
            dropzone.ondrop=function(e){
                e.preventDefault();
                this.className="dropzone";
                upload(e.dataTransfer.files);
            };
            dropzone.ondragover=function(){
                this.className="dropzone dragover";
                return false;
            };
            dropzone.ondragleave=function(){
                this.className="dropzone";
                return false;
             };
            
    }()); 
        </script>
    </body>
</html>