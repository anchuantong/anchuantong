/***
  * ��һ��������ѡ�����ݵ���һ��������
  * Copyright (c) 2008  expopo.cn All Rights Reserved
  * $author:an_chuantong<an_chuantong@yahoo.com.cn> $
  * $Date: 2008-06-23 00:21:25 -0500 (Wed, 06 Feb 2008) $
  */
function  shuangji(name,num){
	add(name,num);
}

function addValueTo(value,text,selectId){
 var select=document.getElementById(selectId);
 var toLength=select.length; 
 select.options[toLength]=new  Option(text,value); 
}

function add(name,max){
     var from=name+"_from";
     var to=name+"_to";
     if(document.getElementById(from).length>0){
     var text=document.getElementById(from).options[document.getElementById(from).selectedIndex].text;
     var value=document.getElementById(from).value;
     if(value<0){
		alert("��ѡ��");
	    return;
	 }
	 var toLength=document.getElementById(to).length; 
	 if(document.getElementById(to).length>=max){
        alert("���ֻ��ѡ��"+max+"��");
		return;
     }
     for(var i=0;i<toLength;i++){
      if(document.getElementById(to)[i].value==value){
        alert("�Բ���,�������ظ�����!!!");
        return;
      }
     }
     document.getElementById(to).options[toLength]=new  Option(text,value); 
     document.getElementById(name).value=writeHidden(to);
     }
}
    function delkey(obv) {
		  if (obv.value=="�ؼ���") {obv.value="";}else{obv.value=obv.value.Trim();}
		 
         }
function del(name){
     var selectName=name+"_to";
     var selectedIndex=document.getElementById(selectName).selectedIndex;
     var selectedLength=document.getElementById(selectName).length; 
     if(selectedIndex<0){
         alert("����ѡ��һ�����ɾ��!");
     }
     if(selectedIndex>=0){
          if(selectedIndex!=(selectedLength-1)){
            for(var i=selectedIndex;i<selectedLength;i++){
                var textNext=document.getElementById(selectName).options[selectedIndex+1].text;
                var valueNext=document.getElementById(selectName).options[selectedIndex+1].value;
                document.getElementById(selectName).options[selectedIndex]=new  Option(textNext,valueNext); 
            }
           }
           document.getElementById(selectName).remove(selectedLength-1);  
     }
     document.getElementById(name).value=writeHidden(selectName);  
}
 
function deleteAll(name){
   var selectName=name+"_to"; 
   document.getElementById(selectName).length=0;
   document.getElementById(name).value=writeHidden(selectName);  
}
        
function writeHidden(name){
   var length=document.getElementById(name).length;
   var hiddenValue="";
   if(length>0){
      hiddenValue=document.getElementById(name).options[0].value;
      if(length>1){
       for(var i=1;i<length;i++){
         hiddenValue+=","+document.getElementById(name).options[i].value;
       } 
      }
   }
   return hiddenValue;
} 
/**����*/
function moveOption(id,up){
var select=document.getElementById(id);
var selectedIndex=select.selectedIndex;
if(selectedIndex<0){
  alert("����ѡ��һ��");
  return false;
}
var len=select.length;
var text="";
var value="";
if(up==1){//����
    if(selectedIndex<=0){
	  return false;
	}
    text=select.options[selectedIndex-1].text;
	value=select.options[selectedIndex-1].value;
    select.options[selectedIndex-1]=new  Option(select.options[selectedIndex].text,select.options[selectedIndex].value); 
    select.options[selectedIndex]=new  Option(text,value);
	select.selectedIndex=selectedIndex-1;
}else if(up==0){//����
    if(selectedIndex+1>=len){
	  return false;
	}
    text=select.options[selectedIndex+1].text;
	value=select.options[selectedIndex+1].value;
    select.options[selectedIndex+1]=new  Option(select.options[selectedIndex].text,select.options[selectedIndex].value); 
    select.options[selectedIndex]=new  Option(text,value);
	select.selectedIndex=selectedIndex+1;
}

}
