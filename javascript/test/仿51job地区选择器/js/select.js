/** 附加数组删除方法*/
Array.prototype.del=function(dx)
  {
    if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
        if(this[i]!=this[dx])
        {
            this[n++]=this[i]
        }
    }
    this.length-=1
}


function SelectCat(){
     this.catParent=[];//存放第一级层html
     this.contentTitle="请选择";
     this.catData={};
	 this.dataType="cat"; //分类类别
	 this.parentDiv;//显示的
	 this.parentHide;//表单字段
	 this.hideIds=[];//选中分类ID集合
     this.selectCats=[];
     this.selectHTML="<input type=\"checkbox\" class=\"checks\" value=\"\"/>";

     this.blankframe=$("<iframe></iframe>"); 
     this.blankframe.attr("frameBorder", 0);
     this.blankframe.attr("scrolling","no");
     this.blankframe.attr("allowTransparency",true); 

     this.box=$("<div id=\"select_cat_box\"></div>");
     this.boxBlank=$("<div id=\"select_cat_blank\"></div>");
     this.boxTitle=$("<div id=\"select_cat_title\"></div>");
     this.boxTitleP=$("<p></p>");
     this.boxTitleA=$("<a href=\"#\">[关闭]</a>");
     this.boxContent=$("<div id=\"select_cat_content\"></div>"); 
     this.boxContentHeader=$("<div></div>"); 
     this.boxContentBottom=$("<div></div>"); 
     this.boxClear=$("<a class=\"tt\" id=\"select_cat_clear\" href=\"#\">不限</a><br/>");
     this.boxBlank.append(this.blankframe); 

     this.boxContentHeader.append(this.boxClear); 
     this.boxContent.append(this.boxContentHeader);
     this.boxContent.append(this.boxContentBottom);
     this.boxTitle.append(this.boxTitleP);
     this.boxTitle.append(this.boxTitleA);
     this.box.append(this.boxTitle);
     this.box.append(this.boxContent);

     var CLASS=this;
     try{
        CLASS.dragBox=dragBox;
     }catch(e){}

     try{
        CLASS.addSelectCookie=addSelectCookie;
     }catch(e){CLASS.addSelectCookie=0;}  
     $(document).ready(function(){
        var width= $("body").width();
        var height=$("body").height();
        if(height<500){height=500;} 
        CLASS.boxBlank.css("width",width+"px");
        CLASS.blankframe.attr("width",width); 
        CLASS.boxBlank.css("height",height+"px");
        CLASS.blankframe.attr("height",height);
        $("body").append(CLASS.box);
        $("body").append(CLASS.boxBlank);
        CLASS.boxTitleA.click(function(){//初始化关闭事件
                CLASS.hideBox();  
        }) 
        if(CLASS.dragBox!=undefined&&CLASS.dragBox==1){//判断对话框是否需要移动
          var dd=new Drags();
          dd.init("select_cat_box","select_cat_title");
          CLASS.boxTitle.css("cursor","move"); 
        }
     });
    
     
}
SelectCat.prototype.init=function(data,hideId,divId){
     var CLASS=this;
     if(data==undefined) {return;}//data不存在则不执行返回
     if(data.type!=undefined){CLASS.dataType=data.type;} 
     CLASS.catData=data;
     var out=CLASS.getParentOut();
     CLASS.boxTitleP.html(data.title);
	 CLASS.boxContentBottom.html(""); 
     CLASS.boxContentBottom.append(out); 
     CLASS.boxContentBottom.children("span").click(function(){
              CLASS.getChildren(this);
              return false;  
     })
     
	 if(divId!=undefined&&divId.length>0){
	    this.parentDiv=$("#"+divId);
     }

	 if(hideId!=undefined&&hideId.length>0){
	    this.parentHide=$("#"+hideId);
		CLASS.initHideIds();
		CLASS.initSelect();
	 }

	 CLASS.boxClear.click(function(){
          CLASS.parentHide.val("");
		  CLASS.hideBox(); 
        }
     ) 
     CLASS.boxBlank.show();  
     CLASS.box.show("normal");
	 
}

SelectCat.prototype.initPosition=function(top,left){//定位对话框位置
    var CLASS=this;
    CLASS.box.css("top",top+"px");
    CLASS.box.css("left",left+"px");
}

