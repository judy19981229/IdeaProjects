<%
String basePath = request.getScheme() + "://" +
request.getServerName() + ":" + request.getServerPort() +
request.getContextPath() + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="<%=basePath%>">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>
<script type="text/javascript">

	$(function(){

		//日期时间选择器
		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});

		//创建市场活动
		$("#addBtn").click(function(){
			$.get("workbench/activity/getUserList",rollback,"json");
			function rollback(param){
				var html="<option></option>"
				$.each(param,function(index,dmoObj){
					html+="<option value="+dmoObj.id+">"+dmoObj.name+"</option>"
					$("#create-owner").html(html);
				});
				//将当前登陆的用户，设置为下拉框的默认选项
				/**
				 * <select id="create-marketActivityOwner">
				 * 		<option value="06f5fc056eac41558a964f96daa7f27c">张三</option>
				 * 		<option value="40f6cdea0bd34aceb77492a1656d9fb3">李四</option>
				 * </select>
				 */
				$("#create-owner").val("${user.id}");
				$("#createActivityModal").modal("show");
			}
		});

		//保存创建的市场活动
		$("#saveBtn").click(function(){
			$.post("workbench/activity/save",
					{	//传递的参数需要去掉前后空格
						"owner":$.trim($("#create-owner").val()),
						"name":$.trim($("#create-name").val()),
						"startDate":$.trim($("#create-startDate").val()),
						"endDate":$.trim($("#create-endDate").val()),
						"cost":$.trim($("#create-cost").val()),
						"description":$.trim($("#create-description").val()),
					},
					function(param){
						if(param.success){
							//添加成功后，刷新市场活动信息列表（局部刷新）
							pageList(1,2);
							//清空添加操作模态窗口中的数据,不然下次点开还是这次输入的内容
							$("#createActivityForm")[0].reset();
							//关闭添加操作的模态窗口-modal方法 show打开 hide关闭
							$("#createActivityModal").modal("hide");
						} else{
							alert(param.message);
						}
					}, "json");
		});

		//页面加载完毕后触发一个方法，进行分页操作，基础组件就是pageNo和pageSize
		pageList(1,2);
		//为查询按钮绑定事件，触发pageList
		$("#searchBtn").click(function(){
			//查询之前把查询的条件存在隐藏域中
			$("#hidden-name").val($.trim($("#search-name").val()));
			$("#hidden-owner").val($.trim($("#search-owner").val()));
			$("#hidden-startDate").val($.trim($("#search-startDate").val()));
			$("#hidden-endDate").val($.trim($("#search-endDate").val()));
			pageList(1,2);
		});

		//pageNo：当前页的页码，pageSize：每页展现的记录数 这两个不可或缺
		//pageList方法：发出ajax请求到后台，从后台获取最新的市场活动信息列表数据
		//通过响应回来的数据，局部刷新市场活动信息列表
		//需要刷新的情况：一共六种情况 workbench/activity/pageList
		//点击左侧菜单的市场活动超链接(页面加载)、添加、修改、删除、查询操作后，点击下面的分页组件
		function pageList(pageNo,pageSize){
			//每次查询之后，都默认设置全选框的状态为全不选
			$("#selectAll").prop("checked",false);
			//查询前，将隐藏域中保存的信息取出来，重新赋予到搜索框中
			$("#search-name").val($.trim($("#hidden-name").val()));
			$("#search-owner").val($.trim($("#hidden-owner").val()));
			$("#search-startDate").val($.trim($("#hidden-startDate").val()));
			$("#search-endDate").val($.trim($("#hidden-endDate").val()));
			$.get("workbench/activity/pageList",
					{
						"pageNo":pageNo,
						"pageSize":pageSize,
						"name":$.trim($("#search-name").val()),
						"owner":$.trim($("#search-owner").val()),
						"startDate":$.trim($("#search-startDate").val()),
						"endDate":$.trim($("#search-endDate").val())
					},
					rollback, "json");
			//接收的参数为一个市场活动集合和查询结果的总条数
			// {"activityList":activityList,"total":total}
			function rollback(param){
				//需要对市场活动集合进行遍历，把遍历结果填充到table中
				var html="";
				$.each(param.dataList,function(index,dmoObj){
					html+='<tr class="active">';
					html+='<td><input type="checkbox" name="select" /></td>';
					html+='<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/activity/detail.jsp\';">'+dmoObj.name+'</a></td>';
					html+='<td>'+dmoObj.owner+'</td>';
					html+='<td>'+dmoObj.startDate+'</td>';
					html+='<td>'+dmoObj.endDate+'</td>';
					html+='</tr>';
				});
				$("#activityBody").html(html);
				//计算总页数,向上取整
				var totalPages=Math.ceil(param.total/pageSize);
				//数据处理完毕后，结合分页查询，对前端展现分页信息
				$("#activityPage").bs_pagination({
					currentPage: pageNo, // 页码
					rowsPerPage: pageSize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: totalPages, // 总页数
					totalRows: param.total, // 总记录条数

					visiblePageLinks: 3, // 显示几个卡片

					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,

					//该回调函数在点击分页组件触发
					onChangePage : function(event, data){
						pageList(data.currentPage , data.rowsPerPage);
					}
				});
			}

		}
		//动态生成的元素，没法使用正常的click事件，要用on方法的形式来触发事件
		//语法： $(绑定元素的有效的外层元素).on(绑定事件，$(绑定元素),回调函数)
		//有效的外层元素：非动态生成的元素,$("#activityBody")
		$("#activityBody").on("click",$(":checkbox[name=select]"),
			function(){
				var num=$(":checkbox[name=select]:checked").length;
				var total=$(":checkbox[name=select]").length
				if(total==num){
				$("#selectAll").prop("checked",true);
				} else{
				$("#selectAll").prop("checked",false);
				}
		});

		$("#selectAll").click(function(){
			var flag=$("#selectAll").prop("checked");
			$(":checkbox[name=select]").prop("checked",flag);
		});
	});


