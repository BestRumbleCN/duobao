var userTable = null;
var userMap = {};
$(function(){
	userTable = $('#datatable-user')
	.dataTable(
			{
				"processing" : true,
				"serverSide" : true,
				"lengthChange" : false,
				"filter" : false,
				"sort" : false,
				"stateSave" : false,
				"displayStart" : 0,
				"pageLength" : 10,
				"language" : { // 汉化
					"sZeroRecords" : "没有检索到数据",
					"sInfo" : "显示第 _START_ 到第 _END_ 条数据；总共有 _TOTAL_ 条记录",
					"sInfoEmtpy" : "没有数据",
					"sProcessing" : "正在加载数据...",
					"oPaginate" : {
						"sPrevious" : "前页",
						"sNext" : "后页"
					}
				},
				"ajax" : {
					"url" : "/user/list",
					"data" : function(d) {
						// 添加额外的参数传给服务器
					//	d.cellphone = $("#sCellphone").val();
					//	d.status = $("#sStatus").val();
					}
				},
				"aoColumns" : [ // 设定各列宽度
								{
									"mDataProp" : "userName",
									"sWidth" : "15%"
								},
								{
									"mDataProp" : "points",
									"sWidth" : "15%"
								},
								{
									"mDataProp" : "remark1",
									"sWidth" : "15%"
								},
								{
									"mDataProp" : "remark2",
									"sWidth" : "10%"
								},
								{
									"mDataProp" : "remark3",
									"sWidth" : "10%"
								},
								{
									"mDataProp" : "status",
									"sWidth" : "10%",
									"fnCreatedCell" : function(nTd, sData,
											oData, iRow, iCol) {
										if (oData.status == 1) {
											$(nTd).html(
													"<span class='label label-info'>正常</span>");
										} else{
											$(nTd).html("<span class='label label-danger'>禁用</span>");
										}

									}
								},
								{
									"mDataProp" : "id",
									"fnCreatedCell" : function(nTd, sData,
											oData, iRow, iCol) {
										userMap[oData.id] = oData;
										if (oData.status == 1) {
										$(nTd).html("<button class='btn btn-sm btn-primary edit-button' onclick='showModal("
																+ oData.id
																+ ")'><i class='glyphicon glyphicon-pencil'></i> 修改</button>"
																+"<button class='btn btn-sm btn-danger' onclick='changeStatus("
																	+ oData.id
																	+ ",2)'><i class='glyphicon glyphicon-remove'></i> 禁用</button>");
										}else{
										$(nTd).html("<button class='btn btn-sm btn-primary edit-button' onclick='showModal("
													+ oData.id
													+ ")'><i class='glyphicon glyphicon-pencil'></i> 修改</button>"
													+"<button class='btn btn-sm btn-success' onclick='changeStatus("
														+ oData.id
														+ ",1)'><i class='glyphicon glyphicon-ok'></i> 启用</button>");
										}
									}
								} ]
					});
})