<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<title></title>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<link href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.theme.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-validate/1.16.0/jquery.validate.js"></script>
		<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.js"></script>
		<script src="js/jquerymobile_popup.js"></script>
	</head>
	<body>
	
<script type="text/javascript">
function login(){
	var emailcode=$("#inviteCode").val();
	if(emailcode.length>0){
		var  openid=$("#oid").val();
		$.ajax({
			type:"POST",
			url:"users/login",
			data:{"openid":openid,"email":emailcode},
			success:function(msg){
				alert(msg);
				if(msg=='0'){
					openPopup('未关注','提示', undefined,true,undefined,'error','cn');
					return;
				}else if(msg=='2'){
					openPopup('邮箱没有认证','提示', undefined,true,undefined,'error','cn');
					return;
				}else if(msg=='3'){
					openPopup('已被绑定','提示', undefined,true,undefined,'error','cn');
				}else{
					openPopup('绑定成功','提示', undefined,true,undefined,'error','cn');
					WeixinJSBridge.call('closeWindow');
				}
			}
		});
	}

}
</script>
 <style type="text/css">
	.ui-page{
	     background:#eee;
	}
</style>
</head>
<body>
<div data-role="page" id="pageMain" style="background-color: #4E90C7;">
  <div style="text-align:center;"><img src="images/dada_logo1.png" style="width: 100%;"></div>
	  		
  <div data-role="content" class="content">
        <form id="login_params" method="post" >
       <div>
       <input type="text" id="oid" value="${openid} "/>
		    <input type="text" id="inviteCode" name="meetingCodes" placeholder="公司邮箱" style="background: url('${pageContext.request.contextPath}/images/ic_mail.png') no-repeat 5px;background-size: 30px;padding-left:40px;">
	  	</div>
	  	<div align="center" style="padding-top:30px;">
			<input id="pubBtn" type="button" value="登录"  onclick="login();" style="padding:10px;background: #E57330;text-shadow: none;opacity:100;color:white;font-size:20px;text-indent:0px;font-family:微软雅黑;border: none;-webkit-appearance:none;-moz-appearance:none;width: 100%;border-radius:7px;" >
	  </div>
  	 </form>
  </div>
</div>
	</body>
</html>
