$(function () {
    var storage=window.localStorage;
    init(storage["id"]);
});


function init(id) {
    console.log(id);
    $.ajax({
        type: "get",
        url: "/user/"+id+".do",
        data: {
        },
        dataType: "json",
        success: function (data) {
            console.log(data);

            if(data.code == "1"){
                $("#nickName").html(data.user.nickName);
                $("#userName").html(data.user.userName);
                $("#phone").html(data.user.phone);
            }
            else{

            }
        },
        error: function () {
            alert("连接失败");
        }
    });

}