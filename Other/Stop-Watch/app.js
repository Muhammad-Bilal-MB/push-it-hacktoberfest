const startStop = document.querySelector(".start-stop")
const reset = document.querySelector("#reset")

var millisecond = 0
var second = 0
var minute = 0
var hour = 0

var millisecondVal = ""
var secondVal = ""
var minuteVal = ""
var hourVal = ""

const msec = document.querySelector(".msec")
const sec = document.querySelector(".sec")
const min = document.querySelector(".min")
const hr = document.querySelector(".hour")

const startStopIcon = document.querySelector(".start-stop i")
const bottomBtn = document.querySelector(".bottom-btn")

var interval = ""

//StopWatch
function stopWatch() {
    millisecond++


    if (millisecond === 100) {
        second++

        millisecond = 0


    }



    if (second == 60) {
        minute++

        second = 0


    }



    if (minute == 60) {
        hour++

        minute = 0

    }

    millisecondVal = updateTime(millisecond)
    msec.innerHTML = millisecondVal

    secondVal = updateTime(second)
    sec.innerHTML = secondVal

    minuteVal = updateTime(minute)
    min.innerHTML = minuteVal

    hourVal = updateTime(hour)
    hr.innerHTML = hourVal
}
function updateTime(i) {
    if (i < 10) {
        i = "0" + i
    }
    return i
}



//Start-Stop Button
var play = false

function playPause() {
    if (play === false) {
        interval = setInterval(stopWatch, 10)

        startStopIcon.classList.remove("fa-play")
        startStopIcon.classList.add("fa-pause")

        play = true

        document.getElementById("reset").style.display = "block"
        document.getElementById("lap-btn").style.display = "block"
    }
    else {
        clearInterval(interval)

        startStopIcon.classList.remove("fa-pause")
        startStopIcon.classList.add("fa-play")

        play = false

    }
}



//Reset Button

function rst() {
    startStopIcon.classList.remove("fa-pause")
    startStopIcon.classList.add("fa-play")

    millisecond = 0
    second = 0
    minute = 0
    hour = 0

    var millisecondVal = updateTime(millisecond)
    msec.innerHTML = millisecondVal

    var secondVal = updateTime(second)
    sec.innerHTML = secondVal

    var minuteVal = updateTime(minute)
    min.innerHTML = minuteVal

    var hourVal = updateTime(hour)
    hr.innerHTML = hourVal

    clearInterval(interval)

    play = false

    document.getElementById("reset").style.display = "none"
    document.getElementById("lap-btn").style.display = "none"

    document.getElementById("border").style.height = "300px"
    document.getElementById("main").style.gridTemplateRows = "70px 1fr 1fr 80px"

    lapList.innerHTML = ""
}


//Light-Dark Theme Icon
const light = document.querySelector("#light-theme")
const dark = document.querySelector("#dark-theme")
const main = document.querySelector("body")
const stopWatchBorder = document.querySelector(".border")
const themeButton = document.querySelector(".theme")

function lightTheme() {
    light.classList.add("active")
    dark.classList.remove("dark-active")

    main.classList.remove("dark-theme")

    stopWatchBorder.classList.remove("dark-theme-border")
}

function darkTheme() {
    dark.classList.add("dark-active")
    light.classList.remove("active")

    main.classList.add("dark-theme")

    stopWatchBorder.classList.add("dark-theme-border")

    themeButton.classList.add("dark-theme-button")

}


//Lap Function
const lapList = document.querySelector(".lap-list")

function lap(){
    document.getElementById("border").style.height = "150px"
    document.getElementById("main").style.gridTemplateRows = "70px 245px 1fr 80px"

    var newLap = document.createElement("li")
    lapList.appendChild(newLap)

    newLap.innerHTML = hourVal + ":" + minuteVal + ":" + secondVal + ":" + millisecondVal
}