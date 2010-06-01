


var switchs=[{name:"CM5_1",portName:""},
			{name:"CM5_2",portName:""},
			{name:"CM6_1",portName:""},
			{name:"CM6_2",portName:""},
			{name:"HZCM4_CSW_C7609_1",portName:"Te1/1"},
			{name:"HZCM4_CSW_C7609_2",portName:"Te1/1"},
			{name:"HZCM2_CSW_C7609_1.taobao.com",portName:"Te1/1"},
			{name:"HZCM2_CSW_C7609_2.taobao.com",portName:"Te1/1"},
			{name:"HZCM3_CSW_C7609_1.taobao.com",portName:"Te1/1"},
			{name:"HZCM3_CSW_C7609_2.taobao.com",portName:"Te1/1"},
			{name:"HZCM1_CSW_C7609_1.taobao.com",portName:"Te1/1"},
			{name:"HZCM1_CSW_C7609_2.taobao.com",portName:"Te1/1"}
			];


var trafficDevices=[];	

var sites=[
		{"name":"CM1","description":"","top":20,"left":20,color:"green","width":60,"height":150,"flagId":1},
		{"name":"CM2","description":"","top":50,"left":350,color:"green","width":60,"height":150,"flagId":2},
		{"name":"CM3","description":"","top":260,"left":150,color:"green","width":60,"height":150,"flagId":3},
		{"name":"CM4","description":"","top":260,"left":600,color:"green","width":60,"height":150,"flagId":17},
		{"name":"CM5","description":"","top":20,"left":730,color:"green","width":60,"height":150,"flagId":0}
]
var siteDescs={}
	siteDescs["CM1"]="»ú·¿1"
	siteDescs["CM2"]="CM2"
	siteDescs["CM3"]="CM3"
	siteDescs["CM4"]="CM4"


trafficDevices.push({"from":"CM5_1","to":"HZCM2_CSW_C7609_2","bps":"0Mb"});
trafficDevices.push({"from":"HZCM2_CSW_C7609_2","to":"CM5_1","bps":"0Mb"});
trafficDevices.push({"from":"CM5_2","to":"HZCM4_CSW_C7609_2","bps":"0Mb"});
trafficDevices.push({"from":"HZCM4_CSW_C7609_2","to":"CM5_2","bps":"0Mb"});
trafficDevices.push({"from":"HZCM2_CSW_C7609_2","to":"HZCM3_CSW_C7609_2.taobao.com","bps":"80.4Mb","fromSite":"CM2","toSite":"CM3","formPort":"2405","toPort":"2981","flagId":"21","href":"#"});
trafficDevices.push({"from":"HZCM3_CSW_C7609_2.taobao.com","to":"HZCM2_CSW_C7609_2.taobao.com","bps":"744.0Mb","fromSite":"CM3","toSite":"CM2","formPort":"2981","toPort":"2405","flagId":"23","href":"#"});
trafficDevices.push({"from":"HZCM1_CSW_C7609_2.taobao.com","to":"HZCM2_CSW_C7609_2.taobao.com","bps":"744.0Mb","fromSite":"CM3","toSite":"CM2","formPort":"2981","toPort":"2405","flagId":"23","href":"#"});
trafficDevices.push({"from":"HZCM2_CSW_C7609_2.taobao.com","to":"HZCM1_CSW_C7609_2.taobao.com","bps":"7.2Gb","fromSite":"CM3","toSite":"CM2","formPort":"2981","toPort":"2405","flagId":"23","href":"#"});
//trafficDevices.push({"from":"HZCM1_CSW_C7609_1.taobao.com","to":"HZCM2_CSW_C7609_1.taobao.com","bps":"744.0Mb","fromSite":"CM3","toSite":"CM2","formPort":"2981","toPort":"2405","flagId":"23","href":"#"});
//trafficDevices.push({"from":"HZCM2_CSW_C7609_1.taobao.com","to":"HZCM1_CSW_C7609_1.taobao.com","bps":"7.2Gb","fromSite":"CM3","toSite":"CM2","formPort":"2981","toPort":"2405","flagId":"23","href":"#"});