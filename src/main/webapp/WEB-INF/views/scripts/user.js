/**
 * Created by shengdong on 2015/1/3.
 */
function ajaxVerifyEmail(urlPath) {
    $.ajax({
        url:urlPath,
        type:"GET",
        data:{"email":$("#email").val()},
        success:function(msg){
            if(msg == 0){
                $("#verifyEmail").html("邮件地址已经被使用");
                $("#verifyEmail").show();
            }else{
                $("#verifyEmail").hide();
            }
        },
        error:function(){

        }
    });
}

function ajaxVerifyUser(urlPath){
    result = false;
    $.ajax({
        url:urlPath,
        type:"GET",
        data:{"username":$("#username").val()},
        success:function(msg){
            if(msg == 0){
                $("#nameVerify_register").html("用户名已经被使用");
                $("#nameVerify_register").show();
                result = true;
            }else{
                $("#nameVerify_register").hide();
            }
        },
        error:function(){

        }
    });
    return result;
}