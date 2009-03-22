function Dreamweaver(iframeId,width){
	this.iframeDesign=document.getElementById(iframeId);
	this.iframeDoc=this.iframeDesign.contentWindow.document;
	this.iframeDoc.open();
	this.iframeDoc.write('<html><body></body></html>');
	this.domId=1;
	this.iframeDesign.width=width+"px;"
	this.rootElement=this.iframeDoc.body;
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
