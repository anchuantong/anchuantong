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

