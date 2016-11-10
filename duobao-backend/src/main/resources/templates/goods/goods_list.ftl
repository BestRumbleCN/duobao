<#-- @ftlvariable name="goodsType" type="team.wuxie.crowdfunding.domain.TGoodsType" -->

<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.goods_list")>

<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="row">
			<div class="col-lg-12">
				<div class="navbar navbar-default bootstrap-admin-navbar-thin">
					<ol class="breadcrumb bootstrap-admin-breadcrumb">
						<li><a href="#">首页</a></li>
						<li class="active"><a href="#">财务信息</a></li>
					</ol>
				</div>
			</div>
		</div>
    <div class="x_panel">
        <div class="x_title">
	        <form role="form" class="form-horizontal">
				<div class="form-group">
					<label for="sGoodsName" class="col-sm-1 control-label">
						商品名称: </label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="sGoodsName"
							name="title" />
					</div>
					<div class="col-sm-1">
						<label for="sGoodsStatus" class=" control-label">
							状态:</label>
					</div>
					<div class="col-sm-2">
						<select id="sGoodsStatus" class="form-control">
							<option value="">全部</option>
							<option value="0">下架</option>
							<option value="1">上架</option>
						</select>
					</div>
					<div class="col-sm-2">
					<button type="button" class="btn btn-info" id="sSearch">查询</button>
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-info" data-toggle="modal" data-target="#modal_create" title="<@spring.message "page.add"/>">添加商品</button>
					</div>
				</div>
			</form>
		</div>
        <div class="x_content">
            <table id="dataTable-goods" class="table table-striped table-bordered dt-responsive nowrap"
                   cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><@spring.message "tableHeader.goods_id"/></th>
                    <th><@spring.message "tableHeader.goods_name"/></th>
                    <th><@spring.message "tableHeader.single_price"/></th>
                    <th><@spring.message "tableHeader.total_amount"/></th>
                    <th><@spring.message "tableHeader.type_name"/></th>
                    <th><@spring.message "tableHeader.status"/></th>
                    <th><@spring.message "tableHeader.operation"/></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="modal_create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center">
					<@spring.message "page.add"/>
                </h4>
            </div>
            <div class="modal-body">
                <form id="form_create-goods" class="form-horizontal form-label-left submit-form">
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
                               for="goodsName"><@spring.message "page.goods_name"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <input type="text" id="goodsName" name="goodsName" class="form-control col-md-7 col-xs-12"
                                   placeholder="<@spring.message "page.goods_name"/>" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
                               for="singlePrice"><@spring.message "page.goods_single_price"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <input type="number" id="singlePrice" name="singlePrice" class="form-control col-md-7 col-xs-12"
                                   placeholder="<@spring.message "page.goods_single_price"/>" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
                               for="totalAmount"><@spring.message "page.goods_total_amount"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <input type="number" id="totalAmount" name="totalAmount" class="form-control col-md-7 col-xs-12"
                                   placeholder="<@spring.message "page.goods_total_amount"/>" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
                               for="channel"><@spring.message "page.goods_channel"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <select id="channel" name="channel" class="form-control col-md-7 col-xs-12" required>
                                <option value="0">普通</option>
                                <option value="1">爆款</option>
                                <option value="2">新货</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
                               for="typeId"><@spring.message "page.type_name"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <select id="typeId" name="typeId" class="form-control col-md-7 col-xs-12" required>
                                <#list goodsTypes as goodsType>
                                    <option value="${goodsType.typeId}">${goodsType.typeName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
                               for="img"><@spring.message "page.goods_img"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <input type="file" multiple id="pic" name="pic" class="form-control col-md-7 col-xs-12 file-loading" data-fv-notempty="false"
                                   required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
                               for="statement"><@spring.message "page.goods_statement"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <textarea rows="5" id="statement" name="statement" data-fv-notempty="false" class="form-control col-md-7 col-xs-12"
                                   placeholder="<@spring.message "page.goods_statement"/>" required></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal"><@spring.message "page.close"/></button>
                        <button type="button" id="create-user-btn"
                                class="btn btn-primary submit-btn"><@spring.message "page.save"/></button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<div class="modal fade" id="modal_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center">
					<@spring.message "page.edit"/>
                </h4>
            </div>
            <div class="modal-body">
                <form role="form" class="form-horizontal submit-form" id="form_update-goods"
                      action="${requestContext.contextPath}/goods" method="post" enctype="multipart/form-data">

                    <input hidden id="typeId" name="typeId" value="" title="">

                    <div class="form-group">
                        <label for="goodsName"
                               class="col-sm-2 control-label"><@spring.message "page.goods_name"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-sm-10">
                            <input class="form-control col-sm-22" id="goodsName" name="goodsName" required
                                   placeholder="<@spring.message "page.goods_name"/>">
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal"><@spring.message "page.close"/></button>
                        <button type="submit" id="update-user-btn"
                                class="btn btn-primary submit-btn"><@spring.message "page.save"/></button>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

</@layout.main>
<script src="${requestContext.contextPath}/static/js/modules/goods.js" type="text/javascript" charset="UTF-8"></script>

