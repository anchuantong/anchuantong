/***
  * <h3>书签操作</h3>
  * Copyright (c) 2008  expopo.com All Rights Reserved
  * $author: $
  * $Date: 2008-04-06 13:21:25 -0500 (Wed, 06 Feb 2008) $
  */
  
// JavaScript Document
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
function ContentElement(title,url,tabInstance,type,menuId){
	this.title = title;
	this.url = url;
	this.tab = tabInstance;
	this.index = this.tab.staticIndex;
	this.frame;
	this.menu;
	this.menuId=menuId
	this.type = type;
}
ContentElement.prototype.create = function(){
	var CLASS = this;
	this.frame = document.createElement("iframe");
	this.frame.style.width = "100%";
	this.frame.style.height = (this.tab.configration.height-parseInt(this.tab.tabContainer.style.height))+"px";
	this.frame.src = this.url;
	this.frame.frameBorder = 0;
	this.frame.scrolling = "auto";
	this.menu = document.createElement("div");
	this.menu.style.width = "110px";
	this.menu.style.margin = "1px";
	this.menu.style.textAlign = "center";
	this.menu.style.height =  this.tab.configration.menuHeight+"px";
	this.menu.style.display = "block";
	this.menu.style.position="relative";
	this.menu.style.top="2px";
	this.menu.style.lineHeight=this.tab.configration.menuHeight+"px";
	this.menu.style.cursor="default";
	this.menu.style.whiteSpace = "nowrap";
	this.menu.style.overflow = "hidden";
	this.menu.style.styleFloat = "left";
	this.menu.style.cssFloat = "left";
	this.menu.style.border = this.tab.configration.mainColor+" 1px solid";
	this.menu.style.borderBottom = "none";
	this.menu.appendChild(document.createTextNode(this.title));
	this.menu.onmousedown = function(){
		CLASS.tab.selectTab(CLASS.index);
	}
	this.menu.onmouseover = function(){
		this.style.background ="url("+CLASS.tab.configration.path+CLASS.tab.configration.menuurl+")";
		
	}
	this.menu.onmouseout = function(){
		if(CLASS.tab.selectedIndex != CLASS.index){
			this.style.background ="";
		}
	}
	this.menu.ondblclick=function(){
	 try{
				CLASS.tab.menuContainer.removeChild(CLASS.menu);
				CLASS.frame.src = "#";
				CLASS.tab.leftDiv.removeChild(CLASS.frame);
				CLASS.tab.closeTab(CLASS.index,CLASS.menuId);
				if(CLASS.type == 2){
					CLASS.tab.hasStaticTab = false;
				}
			}catch(e){
				CLASS.tab.leftDiv.removeChild(CLASS.frame);
				CLASS.tab.closeTab(CLASS.index,CLASS.menuId);
				if(CLASS.type == 2){
					CLASS.tab.hasStaticTab = false;
				}
			}
	}
	if(this.type == 0||this.type == 2){
		var closeImag=document.createElement("img");
		this.closeImg = closeImag;
		closeImag.src=CLASS.tab.configration.path+"closeme.gif";
		closeImag.style.position="absolute";
		closeImag.style.right= "2px";
		closeImag.style.top="2px";
		closeImag.style.width="14px";
		closeImag.style.height="14px";
		closeImag.style.cursor="pointer";
		//修改
		closeImag.style.display="none";
		closeImag.onclick=function(){
			try{
				CLASS.tab.menuContainer.removeChild(CLASS.menu);
				CLASS.frame.src = "#";
				CLASS.tab.leftDiv.removeChild(CLASS.frame);
				CLASS.tab.closeTab(CLASS.index,CLASS.menuId);
				if(CLASS.type == 2){
					CLASS.tab.hasStaticTab = false;
				}
			}catch(e){
				CLASS.tab.leftDiv.removeChild(CLASS.frame);
				CLASS.tab.closeTab(CLASS.index,CLASS.menuId);
				if(CLASS.type == 2){
					CLASS.tab.hasStaticTab = false;
				}
			}
		}
		closeImag.setAttribute("alt","close window");
		this.menu.appendChild(closeImag);
		//alert(closeImag+" "+this.menu.innerText);
	}
		
	this.tab.menuContainer.appendChild(this.menu);
	this.tab.leftDiv.appendChild(this.frame);
	//创建成功后索引自增
	this.tab.staticIndex++;
}
function Tab(){
	this.staticIndex = 0;
	this.list = [];
	this.container;
	this.tabContainer;
	this.toolContainer;
	this.menuContainer;
	this.contentContainer;
	this.leftDiv;
	this.configration = {
		width : 500,
		height : 300,
		mainColor : "#ccc",
		maxCount: 7,
		path:"js/",
		menuurl:"tabbk.gif",
		menuHeight:20,
		hasInit:false
	};
	this.hasStaticTab = false;
	this.selectedIndex ;
}
Tab.prototype.init = function(){
	//this.configration.width = document.documentElement.clientWidth;
	//this.configration.height = document.documentElement.clientHeight;
	this.container = document.createElement("div");
	this.tabContainer = document.createElement("div");
	this.menuContainer = document.createElement("div");
	this.contentContainer = document.createElement("div");
	this.leftDiv = document.createElement("div");
	this.rightDiv = document.createElement("div");
	

	
	this.tabContainer.style.borderBottom = this.configration.mainColor+" 1px solid";
	this.tabContainer.style.height = "24px";
	this.tabContainer.style.background = "url("+this.configration.path+"tabCbk.gif)";
	this.tabContainer.style.width = this.configration.width+"px";
	this.menuContainer.style.width = parseInt(this.tabContainer.style.width);
	this.menuContainer.style.styleFloat = "left";
	this.menuContainer.style.cssFloat = "left";
	this.menuContainer.style.overFlow = "hidden";
	this.tabContainer.setAttribute("id","tabs");
	this.leftDiv.style.width = (this.configration.width)+"px";
	this.leftDiv.style.height = (this.configration.height-parseInt(this.tabContainer.style.height))+"px";
	this.leftDiv.style.styleFloat = "left";
	
	this.contentContainer.appendChild(this.leftDiv);
	this.tabContainer.appendChild(this.menuContainer);
	this.container.appendChild(this.tabContainer);
	this.container.appendChild(this.contentContainer);
	
	document.body.appendChild(this.container);
}
Tab.prototype.hasElement = function(url){
	var flag = false;
	for(var i=0;i<this.list.length;i++){
		if(this.list[i].url ==  url){
			flag = true;
			break;
		}
	}
	return flag;
}
Tab.prototype.openNewTab = function(title,url,type,menuId){
	if(this.list.length >= this.configration.maxCount){
		if(this.configration.hasInit){
		         alert("您已经打开"+this.configration.maxCount+"个标签,请关闭不用的标签");
	    }
		return false;
	}else if(this.hasElement(url)){
		for(var i=0;i<this.list.length;i++){
			if(this.list[i].url ==  url){
				this.selectTab(this.list[i].index);
				this.list[i].frame.src = this.list[i].url;
				//this.list[i].frame.location.reload();
				break;
			}
		}
	}else{
		if(type == 2&&this.hasStaticTab==true){
			var id = this.getStaticId();
			this.selectTab(this.list[id].index);
			this.list[id].frame.src = url;
			this.list[id].menu.childNodes[0].nodeValue = title;
		}
		else if(type == 3){
			window.open(url,'newwindow','width=480,height=400');
		}
		else{
			var contentElement = new ContentElement(title,url,this,type,menuId);
			contentElement.create();
			contentElement.frame.style.width=this.tabContainer.style.width;
			this.list.push(contentElement);
			this.selectTab(contentElement.index);
			if(type == 2){
				this.hasStaticTab=true;
			}
		}
	}
	return true;
}
Tab.prototype.closeTab = function(index,menuId){
   /**删除cookie*/
   if(parent.hidden_menus[menuId+""]){
                    parent.hidden_menus[menuId+""]=false;
        jQuery.set_cookie(cookie_key,(map2array(parent.hidden_menus)).join(","),90,"/");
    } 
	for(var i=0;i<this.list.length;i++){
		if(this.list[i].index == index){
			this.list.del(i);
			if(this.list.length!=0){
				this.selectTab(this.list[this.list.length-1].index);
			}else{
			}
		}
	}
}
Tab.prototype.getStaticId = function(){
	var id
	for(var i=0;i<this.list.length;i++){
		if(this.list[i].type == 2){
			id = i;
		}
	}	
	return id;
}

Tab.prototype.selectTab = function(index){
	for(var i=0;i<this.list.length;i++){
		if(this.list[i].index ==index){
			this.list[i].frame.style.display = "block";
			this.list[i].menu.style.borderBottom ="#fff 1px solid";
			this.list[i].menu.style.background ="url("+this.configration.path+this.configration.menuurl+")";
			if(this.list[i].closeImg!=null){
				this.list[i].closeImg.style.display = "block";
			}
			this.selectedIndex = index;
		}else{
			this.list[i].frame.style.display = "none";
			this.list[i].menu.style.border =this.configration.mainColor+" 1px solid";
			this.list[i].menu.style.background ="url()";
			if(this.list[i].closeImg!=null){
				this.list[i].closeImg.style.display = "none";
			}			
		}
	}
}
Tab.prototype.openLeft = function(width){
this.tabContainer.style.width=width;
   for(var i=0;i<this.list.length;i++){
     this.list[i].frame.style.width=width;
   }
}
