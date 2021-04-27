<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/4/26
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
    //需求：根据交易表的不同阶段的数量进行一个统计，最终形成一个漏斗图（倒三角）
    //将统计出来的阶段的数量比较多的，往上面排列
    //将统计出来的阶段的数量比较少的，往下面排列
    /**
     * 例如： 01资质审查10条 02需求分析85条 07成交100条
     * sql:按照阶段分组
     * resultType="map"
     * select stage,count(*)
     * from tbl_tran
     * group by stage
    * */
%>
<html>
    <head>
        <title>Title</title>
        <base href="<%=basePath%>">
        <script src="ECharts/echarts.min.js"></script>
        <script src="jquery/jquery-1.11.1-min.js"></script>
        <script type="text/javascript">
            $(function(){
                //页面加载完毕后，绘制统计图表
                getCharts();
            });
            function getCharts(){
                $.get("workbench/transaction/getCharts",rollback,"json");
                function rollback(param){
                    //param是自己创建的vo类，有一个总条数和一个集合
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main'));
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '交易漏斗图',
                            subtext: '统计交易阶段数量的漏斗图'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c}%"
                        },
                        toolbox: {
                            feature: {
                                dataView: {readOnly: false},
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        legend: {
                            data: ['展现','点击','访问','咨询','订单']
                        },

                        series: [
                            {
                                name:'交易漏斗图',
                                type:'funnel',
                                left: '10%',
                                top: 60,
                                //x2: 80,
                                bottom: 60,
                                width: '80%',
                                // height: {totalHeight} - y - y2,
                                min: 0,
                                max: param.total, //总条数
                                minSize: '0%',
                                maxSize: '100%',
                                sort: 'descending',
                                gap: 2,
                                label: {
                                    show: true,
                                    position: 'inside'
                                },
                                labelLine: {
                                    length: 10,
                                    lineStyle: {
                                        width: 1,
                                        type: 'solid'
                                    }
                                },
                                itemStyle: {
                                    borderColor: '#fff',
                                    borderWidth: 1
                                },
                                emphasis: {
                                    label: {
                                        fontSize: 20
                                    }
                                },
                                data: param.dataList
                                    /*[
                                    //一个自建类（总条数和阶段）json形式 value和name
                                    /!*{value: 60, name: '01资质审查'},
                                    {value: 40, name: '02需求分析'},
                                    {value: 20, name: '03价值建议'},
                                    {value: 80, name: '04谈判复审'},
                                    {value: 100, name: '07成交'}*!/
                                ]*/
                            }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }

            }
        </script>
    </head>
    <body>
        <div id="main" style="width:600px;height:400px;"></div>

    </body>
</html>
