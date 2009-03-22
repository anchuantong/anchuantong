function Dreamweaver(iframeId,width){
	this.iframeDesign=document.getElementById(iframeId);
	this.iframeDoc=this.iframeDesign.contentWindow.document;
	this.iframeDoc.open();
	this.iframeDoc.write('<html><body></body></html>');
	this.domId=1;
	this.iframeDesign.width=width+"px;"
	this.rootElement=this.iframeDoc.body;
	this.showWindow= new ShowWindow();
}

Dreamweaver.prototype.create = function(domType){
	var CLASS = this;
	var obj = this.iframeDoc.createElement(domType);
    obj.setAttribute("id",domType+"_"+CLASS.domId);
    CLASS.domId=this.domId+1;
	return obj;
}

Dreamweaver.prototype.append = function(element){
	this.rootElement.appendChild(element);
}

Dreamweaver.prototype.openUpdate= function(element){
	var CLASS = this;
	CLASS.showWindow.init("哈哈",400,10);
	CLASS.showWindow.innerHTML("fsfdsdsdsfdsfsdf放大师傅都是是大多数的师傅");
	CLASS.showWindow.openBox();   
	CLASS.showWindow.initPosition(null,100,100);
}
