<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/3/18
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>AJAX实现下拉列表联级</title>
  </head>
  <body>
    <%--引入jquery的在线包--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="jquery-3.6.0.js"></script>
    <script type="text/javascript">
      function cityFind(){
        if($("#province").val()==0){
          $("#city").empty();
          $option=$("<option value='0'>请选择</option>")
          $("#city").append($option);
          return;
        }
        var xmlHttp=new XMLHttpRequest();
        xmlHttp.onreadystatechange=function(){
          if(xmlHttp.readyState==4 && xmlHttp.status==200){
            var data=xmlHttp.responseText;
            var cityArray=eval("("+data+")");
            callBack(cityArray);
          }
        }
        xmlHttp.open("get","/myWeb/city_find?provinceId="+$("#province").val(),true);
        xmlHttp.send();

        function callBack(cityArray){
          //1.将城市下拉列表原有的子标签销毁
          $("#city").empty();
          //2.将数组中的城市对象作为option标签填充到城市下拉列表中
          for(var i=0;i<cityArray.length;i++){
            var cityObj=cityArray[i];
            var $option=$("<option></option>");
            $option.text(cityObj.cityName);
            $option.val(cityObj.cityId);
            $("#city").append($option);
          }
        }
      }

    </script>
    <center>
      省份：<select id="province" onchange="cityFind()">
      <option value="0">请选择</option>
      <option value="1">河北省</option>
      <option value="2">河南省</option>
      </select>
      <br/><br/>
      城市：<select id="city">
      <option value="0">请选择</option>
      <br/><br/>
      </select>
    </center>
  </body>
</html>
