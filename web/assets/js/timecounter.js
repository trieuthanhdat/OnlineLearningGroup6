function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 60 ? "0" + minutes : minutes;
        seconds = seconds < 60 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
            timer = duration;
        }
    }, 1000);
}

window.onload = function () {
    var mins = document.getElementById("time-duration").value;
    var fiveMinutes = 60 * parseInt(mins);
//    console.log(parseInt(mins) + " - " + fiveMinutes);
//    console.log(typeof(mins));
    display = document.querySelector('#time');
    startTimer(fiveMinutes, display);    
};