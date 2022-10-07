
const r = 100;
const x = 175;
const y = 150;
function draw(id){
    let canvas = document.getElementById(id);
    let pen = canvas.getContext("2d");
    //background
    pen.fillStyle = "white" ;
    pen.beginPath();
    pen.rect(0,0,2*x, 2*y);
    pen.closePath();
    pen.fill();

    //axis
    pen.beginPath();
    pen.font = "20px Verdana";
    //y
    pen.moveTo(x, 0);
    pen.lineTo(x, 2*y);
    //x
    pen.moveTo(0, y);
    pen.lineTo(2*x, y);
    //arrow of y
    pen.moveTo(x-7,7);
    pen.lineTo(x,0);
    pen.moveTo(x+7,7);
    pen.lineTo(x,0);
    //arrow of x
    pen.moveTo(2*x-7,y-7);
    pen.lineTo(2*x,y);
    pen.moveTo(2*x-7,y+7);
    pen.lineTo(2*x,y);
    pen.stroke();

    //clear a range and set it's transparency
    pen.fillStyle = "orange";
    pen.clearRect(0,0,canvas.width,canvas.length);
    pen.globalAlpha = 0.5;

    //first quadrant
    pen.beginPath();
    pen.moveTo(x,y);
    pen.lineTo(x,y-100);
    pen.lineTo(x+100,y);
    pen.fill();
    pen.closePath();

    //second quadrant
    pen.beginPath();
    pen.rect(75,50,r,r);
    pen.closePath();
    pen.fill();

    //third quadrant
    pen.beginPath();
    pen.moveTo(x,y);
    pen.arc(x,y,r,0.5*Math.PI,Math.PI);
    pen.closePath();
    pen.fill();

    //find R
    //onx
    let length = 2.5
    pen.beginPath();
    pen.font = "20px Verdana";
    pen.moveTo(x-3/2*r,y+length);
    pen.lineTo(x-3/2*r,y-length);
    pen.moveTo(x-r,y+length);
    pen.lineTo(x-r,y-length);
    pen.moveTo(x-r/2,y+length);
    pen.lineTo(x-r/2,y-length);
    pen.moveTo(x-r/2,y+length);
    pen.lineTo(x-r/2,y-length);
    pen.moveTo(x+r/2,y+length);
    pen.lineTo(x+r/2,y-length);
    pen.moveTo(x+r,y+length);
    pen.lineTo(x+r,y-length);
    pen.moveTo(x+3/2*r,y+length);
    pen.lineTo(x+3/2*r,y-length);
    //ony
    pen.moveTo(x-length,y-r);
    pen.lineTo(x+length,y-r);
    pen.moveTo(x-length,y-1/2*r);
    pen.lineTo(x+length,y-1/2*r);
    pen.moveTo(x-length,y+1/2*r);
    pen.lineTo(x+length,y+1/2*r);
    pen.moveTo(x-length,y+r);
    pen.lineTo(x+length,y+r);
    pen.stroke();
    //put R
    pen.fillStyle = "black";
    pen.font = "15px Verdana";
    pen.fillText("-3R/2", x-1.5*r-20, y+8*length);
    pen.fillText("-R", x-r-10, y+8*length);
    pen.fillText("-R/2", x-0.5*r-15, y+8*length);
    pen.fillText("R/2", x+0.5*r-10, y+8*length);
    pen.fillText("R", x+r-5, y+8*length);
    pen.fillText("3R/2", x+1.5*r-15, y+8*length);
    pen.fillText("R",x+8,y-r+5);
    pen.fillText("R/2",x+8,y-0.5*r+5);
    pen.fillText("-R/2",x+8,y+0.5*r+5);
    pen.fillText("-R",x+8,y+r+5);
    pen.stroke();
    pen.closePath();
}

function doClick(id,R){
    if(R === null){
        alert("If you want to choose a point by click, please set your R at first!");
    }else {
        let keep = R;
        R = parseFloat(R);
        let can = document.getElementById(id);
        let br = can.getBoundingClientRect();
        let left = br.left;
        let top = br.top;
        let Win = window.event;
        let findX = Win.clientX-left;
        let findY = Win.clientY-top;
        //coordinates
        findX = (findX-x);
        findY = (y-findY);
        //real sent
        let sentX = findX/r*R;
        let sentY = findY/r*R;
        sendByClick(sentX.toFixed(2),sentY.toFixed(2),keep);
    }
}
function sendByClick(X,Y,R){
    let XMR = new XMLHttpRequest();
    let url = "Controller?X="+X+"&Y="+Y+"&R="+R;
    XMR.open("GET", url,true);
    XMR.setRequestHeader("Accept-Language", "en-US");
    XMR.send();
    XMR.onreadystatechange = function()
    {
        /*
            make sure that asynchronous
            when state changed, this function will be called
        */
        if(XMR.readyState === 4 && XMR.status === 200) {
            if(!checkR(R)){
                alert("Your R is out of range. Make sure it is set in (1,3)");
            }else if(!checkX(X)){
                alert("Your X is out of range. Make sure it is set in (-4,4)")
            }else if(!checkY(Y)){
                alert("Your Y is out of range. Make sure it is set in (-3,3)");
            }else {
                window.location = 'result.jsp';
            }
        }
    };
}
//input real absolute coordinate to the frame
function drawPoint(id,x,y,inRange) {
    if (x !== null) {
        let canvas = document.getElementById(id);
        let pen = canvas.getContext("2d");

        let xDraw = x;
        let yDraw = y;
        pen.beginPath()
        if (inRange) {
            pen.fillStyle = "green";
        } else {
            pen.fillStyle = "blue";
        }
        pen.fillRect(xDraw, yDraw, 4, 4);
        pen.fill();
        pen.stroke();
        pen.closePath();
    }
}
function checkY(value){
    if(isNaN(value)){
        return false;
    }else if(parseFloat(value) > 3 || parseFloat(value) < -3){
        return false;
    }else {
        return true;
    }
}
function checkX(value){
    if(parseFloat(value)<-4.0 || parseFloat(value)>4.0){
        return false;
    }else {
        return true;
    }
}
function checkR(value) {
    if(parseInt(value) < 1 || parseInt(value) > 3){
        return false;
    }else {
        return true;
    }
}

