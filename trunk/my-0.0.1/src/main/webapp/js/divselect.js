/***
  * <h3>DIV制作下拉选择框</h3>
  * Copyright (c) 2008  expopo.cn All Rights Reserved
  * $author:an_chuantong<an_chuantong@yahoo.com.cn> $
  * $Date: 2008-11-06 13:21:25 -0500 (Wed, 06 Feb 2008) $
  */
  
function DivSelect() {
}
DivSelect.prototype = new DivSelect();
DivSelect.prototype = { 
       init:function(box,id,width,height,bg,url){
           
             this.divId="div_"+id;
             this.hiddenId=id;
             this.width=width;
             this.height=height;
             this.bg=bg;
             this.url=url;
             this.divParent= this.createDiv(this.divId);
             this.selectInit=this.createDiv("init");
             this.selectInit.style.width=width+"px";
	           this.selectInit.style.height=height+"px";
	           this.selectInit.style.background="#C6E1F9 url("+bg+")";
	           this.selectInit.innerHTML="请选择";
	           this.divParent.style.width=width+"px";
	           this.divParent.style.height=height+"px";
	           this.divParent.style.background="#C6E1F9 url("+bg+")";
	         
	         
	           this.divBrother= this.createDiv(this.divId+"_b");
	           this.divBrother.style.display="none";
	           this.divBrother.style.position="absolute";
	           this.divBrother.style.width=width+"px";
	         
	           this.hiddenObj= document.createElement("text");
	           this.hiddenObj.setAttribute("id",this.hiddenId);
	           this.hiddenObj.setAttribute("name",this.hiddenId);
	        
	           this.divParent.appendChild(this.selectInit);
	           this.divParent.appendChild(this.hiddenObj);
	           var CLASS=this;
	           this.selectInit.onclick=function(){
	              if(CLASS.divBrother.style.display=="block"){
	                    CLASS.divBrother.style.display="none";
	              }else{
	                    CLASS.divBrother.style.display="block";
	              }
	           }  
	            
	           if(box!=undefined&&box.length>0){
	               document.getElementById(box).appendChild(this.divParent);
	               document.getElementById(box).appendChild(this.divBrother);
	           }else{
	               document.body.appendChild(this.divParent);
	               document.body.appendChild(this.divBrother);
	           }
        },
        
        createDiv:function(id){
	         var div = document.createElement("div");
	         div.setAttribute("id",id);
	         return div;
        },
        
        add:function(data,selectId){
             var CLASS=this;
             for(var i=0;i<data.length;i++){
	             var divObj=this.createDiv(data[i].value);
	             divObj.innerHTML=data[i].text;
	             divObj.onclick=function(){
	                CLASS.boxClick(this)
	             }
	             
	             divObj.onmousemove = function(){
	                  CLASS.boxMousemove(this)
	             }
	             
	             divObj.onmouseover = function(){
		                CLASS.boxMouseover(this)
		
	             }
	             
	             if(data[i].value==selectId){
	                 CLASS.selectInit.setAttribute("id",data[i].value);
	                 CLASS.selectInit.innerHTML=data[i].text;
	                 divObj.className="on";
	             } 
	           
	            this.divBrother.appendChild(divObj)
	         }
        },
        
        boxClick:function(obj){
              var CLASS=this;
	            CLASS.divBrother.style.display="none";
	            CLASS.hiddenObj.value=obj.getAttribute("id");
	            CLASS.selectInit.setAttribute("id",obj.getAttribute("id"));
	            CLASS.selectInit.innerHTML=obj.innerHTML;
	            if(CLASS.url!=undefined&&CLASS.url.length>0){
	                     location.href=CLASS.url+"&"+CLASS.hiddenId+"="+obj.getAttribute("id");
	            }
        },
        
        boxMousemove:function(obj){
              var CLASS=this;
              var divs=CLASS.divBrother.getElementsByTagName("div");
	            for(var i=0;i<divs.length;i++){
	                      divs[i].className="";
	            }
	            obj.className="on";
        },
        
        boxMouseover:function(obj){
           obj.className="";
        }
}