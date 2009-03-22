/***
  * <h3>div浮动窗口</h3>
  * Copyright (c) 2008  expopo.cn All Rights Reserved
  * $author:an_chuantong<an_chuantong@yahoo.com.cn> $
  * $Date: 2008-11-16 16:21:25 -0500 (Thurs, 16 Oct 2008) $
  * $使用方法：
  *  var test= new ShowWindow();
  *  test.init("哈哈",400,11);
  *  test.innerHTML("fsfdsdsdsfdsfsdf放大师傅都是是大多数的师傅");  //---直接往浮动窗口写html
  *  //test.appendIframe("http://www.expopo.com",400)               //---往浮动窗口引入iframe
  *  test.openBox();                                                //---显示窗口 
  *  test.initPosition(document.getElementById("main"),100,100);    //---窗口定位
  * $
  * $type:1,11   ---允许最小化浮动窗口
  *       10,11  ---允许拖动
  */
function ShowWindow() {
}
ShowWindow.prototype = new ShowWindow();
ShowWindow.prototype = {
     setPath:function(path){
         this.path=path;
     },   
     init:function(title,width,type){
         this.toolWidth=120;
         this.toolHeight=30;
         if(this.path==undefined||this.path.length==0){
             this.setPath("../box_img")
         }
         this.width=width;
         this.appendBox(title,width,type);
         this.appendBlankBox();
         
         this.initPosition(null,0,0);
     },
     appendBlankBox:function(){
         this.blankBox=this.createObj("div","blank_box");
         this.blankframe=this.createObj("iframe","blank_iframe");
         this.blankframe.frameBorder = 0;
         this.blankframe.scrolling = "no";
         this.blankBox.style.width=this.getPageSize().xScroll+"px";
         this.blankBox.style.height=this.getPageSize().yScroll+"px";
         this.blankframe.style.width=this.getPageSize().xScroll+"px";
         this.blankframe.style.height=this.getPageSize().yScroll+"px";
         this.blankframe.allowTransparency=true;
         this.blankBox.style.display="none";
         this.blankBox.appendChild(this.blankframe);
         document.body.appendChild(this.blankBox);
     },
     appendBox:function(title,width,type){
         this.box=this.createObj("div","boxs");
         this.box.style.width=width+"px";
         this.box.style.display="none";
         this.appendTitle(title,width,type);
         this.boxBody=this.createObj("div","box_bodys");
         
         this.box.appendChild(this.boxBody);
         document.body.appendChild(this.box);
         if(type>=10){
              var drag=new Drags();
              drag.init("boxs","box_title")
         }
         
         if(type%10==1){
             this.appendMinTool(title)
         }
     },
     appendTitle:function(title,width,type){
         var CLASS=this;
         this.boxTitle=this.createObj("div","box_title");
         this.boxTitle.style.background="#709cd2 url("+this.path+"/dtl.gif)";
         this.boxTitleH=this.createObj("h3","");
         this.boxTitleH.style.width=title.length*10+20+"px";
         this.boxTitleH.style.fontSize="12px";
         this.boxTitleH.innerHTML=title;
         this.boxTitleA=this.createObj("a","");
         this.boxTitleA.setAttribute("href","#");
         this.boxTitleA.className="closed";
         //this.boxTitleA.setAttribute("style","float:right;");
         this.imgObj=this.createObj("img","");
         
         this.imgObj.src=this.path+"/closet.gif";
         this.imgObj.style.border="0";
         this.boxTitleA.appendChild(this.imgObj);
         this.boxTitleA.setAttribute("title","关闭");
         this.boxTitleA.onclick=function(){
               CLASS.closeBox();
	             return false;
	       }  
	       
         this.boxTitle.appendChild(this.boxTitleH);
         this.boxTitle.appendChild(this.boxTitleA);
         
         if(type%10==1){
	          this.boxTitleA1=this.createObj("a","");
            this.boxTitleA1.setAttribute("href","#");
            this.boxTitleA1.className="closed";
            this.imgObj=this.createObj("img","");
            this.imgObj.src=this.path+"/min.gif";
            this.boxTitleA1.setAttribute("title","最小化");
            this.imgObj.style.border="0";
            this.boxTitleA1.appendChild(this.imgObj);
            this.boxTitleA1.onclick=function(){
                 CLASS.minBox();
                 return false;
	          }
	          this.boxTitle.appendChild(this.boxTitleA1);
	       }
         this.box.appendChild(this.boxTitle);
     },
     appendIframe:function(url,height){
     	 this.frame=this.createObj("iframe","main_iframe");
         this.frame.frameBorder = 0;
         this.frame.scrolling = "no";
         this.frame.src=url;
         this.frame.style.width=this.width-20+"px";
         this.frame.style.margin="10px";
         //this.frame.style.display="none";
         this.frame.style.height=height-20+"px";
         this.frame.style.background="#FFF";
         //this.frameLoading=this.createObj("div","iframe_loading");
         //this.frameLoading.style.display="block";
         //this.frameLoading.innerHTML="正在加载...";
         this.boxBody.appendChild(this.frame);
         //this.boxBody.appendChild(this.frameLoading);
     },	
     appendMinTool:function(title){
         this.boxMinTitle=this.createObj("div","box_min_title");
         this.boxMinTitle.innerHTML="&nbsp;我的工具&nbsp;";
         this.boxMinTitle.style.background="url("+this.path+"/title.gif)";
         this.boxMinTitle.style.color="#0EB6F4";
         this.boxMinTitle.style.width=this.toolWidth+"px";
         this.boxMinTitle.style.height="23px";
         this.boxMinTitle.style.lineHeight="23px";
         this.boxMinTitle.style.fontWeight="bold";
         this.boxMinTitle.style.position="absolute";
         this.boxMinTitle.style.display="none";
         this.boxMinTitleA=this.createObj("a","");
         this.boxMinTitleA.setAttribute("href","#");
         this.boxMinTitleA.setAttribute("title","最大化");
         this.imgObj=this.createObj("img","");
         this.imgObj.src=this.path+"/open.gif";
         this.imgObj.style.border="0";
         this.imgObj.style.float="right";
         this.boxMinTitleA.appendChild(this.imgObj);
         
         var CLASS=this;
         this.boxMinTitleA.onclick=function(){
               CLASS.openBox();
               CLASS.boxMinTitle.style.display="block";
               ClASS.boxMinTitle.style.left=ClASS.getPageSize().windowWidth-ClASS.toolWidth+"px";
         	     ClASS.boxMinTitle.style.top=document.body.scrollTop+ClASS.getPageSize().windowHeight-ClASS.toolHeight+"px";
	             return false;
	       }
	       this.boxMinTitle.appendChild(this.boxMinTitleA);
	       document.body.appendChild(this.boxMinTitle);
     },
     openBox:function(){
         this.blankBox.style.display="block";
         this.box.style.display="block";
     },
     closeBox:function(){
     	   document.body.removeChild(this.blankBox);
     	   document.body.removeChild(this.box);
     },
     close:function(){
     	   document.body.removeChild(this.blankBox);
     	   document.body.removeChild(this.box);
     },
     minBox:function(){
         this.blankBox.style.display="none";
         this.box.style.display="none";
         this.boxMinTitle.style.display="block";
         var ClASS=this;
        
        
         ClASS.boxMinTitle.style.left=this.getPageSize().windowWidth-this.toolWidth+"px";
         ClASS.boxMinTitle.style.top=this.getPageSize().windowHeight+"px";
         window.onresize=window.onscroll =function(){
         	   ClASS.boxMinTitle.style.left=ClASS.getPageSize().windowWidth-ClASS.toolWidth+"px";
         	   
             ClASS.boxMinTitle.style.top=document.body.scrollTop+ClASS.getPageSize().windowHeight-ClASS.toolHeight+"px";
         }
     },
     initPosition:function(obj,ofLeft,ofTop){
        if(obj!=undefined&&obj!=null){
         	  var oLeft=obj.offsetLeft;
         	  if(ofLeft!=undefined){
         	  	oLeft+=ofLeft;
         	  }	
         	  var oTop=obj.offsetTop;
         	  if(ofTop!=undefined){
         	  	oTop+=ofTop;
         	  }
         	  this.box.style.left=oLeft+"px";
         	  this.box.style.top=oTop+"px";
         }else{
            this.box.style.left=ofLeft+"px";
         	  this.box.style.top=ofTop+"px";
         	  
         }	
     },	
     innerHTML:function(data){
         this.boxBody.innerHTML=data;
     },
     createObj:function(name,id){
	       var obj = document.createElement(name);
	       if(id.length>0){
	          obj.setAttribute("id",id);
	       }
	       return obj;
     },
     getPageSize:function(){
		     var xScroll, yScroll;//页面实际长宽
		     if (window.innerHeight && window.scrollMaxY) {	
			     xScroll = window.innerWidth + window.scrollMaxX;
			     yScroll = window.innerHeight + window.scrollMaxY;
		     } else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
			     xScroll = document.body.scrollWidth;
			     yScroll = document.body.scrollHeight;
		     } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
			     xScroll = document.body.offsetWidth;
			     yScroll = document.body.offsetHeight;
		     }
	     	 var windowWidth, windowHeight;//当前页面区域长宽
		     if (self.innerHeight) {	// all except Explorer
			     if(document.documentElement.clientWidth){
				     windowWidth = document.documentElement.clientWidth; 
			     } else {
				     windowWidth = self.innerWidth;
			     }
			     windowHeight = self.innerHeight;
		     } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
			     windowWidth = document.documentElement.clientWidth;
			     windowHeight = document.documentElement.clientHeight;
		     } else if (document.body) { // other Explorers
			     windowWidth = document.body.clientWidth;
			     windowHeight = document.body.clientHeight;
		     }	
		     arrayPageSize =[{"xScroll":xScroll, "yScroll":yScroll,"windowWidth":windowWidth, "windowHeight":windowHeight}]
		     return arrayPageSize[0];
     }
     
}

function isFrameComplete(ifrID,callback){
       var ifr = document.getElementById (ifrID);
       var isLoad = alert(isLoad)
       if(isLoad){
            return true;
       }else{
       setTimeout ("isFrameComplete('" + ifrID + "',"+callback+")",200);
       }
}

function isLoadIframe(ifrID){
       var ifr = document.getElementById(ifrID);
       if(ifr.onreadystatechange){
              var state = ifr.readyState;
              if(state == "complete" ||state == "interactive"){
                  return true;
              }
       }else if(ifr.onload){
                 return true;
       }
         return false;
}