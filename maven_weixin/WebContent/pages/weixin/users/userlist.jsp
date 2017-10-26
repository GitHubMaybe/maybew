<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
    String path = request.getContextPath();
    String basePath= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<html>
<head>
<meta charset="utf-8">
<base href="<%=basePath%>"  />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />

    
<title>用户列表</title>
<script type="text/javascript">
		function delForm(uid){
			document.delUsersForm.action="users/"+uid;
			document.delUsersForm.submit();
		}
	</script>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"><a href="javascript:;" onclick="admin_add('添加用户','pages/weixin/users/useradd.jsp','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加用户</a> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="12">用户列表</th>
			</tr>
			<tr class="text-c">
				<th width="100">ID</th>
				<th width="40">姓名</th>
				<th width="40">省份</th>
				<th width="40">城市</th>
				<th width="80">手机号</th>
				<th width="80">邮箱</th>
				<th width="60">角色</th>
				<th width="50">用户状态</th>
				<th width="100">注册时间</th>
				<th width="100">微信ID</th>
				<th width="60">操作</th>
			</tr>
	<c:forEach items="${list}" var="users">
		<tr>
			<td>${users.uid}</td>
			<td>${users.uname}</td>
			<td>${users.province}</td>
			<td>${users.city}</td>
			<td>${users.telphone}</td>
			<td>${users.email}</td>
			<td>${users.rolesName}</td>
			<td>${users.status}</td>
			<td>${users.currDate}</td>
			<td>${users.wid}</td>
			<td><a href="javascript:delForm('${users.uid}')" onclick="return confirm('是否确定删除？');">删除</a>
			    &nbsp;&nbsp; &nbsp;&nbsp;<a href="users/${users.uid}/1">更新</a> 
		   </td>
		</tr>
	</c:forEach>
	</table>
	<form name="delUsersForm" action="users/1" method="post">
	<input type="hidden" name="_method" value="DELETE">
</form>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
 // function admin_del(){
	//	layer.confirm('确认要删除吗？',function(){
		//	$.ajax({
		//		type: 'POST',
		//		url: 'users/delete',
		//		dataType: 'json',
			//	success: function(data){
			//		$(uid).parents("tr").remove();
		//		},
			//	error:function(data) {
				//	console.log(data.msg);
			//	},
		//	});		
	//	});
//}

</script>
</body>
</html>