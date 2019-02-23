// 它将文本内容转换为rgb，这样文本内容就可以保存为一张图片，然后在通过js又可以将图片中的数据转换为相应的文本内容
// 下面是es6代码



/***
 * @author					tangtao
 * @date					2019年1月27日19:57:35
 * @function 				
 * @achieve					
 * @param unknown 
 * @return number			
 */
 
 
/******************    调用语句       **********************/      以下
        // 需要转换的字符串
        jsorg='absggd1324452';
        // 将字符串转换为png
        toPng(jsorg);
        // 将png转换为字符串
        tojs("my_png");



/**************                实现代码        **************/


//将js代码编码为图片
function toPng(jsorg){
    var canvas  = document.getElementById("my_png");
    var context = canvas.getContext("2d");
    var width  = Math.ceil(Math.sqrt(jsorg.length / 3));
    var height = width;
    $("#my_png").attr("width",width).attr("height",height);
    $("#my_png").width(width).height(height);
    var imageData = context.createImageData(width, height);
    
    var a = stringToHex(jsorg);
    var arr = a.split(",");
    for(var i = 0,j=0;j<width*height*4;i++,j++){
        if((j+1)%4==0){
            imageData.data[j]=255;
            j++;
        }
        if(i<arr.length)
            imageData.data[j] = arr[i];
    }
    consolelog(imageData.data);
    context.putImageData(imageData, 0, 0);
}


function stringToHex(str){
var val="";
for(var i = 0; i < str.length; i++){
if(val == "")
val = str.charCodeAt(i).toString(16);
else
val += "," + str.charCodeAt(i).toString(16);
}
return val;
}



//解析图片为js代码
function tojs(canvasId){
    var canvas  = document.getElementById(canvasId);
    var context = canvas.getContext("2d");
    var width  = $("#"+canvasId).width();
    var height = $("#"+canvasId).height();
    var imageData = context.getImageData(0, 0, width, height);
//console.log(imageData.data);
    var str = "";
    for(var i = 0;i<imageData.data.length;i++){
        if((i+1)%4==0)
            continue;
        str += imageData.data[i]!=0?hexToString(imageData.data[i]):'';
    }
    $("#my_js").html(str);
    consolelog(str);
}


function hexToString(str){
        var i=String.fromCharCode(parseInt(str,16));
        return i;
}


//************************分割*************************************


// 接下来是html代码

<div style="margin:20 ;">
        
        <canvas id="my_png" ></canvas>
        <div id="my_js"></div>
        
</div>