SelectCat.prototype.hideBox=function(){//隐藏对话框
    var CLASS=this;
    CLASS.box.hide();
    CLASS.boxBlank.hide();
    return false;
}



SelectCat.prototype.initSelect=function(){//初始化已经选择的分类
    var CLASS=this;
	
		   var ids=CLASS.hideIds;

           var categories=CLASS.catData.categories;
		   CLASS.selectCats={}
		   for(var i=0;i<categories.length;i++){
                 var childOne= categories[i].children;
				 for(var j=0;j<childOne.length;j++){
					 var oneId=childOne[j].id;
					 if(CLASS.contains(ids,oneId)){
						 CLASS.pushSelectCats(oneId,childOne[j].name,{"id":oneId,"name":childOne[j].name});
					 }
					 var childTwo=childOne[j].children;
				     for(var k=0;k<childTwo.length;k++){
						 if(CLASS.contains(ids,childTwo[k].id)){
						     CLASS.pushSelectCats(oneId,childOne[j].name,{"id":childTwo[k].id,"name":childTwo[k].name});
					     }
					 }

				 }
		   }
		   var outObj=$("<div class=\"t_checked\"></div>");
           var outObjt=$("<h3>已选择</h3>");
		   if(CLASS.hideIds.length>0&&CLASS.parentHide.val().length>0){
						outObjt="";
			}
		   outObj.append(outObjt)
		   for (n in CLASS.selectCats) {
			   var outObjt=$("<p class=\"t\">"+CLASS.selectCats[n].name+"</p>");
               outObj.append(outObjt)
               var child=CLASS.selectCats[n].categories;
			   CLASS.appendSelect(child,outObj)
           }
		   CLASS.boxContentHeader.html("");
		   CLASS.boxContentHeader.append(CLASS.boxClear);
		   CLASS.boxContentHeader.append(outObj);
	
    
}

SelectCat.prototype.addHideIds=function(id){//增加选择
    var CLASS=this;
	var pos=id.indexOf("_");
	id=id.substring(0,pos)
    if(!CLASS.contains(CLASS.hideIds,id)){
		CLASS.hideIds.push(id);
		CLASS.setHideValue();
		CLASS.initSelect();
	}
}

SelectCat.prototype.delHideIds=function(id){//增加选择
    var CLASS=this;
	var pos=id.indexOf("_");
	id=id.substring(0,pos);
	var ids=CLASS.hideIds;
	for(var i=0;i<ids.length;i++){
		if(ids[i]==id){
             CLASS.hideIds.del(i);
			 break;
		}
	}
	CLASS.initSelect();
    CLASS.setHideValue();
}

SelectCat.prototype.setHideValue=function(){//设置隐藏域值
    var CLASS=this;
    var ids=CLASS.hideIds;
	var val="";
	for(var i=0;i<ids.length;i++){
		 if(i>0){val+=",";}
         val+=ids[i]
	}
    CLASS.parentHide.val(val);
	
}

SelectCat.prototype.initHideIds=function(){//解析已选择的分类ID
    var CLASS=this;
	if(CLASS.parentHide!=undefined){
		var val=CLASS.parentHide.val();
		if(val.length>0){
		   var ids=val.split(",");
		   CLASS.hideIds=val.split(",");
		   
		}
	}

}

SelectCat.prototype.foreachCat=function(categories,arr){//
    for(var i=0;i<categories.length;i++){

	}
}

SelectCat.prototype.appendSelect=function(child,outObj){//增加分类选择节点
    var CLASS=this;
	if(child==undefined){return;}
    for(var j=0;j<child.length;j++){
        CLASS.appendOneSelect(child[j],outObj)
	}
}
SelectCat.prototype.appendOneSelect=function(obj,outObj){//增加分类选择节点
    var CLASS=this;
    var inputObj=$(CLASS.selectHTML);
	if(CLASS.contains(CLASS.hideIds,obj.id)){
			 inputObj.attr("checked","checked");
	}
    inputObj.val(obj.id+"_"+obj.name);
    inputObj.click(function(){
                CLASS.selectValue(this)}
    )
     outObj.append(inputObj).append($("<a>"+obj.name+"</a>"));
}
SelectCat.prototype.pushSelectCats=function(key,name,cat){//增加已选择的分类，使用JSON格式组装，方便页面展示
    var CLASS=this; 
    var arr=[]
    var map={};
    if(CLASS.selectCats[key]!=undefined){
		map=CLASS.selectCats[key];
		if(map.categories!=undefined){
			 arr=map.categories;
		}
	}
	map.categories=arr;
	if(!CLASS.contains(arr,cat)){
	   arr.push(cat);
	}
	map.name=name;
	CLASS.selectCats[key]=map;
}

