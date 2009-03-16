function ShowWindow() {
}
ShowWindow.prototype = new ShowWindow();
ShowWindow.prototype = {
    init:function(title,url,width,height){
       this.box=document.createElement("div"); 
       this.box.style.width = document.documentElement.clientWidth+"px";
       this.box.style.height =  document.documentElement.clientHeight+"px";
       this.box.style.position="absolute";
       this.box.style.background="#FFF";
       this.box.style.filter="alpha(opacity=70,Style=0);";
       this.box.style.opacity="0.6";
       this.box.style.zIndex=5;
       this.box.style.top="0px";
       this.box.style.left="0px";
       
       this.boxBody=document.createElement("div"); 
       this.boxBody.style.position="absolute";
       this.boxBody.style.width = width+"px";
       this.boxBody.style.height = height+"px";
       this.boxBody.style.border="1px #000 solid";
       this.boxBody.style.zIndex=11;
       this.boxBody.style.left=document.documentElement.clientWidth/2 - width/2+"px";
       this.boxBody.style.top=document.documentElement.clientHeight/2 - height/2 - 10+"px";
       this.boxBody.id="box_body";
       this.boxBody.style.background="#FFF";
       
       this.boxTitle=document.createElement("div"); 
       this.boxTitle.style.width = width+"px";
       this.boxTitle.style.height = "24px";
       this.boxTitle.style.background="#70a7cd";
       this.boxTitle.style.margin="0 0 5px 0";
       this.boxTitle.style.padding="0px";
       this.boxTitle.style.lineHeight="24px";
       
       this.titleObj=document.createElement("P");
       this.titleObj.innerHTML=title;
       this.titleObj.style.styleFloat = "left";
       this.titleObj.style.cssFloat = "left";
       this.titleObj.style.textAlign = "left";
       this.titleObj.style.width="80%";
       this.titleObj.style.fontWeight="bold";
       this.titleObj.style.margin="0px";
       this.titleObj.style.padding="0px";
       this.titleObj.style.padding="0px";
       this.titleObj.style.cursor= "move";
       this.titleObj.id="box_title";
        
       this.objClose = document.createElement("P");
       this.objClose.innerHTML="[¹Ø±Õ]";
       this.objClose.style.styleFloat = "right";
       this.objClose.style.cssFloat = "right";
       this.objClose.style.width="18%";
       this.objClose.style.textAlign = "right";
       this.objClose.style.margin="0px";
       this.objClose.style.padding="0px";
       this.objClose.style.cursor="pointer";
       var CLASS = this;
       this.objClose.onclick=function(){
          CLASS.close()
       }
       
       this.boxTitle.appendChild(this.titleObj);
       this.boxTitle.appendChild(this.objClose);
       this.boxBody.appendChild(this.boxTitle);
       this.createFrame(width,height,url)
       
      
       this.boxBody.appendChild(this.frame);
       this.boxBody.style.display="block";
       document.body.appendChild(this.box);
       document.body.appendChild(this.boxBody);
    },
    
    removeBox:function(){
       var CLASS = this;
       document.body.removeChild(CLASS.boxBody);
    },
    close:function(){
        document.body.removeChild(this.boxBody);
        document.body.removeChild(this.box);
    },
    createFrame:function(width,height,url){
       var CLASS = this;
       CLASS.frame=document.createElement("iframe");
	   CLASS.frame.style.width = width-10+"px";
       CLASS.frame.style.height = height+"px";
       CLASS.frame.frameBorder = 0;
	   CLASS.frame.scrolling = "auto";
       CLASS.frame.src = url;
       CLASS.frame.style.margin = "0 0 0 10px";
    }
}