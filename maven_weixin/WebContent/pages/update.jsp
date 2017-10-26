<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%
    String path = request.getContextPath();
    String basePath= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">

		<title></title>
				<script type="text/javascript">
			window.onload = function() {
				//绑定事件
				var provinceElement = document.getElementsByClassName("province")[0];
				var cityElement = document.getElementsByClassName("city")[0];
				//onchange 事件会在域的内容改变时发生。
                //onchange 事件也可用于单选框与复选框改变后触发的事件。
				provinceElement.onchange = function() {
					//取值
					var selectvalue = provinceElement.value;
					
					if(selectvalue == "jx") {
					 cityElement.innerHTML= '<option value="nc">南昌</option><option value="jj">九江</option><option value="gz">赣州</option><option value="ja">吉安</option>';
					}else if (selectvalue=="gd") {	
					 cityElement.innerHTML=	'<option value="gz">广州</option><option value="sz">深圳</option><option value="dg">东莞</option><option value="fs">佛山</option>';
					}else if (selectvalue=="zj") {
					 cityElement.innerHTML=	'<option value="hz">杭州</option><option value="wz">温州</option><option value="nb">宁波</option><option value="tz">台州</option>';
					}else if(selectvalue=="js"){
					 cityElement.innerHTML=	'<option value="nj">南京</option><option value="sq">宿迁</option><option value="wx">无锡</option><option value="sz">苏州</option>';
					}		        
				}
			}
		</script>
		<style type="text/css">
			#box{
				width: 500px;
				height: 600px;
				margin: auto;
			background: #ccc;}
		</style>
	</head>

	<body>
		<div id="box">
			
		
	<form action="users/update" method="post" class="form form-horizontal" id="form-admin-add">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>姓名：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${users.uname }" placeholder="" id=" uname" name="uname">
		</div>
	</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>省份：</label>
		<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="province" name="province" size="1" value="${users.province }">
			<option value="jx">江西</option>
			<option value="gd">广东</option>
			<option value="zj">浙江</option>
			<option value="js">江苏</option>
			</select>
			</span> </div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>城市：</label>
		<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="city" name="city" size="1" value="${users.city">
			<option value="nc">南昌</option>
			<option value="jj">九江</option>
			<option value="gz">赣州</option>
			<option value="ja">吉安</option>
			</select>
			</span> </div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机号：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" value="value="${users.telphone }"" placeholder="请输入手机号码" id="phone" name="telphone">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" value="${users.email }" placeholder="请输入邮箱" id="email" name="email">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色：</label>
		<div class="formControls col-xs-8 col-sm-9 skin-minimal">
			<div class="radio-box" value="${users.rolesName }">
				<input name="rolesName" type="radio" id="sex-1" value="发单人员">
				<label for="sex-1">发单人员</label>
			</div>
			<div class="radio-box" value="${users.rolesName }">
				<input type="radio" id="sex-2" name="rolesName"  value="抢单人员">
				<label for="sex-2">抢单人员</label>
			</div>
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户状态：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${users.status }" placeholder="" id="phone" name="status">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>注册时间：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="date" name="currDate" id="" value="${users.currDate }" />
		</div>
	</div>

	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
	</div>
	</body>
</html>