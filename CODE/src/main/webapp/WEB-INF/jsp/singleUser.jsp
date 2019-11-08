<html>
<body bgcolor="gray">
<h1 style="color: blue;margin-bottom: 20px;" align="center">Welcome To HangMan,Lets Start The Game </h1>
<div style="border: 1px solid black" title="Hello" align="center">
<br/>
<button onclick="showDiv()">Start Game</button>
</br>
</br>
<button>End Game</button>
</br>
</br>
<button style="width: 80px;">Help</button>
</br></br>
</div>
<script type="text/javascript">
function showDiv()
{
document.getElementById('welcomeDiv').style.display = "block";
}
</script>

<div style="border: 1px solid black;margin-top: 50px" align="center">
<h3>Level Of Difficulty</h3>
<a href="">Easy</a></br></br>
<a href="">Medium</a></br></br>
<a href="">Difficult</a>
</div>

<div style="border: 1px solid black;margin-top: 50px;display: none" align="center"  id="welcomeDiv">
<h3>Time Remaining:</h3>
<h3>00:00</h3>

<h4>Guess Word</h4><p>
<input type="text" value=" " style="width: 10px">
<input type="text" value=" " style="width: 10px">
<input type="text" value=" " style="width: 10px">
<input type="text" value=" " style="width: 10px">
<input type="text" value=" " style="width: 10px">
<input type="text" value=" " style="width: 10px">

</p>

</div>



</body>
</html>