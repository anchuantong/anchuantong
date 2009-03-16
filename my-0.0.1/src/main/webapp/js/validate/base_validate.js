$(document).ready(function(){
   var forms=$("form");
   for(var i=0;i<forms.length;i++){
	var validator = $("#"+forms[i].id).validate({
	    errorClass: "error",
	    errorElement: "p",
	    rules: rules,
	    onsubmit: true,
	    messages: messages,
	    onfocusin: function(element) {
			this.lastActive = element;
			var obj=$("#"+element.name).parent().parent().children(".status").children(".descs");
			obj.css("display","block")
			obj.css("border"," 1px solid #e3e7ea; ")
		},
		onfocusout: function(element) {
		  var obj,result
		  if(element.name.indexOf("cat_")>-1){
		    var  elementTmp=$("#"+element.id).parent().next()
		    obj=$("#"+elementTmp.name).parent().parent().children(".status").children(".descs")
		    result=this.element(elementTmp)
		  }else{
		    obj=$("#"+element.name).parent().parent().children(".status").children(".descs")
		    result=this.element(element)
		  }
		  if(!result){obj.css("display","none")}else{obj.css("border"," 0 ")}
		},
		errorPlacement: function(error, element) {
			if ( element.is(":radio") ){
				error.appendTo( element.parent().next().next() );
			}else if ( element.is(":checkbox") ){
				error.appendTo ( element.next() );
			}else{
			  if(element.parent().next().html()==null){
			      error.appendTo( element.next() );
			      element.next().children("p").css("color","red")	
			  }else{
				   error.appendTo( element.parent().next() );
			  }	
		     }
			element.parent().next().children("p").css("color","red")	
		}
		}
	)
  }
  }
);