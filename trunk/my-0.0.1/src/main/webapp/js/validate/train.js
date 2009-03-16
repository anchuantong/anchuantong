var rules={
            title:{required: true} ,
            trainDate:{required: true} ,
            outlay:{required: true} ,
            locationId:{required: true} ,
            dateEnd:{required: true} ,
            keywords:{required: true} ,
            //description:{required: true} ,
            title:{required: true} ,
			email: {required: true,email: true},
			phone:{required: true,phone:true},
			address: {required: true	},
			zip: {required: true,minlength: 6,maxlength: 6},
			name: {required: true,maxlength: 20}
};
var messages={
         name:{required: "请输入联系人名称",maxlength: "请输入小于20位的联系人名称"	},
         title: {required: "请输入培训课程名称"	},
         trainDate: {required: "请选择报名截止日期"	},
         outlay: {required: "请输入课程收费"	},
         locationId: {required: "请选择授课地点"	},
         dateEnd:{required: "请选择授课时间"} ,
         keywords:{required: "请输入主要课程"} ,
         //description:{required: "请输入详细信息"} ,
		 email: {required: "请输入email", email:"请输入正确的email格式,如：expopo@expopo.com"},
		 phone:{required:"请输入电话号码",phone:"请输入的正确的电话号码格式,必须是数字,刮好和-"},
		 address: {required: "请输入公司详细地址"	},
		 location: {required: "请选择公司所在地区"	},
		 zip: {required: "请输入邮编",minlength:"请输入6位的邮编",maxlength:"请输入6位的邮编"}
};
 