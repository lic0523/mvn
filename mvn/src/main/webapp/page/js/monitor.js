layui.define(function(e) {
	layui.use(["admin", "carousel"], function() {
		var e = layui.$,
			a = (layui.admin, layui.carousel),
			t = layui.element,
			i = layui.device();
		e(".layadmin-carousel").each(function() {
			var t = e(this);
			a.render({
				elem: this,
				width: "100%",
				arrow: "none",
				interval: t.data("interval"),
				autoplay: t.data("autoplay") === !0,
				trigger: i.ios || i.android ? "click" : "hover",
				anim: t.data("anim")
			})
		}), t.render("progress")
	}), layui.use(["carousel", "echarts"], function() {
			var legend1=$("#legend1").val().split(",");
			var xAxis1=$("#xAxis1").val().split(",");	
			var resultObj1=$("#resultSeries1").val();
			var obj=JSON.parse(resultObj1);
			var series1=[];
			
			for(var i=0;i<obj.length;i++){
				series1.push({
		            name: obj[i].name,
		            type: 'line',
		            itemStyle: {  
		                normal: {  
		                    label: {  
		                        show: false,  
		                        position: 'top',  
		                        formatter: '{c}'  
		                    }  
		                }  
		            },
		            data: (function (){
		            	var res = [];
		                var len = 0;
		                while (len < xAxis1.length) {
		                    res.push(parseFloat(obj[i].data[len]));
		                    len++;
		                }
		                return res;
		            })(),
		            markPoint: {
						data: [{
							type: "max",
							name: "最大值",
							symbol: "circle",
							symbolSize:10,
							itemStyle: {
								normal: {
									color: "#dc143c",
									label: {
										position: "top"
									}
								}
							}
						}, {
							type: "min",
							name: "最小值",
							symbol: "circle",
							symbolSize:10,
							itemStyle: {
								normal: {
									color: "#0f990f",
									label: {
										position: "top"
									}
								}
							}
						}]
					},
					markLine: {
						data: [{
							type: "average",
							name: "平均值"
						}]
					}
		        });		
			}
		var e = layui.$,
			a = (layui.carousel, layui.echarts),
			t = [],
			i = [{
				tooltip: {
					trigger: "axis"
				},
				legend: {
					data: legend1
				},
				calculable: !0,
				xAxis: [{
					type: "category",
					boundaryGap: !1,
					data: xAxis1
				}],
				yAxis: [ {
		            type: 'value',
		            name: '数据',
		            scale: true
		        }],
				series: series1
			}],
			n = e("#monitor-kbb-view").children("div"),
			l = function(e) {
				t[e] = a.init(n[e], layui.echartsTheme), t[e].setOption(i[e]), window.onresize = t[e].resize
			};
			
			if (n[0]) {
				l(0);
				var legend2=$("#legend2").val().split(",");
				var xAxis2=$("#xAxis2").val().split(",");	
				var resultObj2=$("#resultSeries2").val();
				var obj=JSON.parse(resultObj2);
				var series2=[];
				
				for(var i=0;i<obj.length;i++){
					series2.push({
			            name: obj[i].name,
			            type: obj[i].type,
			            yAxisIndex:obj[i].yAxisIndex,
			            z:obj[i].z,
			            data: (function (){
			            	var res = [];
			                var len = 0;
			                while (len < xAxis2.length) {
			                	var v=obj[i].data[len];
			                    res.push(v);
			                    len++;
			                }
			                return res;
			            })(),
			            markPoint: {
							data: [{
								type: "max",
								name: "最大值",
								symbol: "arrow",
								symbolSize:10,
								itemStyle: {
									normal: {
										color: "#dc143c",
										label: {
											position: "top"
										}
									}
								}
							}]
						}
			        });		
				}
				var r = [],
					o = [{
						tooltip: {
							trigger: "axis"
						},
						legend: {
							data: legend2,
							x: "left"
						},
						calculable: !0,
						xAxis: [{
							type: "category",
							boundaryGap: !1,
							data: xAxis2
						}],
						yAxis: [{
					        type: 'value',
					        name:'错误码数量',
					        min:0,
					        axisLabel: {  
				              show: true,  
				              interval: 'auto',  
				              formatter: '{value}'  
				            }
						},{
					        type: 'value',
					        name: '错误率',
					        axisLabel: {  
				              show: true,  
				              interval: 'auto',  
				              formatter: '{value}%'  
				            }
					    }],
						series: series2
					}],
					m = e("#monitor-ercode-view").children("div"),
					s = function(e) {
						r[e] = a.init(m[e], layui.echartsTheme), r[e].setOption(o[e]), window.onresize = r[e].resize
					};
		}s(0);
		setInterval(function (){
			var now =new Date();
		    var min=now.getMinutes();
            if(min%5==0){
            	window.location.reload();
            }
		},60000);
	}), e("senior", {})
});