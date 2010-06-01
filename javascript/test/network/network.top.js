(function($){
    $.fn.drawTop = function(options){
        options = options ||
        {};
		if(options.scaleX==undefined){
			options.scaleX=1;
		}
		if(options.scaleY==undefined){
			options.scaleY=1;
		}
		if(options.totalTraffic==undefined){
			options.totalTraffic=10000;
		}
        this.drawTop.root = this;
		this.drawTop.options=options;
        this.drawTop.divObj = $("<div ></div>");
        this.drawTop.divObj.attr("id", "board");
        this.drawTop.divColorObj = $("<div ></div>");
        this.drawTop.divColorObj.attr("id", "board_color");
        this.drawTop.divColorObj.html(this.drawTop.getColorHTML())
        this.append(this.drawTop.divObj);
        this.append(this.drawTop.divColorObj);
		this.drawTop.drawTop();
		this.drawTop.divColorObj.css("left",this.offset().left+"px").css("top",(this.drawTop.divObj.height()-100)+"px")
    }
    $.fn.drawTop.initSwitch = function(){
    
        for (var i = 0; i < sites.length; i++) {
            var site = sites[i];
            var checkExits = {};
            for (var j = 0; j < switchs.length; j++) {
                var switchStr = switchs[j].name;
                var top = site.top + 20;
                var left = site.left + 8;
                if (switchStr.indexOf(site.name) < 0) {
                    continue;
                }
                if (checkExits[switchStr + ""]) {
                    continue;
                }
                checkExits[switchStr + ""] = true;
                var pos = switchStr.indexOf(".");
                var flagName = switchStr
                if (pos > -1) {
                    flagName = flagName.substring(0, pos);
                }
                
                var divObj = $("<div></div>");
                divObj.attr("id", flagName);
                var imgObj1 = $("<img></img>");
                imgObj1.attr("src", "switch.png");
                imgObj1.attr("width", 40);
                imgObj1.css("float", "left");
                //imgObj1.css("filter","progid:DXImageTransform.Microsoft.AlphaImageLoader(src='../images/topology/switch.png', sizingMethod='crop')");
                var spanText = $("<div></div>");
                spanText.attr("id", flagName + "_text");
                spanText.css("display", "none");
                spanText.addClass("dv_text");
                imgObj1.mouseover(function(){
                    $("#" + $(this).parent().attr("id") + "_text").show();
                    $("#" + $(this).parent().attr("id") + "_text").css("left", $(this).offset().left - 120);
                    $("#" + $(this).parent().attr("id") + "_text").css("top", $(this).offset().top + 50);
                })
                
                imgObj1.mouseout(function(){
                    $("#" + $(this).parent().attr("id") + "_text").hide();
                })
                
                spanText.html("设备:" + switchStr)
                var pObj = $("<p></p>");
                pObj.css("font-size", "14px").css("font-weight", "bold").css("clear", "both");
                pObj.hide()
                pObj.html(switchStr)
                divObj.append(imgObj1);
                this.divObj.append(spanText);
                divObj.append(pObj);
                if (flagName.indexOf("C7609_1") > -1 || flagName.indexOf("CM5_2") > -1) {
                    if (site.width > site.height) {
                        left = left + 60;
                    }
                    else {
                        top = top + 60;
                    }
                }
                divObj.css("position", "absolute").css("left", left + "px").css("top", top + "px").css("z-index", 500);
                spanText.css("position", "absolute").css("left", left + "px").css("top", top + "px").css("z-index", 999);
                this.divObj.append(divObj);
                
                
            }
            
            var spanObj = $("<span></span>");
            var siteDesc = site.name;
            if (siteDescs[site.name] != undefined) {
                siteDesc = siteDescs[site.name];
            }
            spanObj.html("<a href='trafficDetail.htm?tab=1&flagId=" + site.flagId + "'>" + siteDesc + "</a>");
            spanObj.css("position", "absolute").css("text-align", "center").css("z-index", "25").css("width", site.width + "px").css("left", site.left + "px").css("top", (site.top + site.height + 10) + "px").css("z-index", 5);
            this.divObj.append(spanObj);
        }
        
        
        
    }
    
    $.fn.drawTop.drawTop=function(){
        var roott = document.getElementById("board");
        var canvas = document.createElement("canvas");
        roott.appendChild(canvas)
        canvas.setAttribute("width", 800);
        canvas.setAttribute("height", 700);
        canvas.setAttribute("style", "top:" + this.divObj.offset().top + "px");
        if (canvas.getContext == undefined) {//IE情况
            this.drawTopIE()
            return;
        }
        var ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, 800, 600);
        
        ctx.scale(this.options.scaleX, this.options.scaleY);
        for (var i = 0; i < sites.length; i++) {
            var siteObj = $("<div></div>");
            var site = sites[i];
            ctx.save();
            y = site.top - this.divObj.offset().top + 75;
            x = site.left - this.divObj.offset().left + 30;
            ctx.beginPath();
            ctx.translate(x, y)
            ctx.scale(0.5, 1);
            ctx.lineWidth = 2;
            ctx.strokeStyle = site.color;
            ctx.arc(0, 0, 70, 0, Math.PI * 2, true);
            ctx.stroke();
            ctx.restore();
        }
        
        ctx.fillStyle = "#FFFFFF";
        this.initSwitch();
        
        for (var i = 0; i < trafficDevices.length; i++) {
            var trafficDevice = trafficDevices[i];
			var formId=trafficDevice.from;
			var pos=formId.indexOf(".")
			if(pos>-1){
				formId=formId.substring(0,pos);
			}
			var toId=trafficDevice.to;
			pos=toId.indexOf(".")
			if(pos>-1){
				toId=toId.substring(0,pos);
			}
            var from = $("#" + formId);

            var to = $("#" + toId);
            var left = to.offset().left;
            var top = to.offset().top;
            if (from.offset().left > to.offset().left) {
                left = left + (from.offset().left - to.offset().left) / 2;
            }
            else {
                left = left - (to.offset().left - from.offset().left) / 2;
            }
            if (from.offset().top > to.offset().top) {
                top = top + (from.offset().top - to.offset().top) / 2;
            }
            else {
                top = top - (to.offset().top - from.offset().top) / 2;
            }
            var left2 = from.offset().left - to.offset().left;
            var top2 = from.offset().top - to.offset().top;
            //if(left2<0){left2=-left2;};
            //if(top2<0){top2=-top2;};
            
            var leftFrom = from.offset().left - this.divObj.offset().left + 20;
            var topFrom = from.offset().top - this.divObj.offset().top + 20;
            var leftTo = left - this.divObj.offset().left + 20;
            var topTo = top - this.divObj.offset().top + 20;
            var bps = trafficDevice.bps;
            var color = this.getLineColor(bps);
            CanvasUtil.drawLine(ctx, leftFrom, topFrom, leftTo, topTo, 6, color)
            
            
            
            if (trafficDevice.flagId != undefined) {
                var href = trafficDevice.href;
                var aObj = $("<a></a>");
                aObj.attr("href", href);
                aObj.attr("title", "点击查看此链路趋势");
                var spanObj = $("<span></span>");
                aObj.html(trafficDevice.bps);
                spanObj.addClass("traffic_val");
                var left2 = to.offset().left - from.offset().left;
                var top2 = to.offset().top - from.offset().top;
                left2 = from.offset().left + left2 / 3;
                top2 = from.offset().top + top2 / 3 + 10;
                
                spanObj.css("left", left2 + "px").css("top", top2 + "px").css("border", "2px solid " + color);
                spanObj.append(aObj);
                this.divObj.append(spanObj);
            }
        }
        
    }
    
    $.fn.drawTop.drawTopIE=function(){
		var vmlStr="<!--[if gte vml 1]>";
		if(this.options.oval!=undefined){
			var oval=this.options.oval
			  vmlStr += "<v:oval strokecolor="+oval.strokecolor+" dashstyle='dash' style='position:absolute;z-index:1;left:"+oval.left+"px;top:"+oval.left+"px;width:"+oval.width+"px;height:"+oval.height+"px'><v:stroke   dashstyle='dash'/> </v:oval>"
		}
		for (var i = 0; i < sites.length; i++) {
			 var site = sites[i];
			 vmlStr += "<v:Oval strokecolor='" + site.color + "'    id='node_" + site.name + "' class='sites' style='width:" + site.width + ";height:" + site.height + ";left:" + site.left + ";top:" + site.top + ";'></v:Oval> "
		}
		
		vmlStr += "<![endif]-->"
        document.getElementById("board").innerHTML = vmlStr;
        this.initSwitch()
        var str = "<!--[if gte vml 1]>";
        for (var i = 0; i < trafficDevices.length; i++) {
            var flag = trafficDevices[i];
            var formObj = $("#" + flag.from);
            var leftFrom = formObj.offset().left - this.divObj.offset().left + 20;
            var topFrom = formObj.offset().top - this.divObj.offset().top + 20;
            var toObj = $("#" + flag.to);
            var leftTo = toObj.offset().left - this.divObj.offset().left + 20;
            var topTo = toObj.offset().top - this.divObj.offset().top + 20;
            
            
        }
        
        for (var i = 0; i < trafficDevices.length; i++) {
            var trafficDevice = trafficDevices[i];
            var formId=trafficDevice.from;
			var pos=formId.indexOf(".")
			if(pos>-1){
				formId=formId.substring(0,pos);
			}
			var toId=trafficDevice.to;
			pos=toId.indexOf(".")
			if(pos>-1){
				toId=toId.substring(0,pos);
			}
            var from = $("#" + formId);

            var to = $("#" + toId);
            var left = to.offset().left;
            var top = to.offset().top;
            if (from.offset().left > to.offset().left) {
                left = left + (from.offset().left - to.offset().left) / 2;
            }
            else {
                left = left - (to.offset().left - from.offset().left) / 2;
            }
            if (from.offset().top > to.offset().top) {
                top = top + (from.offset().top - to.offset().top) / 2;
            }
            else {
                top = top - (to.offset().top - from.offset().top) / 2;
            }
            var leftFrom = from.offset().left - this.divObj.offset().left + 20;
            var topFrom = from.offset().top - this.divObj.offset().top + 20;
            var toObj = $("#" + flag.to);
            var leftTo = left - this.divObj.offset().left + 20;
            var topTo = top - this.divObj.offset().top + 20;
            var bps = trafficDevice.bps;
            var color = this.getLineColor(bps);
            str += "<v:line class=\"line\"  id='" + trafficDevice.from + "  ::  " + trafficDevice.to + "' format2=\"" + trafficDevice.from + "," + trafficDevice.to + "\""
            str += " from=\"" + leftFrom + "px," + topFrom + "px\" ";
            str += " to=\"" + leftTo + "px," + topTo + "px\"   strokecolor=\"" + color + "\" strokeweight=\"5px\"   o:insetmode=\"auto\">";
            str += "<v:stroke Endarrow='classic' ></v:stroke>";
            //str+="<span class='traffic_val'>"+trafficDevice.bps+"</span>"
            str += "</v:line>";
            if (trafficDevice.flagId != undefined) {
                var href = trafficDevice.href;
                var aObj = $("<a></a>");
                aObj.attr("href", href);
                aObj.attr("title", "点击查看此链路趋势");
                var spanObj = $("<span></span>");
                aObj.html(trafficDevice.bps);
                spanObj.addClass("traffic_val");
                var left2 = to.offset().left - from.offset().left;
                var top2 = to.offset().top - from.offset().top;
                left2 = from.offset().left + left2 / 3;
                top2 = from.offset().top + top2 / 3 + 10;
                
                spanObj.css("left", left2 + "px").css("top", top2 + "px").css("border", "2px solid " + color);
                spanObj.append(aObj);
                this.divObj.append(spanObj);
            }
            
        }
        
        str += "<![endif]-->";
        var lineObj = $("<div></div>");
        lineObj.html(str);
        lineObj.css("position", "absolute").css("left", (this.divObj.offset().left) + "px").css("top", this.divObj.offset().top + "px").css("z-index", 15);
        this.divObj.append(lineObj);
        
        
        
        $(".line").mouseover(function(){
            //$(this).attr("strokecolor","red");
            var text = $(this).attr("format2");
            var ids = text.split(",");
            for (var i = 0; i < ids.length; i++) {
                $("#" + ids[i] + "_text").show()
                $("#" + ids[i] + "_text").css("z-index", 999);
                $("#" + ids[i] + "_text").css("left", $("#" + ids[i]).offset().left - 120);
                $("#" + ids[i] + "_text").css("top", $("#" + ids[i]).offset().top + 50);
            }
        })
        
        $(".line").mouseout(function(){
            //$(this).attr("strokecolor","blue");
            var text = $(this).attr("format2");
            var ids = text.split(",");
            for (var i = 0; i < ids.length; i++) {
                $("#" + ids[i] + "_text").hide()
            }
        })
    }
    
    $("canvas").mouseover(function(){
        var ctx = this.getContext('2d');
        
    })
    
    $("canvas").mouseout(function(){
        $(this).attr("strokecolor", "blue");
        $(this).attr("strokeweight", "2px")
    })
	
    var colors2 = [{
        start: 0,
        end: 1,
        color: "#dddddd"
    }, //{start:1,end:40,color:"#8305FF"},
    {
        start: 1,
        end: 40,
        color: "green"
    }, //{start:10,end:25,color:"#1625F0"},
    //{start:25,end:40,color:"#00C4FA"},
    //{start:40,end:55,color:"#00F109"},
    {
        start: 41,
        end: 60,
        color: "#EEF006"
    }, {
        start: 61,
        end: 80,
        color: "#FFBE00"
    }, {
        start: 80,
        end: 100,
        color: "#FB0103"
    }]
    
    $.fn.drawTop.getColorHTML=function(){
    
        var colorHtml = "";
        for (var i = 0; i < colors2.length; i++) {
            colorHtml += "<p><span style=\"clear:both;background:" + colors2[i].color + ";width:60px;display:block;height:10px;line-height:10px;float:left;\">&nbsp;</span>:" + colors2[i].start + "~" + colors2[i].end + "%</p>"
        }
        return colorHtml;
    }
    
    $.fn.drawTop.getLineColor=function(bps){//根据流量获取线条颜色
        var color = "#000000";
        var value = parseFloat(bps.substring(0, bps.length - 2));
        var unit = bps.substring(bps.length - 2);
        if (unit == "Gb") {
            value = value * 1000;
        }
        value = value * 100;
        value = parseInt(value / this.options.totalTraffic);
        for (var j = 0; j < colors2.length; j++) {
            var colorObj = colors2[j];
            if (colorObj.end >= value && value >= colorObj.start) {
                color = colorObj.color;
            }
        }
		
        return color;
    }
    
    function containsArr(arr, name){
        for (var i = 0; i < arr.length; i++) {
            if (arr[i].name == name) {
                return true;
            }
        }
        return false;
    }
    
    
})(jQuery);
