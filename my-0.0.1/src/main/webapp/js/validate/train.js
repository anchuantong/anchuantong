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
         name:{required: "��������ϵ������",maxlength: "������С��20λ����ϵ������"	},
         title: {required: "��������ѵ�γ�����"	},
         trainDate: {required: "��ѡ������ֹ����"	},
         outlay: {required: "������γ��շ�"	},
         locationId: {required: "��ѡ���ڿεص�"	},
         dateEnd:{required: "��ѡ���ڿ�ʱ��"} ,
         keywords:{required: "��������Ҫ�γ�"} ,
         //description:{required: "��������ϸ��Ϣ"} ,
		 email: {required: "������email", email:"��������ȷ��email��ʽ,�磺expopo@expopo.com"},
		 phone:{required:"������绰����",phone:"���������ȷ�ĵ绰�����ʽ,����������,�κú�-"},
		 address: {required: "�����빫˾��ϸ��ַ"	},
		 location: {required: "��ѡ��˾���ڵ���"	},
		 zip: {required: "�������ʱ�",minlength:"������6λ���ʱ�",maxlength:"������6λ���ʱ�"}
};
 