/***
  * <h3>div移动拖放</h3>
  * Copyright (c) 2008  expopo.cn All Rights Reserved
  * $author:an_chuantong<an_chuantong@yahoo.com.cn> $
  * $Date: 2008-11-16 16:21:25 -0500 (Thurs, 16 Oct 2008) $
  * $使用方法：
  *  var dd=new Drags();
  *  dd.init("要移动的DIV id","鼠标点击后触发移动的DIV ID")
  * $
  */
function Drags(){
}
Drags.prototype = new Drags();
Drags.prototype = {
      init:function(parentId,objId){
        this.objParent=document.getElementById(objId);
        this.obj=document.getElementById(objId);
        this.objId=objId;
        this.parentId=parentId;
        var CLASS=this;
        this.objParent.onmousedown=function(e){
           var ie=document.all
           var ns6=document.getElementById&&!document.all
    
           if (!ie&&!ns6)return
           var firedobj=ns6? e.target : event.srcElement
           var topelement=ns6? "HTML" : "BODY"
           if (firedobj.id==CLASS.objId){
                CLASS.dragapproved=true
                CLASS.z=firedobj
                CLASS.temp1=parseInt(document.getElementById(CLASS.parentId).offsetLeft+0)
                CLASS.temp2=parseInt(document.getElementById(CLASS.parentId).offsetTop+0)
                CLASS.x=ns6? e.clientX: event.clientX
                CLASS.y=ns6? e.clientY: event.clientY
                return false
           }     
        }
        this.objParent.onmousemove=function(e){
           var ie=document.all
           var ns6=document.getElementById&&!document.all
           if (CLASS.dragapproved){
              var left=ns6? CLASS.temp1+e.clientX-CLASS.x: CLASS.temp1+event.clientX-CLASS.x;
              var top=ns6? CLASS.temp2+e.clientY-CLASS.y : CLASS.temp2+event.clientY-CLASS.y;
             
              document.getElementById(CLASS.parentId).style.left=left+"px";
              document.getElementById(CLASS.parentId).style.top=top+"px";
              return false
           }
        }
        this.objParent.onmouseup=function(){
            CLASS.dragapproved=false;
        }
        
        this.i=0;
        this.z=0;
        this.x=0;
        this.y=0;
        this.dragapproved=false;
      }
} 