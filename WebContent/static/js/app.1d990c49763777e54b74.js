webpackJsonp([1],{"5crh":function(e,t){},LicG:function(e,t){},NHnr:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("7+uW"),i={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},staticRenderFns:[]};var o=a("VU/8")({name:"App"},i,!1,function(e){a("fIwp")},null,null).exports,l=a("/ocq"),r={data:function(){return{activeIndex:"0",routes:[{url:"/index/login",title:"打卡",currentIndex:"0"},{url:"/index/register",title:"注册",currentIndex:"1"},{url:"/index/adminlogin",title:"管理员",currentIndex:"2"}]}},methods:{handleSelect:function(e,t){console.log(e,t)},activeHandler:function(e){this.activeIndex=this.routes[e].currentIndex}}},s={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-menu",{staticClass:"el-menu-demo",attrs:{"default-active":e.activeIndex,mode:"horizontal","background-color":"#545c64","text-color":"#fff","active-text-color":"#ffd04b",router:""},on:{select:e.handleSelect}},[a("el-menu-item",{attrs:{index:"4"}},[a("i",{staticClass:"el-icon-news"}),e._v(" 公司考勤系统  ")]),e._v(" "),e._l(e.routes,function(t,n){return a("el-menu-item",{key:n,attrs:{index:t.url},on:{click:function(t){e.activeHandler(n)}}},[e._v(e._s(t.title)+" \n      ")])})],2)},staticRenderFns:[]};var c={data:function(){return{}},components:{vheader:a("VU/8")(r,s,!1,function(e){a("gKsD")},"data-v-1be35e9a",null).exports}},d={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",[t("vheader"),this._v(" "),t("div",{staticClass:"index-container"},[t("router-view")],1)],1)},staticRenderFns:[]};var u=a("VU/8")(c,d,!1,function(e){a("mETv")},"data-v-1258698e",null).exports,m={components:{},props:{},data:function(){return{imageUrl:"",fileList:[],limit:1,dialogVisible:!1,isfullscreen:!0,isCanvasShow:!1,form:{id:this.loginId,name:this.loginName}}},watch:{},computed:{},methods:{onSubmit:function(){if(""!=this.imageUrl){var e=(new Date).getHours(),t=this,a="",n=this.form.id;a=e<13?"/FaceProject/admin/time/create":"/FaceProject/admin/time/update",this.axios.post(a,{id:n}).then(function(e){t.$message(e.data.msg)}).catch(function(e){t.$message("打卡失败，请重试！")}),this.form.id="",this.form.name="",this.imageUrl=""}else this.$message("您还未录入人脸信息")},submitUpload:function(){if(""!=this.imageUrl){var e=this.imageUrl,t=this;this.axios.post("/FaceProject/admin/employee/loginface",{faceInfo:e}).then(function(e){t.$message("人脸识别成功，请检查您的个人信息是否正确"),t.form.id=e.data.data.eiId,t.form.name=e.data.data.eiName}).catch(function(e){t.$message("人脸识别失败，请重试！"),console.log("人脸识别失败，请重试！")})}else this.$message("您还未录入人脸信息")},uploadFiles:function(e){var t=this,a=e.file;"image/jpeg"==a.type||"image/png"==a.type||a.type;var n=new FileReader;n.readAsDataURL(a),n.onload=function(e){var a=e.target.result;t.imageUrl=a}},handleOpen:function(){var e=document.getElementById("canvas");e.getContext("2d").clearRect(0,0,e.width,e.height),this.isCanvasShow=!0,this.getMedia()},handleCameraClose:function(){var e=document.getElementById("video");this.stopStreamedVideo(e),this.isCanvasShow=!1,this.dialogVisible=!1},getMedia:function(){var e=document.getElementById("video");navigator.mediaDevices.getUserMedia({video:{width:633,height:468},audio:!0}).then(function(t){e.srcObject=t,e.play()})},takePhoto:function(){var e=document.getElementById("video"),t=document.getElementById("canvas");t.getContext("2d").drawImage(e,0,0,633,468);var a=t.toDataURL("image/jpeg");this.imageUrl=a},stopStreamedVideo:function(e){e.srcObject.getTracks().forEach(function(e){e.stop()}),e.srcObject=null}},created:function(){},mounted:function(){}},p={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-row",[a("el-col",{attrs:{span:8,offset:4}},[a("div",[a("el-button",{staticStyle:{"margin-left":"40px"},attrs:{size:"large",type:"primary"},on:{click:function(t){e.dialogVisible=!0}}},[e._v("拍照")]),e._v(" "),a("el-button",{staticStyle:{"margin-left":"40px"},attrs:{size:"large",type:"success"},on:{click:e.submitUpload}},[e._v("进行识别 ")]),e._v(" "),a("img",{directives:[{name:"show",rawName:"v-show",value:e.imageUrl,expression:"imageUrl"}],staticClass:"avatar",attrs:{src:e.imageUrl}}),e._v(" "),a("el-dialog",{attrs:{title:"请点击按钮录入人脸",visible:e.dialogVisible,width:"30%",fullscreen:e.isfullscreen},on:{"update:visible":function(t){e.dialogVisible=t},opened:e.handleOpen}},[a("div",[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:12}},[a("el-row",[a("el-button",{attrs:{disabled:""},on:{click:e.getMedia}},[e._v("摄像头")])],1),e._v(" "),a("video",{attrs:{id:"video",width:"633px",height:"468px",autoplay:"autoplay"}})],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-row",[a("el-button",{attrs:{type:"success",id:"snap"},on:{click:e.takePhoto}},[e._v("拍照")])],1),e._v(" "),a("canvas",{directives:[{name:"show",rawName:"v-show",value:e.isCanvasShow,expression:"isCanvasShow"}],attrs:{id:"canvas",width:"500px",height:"500px"}})],1)],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:e.handleCameraClose}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.handleCameraClose}},[e._v("确 定")])],1)])],1)]),e._v(" "),a("el-col",{attrs:{span:8}},[a("div",[a("el-col",{attrs:{span:24}},[a("el-col",{staticStyle:{"margin-bottom":"30px","font-size":"24px"},attrs:{span:13,offset:6}},[a("el-card",{attrs:{shadow:"hover"}},[e._v("\n            请查看您的个人信息\n          ")])],1)],1),e._v(" "),a("el-form",{ref:"form",attrs:{model:e.form,"label-width":"80px"}},[a("el-form-item",{attrs:{label:"您的工号"}},[a("el-input",{attrs:{disabled:!0},model:{value:e.form.id,callback:function(t){e.$set(e.form,"id",t)},expression:"form.id"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"您的姓名"}},[a("el-input",{attrs:{disabled:!0},model:{value:e.form.name,callback:function(t){e.$set(e.form,"name",t)},expression:"form.name"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("确认")]),e._v(" "),a("el-button",[e._v("取消")])],1)],1)],1)])],1)},staticRenderFns:[]};var h=a("VU/8")(m,p,!1,function(e){a("uCrJ")},null,null).exports,g={data:function(){return{ruleForm:{name:""},rules:{name:[{required:!0,message:"请输入您的姓名",trigger:"blur"},{min:2,max:5,message:"长度在 2 到 5 个字符",trigger:"blur"}]}}},props:{childImgUrl:String,required:!0},methods:{submitForm:function(e){if(""==this.childImgUrl||null==this.childImgUrl)return this.$message("您还未录入人脸信息"),!1;if(""==this.ruleForm.name||null==this.ruleForm.name)return this.$message("您还未填写个人信息"),!1;var t=this.ruleForm.name,a=this.childImgUrl,n=this;this.axios.post("/FaceProject/admin/employee/create",{name:t,faceInfo:a}).then(function(e){console.log(e.data.msg),n.$message(e.data.msg)}).catch(function(e){console.log("注册失败，请重试！"),n.$message("注册失败，请重试!")})},resetForm:function(e){this.$refs[e].resetFields()}}},f={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-col",{attrs:{span:24}},[a("el-col",{staticStyle:{"margin-bottom":"30px","font-size":"24px"},attrs:{span:13,offset:6}},[a("el-card",{attrs:{shadow:"hover"}},[e._v("\n        请输入您的个人信息\n      ")])],1)],1),e._v(" "),a("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"姓名",prop:"name"}},[a("el-input",{model:{value:e.ruleForm.name,callback:function(t){e.$set(e.ruleForm,"name",t)},expression:"ruleForm.name"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.submitForm("ruleForm")}}},[e._v("立即创建")]),e._v(" "),a("el-button",{on:{click:function(t){e.resetForm("ruleForm")}}},[e._v("重置")])],1)],1)],1)},staticRenderFns:[]},v={components:{vregisterform:a("VU/8")(g,f,!1,null,null,null).exports},props:{},data:function(){return{imageUrl:"",fileList:[],limit:1,dialogVisible:!1,isfullscreen:!0,isCanvasShow:!1,childImgUrl:""}},watch:{},computed:{},methods:{submitUpload:function(){""==this.imageUrl?this.$message("您还未录入人脸信息"):(this.$message("人脸信息等待上传，请填写您的个人信息。"),this.childImgUrl=this.imageUrl)},uploadFiles:function(e){var t=this,a=e.file;"image/jpeg"==a.type||"image/png"==a.type||a.type;var n=new FileReader;n.readAsDataURL(a),n.onload=function(e){var a=e.target.result;t.imageUrl=a}},handleOpen:function(){var e=document.getElementById("canvas");e.getContext("2d").clearRect(0,0,e.width,e.height),this.isCanvasShow=!0,this.getMedia()},handleCameraClose:function(){var e=document.getElementById("video");this.stopStreamedVideo(e),this.isCanvasShow=!1,this.dialogVisible=!1},getMedia:function(){var e=document.getElementById("video");navigator.mediaDevices.getUserMedia({video:{width:633,height:468},audio:!0}).then(function(t){e.srcObject=t,e.play()})},takePhoto:function(){var e=document.getElementById("video"),t=document.getElementById("canvas");t.getContext("2d").drawImage(e,0,0,633,468);var a=t.toDataURL("image/jpeg");this.imageUrl=a},stopStreamedVideo:function(e){e.srcObject.getTracks().forEach(function(e){e.stop()}),e.srcObject=null}}},b={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-row",[a("el-col",{attrs:{span:8,offset:4}},[a("div",[a("el-button",{staticStyle:{"margin-left":"40px"},attrs:{size:"large",type:"primary"},on:{click:function(t){e.dialogVisible=!0}}},[e._v("拍照")]),e._v(" "),a("el-button",{staticStyle:{"margin-left":"40px"},attrs:{size:"large",type:"success"},on:{click:e.submitUpload}},[e._v(" 确认使用该照片 ")]),e._v(" "),a("img",{directives:[{name:"show",rawName:"v-show",value:e.imageUrl,expression:"imageUrl"}],staticClass:"avatar",attrs:{src:e.imageUrl}}),e._v(" "),a("el-dialog",{attrs:{title:"请点击按钮打卡",visible:e.dialogVisible,width:"30%",fullscreen:e.isfullscreen},on:{"update:visible":function(t){e.dialogVisible=t},opened:e.handleOpen}},[a("div",[a("el-row",{attrs:{gutter:20}},[a("el-col",{attrs:{span:12}},[a("el-row",[a("el-button",{attrs:{disabled:""},on:{click:e.getMedia}},[e._v("摄像头")])],1),e._v(" "),a("video",{attrs:{id:"video",width:"633px",height:"468px",autoplay:"autoplay"}})],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-row",[a("el-button",{attrs:{type:"success",id:"snap"},on:{click:e.takePhoto}},[e._v("拍照")])],1),e._v(" "),a("canvas",{directives:[{name:"show",rawName:"v-show",value:e.isCanvasShow,expression:"isCanvasShow"}],attrs:{id:"canvas",width:"500px",height:"500px"}})],1)],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:e.handleCameraClose}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.handleCameraClose}},[e._v("确 定")])],1)])],1)]),e._v(" "),a("el-col",{attrs:{span:8}},[a("vregisterform",{attrs:{childImgUrl:e.childImgUrl}})],1)],1)},staticRenderFns:[]};var _=a("VU/8")(v,b,!1,function(e){a("arwl")},"data-v-2edd03f9",null).exports,w={components:{},props:{name:String,required:!0},data:function(){return{}},watch:{},computed:{},methods:{},created:function(){},mounted:function(){}},x={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticClass:"title"},[t("span",[this._v(this._s(this.name))])])},staticRenderFns:[]};var y=a("VU/8")(w,x,!1,function(e){a("5crh")},"data-v-753814ea",null).exports,F=a("nUeE"),k=a.n(F),C={components:{vlargetitle:y},data:function(){return{name:"",msg:"",tempRoute:"",isdisable:!1,routes:[{url:"/admin/employee",title:"员工信息管理",routeid:"1",img:"el-icon-document",childroutes:[{url:"/admin/employee/info",title:"员工信息列表",routeid:"1-1"}]},{url:"/admin/attendance",title:"考勤管理",routeid:"2",img:"el-icon-time",childroutes:[{url:"/admin/attendance/time",title:"签到时间管理",routeid:"2-1"},{url:"/admin/attendance/count",title:"考勤统计管理",routeid:"2-2"},{url:"/admin/attendance/rate",title:"考勤率管理",routeid:"2-3"}]}]}},methods:{handleOpen:function(e,t){console.log(e,t)},handleClose:function(e,t){console.log(e,t)},handleButton:function(e){"1"==e?(this.isdisable=!0,console.log(e),console.log(this.isdisable)):(this.isdisable=!1,console.log(e),console.log(this.isdisable))},handleAttendanceCount:function(){var e=this;this.axios.get("/FaceProject/admin/count/create",{}).then(function(t){e.openCreateResult(t.data.msg)}).catch(function(e){console.log(e)})},openCreateResult:function(e){this.$message(e)},logout:function(){k.a.set("admin_login_token",""),console.log("注销成功！"),this.$router.push({path:"/adminlogin",name:"adminlogin"})}},mounted:function(){this.name=this.$route.query.dataObj.aiName}},I={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-container",{staticStyle:{height:"750px"}},[a("el-aside",{staticStyle:{"background-color":"rgb(84, 92, 100)"},attrs:{width:"240px"}},[a("el-row",{staticClass:"tac"},[a("el-menu",{attrs:{"default-active":"2","background-color":"#545c64","text-color":"#fff","active-text-color":"#ffd04b"},on:{open:e.handleOpen,close:e.handleClose}},[a("el-menu-item",{staticStyle:{height:"130px","padding-top":"40px"},attrs:{index:"0"}},[a("span",{staticStyle:{"font-size":"26px","margin-left":"12px"},attrs:{slot:"title"},slot:"title"},[e._v("考勤管理系统")])]),e._v(" "),e._l(e.routes,function(t,n){return a("el-submenu",{key:t.routeid,attrs:{index:t.routeid}},[a("template",{slot:"title"},[a("i",{class:t.img}),e._v("\n            "+e._s(t.title)+"\n          ")]),e._v(" "),e._l(t.childroutes,function(t,n){return a("el-menu-item-group",{key:t.routeid},[a("router-link",{staticStyle:{color:"white","text-decoration":"none"},attrs:{to:t.url}},[a("el-menu-item",{attrs:{index:t.routeid},on:{click:function(t){e.handleButton(n)}}},[e._v("\n                "+e._s(t.title)+"\n              ")])],1)],1)})],2)})],2)],1)],1),e._v(" "),a("el-container",[a("el-header",[a("div",{staticStyle:{"text-align":"left","font-size":"12px","margin-left":"6px",width:"500px",float:"left"}},[e.isdisable?a("el-button",{on:{click:e.handleAttendanceCount}},[e._v("统计今日考勤信息")]):e._e()],1),e._v(" "),a("div",{staticStyle:{"text-align":"right","font-size":"12px"}},[a("el-button",{staticStyle:{"text-decoration":"none","margin-right":"20px"},on:{click:e.logout}},[e._v("退出系统 ")]),e._v(" "),a("span",[e._v(e._s(e.name)+",您好 ")])],1)]),e._v(" "),a("el-main",[a("vlargetitle"),e._v(" "),a("router-view")],1)],1),e._v(" "),a("el-button",{directives:[{name:"show",rawName:"v-show",value:!1,expression:"false"}],attrs:{plain:!0},on:{click:e.openCreateResult}})],1)},staticRenderFns:[]};var U=a("VU/8")(C,I,!1,function(e){a("jlVs")},null,null).exports,D={components:{},data:function(){return{ruleForm:{id:"",pwd:""},rules:{id:[{required:!0,message:"请输入您的ID",trigger:"blur"}],pwd:[{required:!0,message:"请输入您的密码",trigger:"blur"},{min:4,message:"长度大于4",trigger:"blur"}]}}},methods:{submitForm:function(e){var t=this.ruleForm.id,a=this.ruleForm.pwd,n=this,i=this.ruleForm;this.axios.post("/FaceProject/admin/login/query",{id:t,pwd:a}).then(function(e){console.log(e.data),n.$message(e.data.msg);var t=e.data.data;if(100!=e.data.statu)return!1;k.a.set("admin_login_token",i),n.$router.push({path:"/admin",name:"admin",query:{dataObj:t}})}).catch(function(e){n.$message("登录失败，请重试！")})},resetForm:function(e){this.$refs[e].resetFields()}}},S={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-row",[a("el-col",{staticStyle:{"margin-top":"100px"},attrs:{span:8,offset:8}},[a("div",[a("el-col",{attrs:{span:24}},[a("el-col",{staticStyle:{"margin-bottom":"30px","font-size":"24px"},attrs:{span:13,offset:6}},[a("el-card",{staticStyle:{"text-align":"center"},attrs:{shadow:"hover"}},[e._v("\n              管理员登录\n            ")])],1)],1),e._v(" "),a("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"账户ID",prop:"id"}},[a("el-input",{model:{value:e.ruleForm.id,callback:function(t){e.$set(e.ruleForm,"id",e._n(t))},expression:"ruleForm.id"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"密码",prop:"pwd"}},[a("el-input",{model:{value:e.ruleForm.pwd,callback:function(t){e.$set(e.ruleForm,"pwd",t)},expression:"ruleForm.pwd"}})],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.submitForm("ruleForm")}}},[e._v("登录")]),e._v(" "),a("el-button",{on:{click:function(t){e.resetForm("ruleForm")}}},[e._v("重置")])],1)],1)],1)])],1)],1)},staticRenderFns:[]},P=a("VU/8")(D,S,!1,null,null,null).exports,$={components:{vlargetitle:y},data:function(){return{total:0,pageSize:8,currentPage:1,tableData:[],name:"签到时间列表",cols:[{prop:"atId",label:"签到时间表ID",width:120},{prop:"atEmployeeId",label:"员工ID",width:120},{prop:"eiName",label:"员工姓名",width:100},{prop:"atDate",label:"考勤时间",width:100},{prop:"atStart",label:"上班时间",width:100},{prop:"atEnd",label:"下班时间",width:100}]}},methods:{readData:function(e){var t=this;this.axios.get("/FaceProject/admin/time/queryall",{params:{pageIndex:e}}).then(function(e){t.total=e.data.page.pageNumber,t.currentPage=e.data.page.pageIndex,t.tableData=e.data.data,console.log(e.data.data)}).catch(function(e){console.log(e)})},handleChangePage:function(e){this.readData(e)}},mounted:function(){this.readData(1)}},V={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("vlargetitle",{attrs:{name:e.name}}),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:""}},e._l(e.cols,function(e,t){return a("el-table-column",{key:t,attrs:{prop:e.prop,label:e.label,"min-width":e.width}})})),e._v(" "),a("div",{staticClass:"block"},[a("el-pagination",{attrs:{"page-size":e.pageSize,layout:"prev, pager, next",total:e.total,"current-page":e.currentPage},on:{"current-change":e.handleChangePage}})],1)],1)},staticRenderFns:[]},j=a("VU/8")($,V,!1,null,null,null).exports,E={components:{vlargetitle:y},data:function(){return{total:0,pageSize:8,currentPage:1,tableData:[],name:"考勤统计列表",cols:[{prop:"arId",label:"考勤表ID",width:80},{prop:"arEmployeeId",label:"员工ID",width:120},{prop:"eiName",label:"员工姓名",width:100},{prop:"arDate",label:"考勤时间",width:100},{prop:"arLate",label:"迟到",width:100},{prop:"arEarly",label:"早退",width:100},{prop:"arAbsence",label:"缺勤",width:100}]}},methods:{readData:function(e){var t=this;this.axios.get("/FaceProject/admin/count/queryall",{params:{pageIndex:e}}).then(function(e){t.tableData=e.data.data,t.total=e.data.page.pageNumber,t.currentPage=e.data.page.pageIndex,console.log(e.data.data)}).catch(function(e){console.log(e)})},handleChangePage:function(e){this.readData(e)}},mounted:function(){this.readData(1)}},z={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("vlargetitle",{attrs:{name:e.name}}),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:""}},e._l(e.cols,function(e,t){return a("el-table-column",{key:t,attrs:{prop:e.prop,label:e.label,"min-width":e.width}})})),e._v(" "),a("div",{staticClass:"block"},[a("el-pagination",{attrs:{"page-size":e.pageSize,layout:"prev, pager, next",total:e.total,"current-page":e.currentPage},on:{"current-change":e.handleChangePage}})],1)],1)},staticRenderFns:[]},R=a("VU/8")(E,z,!1,null,null,null).exports,N={components:{vlargetitle:y},data:function(){return{total:0,pageSize:8,currentPage:1,tableData:[],name:"本月出勤率统计表",cols:[{prop:"eid",label:"员工工号",width:240},{prop:"name",label:"员工姓名",width:200},{prop:"late",label:"迟到天数",width:120},{prop:"early",label:"早退天数",width:120},{prop:"absence",label:"缺勤天数",width:120}]}},methods:{readData:function(e){var t=this;this.axios.get("/FaceProject/admin/rate/queryall",{params:{pageIndex:e}}).then(function(e){t.total=e.data.page.pageNumber,t.currentPage=e.data.page.pageIndex,t.tableData=e.data.data,console.log(e.data.data)}).catch(function(e){console.log(e)})},handleChangePage:function(e){this.readData(e)}},mounted:function(){var e=(new Date).getMonth()+1;this.name=e+"月出勤率统计表",this.readData(1)}},O={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("vlargetitle",{attrs:{name:e.name}}),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:""}},e._l(e.cols,function(e,t){return a("el-table-column",{key:t,attrs:{prop:e.prop,label:e.label,"min-width":e.width}})})),e._v(" "),a("div",{staticClass:"block"},[a("el-pagination",{attrs:{"page-size":e.pageSize,layout:"prev, pager, next",total:e.total,"current-page":e.currentPage},on:{"current-change":e.handleChangePage}})],1)],1)},staticRenderFns:[]},q=a("VU/8")(N,O,!1,null,null,null).exports,L={data:function(){return{closeClick:!1,form:{eiId:0,eiName:"",ejProfession:""},formLabelWidth:"100px",options:[{value:"1",label:"员工"},{value:"2",label:"经理"},{value:"3",label:"副总经理"},{value:"4",label:"总经理"}]}},props:["updateData","dialogFormVisible"],methods:{handleVisble:function(){this.$emit("visble")},addForm:function(){this.form.eiId=this.updateData.eiId,this.form.eiName=this.updateData.eiName,this.form.ejProfession=this.updateData.ejProfession},handleUpdate:function(){var e=this;this.axios.post("/FaceProject/admin/employee/update",{id:this.form.eiId,name:this.form.eiName,job:this.form.ejProfession}).then(function(t){console.log(t),e.$emit("update")}).catch(function(e){console.log(e)})}},mounted:function(){}},B={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:"修改员工个人信息",visible:e.dialogFormVisible,"close-on-click-modal":e.closeClick},on:{"update:visible":function(t){e.dialogFormVisible=t},open:e.addForm}},[a("el-form",{attrs:{model:e.form}},[a("el-form-item",{attrs:{label:"员工工号","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off",disabled:!0},model:{value:e.form.eiId,callback:function(t){e.$set(e.form,"eiId",t)},expression:"form.eiId"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"员工姓名","label-width":e.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.eiName,callback:function(t){e.$set(e.form,"eiName",t)},expression:"form.eiName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"员工职位","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.form.ejProfession,callback:function(t){e.$set(e.form,"ejProfession",t)},expression:"form.ejProfession"}},e._l(e.options,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:e.handleVisble}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:e.handleUpdate}},[e._v("确 定")])],1)],1)],1)},staticRenderFns:[]},M={components:{vlargetitle:y,vupdateinfo:a("VU/8")(L,B,!1,null,null,null).exports},data:function(){return{total:0,pageSize:8,currentPage:1,tableData:[],updateData:[],name:"员工个人信息表",dialogFormVisible:!1,cols:[{prop:"eiId",label:"员工工号",width:200},{prop:"eiName",label:"员工姓名",width:100},{prop:"ejProfession",label:"职称",width:150}]}},mounted:function(){this.readData(1)},methods:{readData:function(e){var t=this;this.axios.get("/FaceProject/admin/employee/queryall",{params:{pageIndex:e}}).then(function(e){t.total=e.data.page.pageNumber,t.currentPage=e.data.page.pageIndex,t.tableData=e.data.data,console.log(e.data.data)}).catch(function(e){console.log(e)})},handleEdit:function(e,t){console.log(e,t),this.updateData=t,console.log(this.updateData),this.dialogFormVisible=!0},handleDelete:function(e,t){console.log(e,t)},handleVisble:function(){this.dialogFormVisible=!1},handleUpdate:function(){this.handleVisble(),this.readData()},handleChangePage:function(e){this.readData(e)}}},A={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("vlargetitle",{attrs:{name:e.name}}),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,border:""}},[e._l(e.cols,function(e,t){return a("el-table-column",{key:t,attrs:{prop:e.prop,label:e.label,"min-width":e.width}})}),e._v(" "),a("el-table-column",{attrs:{label:"操作","min-width":"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini"},on:{click:function(a){e.handleEdit(t.$index,t.row)}}},[e._v("编辑")])]}}])})],2),e._v(" "),a("vupdateinfo",{attrs:{updateData:e.updateData,dialogFormVisible:e.dialogFormVisible},on:{visble:e.handleVisble,update:e.handleUpdate}}),e._v(" "),a("div",{staticClass:"block"},[a("el-pagination",{attrs:{"page-size":e.pageSize,layout:"prev, pager, next",total:e.total,"current-page":e.currentPage},on:{"current-change":e.handleChangePage}})],1)],1)},staticRenderFns:[]},H=a("VU/8")(M,A,!1,null,null,null).exports,T=a("lNfz"),W=a.n(T);k.a.addPlugin(W.a);var J=function(){return k.a.get("admin_login_token")||""},G=a("DVuL"),K=a.n(G);n.default.use(l.a);var Q=new l.a({routes:[{path:"/",redirect:"/index/login",name:"home"},{path:"/index",component:u,redirect:"/index/login",children:[{path:"/index/login",component:h},{path:"/index/register",component:_},{path:"/index/adminlogin",name:"adminlogin",component:P}]},{path:"/admin",component:U,name:"admin",redirect:"/admin/employee",children:[{path:"/admin/employee",redirect:"/admin/employee/info",meta:{login:!0}},{path:"/admin/employee/info",component:H,meta:{login:!0}},{path:"/admin/attendance",redirect:"/admin/attendance/time",meta:{login:!0}},{path:"/admin/attendance/time",component:j,meta:{login:!0}},{path:"/admin/attendance/count",component:R,meta:{login:!0}},{path:"/admin/attendance/rate",component:q,meta:{login:!0}}]},{path:"*",redirect:{name:"home"}}]});Q.beforeEach(function(e,t,a){e.meta.login?J().id&&J().pwd?a():(Object(G.Message)("未登录，请先登录"),a({path:"/index",query:{redirect:e.path}})):a()});var X=Q,Y=a("aozt"),Z=a.n(Y),ee=a("mw3O"),te=a.n(ee);a("LicG");n.default.config.productionTip=!1,n.default.prototype.axios=Z.a,n.default.prototype.qs=te.a,n.default.use(K.a),new n.default({el:"#app",router:X,components:{App:o},template:"<App/>"})},arwl:function(e,t){},fIwp:function(e,t){},gKsD:function(e,t){},jlVs:function(e,t){},mETv:function(e,t){},uCrJ:function(e,t){}},["NHnr"]);
//# sourceMappingURL=app.1d990c49763777e54b74.js.map