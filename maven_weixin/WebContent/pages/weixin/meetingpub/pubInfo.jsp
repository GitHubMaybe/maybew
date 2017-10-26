<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String uid=request.getParameter("uid");
%>
<!DOCTYPE html>
<html>
	<head>
	<base href="<%=basePath%>">
		<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
				
		<title></title>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<link href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.theme.css" rel="stylesheet">
		
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		
	</head>
	<body>
		
<div data-role="page" id="pageDetail" style="background-color: white;">
	<div data-role="header" data-theme="c" style="background-color: #4E90C7;" data-position="fixed">
		<a href="javascript:history.go(-1)"  data-role="button" >返回</a>
	    <h1>会议详情</h1>
	</div>
	<div id="one" class="ui-body-d ui-content" style="padding:1em 0 1em 0;">
		<div style="padding-right:15px;padding-left:15px">
			<label for="pcode" class="font-label">会议编号</label>
			<input type="text" id="pcode" value="${meetingPub.pcode}" class="font-blue input-lightblue" readonly="readonly">
		</div>

		<div style="padding-right:15px;padding-left:15px">
			<label for="ptitle" class="font-label">会议标题</label>
			<input type="text" id="ptitle" value="${meetingPub.ptitle }" class="font-blue input-lightblue" readonly="readonly">
		</div>
				<div class="ui-grid-a" style="padding-right:15px;padding-left:15px;">
			<div class="ui-block-a" style="padding-right:10px">
					<label for="ptype" class="font-label">会议类型</label>
		    		<input type="text" value="${meetingPub.ptype }"  id="ptype" class="font-blue input-lightblue" readonly="readonly" />
		    </div>
			<div class="ui-block-b" style="padding-left:10px">
				<label for="ptime" class="font-label">开会时间 </label>
	    		<input type="text" value="${meetingPub.ptime }"  id="ptime" class="font-blue input-lightblue" readonly="readonly" />
			</div>	
		</div>
		<div style="padding-right:15px;padding-left:15px">
			<label for="premark" class="font-label">会议描述</label>
			<input type="text" name="premark" id="meeting_date" value="${meetingPub.premark }"  class="required font-blue input-lightblue" readonly="readonly">
		</div>


		
	</div>
</div>		
		
	</body>
</html>
