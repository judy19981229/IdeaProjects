<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath = request.getScheme() + "://" +
request.getServerName() + ":" + request.getServerPort() +
request.getContextPath() + "/";
/**String id=request.getParameter("id");
String fullname=request.getParameter("fullname");
String appellation=request.getParameter("appellation");
String company=request.getParameter("company");
String owner=request.getParameter("owner");*/
%>
<html>
<head>
	<base href="<%=basePath%>">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>


<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
	$(function(){

		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});

		$("#isCreateTransaction").click(function(){
			if(this.checked){
				$("#create-transaction2").show(200);
			}else{
				$("#create-transaction2").hide(200);
			}
		});

		//放大镜图标打开市场活动的模态窗口
		$("#openSearchModalBtn").click(function(){
			$("#searchActivityModal").modal("show");
			$("#activityName").keydown(function(event){
				if(event.keyCode==13){//搜索市场活动
					$.get("workbench/clue/getActivityByName",
							"activityName="+$.trim($("#activityName").val()),
							rollback,"json");
					function rollback(param){
						var html="";
						$.each(param,function(index,dmoObj){
							html+='<tr>';
							html+='<td><input type="radio" value="'+dmoObj.id+'" name="select"/></td>';
							html+='<td id="'+dmoObj.id+'">'+dmoObj.name+'</td>';
							html+='<td>'+dmoObj.startDate+'</td>';
							html+='<td>'+dmoObj.endDate+'</td>';
							html+='<td>'+dmoObj.owner+'</td>';
							html+='</tr>';
						});
						$("#activitySearchBody").html(html);
					}
					return false;//防止敲完回车后模态窗口自动关闭
				}
			});
		});
		//提交选中的市场活动，在市场活动源处显示选中的市场活动信息
		$("#submitActivityBtn").click(function(){
			var id=$(":radio:checked").val();
			var name=$("#"+id).text();
			$("#aName").val(name);
			$("#activityId").val(id);
			$("#searchActivityModal").modal("hide");
		});

		//给转换按钮绑定事件，执行线索的转换操作
		$("#convertBtn").click(function(){
			//提交请求到后台，发出传统请求（不需要局部刷新）
			//请求结束后回到线索列表页，根据为客户创建交易有没有选中来判断是否创建交易
			if($("#isCreateTransaction").prop("checked")){
				//需要创建交易,参数太多，直接挂参数太麻烦了，使用提交交易表单
				//要传clueId activityId money name expectedDate stage
				//window.location.href= "workbench/clue/convert?clueId=&money=&expectedDate=&name=&stage=&source=&activityId="
				//在表单中写name/value属性提交，并且可以发出post请求
				$("#tranForm").submit();
			}else{
				//不用创建交易-只需要传clueId，用EL表达式从参数中取
				window.location.href=
						"workbench/clue/convert?clueId=${param.id}"
			}
		})

	});
</script>

</head>
<body>
	
	<!-- 搜索市场活动的模态窗口 -->
	<div class="modal fade" id="searchActivityModal" role="dialog" >
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">搜索市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input type="text" class="form-control" id="activityName" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="activitySearchBody">
							<%--<tr>
								<td><input type="radio" name="activity"/></td>
								<td>发传单</td>
								<td>2020-10-10</td>
								<td>2020-10-20</td>
								<td>zhangsan</td>
							</tr>
							<tr>
								<td><input type="radio" name="activity"/></td>
								<td>发传单</td>
								<td>2020-10-10</td>
								<td>2020-10-20</td>
								<td>zhangsan</td>
							</tr>--%>
						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="submitActivityBtn">提交</button>
				</div>
			</div>
		</div>
	</div>

	<div id="title" class="page-header" style="position: relative; left: 20px;">
		<h4>转换线索 <small>${param.fullname}${param.appellation}-${param.company}</small></h4>
	</div>
	<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
		新建客户：${param.company}
	</div>
	<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
		新建联系人：${param.fullname}${param.appellation}
	</div>
	<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
		<input type="checkbox" id="isCreateTransaction"/>
		为客户创建交易
	</div>
	<div id="create-transaction2" style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;" >
	
		<form id="tranForm" action="workbench/clue/convert" method="get">
			<input type="hidden" name="clueId" value="${param.id}" />
			<input type="hidden" name="flag" value="createTran">
		  <div class="form-group" style="width: 400px; position: relative; left: 20px;">
		    <label for="amountOfMoney">金额</label>
		    <input type="text" class="form-control" name="money" id="amountOfMoney">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tradeName">交易名称</label>
		    <input type="text" class="form-control" id="tradeName" name="name" value="">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="expectedClosingDate">预计成交日期</label>
		    <input type="text" class="form-control time" name="expectedDate" id="expectedClosingDate">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="stage">阶段</label>
		    <select id="stage"  name="stage" class="form-control">
		    	<option></option>
		    	<c:forEach items="${stage}" var="s" >
					<option value="${s.value}">${s.text}</option>
				</c:forEach>
		    </select>
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="aName">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" id="openSearchModalBtn" style="text-decoration: none;"><span class="glyphicon glyphicon-search"></span></a></label>
		    <input type="text" class="form-control" id="aName" placeholder="点击上面搜索" readonly>
		  	<input type="hidden" name="activityId" id="activityId" />
		  </div>
		</form>
		
	</div>
	
	<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
		记录的所有者：<br>
		<b>${param.owner}</b>
	</div>
	<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
		<input class="btn btn-primary" type="button" value="转换" id="convertBtn">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="btn btn-default" type="button" value="取消">
	</div>
</body>
</html>