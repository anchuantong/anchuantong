$(document).ready(function(){
   var forms=$("form");
   for(var i=0;i<forms.length;i++){
	var validator = $("#"+forms[i].id).validate({
	    errorClass: "error",
	    errorElement: "span",
	    rules: rules,
	    onsubmit: true,
	    messages: messages,
	    onfocusin: function(element) {
			this.lastActive = element;
		},
		onfocusout: function(element) {
		  if(element.name.indexOf("cat_")>-1){
		    var  elementTmp=$("#"+element.id).parent().next()
		    result=this.element(elementTmp)
		  }else{
		    result=this.element(element)
		  }
		}
		}
	)
  }
  }
);