var color_name;
var color_value="";
var colorParent = document.createElement("div");
var colorBoxDiv = document.createElement("div");
colorBoxDiv.setAttribute("id","color_box_div");
var colorBoxFrame = document.createElement("iframe");
colorBoxFrame.setAttribute("id","color_box_iframe");
colorBoxFrame.setAttribute("name","color_box_iframe");
colorBoxDiv.style.margin = "0";
colorBoxDiv.style.border = "1px solid #ccc";
colorBoxDiv.style.position="absolute";
colorBoxDiv.style.display="none";
colorBoxFrame.src=select_color_html;
colorBoxFrame.width=300;
colorBoxFrame.height=160;
colorBoxFrame.frameBorder = 0;
colorBoxFrame.scrolling = "no";
colorBoxDiv.appendChild(colorBoxFrame);
colorParent.appendChild(colorBoxDiv);
document.write(colorParent.innerHTML);
colorBoxDiv= document.getElementById("color_box_div");
function onSelectColor(obj){
      if(colorBoxDiv.style.display=="none"){
         colorBoxDiv.style.display="block";
      }else if(obj==color_name){
    	 colorBoxDiv.style.display="none";
      }	  
      color_name=obj;
      color_value=obj.value;
      colorBoxDiv.style.left=$(obj).offset().left+"px";
      colorBoxDiv.style.top=$(obj).offset().top+20+"px";
      //color_box_iframe.document.getElementById("ShowColor").
      color_box_iframe.colorObj.getShowColor().style.backgroundColor =obj.value;
}

function hideSelectColor(){
      colorBoxDiv.style.display="none";
}

function showSelectColor(){
      colorBoxDiv.style.display="block";
}