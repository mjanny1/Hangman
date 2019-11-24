<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
<style>
     <%@ include file="../css/menu.css"%>
</style>
<script>


/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>
</head>
<body bgcolor="grey">
<div class="dropdown">
  <button onclick="myFunction()" class="dropbtn">Main Menu</button>
  <div id="myDropdown" class="dropdown-content">
    <a href="#home">Home</a>
    <a href="#about">Help</a>
    <a href="#contact">Quit</a>
  </div>
</div>
<form action="/HangManTechTycoons/guessLetterOrWord.html" method="post">
<h1 style="color: orange;" align="center">Welcome To HangMan,Lets Start The Game </h1>
<h2 style="color: white" align="center">Single User Mode</h2>
<div align="center" style="border: 1px solid white;">
<h2>Score Board:</h2><textarea rows="4" cols="10" disabled="disabled"></textarea></br></br></div>

<div style="border: 1px solid white;margin-top: 20px;" title="Hello" align="center">
<h3>Number Of Guess Left</h3><input type="text" value="" disabled="disabled">
<h3>Wrong Guesses</h3><input type="text" value="${wrongGuess}" disabled="disabled"></br></br>
<h3>Wrong Guesses</h3><input type="text" value="${wrongGuess}" disabled="disabled" hidden="true" name="wronguess"></br></br>
</div>


<div align="center" style="border: 1px solid white;margin-top: 20px;margin-bottom: 20px" >
<h3>Guess Word:
${word}</h3>
<input value=${word} name="modified1" hidden="true"/>


<input type="text" name="letterOrWord">
&nbsp&nbsp&nbsp&nbsp&nbsp
<input type="submit" style="background-color: orange;color: white;" value="Enter Guessed Letter/word"  >
</div>
</form>



</body>
</html>