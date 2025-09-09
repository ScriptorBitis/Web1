const canvas = document.getElementById("graphic");
const ctx = canvas.getContext("2d");

console.log("Поехали")

function drawEvr() {
    ctx.font = "bold 16px Arial";


    ctx.beginPath();
    ctx.moveTo(200, 200);
    ctx.lineTo(200, 400);

    ctx.moveTo(200, 200);
    ctx.lineTo(200, 0);

    ctx.lineTo(215, 15);
    ctx.moveTo(200, 0);
    ctx.lineTo(185, 15);

    ctx.moveTo(200, 200);
    ctx.lineTo(0, 200);

    ctx.moveTo(200, 200);
    ctx.lineTo(400, 200);

    ctx.lineTo(385, 215);
    ctx.moveTo(400, 200);
    ctx.lineTo(385, 185);
    ctx.closePath();
    ctx.fillStyle = "#000000";
    ctx.fillText("Y", 230, 15)
    ctx.fillStyle = "#000000";
    ctx.fillText("X", 385, 170)


//рисуем деления на оси x

    for (let n1 = 40; n1 <= 360; n1 += 40) {
        ctx.moveTo(n1, 200);
        ctx.lineTo(n1, 206);
        ctx.lineTo(n1, 194);

    }
//рисуем деления на оси y

    for (let n1 = 40; n1 <= 360; n1 += 40) {
        ctx.moveTo(200, n1);
        ctx.lineTo(206, n1);
        ctx.lineTo(194, n1);
    }

    ctx.fillText("-4R", 30, 190)
    ctx.fillText("-3R", 70, 190)
    ctx.fillText("-2R", 110, 190)
    ctx.fillText("-1R", 150, 190)
    ctx.fillText("4R", 350, 190)
    ctx.fillText("3R", 310, 190)
    ctx.fillText("2R", 270, 190)
    ctx.fillText("1R", 230, 190)

    ctx.fillText("4R", 210, 40)
    ctx.fillText("3R", 210, 80)
    ctx.fillText("2R", 210, 120)
    ctx.fillText("1R", 210, 160)
    ctx.fillText("-4R", 210, 360)
    ctx.fillText("-3R", 210, 320)
    ctx.fillText("-2R", 210, 280)
    ctx.fillText("-1R", 210, 240)

    ctx.strokeStyle = 'black';
    ctx.lineWidth = 2;
    ctx.stroke();
}
const myForm = document.forms['Rform'];
for (let i = 0; i < myForm.radioR.length; i++) {
    myForm.radioR[i].addEventListener('click', onclick);
}

function onclick(e){
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    let r = e.target.value;
    ctx.beginPath()

    //треугольник  в 2 четверти
    ctx.moveTo(200-20*r, 200);

    ctx.lineTo(200,200 - r*20 );
    ctx.lineTo(200,200)
    ctx.lineTo(200-20*r, 200)

    ctx.fillStyle = "red";
    ctx.fill()
    //четверть круга  в 1 четверти
    ctx.moveTo(200, 200);


    ctx.arc(200,200,r*40, -Math.PI/2, 0)
    ctx.moveTo(200, 200);
    ctx.moveTo(200, 200+40*r)
    ctx.fill()

    //прямоугольник 0.5 К 1 В 4  четверти

    ctx.lineTo(200, 200);
    ctx.lineTo(200+r*40, 200);
    ctx.lineTo(200+r*40, 200+r*20);
    ctx.lineTo(200,200+20*r)
    ctx.fill()


    ctx.closePath();
    ctx.strokeStyle = "red";
    ctx.lineWidth = 2;
    ctx.stroke();


    drawEvr();
}


document.getElementById("Xinput").addEventListener("input", checkX);

function  checkX(e) {
    console.log("Началась проверка вот этого - >" + e.target.value);


    //пережиток прошлого
    e.target.value = e.target.value.replace(/[^0-9.-]/g, "");
    e.target.in

    const input = e.target;
    const selectionStart = input.selectionStart;
    let value = input.value;

    if (value === "" || value === "-" || value === "." || value === "-.") {
        return;
    }

    if (isNaN(Number(value))) {
        input.value = value.slice(0, selectionStart - 1) + value.slice(selectionStart);
        input.setSelectionRange(selectionStart - 1, selectionStart - 1);

    }
    if (Number(value) > 3 ) {
        input.value = value.slice(0, selectionStart - 1) + value.slice(selectionStart);
        input.setSelectionRange(selectionStart - 1, selectionStart - 1);
    }
    if (Number(value) < - 3) {
        input.value = value.slice(0, selectionStart - 1) + value.slice(selectionStart);
        input.setSelectionRange(selectionStart - 1, selectionStart - 1);

    }
}

drawEvr()