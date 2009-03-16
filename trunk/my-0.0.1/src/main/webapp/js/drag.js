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
                CLASS.temp1=parseInt($("#"+CLASS.parentId).offset().left+0)
                CLASS.temp2=parseInt($("#"+CLASS.parentId).offset().top+0)
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
              if(left<5){
                 left=5
              }
              if(left>$("body").width()-$("#"+CLASS.parentId).width()-5){
                 left=$("body").width()-$("#"+CLASS.parentId).width()-5
              }
              if(left>$(document).width()){
                 left=$(document).width()
              }
             
              if(top>$(document).height()-$("#"+CLASS.parentId).height()-5){
                 top=$(document).height()-$("#"+CLASS.parentId).height()-5;
              }
              if(top<10){
                top=10;
              }
              $("#"+CLASS.parentId).css("left",left).css("top",top); 

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
