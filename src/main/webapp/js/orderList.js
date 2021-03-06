$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    //读取local
    var storage=window.localStorage;

    //删除
    $("#btn_delete").click(function (){
        var temp= $("#orderTable").bootstrapTable('getSelections');
        if(temp.length<=0) {
            alert("请至少选中一行")
        } else {
            var putTemp = new Array();
            var content = "";
            for(var i=0;i<temp.length;i++){
                content += "ids="+temp[i].id+"&";
            }
            $.ajax({
                type: "delete",
                url: "/api/order/deleteByIds.do?"+ content,
                /* data: {"ids": bb},*/
                dataType: "json",
                success: function(data) {
                    if(data.status == "1"){
                        alert("删除成功");
                        window.location.reload();
                    }
                    else{
                        alert("删除失败");
                    }
                },
                error: function() {
                    alert("连接失败");
                }
            });
        }
    });

    //查询
    $("#btn_query").click(function (){

        $.ajax({
            type: 'get',
            url: "/api/order/get.do",
            dataType: 'json',
            data: {
                limit: 10,   //页面大小
                curPage: 1,  //页码
                nickName: $("#nickName").val(),
                goodsName:$("#findGoodsName").val()
            },
            success: function(data){
                console.log(data);
                $('#orderTable').bootstrapTable('removeAll');
                $('#orderTable').bootstrapTable('append', data.list);

            }
        })
    });

    //修改
    $("#btn_edit").click(function(){
        var temp= $("#orderTable").bootstrapTable('getSelections');
        if(temp.length<=0){
            alert("请至少选中一行");
        }else if(temp.length==1){

            $("#orderDetail").modal({show:true});

            $.ajax({
                type:"post",
                url:"/city/findCityAll.do",
                async:true,
                dataType: "json",
                success:function (data) {
                    var str = "";
                    for(var i = 0;i<data.data.length;i++){
                        if(data.data[i].id == temp[0].addressId)
                        {
                            str+='<option value='+data.data[i].id+' selected>'+data.data[i].cityName+'</option>'
                        }
                        else{
                            str+='<option value='+data.data[i].id+'>'+data.data[i].cityName+'</option>'
                        }

                    }
                    $(".selectpickerEdit").html(str);

                }
            });

            //初始化
            var goodsName =  $("#goodsName");
            var unitPrice = $("#unitPrice");
            var needAmount = $("#needAmount");
            var percentOfPass = $("#percentOfPass");
            var timeLimit = $("#timeLimit");
            var city = $(".selectpickerEdit");

            goodsName.val(temp[0].goodsName);
            unitPrice.val(temp[0].unitPrice);
            needAmount.val(temp[0].needAmount);
            percentOfPass.val(temp[0].percentOfPass);
            timeLimit.val(temp[0].timeLimit);
            city.val(temp[0].addressId);

            //提交
            $("#btn_submit").click(function () {
                $.ajax({
                    type: "put",
                    url: '/api/order/update.do',
                    data: {

                        "id":temp[0].id,
                        "goodsName": goodsName.val(),
                        "unitPrice": unitPrice.val(),
                        "needAmount": needAmount.val(),
                        "percentOfPass": percentOfPass.val(),
                        "timeLimit": timeLimit.val(),
                        "addressId":  city.val()
                    },
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        if (data.status == 1) {
                            alert("修改成功！");
                            $("#orderDetail").modal({show: false});
                            window.location.reload();
                        }
                        else {
                            alert("修改失败");
                            $("#orderDetail").modal({show: false});
                        }
                    },
                    error: function () {
                        alert("连接失败");
                        $("#orderDetail").modal({show: false});
                    }
                });

            });
        }else{
            alert('最多只能选择一行');
        }
    });

});

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#orderTable').bootstrapTable({
            url: '/api/order/get.do',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [5, 10],         //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 526,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            responseHandler: function(result) {
                return {
                    //总页数,前面的key必须为"total"
                    total : result.total,
                    //行数据，前面的key要与之前设置的dataField的值一致.
                    data : result.list
                };
            },
            onLoadSuccess: function(data) {
                $('#orderTable').bootstrapTable('removeAll');
                $('#orderTable').bootstrapTable('append',data.data);
            },
            columns: [{
                checkbox: true
            }, {
                field: 'id',
                title: '订单编号'
            }, {
                field: 'userId',
                title: '用户编号',
                visible:false
            }, {
                field: 'nickName',
                title: '用户名'
            },{
                field: 'goodsName',
                title: '货物名'
            }, {
                field: 'unitPrice',
                title: '货物单价(元)'
            }, {
                field: 'needAmount',
                title: '货物数量(个)'
            }, {
                field: 'percentOfPass',
                title: '合格率(%)'
            }, {
                field: 'timeLimit',
                title: '到货天数(天)'
            }, {
                field: 'cityName',
                title: '地址'
            },  {
                field: 'tool',
                title: '操作',
                align: 'center',
                formatter:function(value,row,index){
                    var element =
                        "<a class='edit' href='../html/bestSupplier.html?id="+row.id +"'>选配</a> ";
                    return element;
                }
            }
            ]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            curPage: parseInt(params.offset)/parseInt(params.limit)+1,  //页码
            nickName: $("#nickName").val(),
            goodsName:$("#findGoodsName").val()

        };
        return temp;
    };
    return oTableInit;
};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit;
};