SelectCat.prototype.contains=function(arr,val){//
    for(var i=0;i<arr.length;i++){
		 if(arr[i].id!=undefined){
			 if(arr[i].id==val.id){
              return true;
			 }
		 }else if(arr[i]==val){
			 return true;
		 }
	}
	return false;
}

SelectCat.prototype.getParentOut=function(){
     var CLASS=this;  
     data=CLASS.catData;
     var outObj=$("<div></div>");
          //if(CLASS.catParent[data.type]==undefined){ 
          var parent=data.categories;
          if(parent==undefined||parent.length==0){//数据不存在或者长度为0
               
                 
          }else{
			  var obj=$("<p class=\"t\"></p>")
              var objTitle=CLASS.contentTitle;
              if(data.levelNames!=undefined&&data.levelNames.length>0){
                  objTitle+=data.levelNames[0]
              } 
              obj.html(objTitle)
              outObj.append(obj) 

              for(var i=0;i<parent.length;i++){
                 var child=parent[i].children;
                 if(child!=undefined&&child.length>0){
                     obj=$("<span class=\"tt\">"+parent[i].name+":</span>");
                     outObj.append(obj)
                     for(var j=0;j<child.length;j++){
                         obj=$("<span rel=\""+i+"_"+child[j].id+"\"><a href=\"#\">"+child[j].name+"</a></span>");
						 obj.click(function(){
                            CLASS.getChildren(this);
                            return false;  
                         })
						 outObj.append(obj)
                     }
                     outObj.append($("<br/>"))
                 }
                
              }
          } 
         // CLASS.catParent[data.type]=outObj;
         //}else{
         // outObj=CLASS.catParent[data.type]
         // }
     return outObj
}
SelectCat.prototype.selectValue=function(obj){//选择一个分类
     //TODO  
     var CLASS=this;
     if(obj.checked){
		CLASS.addHideIds(obj.value)
	 }else{
        CLASS.delHideIds(obj.value)
	 }
}

SelectCat.prototype.getChildren=function(obj){//选择子分类
    var CLASS=this;
    var idStr=$(obj).attr("rel");
    if(idStr!=undefined){
           var pos=idStr.indexOf("_");
           var index=parseInt(idStr.substring(0,pos));
           var id=parseInt(idStr.substring(pos+1));
           var out="";
           var data=CLASS.catData.categories[index].children;
           if(data==undefined){
                 return;
           } 
           for(var i=0;i<data.length;i++){
                if(data[i].id==id){
                  var child=data[i].children;
                  var outObj=$("<div></div>");
                  var outObjt=$("<p class=\"t\">"+data[i].name+"</p>");
                  
                  var returnVal="";
                  if(CLASS.catData.levelNames!=undefined&&CLASS.catData.levelNames.length>0){
                     returnVal=CLASS.catData.levelNames[0]
                  } 
                  var returnObj=$("<a href=\"\" id=\"box_returns\">[返回选择"+returnVal+"]</a>");
                  returnObj.click(function(){
                       var out=CLASS.getParentOut();
					   CLASS.boxContentBottom.html(""); 
                       CLASS.boxContentBottom.append(out); 
                       return false;
                  }) 
                  outObjt.append(returnObj);
                  outObj.append(outObjt);
				  CLASS.appendOneSelect(data[i],outObj)
                  CLASS.appendSelect(child,outObj)
                  outObj.append($("<p style=\"clear:both\"></p>"));
                }
           } 
          CLASS.boxContentBottom.html("");
          CLASS.boxContentBottom.append(outObj)
          
    }
}
var selectCat=new SelectCat();