</script>
</head>
<body>
	<input type="hidden" id="hidden-owner"/>
	<input type="hidden" id="hidden-name"/>
	<input type="hidden" id="hidden-startDate"/>
	<input type="hidden" id="hidden-endDate"/>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" id="createActivityForm">
					
						<div class="form-group">
							<label for="create-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">
									<option></option>
								</select>
							</div>
                            <label for="create-name" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate">
							</div>
							<label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endDate">
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<!--data-dismiss="modal"：关闭模态窗口-->
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">
								  <option>zhangsan</option>
								  <option>lisi</option>
								  <option>wangwu</option>
								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-startTime" value="2020-10-10">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-endTime" value="2020-10-20">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="search-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="search-owner">
				    </div>
				  </div>

				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control time" type="text" id="search-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control time" type="text" id="search-endDate">
				    </div>
				  </div>
				  
				  <button type="button" id="searchBtn" class="btn btn-default">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
					<!--点击创建按钮，观察两个属性和属性值
					data-toggle="modal"表示触发该按钮，将要打开一个模态窗口
					data-target="#createActivityModal"表示要打开哪个模态窗口，通过#id的形式找到该窗口
					现在我们是以属性和属性值的方式写在了button元素中，用来打开模态窗口，但是这样做是有问题的
					问题在于没有办法对按钮的功能进行扩充
					所以未来的实际项目开发，对于模态窗口的操作，一定不要写死在元素当中，应该由我们自己写js代码操控，
					把data-toggle和data-target删掉，加一个id
					-->
				  <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="selectAll" /></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">
		<%--				<tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
						</tr>
                        <tr class="active">
                            <td><input type="checkbox" /></td>
                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
                            <td>2020-10-10</td>
                            <td>2020-10-20</td>
                        </tr>--%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">
				<div id="activityPage"></div>
			</div>
			
		</div>
		
	</div>
</body>
</html>