layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


 /*   $("#refreshCaptcha").click(function (){
        $(this).attr("src", "/image?"+Math.floor(Math.random() * 100));
    })*/



    // 进行登录操作
    form.on('submit(login)', function (data) {
        data = data.field;
       /* if ( data.userName =="undefined" || data.username =="" || data.username.trim()=="") {
            layer.msg('用户名不能为空');
            return false;
        }
        if ( data.password =="undefined" || data.password =="" || data.password.trim()=="")  {
            layer.msg('密码不能为空');
            return false;
        }*/

        $.ajax({
            type:"post",
            url:ctx+"/user/login",
            data:{
                userName:data.username,
                password:data.password,

               //rememberMe:$("#rememberMe").is(":checked")
            },
            dataType:"json",
            success:function (result) {
                if(result.code==200){
                    layer.msg('登录成功', function () {
                        if ($("#rememberMe").prop("checked")){
                            $.cookie("userId",result.result.userId,{expires:7});
                            $.cookie("userName",result.result.userName,{expires:7});
                            $.cookie("trueName",result.result.trueName,{expires:7});
                        }else{
                            $.cookie("userId",result.result.userId);
                            $.cookie("userName",result.result.userName);
                            $.cookie("trueName",result.result.trueName);
                        }


                        window.location.href=ctx+"/main";
                    });
                }else {
                    //登录失败
                    layer.msg("登录失败！")
                }
            }
        });
        return false;
    });
});