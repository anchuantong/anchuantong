jQuery.extend({
       yy:function(key,countYY,color){
          if(countYY==undefined){
              countYY=5;
		  }
		  if(color==undefined){
              color="#000";
		  }
          var obj=$(key);
          var left=obj.offset().left;
          var top=obj.offset().top;

          var width=obj.width();
          var height=obj.height();
          obj.css("left",left+"px");
          obj.css("top",top+"px");
          obj.css("position","absolute");
          var opacity=10;
          var boxYY=$("<div id=\""+key+"_yy\"></div>");
          boxYY.css("position","absolute");
          boxYY.css("left",left+"px");

          boxYY.css("top",(top-countYY)+"px");
          height2=height+countYY*2+parseInt(countYY/2);
          width2=width+parseInt(countYY/2);
          for(var i=0;i<countYY;i++){
             opacity+=i;
             var left1=-i;
             var top1=i;
             width1=width2+2*i;
             height1=height2-2*i;
			 
             var boxChild=$("<div>&nbsp;</div>");
             boxChild.css("position","absolute");
             boxChild.css("margin-top",top1+"px");
             boxChild.css("margin-left",left1+"px");
             boxChild.css("filter","alpha(opacity="+opacity+",Style=0)");
             boxChild.css("-moz-opacity","0."+opacity);
             boxChild.css("z-index",i);
             boxChild.css("background",color);
             boxChild.css("width",width1+"px");
             boxChild.css("height",height1+"px");

             boxYY.append(boxChild);
          }
          $("body").append(boxYY)

	   }
});