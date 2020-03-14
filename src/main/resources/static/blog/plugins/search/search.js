$(function () {
    $('#searchbox').keypress(function (e) {
        var key = e.which; //e.which是按键的值
        if (key == 13) {
            var q = $(this).val();
            if(q.length>20){
                //swal()样式是sweetalert提供的样式，需要引入期css样式和js文件
                swal("搜索长度过大，请控制在20个字节以内",{
                    icon:"error",
                });
                return false;
            }
            if (q && q != '') {
                window.location.href = '/search/' + q;
            }
        }
    });
});

function search() {
    var q = $('#searchbox').val();
    if(q.length>20){
        swal("搜索长度过大，请控制在20个字节以内",{
            icon:"error",
        });
        return false;
    }
    if (q && q != '') {
        window.location.href = '/search/' + q;
    }
}