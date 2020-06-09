

function commentAdd() {
    reset();
    $('.modal-title').html('添加评论');
    $('#commentModal').modal('show');
}

$('#commentSubmit').click(function () {
    var blogId = $('#blogId').val();
    var verifyCode = $('#verifyCode').val();
    var commentator = $('#commentator').val();
    var email = $('#email').val();
    var websiteUrl = $('#websiteUrl').val();
    var commentBody = $('#commentBody').val();
    if (isNull(blogId)) {
        swal("参数异常", {
            icon: "warning",
        });
        return;
    }
    if (isNull(commentator) || !validCN_ENString2_100(commentator)) {
        swal("请输入正确的称呼", {
            icon: "warning",
        });
        return;
    }
    if (isNull(email) || !validEmail(email)) {
        swal("请输入正确的邮箱", {
            icon: "warning",
        });
        return;
    }
    if (isNull(verifyCode)) {
        swal("请输入验证码", {
            icon: "warning",
        });
        return;
    }

    var data = {
        "blogId": blogId, "verifyCode": verifyCode, "commentator": commentator,
        "email": email, "websiteUrl": websiteUrl, "commentBody": commentBody
    };
    console.log(data);
    $.ajax({
        type: 'POST',//方法类型
        url: '/blog/comment',
        data: data,
        success: function (result) {
            if (result.resultCode == 200) {
                $('#commentModal').modal('hide');
                swal("评论提交成功请等待博主审核", {
                    icon: "success",
                });
                $('#commentBody').val('');
                $('#verifyCode').val('');
            }
            else {
                $('#commentModal').modal('hide');
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});

function reset() {
    $('#verifyCode').val('');
    $('#commentator').val('');
    $('#email').val('');
    $('#websiteUrl').val('');
    $('#commentBody').val('');
}