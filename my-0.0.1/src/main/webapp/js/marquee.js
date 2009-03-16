/***
  * 根据节点ID和滚动速度speed
  * Copyright (c) 2008  expopo.cn All Rights Reserved
  * $author:an_chuantong<an_chuantong@yahoo.com.cn> $
  * $Date: 2008-08-03 00:21:25 -0500 (Wed, 06 Feb 2008) $
  */

function MarqueeJs() {
}
MarqueeJs.prototype = new MarqueeJs();
MarqueeJs.prototype = {
   init:function(id,speed,way){
        this.speed=speed;
        this.way=way;
        this.objBrother=this.createDiv(id+"2");
        this.objParent=document.getElementById(id);
        this.obj=this.createDiv(id+"1");
        this.objParent.className="boxs";
        this.obj.innerHTML=this.objParent.innerHTML;
        this.objParent.innerHTML="";
		//appendChild
		    this.num=1;
		    this.objParent.appendChild(this.obj);
		    this.objParent.appendChild(this.objBrother);
		    if(way==2){
		    this.obj.style.styleFloat="left";
		    this.obj.style.cssFloat="left";
		    this.objBrother.style.cssFloat="left";
		    this.objBrother.style.styleFloat="left";
		    }
		    this.objBrother.innerHTML=this.obj.innerHTML;
		    this.start(way)
   },
   start:function(way){
       var thisObj = this;	
       if(way==1){
		     thisObj.scrollID = function(){
		        thisObj.up();
		     }
	   }else if(way==2){
		     thisObj.scrollID = function(){
		        thisObj.left();
		     }
	   }else if(way==3){
		     thisObj.scrollID = function(){
		        thisObj.down();
		     }
	   }else if(way==11){
		     thisObj.scrollID = function(){
		     	 
		     	  thisObj.num=thisObj.num+1;
		        thisObj.up1( thisObj.num);
		     }
		   }      
		   thisObj.objParent.onmouseover=function() {clearInterval(thisObj.MyMar)}
       thisObj.objParent.onmouseout=function() {thisObj.MyMar=setInterval(thisObj.scrollID,thisObj.speed)}
		   thisObj.MyMar=setInterval(thisObj.scrollID,thisObj.speed)
   },
   createDiv:function(id){
	      var div = document.createElement("div");
	      div.setAttribute("id",id);
	      return div;
   },
   up:function(){
		  if(this.objBrother.offsetTop-this.objParent.scrollTop<=0)
				this.objParent.scrollTop-=this.obj.offsetHeight
		  else{
				this.objParent.scrollTop++
		  }
   },
   down:function(){
   	  if(this.objParent.scrollTop<=0){
		  	 
				this.objParent.scrollTop+=this.obj.offsetHeight
		  }else{
		 
				this.objParent.scrollTop--
		  }
   },
   left:function(){
   	 this.objParent.scrollRight=270;
   },
   up1:function(num){
   	if(num>10&&num%10==0){
		  if(this.objBrother.offsetTop-this.objParent.scrollTop<=0)
				this.objParent.scrollTop-=this.obj.offsetHeight
		  else{
				this.objParent.scrollTop++
		  }
		 } 
		 num++;
   }
}