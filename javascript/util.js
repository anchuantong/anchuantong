function StringBuffer() {
     this.__strings__ = new Array();
 }
 StringBuffer.prototype.append = function(str) {
     this.__strings__.push(str);
 }
 StringBuffer.prototype.insert = function(pos,str) {
    this.__strings__.splice(pos,0,str);
 }
 StringBuffer.prototype.toString = function() {
     return this.__strings__.join("");
 }
function in_map(map, o) {
     return map[o+""]!=undefined && map[o+""];
 }
 function map2array (map) {
     var arr = [];
     for (n in map) {
         if(map[n])
             arr.push(n);
     }
     return arr;
 }
 function map2str (map) {
     var arr = new StringBuffer();
     var i=0;
     for (n in map) {
    	 if(i>0){
    		 arr.append(";");
    	 }	 
         if(map[n].length>0){
        	 arr.append(n);
        	 arr.append("=");
        	 arr.append(map[n]);
        	 i++;
         }	 
     }
     return arr;
 }
 function array2map (arr) {
     var map = {};
     var key,value,pos;
     for (i=0;i<arr.length;i++) {
    	 value=arr[i]+"";
    	 pos=value.indexOf("=");
    	 if(pos>-1){
    		 key=value.substring(0,pos);
    		 map[key]=value.substring(pos+1);
    	 }else{
    		 map[value]=true;
    	 }	 
     }
     return map;
 }
 
 function getPageSize(){
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

 
 function loadJs(head,file){
		var obj = parent.parent.document.createElement('script');
		obj.setAttribute("src" ,file);
		obj.setAttribute("type","text/javascript");
		head.appendChild(obj)
 }


function changeUrl(){
							var page=document.getElementById("page").value;
							var url=document.getElementById("page_base_url").value;
							var last_url=document.getElementById("page_last_url").value;
							location.href=url+page+last_url;
							}
							function checkPage(evt,page){
							evt = (evt) ? evt : ((window.event) ? event : null);
							if (evt) {
							if (evt.keyCode==13) {
							location.href=url+page+last_url
							}
							}
							}

function openLogin() {
   jQuery.ajax({
        type:"get", 
        url:ajax_url + "login.ajax", 
        data:"done=" + location.href + "&action=OpenLoginForm", 
        dataType:"html", 
        success:function (data) {
		    if(data==null||data.length<10){location.href=ajax_url+"user/login.jspa";return false;}
		    $("body").append(data);
		    openBlank();
	        var top = $("#login_img").offset().top + $("#login_img").height();
	        var left = $("#login_img").offset().left - $(".logindiv").width() / 2;
	        $(".logindiv").css("margin", top + "px 0 0 " + left + "px");
	        $("#close_login").click(function () {
		        closeBlank();
	        });
	    },
	    error:function(){
	        location.href=ajax_url+"user/login.jspa";return false;
	    }
	});
	
}
function openBlank() {
	$("#loginblank").css("display", "block");
	$("#loginblank").css("height", $("body").height() + "px");
}
function closeBlank() {
	$("#loginblank").css("display", "none");
	$("body").remove("#loginblank");
}
function initBox(){
    $("#box").css("display", "block");
	$("#box").css("height", $("body").height() + "px");
	$("#box").css("width", $("body").width() + "px");
	
}
function openBox(obj) {
	initBox()
	$("#box_body").css("display", "block");
	
	if(obj==undefined){
	    var left = ($("#box").parent().width() - $("#box_body").width()) / 2;
	    var height = ($("#box").parent().height() - $("#box_body").height()) / 2;
	    $("#box_body").css("left", left + "px");
	    $("#box_body").css("top", height + "px");
	}else{
	    var left = $(obj).offset().left;
	    if((left+$("#box_body").width())>$("#box").parent().width()){
	       left=$("#box").parent().width()-$("#box_body").width()-5;
	    }
	    var height = $(obj).offset().top+$(obj).height()+5;
	    if((height+$("#box_body").height())>$("#box").parent().height()){
	       height=$("#box").parent().height()-$("#box_body").height()-height;
	    }
	    $("#box_body").css("left", left + "px");
	    $("#box_body").css("top", height + "px");
	}
	$("#box").click(function () {
		//closeBox();
	});
	$(".closed").click(function () {
		closeBox();
		return false;
	});
	$("#top_search_select").css("visibility","hidden");
	if(obj==undefined){
	    window.scroll(left,height)
	}
}
function closeBox() {
	$("#box").css("display", "none");
	$("#box_body").css("display", "none");
	$("#box").remove();
	$("#box_body").remove();
	$("#top_search_select").css("visibility","");
}
/*
 * window.sidebar为firefox
 * document.all为IE
 */
 
//加入收藏夹  
  function addBookmark(title,url) {
		
      if (window.sidebar) { 
          window.sidebar.addPanel(title, url,""); 
      } else if( document.all ) {
         window.external.addFavorite(url, title);
           
      } else if( window.opera && window.print ) {
          return true;
      }
    } 
    
//将当前页设为首页    
 function setHomeHref(jobmdHome){
     var hrefStr="http://www.expopo.com"
     if(window.sidebar)
     {
       try { 
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
           } catch(e){ 
             alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将[signed.applets.codebase_principal_support]设置为'true'"); 
           }     
       var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
       prefs.setCharPref('browser.startup.homepage',hrefStr);
     }else if(document.all){
       jobmdHome.style.behavior='url(#default#homepage)';
       jobmdHome.setHomePage(hrefStr);
    }
}   

function MM_openBrWindow(theURL,winName,features) {
		var w = window.open(theURL,winName,features);
		if(w != null){ w.focus();}
}

function changeCaptcha()  
{  
    $("#captcha_img").attr("src",ajax_url+"captcha.jspa?"+Math.random());
    return false;
}  


function createFormData(formId){
    	formId="#"+formId;
		var forms=jQuery(formId).find("input");
		var len=forms.length;
		if(len<=0){
  		  // alert("表单为空");
   		   return "";
		}
		var out = "";
		var obj;
		var j=0;
		for(var i=0;i<len;i++){
    		obj=jQuery(forms[i]);
			if(obj.val().length>0){
			if(j>0){
			  out+="&";
			}
			out+=obj.attr("name");
			out+="=";
			out+=encodeURIComponent(obj.val());
			j++;
	    }
       }
       
       var textareas=jQuery(formId).find("textarea"); 
	   var len2=textareas.length;
	   if(j==0){
	      out+="1=1";
	   }
	   for(var i=0;i<len2;i++){
	        obj=jQuery(textareas[i]);
	        out+="&";
			if(obj.val().length>0){
			   out+=obj.attr("name");
			   out+="=";
			   out+=obj.val();
			}
	   }
  return out;
}

function createcube(key,parent,limit,background,docmargin,border,type){
  var top="<b class=\"xtop\">";
  var bottom="<b class=\"xbottom\">";
  var bi=limit;
  for(var i=1;i<=limit;i++){
    bottom+="<b class=\"xb"+bi+"\"></b>";
	top+="<b class=\"xb"+i+"\"><b></b></b>";
	jQuery(parent+" .xb"+i).css("margin","0 "+i+"px");
	bi--;
	
  }
  top+="</b>";
  bottom+="</b>";
  if(type==1){
    createBottomCube(key,bottom,docmargin,background,border,parent)
  }else if(type==2){
    createTopCube(key,top,docmargin,background,border,parent)
  }else{
    createTopCube(key,top,docmargin,background,border,parent)
    createBottomCube(key,bottom,docmargin,background,border,parent)
  }
  bi=limit
  for(var i=1;i<=limit;i++){
	jQuery(".xb"+i).css("margin","0 "+bi+"px");
	if(border.length>0&&i==0){
      jQuery(".xb"+i).css("border-bottom",border);
    }
    bi--;
  }
  jQuery(parent+"  .xb"+limit).css("height","2px");
  limit=limit+1
  jQuery(parent+"  .xb1").css("margin","0 "+limit+"px");
}
function createBottomCube(key,bottom,docmargin,background,border,parent){
  jQuery(key).after(bottom)
  jQuery(parent+" .xbottom").css("margin",docmargin);
  jQuery(parent+" .xbottom b").css("background",background).css("border-left",border);
  jQuery(parent+" .xbottom b").css("background",background).css("border-right",border);
 
}

function createTopCube(key,top,docmargin,background,border,parent){
  jQuery(key).before(top)
  jQuery(parent+" .xtop").css("margin",docmargin);
  jQuery(parent+" .xtop b").css("background",background).css("border-left",border);
 
}

/**打开查看拒绝/禁用理由*/
function showReason(obj){
  obj=$(obj);
  obj.next(".reason_box").show("normal")
  return false;
}

/**隐藏关闭拒绝/禁用理由*/
function hideReason(obj){
  obj=$(obj);
 // obj.parent().parent().css("display","none");
  obj.parent().parent().hide("normal")
  return false;
}

/**资讯字体大小切换*/
function changeFontSize(id, size) {
	$(id).css("font-size", size);
	$(id).css("font-size", size);
	$(id).children("p").css("font-size", size);
}

function addInput(obj,type,name){
	     $(obj).before("<div><input type='text' name='"+name+"' value='' size=\"40\"/><a href=\"#\" onclick=\"return deleteInput1(this)\" title=\"删除\"><img src=\""+ajax_url+"images/user/delete.gif\" valign=\"absmiddle\"/></a></div>")
}
function deleteInput(id){
        if(confirm('确认删除')){
	     $(id).remove();
	     }
}
function deleteInput1(obj){
        if(confirm('确认删除')){
	      $($(obj).parent()).remove();
	    }
	    return false; 
}
/**预览上传图片*/
function changeSrc(obj,id){
   
   if(!checkImgType(obj.value)){
       alert("上传图片必须是gif,png,jpg格式")
       return false;
   }
   alert(obj.width)
   alert($("#"+id).html())
   $("#"+id).html("<img src=\""+obj.value+"\"/>"  ); 
}

function checkImgType(fileURL){
    if(fileURL.indexOf(".")>-1){
         var imgType=fileURL.substring(fileURL.indexOf(".")+1);
         if(imgType=="JPG"||imgType=="GIF"||imgType=="PNG"||imgType=="jpg"||imgType=="gif"||imgType=="png"){
           return true;
         }
    }
    
    return false;
} 


function openComplainForm(obj) {
    $("body").append("<div id=\"box\"></div>");
    initBox()
	$.ajax({type:"get", url:ajax_url + "complain.ajax", dataType:"html", success:function (data) {
		$("body").append(data);
		openBox(obj);
	}});
}
function sendMail() {
	$.ajax({type:"get", url:ajax_url + "user/register.jspa?action=SendMail", dataType:"html", success:function (data) {
	  alert("fffff")
	}});
}