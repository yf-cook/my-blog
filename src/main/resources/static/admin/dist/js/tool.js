$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/tools/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'toolId', index: 'toolId', width: 50, key: true, hidden: true},
            {label: '工具名称', name: 'toolName', index: 'toolName', width: 100},
            {label: '工具url', name: 'toolUrl', index: 'toolUrl', width: 100},
            {label: '工具描述', name: 'toolDescription', index: 'toolDescription',width: 100}
        ],
        height: 560,
        rowNum: 10,
        rowList: [10,20,50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中。。。',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
});

/*jqGrid重新加载*/
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

/*添加数据*/
function toolAdd() {
    reset();
    $('.modal-title').html('在线工具添加');
    $('#toolModal').modal('show');
}

/*编辑数据*/
function toolEdit() {
    //获取选中数据的id值
    var id = getSelectedRow();
    if(id == null){
        return;
    }
    reset();
    //请求数据
    $.get("/admin/tools/info/" + id, function (r) {
        if(r.resultCode == 200 && r.data != null){
            //填充数据至模态框
            $("#toolName").val(r.data.toolName);
            $("#toolUrl").val(r.data.toolUrl);
            $("#toolDescription").val(r.data.toolDescription);
        }
    });
    $('.modal-title').html('在线工具编辑');
    $('#toolModal').modal('show');
    $('#toolId').val(id);
}

/*删除数据*/
function toolDelete() {
    var ids = getSelectedRows();
    if(ids == null){
        return;
    }
    swal({
        title: '确认弹框',
        text: '确认要删除数据吗？',
        icon: 'warning',
        buttons: true,
        dangerMode: true
    }).then((flag) => {
        if(flag){
            $.ajax({
                type: "POST",
                url: "/admin/tools/delete",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (r) {
                    if(r.resultCode == 200){
                        swal('删除成功',{
                            icon: 'success'
                        });
                        $('#jqGrid').trigger('reloadGrid');
                    }else {
                        swal(r.message, {
                            icon: 'error'
                        });
                    }
                }
            });
        }
    })
}

/*绑定模态框的保存按钮*/
$('#saveButton').click(function () {
    var toolId = $('#toolId').val();
    var toolName = $('#toolName').val();
    var toolUrl = $('#toolUrl').val();
    var toolDescription = $('#toolDescription').val();
    //判断是否为空值和数据是否合法
    if(!validCN_ENString2_18(toolName)){
        $('#edit-error-msg').css('display', 'block');
        $('#edit-error-msg').html('请输入符合规范的工具名称');
        return;
    }
    if(!isURL(toolUrl)){
        $('#edit-error-msg').css('display', 'block');
        $('#edit-error-msg').html('请输入符合规范的工具链接');
        return;
    }
    if(!validCN_ENString2_18(toolDescription)){
        $('#edit-error-msg').css('display', 'block');
        $('#edit-error-msg').html('请输入符合规范的工具描述');
        return;
    }

    var url = '/admin/tools/update';
    var params = $('#toolForm').serialize();
    //判断id是否有值
    if(toolId == null || toolId < 1){
        url = '/admin/tools/save';
    }

    $.ajax({
        type: 'POST',
        url: url,
        data: params,
        success: function (r) {
            if(r.resultCode == 200 && r.data != null){
                $('#toolModal').modal('hide');
                swal('保存成功', {
                    icon: 'success'
                });
                reload();
            }else {
                $('#toolModal').modal('hide');
                swal('保存失败', {
                    icon: 'error'
                });
            }
        },
        error: function () {
            swal('操作失败', {
                icon: 'error'
            });
        }
    });


})


function reset() {
    $("#toolName").val('');
    $("#toolUrl").val('');
    $("#toolDescription").val('');
    $('#edit-error-msg').css("display", "none");
}