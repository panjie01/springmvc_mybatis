<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.ocupload-1.1.2.js"></script>
    <%--<script type="text/javascript" src="js/common.js"></script>--%>
    <title>文件上传</title>
</head>
<body>
    <div>

        <form action="hello" enctype="multipart/form-data" method="post">
            请选择文件<input type="file" name="file2" value="" />
            <input type="submit" value="提交" id='submit'>
        </form>

        <button id="button-import" type="button" name="button">上传</button>
        <button id="bu" type="button" name="button2">测试</button>

    </div>
    <script type="text/javascript">
        $('input[name=file2]').change(function() {

            alert($(this).val());

        });

        $("#bu").click(function () {
            alert("213");
        });
        //使用文件上传组件
        //给导入按钮绑定一键上传
        //要绑定元素可以是任意可见可点击的元素。
        var myUpload = $("#button-import").upload({
            name: 'upload',//<input type=file name=upload,默认是file
            action: 'upload/file',//form action="服务器地址"
            //enctype: 'multipart/form-data',//enctype="xxx",默认值，不建议写
            //params: {},//上传过程中是否要额外携带参数，底层实现隐藏域
            //autoSubmit: true,//自动提交表单开关，默认是开
            autoSubmit:false,
            onSubmit: function() {
                alert("提交表单后触发");
            },
            onComplete: function(data) {
                //底层：响应的内容是页面上隐藏的iframe中的内容。
                var result = JSON.parse(data);
                alert(result);
                if(result == '1') {
                    // $.messager.alert("提示","导入成功");
                    alert("导入成功");
                }else {
                    // $.messager.alert("提示","导入失败");
                    alert("导入失败");
                }
            },
            onSelect: function() {
                //alert("选择文件后触发");
                var filename = this.filename();
                var arr = filename.split("\\");
                var name = arr[arr.length-1];
                console.log(name);
                // alert(this.filename());
                var regExp = /^.*\.xls?$/img;
                if(regExp.test(name)) {
                    //alert(1);
                    //开启提交
                    this.autoSubmit=true;
                }else {
                    this.autoSubmit=false;
                    // $.messager.alert("提示","请选择正确的文件类型");
                    alert("请选择正确的文件类型");
                }
            }
        });

    </script>
</body>
</html>