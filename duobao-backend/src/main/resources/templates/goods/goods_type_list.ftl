<#import '../common/layout.ftl' as layout>

<@layout.main pageTitle=springMacroRequestContext.getMessage("pageTitle.goods_type_list")>

<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2><@spring.message "pageTitle.goods_type_list"/></h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <i class="fa fa-wrench"></i></a>
                </li>
                <li><a href="#" data-toggle="modal" data-target="#modal_create" title="<@spring.message "page.add"/>">
                    <i class="fa fa-plus-circle"></i></a>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <table id="dataTable-goodsType" class="table table-striped table-bordered dt-responsive nowrap"
                   cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><@spring.message "tableHeader.type_id"/></th>
                    <th><@spring.message "tableHeader.type_name"/></th>
                    <th><@spring.message "tableHeader.type_img"/></th>
                    <th><@spring.message "tableHeader.status"/></th>
                    <th><@spring.message "tableHeader.create_time"/></th>
                    <th><@spring.message "tableHeader.operation"/></th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="modal_create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel" style="text-align: center">
					<@spring.message "page.add"/>
                </h4>
            </div>
            <div class="modal-body">
                <form id="form_create-goodsType" class="form-horizontal form-label-left submit-form"
                      action="${requestContext.contextPath}/goodsTypes" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
                               for="typeName"><@spring.message "page.type_name"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <input type="text" id="typeName" name="typeName" class="form-control col-md-7 col-xs-12"
                                   required placeholder="<@spring.message "page.type_name"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-2 col-sm-2 col-xs-12"
                               for="typeImg"><@spring.message "page.type_img"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-md-10 col-sm-10 col-xs-12">
                            <input type="file" id="typeImg" name="file" class="form-control col-md-7 col-xs-12"
                                   required placeholder="<@spring.message "page.type_img"/>">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal"><@spring.message "page.close"/></button>
                        <button type="submit" id="create-user-btn"
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
                <form role="form" class="form-horizontal submit-form" id="form_update-goodsType"
                      action="${requestContext.contextPath}/goodsTypes" method="post" enctype="multipart/form-data" >

                    <input hidden id="typeId" name="typeId" value="" title="">

                    <div class="form-group">
                        <label for="typeName"
                               class="col-sm-2 control-label"><@spring.message "page.type_name"/> <span
                                class="required">*</span>
                        </label>

                        <div class="col-sm-10">
                            <input class="form-control col-sm-22" id="typeName" name="typeName" required
                                   placeholder="<@spring.message "page.type_name"/>">
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
<script src="${requestContext.contextPath}/static/js/goods-type.js" type="text/javascript" charset="UTF-8"></script>

