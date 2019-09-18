<template>
  <div :class="className" :style="{height:height,width:width}"></div>
</template>

<script>
  import echarts from 'echarts';
  require('echarts/theme/macarons'); // echarts 主题
  import {
	  getFileCount
	} from 'api/admin/file/index';
  const animationDuration = 3000;
  export default {
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '100%'
      },
      height: {
        type: String,
        default: '300px'
      }
    },
    data() {
      return {
        chart: null,
        dict:{'onedimen':'一维径向辐相','twodimen':'二维成像图像','benchmark':'BenchMark模型'}
      };
    },
    mounted() {
      this.showData();
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      this.chart.dispose();
      this.chart = null;
    },
    methods: {
    	showData(){
    		getFileCount().then(response=>{
	      		this.initChart(response);
	      	}
    		);
    	},    	
      initChart(response) {
      	let xdata=[];
      	let ydata=[];
      	let dicts=this.dict;
      	if(response.length>0){
      		response.forEach(function(item){
      			ydata.push(dicts[item.dataType]);
      			xdata.push(item.num);
      		});
      	}
        this.chart = echarts.init(this.$el, 'macarons');

        this.chart.setOption({
          title: {
        	text: '特性数据分布'
		    },
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'value',
		        boundaryGap: [0, 0.01]
		    },
		    yAxis: {
		        type: 'category',
		        data: ydata
		    },
		    series: [
		        {
		            type: 'bar',
		            data: xdata
		        }
		    ]
        })
      }
    }
}
</script>
