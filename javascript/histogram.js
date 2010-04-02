/***
  * <h3>柱状图</h3>
  * Copyright (c) 2008  expopo.cn All Rights Reserved
  * $author:an_chuantong<an_chuantong@yahoo.com.cn> $
  * $Date: 2008-10-16 22:21:25 -0500 (Thurs, 16 Oct 2008) $
  * 为了在FireFox中支持,需要在html代码中<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  * 中去掉XHTML,或改为HTML
  */
function Histogram() {
}
Histogram.prototype = new Histogram();
Histogram.prototype = {
          init:function(parentId,width,height,limit){
              this.width=width;
              this.height=height;
              this.limit=limit;
              this.xwidth=60;
              this.xlimit=60;
              this.parentObj=document.getElementById(parentId);
              this.content=this.createDiv("content");
              this.content.style.width=width+"px";
              this.content.style.height=height+"px";
              this.content.style.border="1px dashed red";
              this.content.style.margin="10px 10px 20px 20px";
              this.parentObj.style.margin="30px 10px 20px 20px";
              this.addLine()
              this.addIntro("次数","时间(月/日)")
              this.parentObj.appendChild(this.content);
              if(window.sidebar){
                 this.parentObj.style.width=width+40+"px";
              }   
          },
          add:function(data){
             var CLASS=this;
             
             if(data.length>0){
                
                var xwidth=parseInt(CLASS.width/CLASS.xlimit);
                
                
                var xheight=CLASS.height;
                var last=0;
                var len=data.length;
                var wl=0;
                if(data.length<CLASS.xlimit){
                  //wl=parseInt(CLASS.width/2-data.length*5);
                  
                  if((CLASS.width%data.length)%2==1){
                  wl=parseInt((CLASS.width%data.length)/2)-1;
                  xwidth=parseInt(CLASS.width/data.length)-1;
                  }else{
                  wl=parseInt((CLASS.width%data.length)/2);
                  xwidth=parseInt(CLASS.width/data.length);
                  }
                }else{
                  len=CLASS.xlimit;
                }
                
                CLASS.xwidth=xwidth;
                
                for(var i=0;i<len;i++){
                     var xObj=this.createDiv("x"+data[i].rel);
                     var h=(CLASS.height*data[i].ys)/CLASS.limit-2;
                     if(data[i].ys==0){
                       h=0;
                     }
                     last=0;
                     if(i==0){
                        xObj.style.borderLeft="1px solid #ccc";
                        last+=1;
                     }else if(data[i-1].ys<=data[i].ys){
                        xObj.style.borderLeft="1px solid #ccc";
                        last+=1;
                     }
                     
                     if(i==len-1){
                        xObj.style.borderRight="1px solid #ccc";
                        last+=1;
                     }else if(data[i+1].ys<data[i].ys){
                        xObj.style.borderRight="1px solid #ccc";
                        last+=1;
                     }
                     xObj.style.width=xwidth+"px";
                     if(i>0){ 
                        wl+=xwidth;
                     }
                     if(document.all){
                       xObj.style.width=xwidth-last+"px";
                     }
                     if(h>0){
                     xObj.style.height=h+"px";
                     // xObj.style.background="#dcdcf4";
                     xObj.style.background="#e8e8e8";
                     xObj.style.position="absolute";
                     xObj.style.borderTop="1px solid #ccc";
                     xObj.style.left=CLASS.content.offsetLeft+wl;
                     if(last==0){
                       if(window.sidebar){
                         xObj.style.left=CLASS.content.offsetLeft+wl+2;  
                       }   
                     }
                     xObj.style.top=CLASS.content.offsetTop + CLASS.height-h;
                     xObj.onmouseover = function(){
                            CLASS.mouseover(this)
	                 }
	                   
	                 xObj.onmouseout = function(){
	                        CLASS.mouseout(this)
		             }
		             xObj.onclick= function(){
		                   CLASS.xclick(this);
		             }
		             }
		            // if(i%4==0||i==len-1){
                       var xaObj=this.createDiv("xa"+i);
                       xaObj.style.position="absolute";
                       xaObj.innerHTML=data[i].xa;
                       xaObj.style.left=CLASS.content.offsetLeft+wl;
                       xaObj.style.top=CLASS.content.offsetTop + CLASS.height;
                       xaObj.style.textAlign="center";
                       CLASS.parentObj.appendChild(xaObj);
                    // }
                     if(h>0){
                       CLASS.parentObj.appendChild(xObj);
                     }
                    
                }
             }
          },
          addLine:function(){
                var CLASS=this;
                var ha=CLASS.height/5;
                var ysg=CLASS.limit/5;
                for(var i=1;i<5;i++){
                    var lineObj=CLASS.createDiv("line"+i);
                    lineObj.style.width=CLASS.width-5+"px";
                    lineObj.style.borderBottom="1px dashed #ccc";
                    lineObj.style.height=ha+"px";
                    lineObj.style.margin="0 0 0 5px";
                    this.content.appendChild(lineObj);
                    var ylabel=CLASS.createDiv("y"+i);
                    ylabel.style.position="absolute";
                    var ys=CLASS.limit-parseInt(ysg*i)+"";
                    ylabel.innerHTML=ys;
                    ylabel.style.left=CLASS.parentObj.offsetLeft - ys.length+2;
                    ylabel.style.top=ha*i+CLASS.parentObj.offsetTop-6;
                    CLASS.parentObj.appendChild(ylabel)
                }
          },
          addIntro:function(yIntro,xIntro){
               var CLASS=this;
               var yIntroObj=CLASS.createDiv("yIntro");
               yIntroObj.innerHTML=yIntro;
               yIntroObj.style.position="absolute";
               yIntroObj.style.left=CLASS.parentObj.offsetLeft;
               yIntroObj.style.top=CLASS.parentObj.offsetTop-12;
               CLASS.parentObj.appendChild(yIntroObj);  
               var xIntroObj=CLASS.createDiv("xIntro");
               xIntroObj.innerHTML=xIntro;
               xIntroObj.style.position="absolute";
               xIntroObj.style.left=CLASS.parentObj.offsetLeft+CLASS.width/2;
               xIntroObj.style.top=CLASS.parentObj.offsetTop+CLASS.height+25;
               CLASS.parentObj.appendChild(xIntroObj);    
               
          },
          createDiv:function(id){
	            var div = document.createElement("div");
	            div.setAttribute("id",id);
	            return div;
          },
          mouseout:function(obj){
                var CLASS=this;
                obj.style.background ="#e8e8e8";
		        CLASS.parentObj.removeChild(document.getElementById("xtitle"));
          },
          mouseover:function(element){
                var CLASS=this;
                element.style.background ="#dcdcf4";
		        var obj=CLASS.createDiv("xtitle");
		        var str=element.id.substring(1);
		        str=str.substring(5,7)+"月"+str.substring(8,10)+"日:点击"+str.substring(11,str.indexOf("<br/>"))+"次"+str.substring(str.indexOf("<br/>")).replace("ip","独立IP").replace("username","独立用户");
		        obj.innerHTML=str;
		        obj.style.position="absolute";
		        obj.style.left=element.offsetLeft;
                obj.style.top=element.offsetTop-60;
                obj.style.background="#FFFCCC";
                obj.style.border="1px dashed #ccc";
                obj.style.padding="5px";
                obj.style.textAlign="left";
		        CLASS.parentObj.appendChild(obj); 
		      }  
}