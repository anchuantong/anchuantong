/***
  * <h3>颜色选择器</h3>
  * Copyright (c) 2009  expopo.cn All Rights Reserved
  * $author:an_chuantong<an_chuantong@yahoo.com.cn> $
  * $Date: 2009-01-14 15:21:25 -0500 (Wed, 14 Jan 2009) $
  * $使用方法：
  *  var selectColor=new SelectColor();
  *  selectColor.init("输入框ID") //<div><input type="text" name="color" id="color" value="#e8e8e8" size="7" maxLength="7" /></div>
  * $
  */
function SelectColor() {
}
SelectColor.prototype = new SelectColor();
SelectColor.prototype = { 
    init:function(iptIds){
        this.hexch = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');
        this.nowIptId="";
        this.initColor="";
        this.nowIpt;
        this.nowIptBoxView;
        this.initSelectDiv();
    },
    initSelectDiv:function(){
        var CLASS=this;
        this.selectColorId="select_color_id";
        this.selectColorDiv=this.createDiv(this.selectColorId);
        this.selectColorDiv.style.display="none";
        this.selectColorDiv.style.width="360px";
        this.selectColorDiv.style.height="270px";
        
       
        this.selectColorDiv.style.position="absolute";
        this.selectColorDiv.style.background="#FFF";
        this.selectColorDiv.style.zIndex=12;
        this.selectColorDiv.style.border="1px solid #ccc";
        this.selectColorDiv2=this.createDiv(this.selectColorId+"2");
        
        var ulObj = document.createElement("ul");
        ulObj.style.width="240px";
        ulObj.style.height="128px";
        ulObj.style.listStyle="none";
        ulObj.style.cursor="pointer";
        var cnum = new Array(1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0);
        var r, g, b, n,color;
        
        for(i = 0; i < 16; i ++){
            for(j = 0; j < 30; j ++){
                n1 = j % 5;
                n2 = Math.floor(j / 5) * 3;
                n3 = n2 + 3;
                r=(cnum[n3] * n1 + cnum[n2] * (5 - n1));
                g=(cnum[n3 + 1] * n1 + cnum[n2 + 1] * (5 - n1));
                b=(cnum[n3 + 2] * n1 + cnum[n2 + 2] * (5 - n1));
                color=this.wc(r,g,b, i);
                var liObj = document.createElement("li");
                liObj.style.width="8px";
                liObj.style.height="8px";
                liObj.style.lineHeight="8px";
                liObj.style.listStyle="none";
                liObj.style.background="#"+color;
                liObj.style.styleFloat="left";
                liObj.style.cssFloat = "left";
                liObj.innerHTML="&nbsp;";
                liObj.setAttribute("rel",color);
                liObj.onmouseover=function(){
                   CLASS.mouseoverColor(this);
                }
                liObj.onmouseout=function(){
                   CLASS.mouseoutColor(this);
                }
                liObj.onclick=function(){
                   CLASS.clickColor(this.getAttribute("rel"));
                }
                ulObj.appendChild(liObj);
            }
        }
        ulObj.style.styleFloat="left";
        ulObj.style.cssFloat = "left";
        ulObj.style.textAlign = "left";
        ulObj.style.margin = "120px 0 0 0";
        this.selectColorDiv2.style.styleFloat="left";
        this.selectColorDiv2.style.cssFloat = "left";
        this.selectColorDiv2.style.textAlign = "left";
        this.selectColorDiv2.style.margin = "0";
        this.selectColorDiv.appendChild(ulObj);
        this.selectColorDiv.appendChild(this.selectColorDiv2);
        //var parentObj; 
        //if(this.color_box_id!=undefined){
        //   parentObj=document.getElementById(this.color_box_id);
        //}
        //if(parentObj==undefined){
        //   parentObj=document.body;
        //}
        document.body.appendChild(this.selectColorDiv);
    },
    showSelectDiv:function(obj,viewId){
        if(this.selectColorDiv.style.display=="none"){
           this.selectColorDiv.style.display="block";
           this.setPostion(obj,viewId);
        }else{
           if(obj.id==this.nowIptId) { 
              this.selectColorDiv.style.display="none";
           }else{
              this.setPostion(obj,viewId);
           }
        }
        
        
        
       
    },
    setPostion:function(obj,viewId){
    	  var top=$(obj).offset().top+22;
        var left=$(obj).offset().left;
        this.selectColorDiv.style.top=top+"px";
        this.selectColorDiv.style.left=left+"px";
        this.nowIptId=obj.id;
        this.nowIptBoxView=document.getElementById(viewId);
        this.nowIptBoxView.style.background=obj.value;
        this.nowIpt=obj;
        if(obj.value.length>0){
          this.clickColor(obj.value)
        }
    },	
    createDiv:function(id){
	      var div = document.createElement("div");
	      div.setAttribute("id",id);
	      return div;
    },
    ToHex:function(n){
        var CLASS=this; 
        var h, l;
        n = Math.round(n);
        l = n % 16;
        h = Math.floor((n / 16)) % 16;
        return (this.hexch[h] + this.hexch[l]);
    },
    wc:function(r, g, b, n){
        r = ((r * 16 + r) * 3 * (15 - n) + 0x80 * n) / 15;
        g = ((g * 16 + g) * 3 * (15 - n) + 0x80 * n) / 15;
        b = ((b * 16 + b) * 3 * (15 - n) + 0x80 * n) / 15;
        return this.ToHex(r) + this.ToHex(g) + this.ToHex(b);
    },
    DoColor:function (c, l){
        var r, g, b;
        r = '0x' + c.substring(1, 3);
        g = '0x' + c.substring(3, 5);
        b = '0x' + c.substring(5, 7);

        if(l > 120){
            l = l - 120;
            r = (r * (120 - l) + 255 * l) / 120;
            g = (g * (120 - l) + 255 * l) / 120;
            b = (b * (120 - l) + 255 * l) / 120;
        }else{
            r = (r * l) / 120;
            g = (g * l) / 120;
            b = (b * l) / 120;
        }
        return '#' + this.ToHex(r) + this.ToHex(g) + this.ToHex(b);
    },
    mouseoverColor:function(obj){
    	  var colorTmp=obj.getAttribute("rel");
    	  if(colorTmp.length<7){
    	  	colorTmp="#"+colorTmp;
    	  }	
        this.nowIptBoxView.style.background=colorTmp;
        this.nowIpt.value=colorTmp; 
    },
    mouseoutColor:function(){
    	  if(this.initColor.length<7){
    	  	this.initColor="#"+this.initColor;
    	  }
        this.nowIptBoxView.style.background=this.initColor;
       this.nowIpt.value=this.initColor; 
    },
    clickColor:function(colorP){
      
        var CLASS =this
        var ulObj = document.createElement("ul");
        ulObj.style.width="30px";
        ulObj.style.cursor="pointer";
        var colorC;
        for(i = 0; i <= 30; i ++)
        {
                var liObj = document.createElement("li");
                liObj.style.width="30px";
                liObj.style.height="8px";
                liObj.style.lineHeight="8px";
                liObj.style.listStyle="none";
                colorC=this.DoColor(colorP, 240 - i * 8);
                liObj.style.background=colorC;
                liObj.setAttribute("rel",colorC)
                liObj.innerHTML="&nbsp;";
                liObj.style.styleFloat="left";
                liObj.onmouseover=function(){
                   CLASS.mouseoverColor(this);
                }
                liObj.onmouseout=function(){
                   CLASS.mouseoutColor(this);
                }
                liObj.onclick=function(){
                	 CLASS.initColor=this.getAttribute("rel");
                	 CLASS.mouseoutColor();
                	 CLASS.selectColorDiv.style.display="none";
                }
                ulObj.appendChild(liObj);
           
        
        }
       
        this.selectColorDiv2.innerHTML="";
        this.selectColorDiv2.appendChild(ulObj);
        this.initColor=colorP;
        this.mouseoutColor()
    }
}