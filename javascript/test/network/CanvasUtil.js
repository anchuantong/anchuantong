var CanvasUtil={
drawLine:function(ctx,x1,y1,x2,y2,width,color,wl){
	if(width==undefined) width=10;
	if(wl==undefined) wl=width;//箭头宽度
	if(wl<4)wl=4;
	if(color==undefined) color="#FFdd00";
	ctx.save();
	ctx.beginPath();
	ctx.strokeStyle=color;
	
	ctx.lineWidth=width;
	ctx.moveTo(x1,y1)
	ctx.lineTo(x2,y2);
	
	ctx.stroke();
	var lineX=x2-x1;
	var lineY=y2-y1;
	if(lineX<0){lineX=-lineX};
	if(lineY<0){lineY=lineY};
    ctx.save();
	ctx.beginPath();
	ctx.translate(x2,y2);//原点移动到某一点
	ctx.strokeStyle="#00FF00";
	ctx.fillStyle="#FFFFFF";
	ctx.lineWidth=1;
	var wl2=wl+1;
	var wlh=wl+parseInt(wl/2);
	var wls=wl*2;
	if(x2>=x1){
		
		ctx.rotate(Math.atan(lineY/lineX));//canvas旋转
		ctx.moveTo(-wl,0);
		ctx.fillRect(-wl,-wl,wl2,wls);
		ctx.fillStyle=color;
		ctx.lineTo(-wlh,-wl);
		ctx.lineTo(0,0);
		ctx.lineTo(-wlh,wl);
		ctx.lineTo(-wl,0);
	}else{
		ctx.rotate(-Math.atan(lineY/lineX));//canvas旋转
		ctx.fillRect(wl,wl,-wl2,-wls);
		ctx.fillStyle=color;
		ctx.moveTo(wl,0);
		ctx.lineTo(wlh,wl);
		ctx.lineTo(0,0);
		ctx.lineTo(wlh,-wl);
		ctx.lineTo(wl,0);
	}
	ctx.fill();
	ctx.restore();
	ctx.restore();
}

}
 