<template>
	<div style="height:100%;width:100%;">
		<a v-for="(item, index ) in datas"  @click="showData(index)">
   			 <el-tag
      		type="success"
     			 disable-transitions>
     			 {{index+1}}
     		</el-tag>
   	</a>
   	<br/>
		<div :class="className" :style="{height:height,width:width}"></div>
	</div>

  		
	</div>
	
  
</template>

<script>
	import { mapGetters } from 'vuex';
	import {
	  getLineData
	} from 'api/admin/file/index';
	import {
	  getToken
	} from 'utils/auth';
  import echarts from 'echarts';
  require('echarts/theme/macarons'); // echarts 主题
  import { debounce } from 'utils';

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
        default: '450px'
      },
      autoResize: {
        type: Boolean,
        default: true
      }
    },
    data() {
      return {
        chart: null,
        datas:[],
      };
    },
    mounted() {
      //this.initChart();
      if (this.autoResize) {
        this.__resizeHanlder = debounce(() => {
          if (this.chart) {
            this.chart.resize()
          }
        }, 100)
        window.addEventListener('resize', this.__resizeHanlder)
      }

      // 监听侧边栏的变化
      const sidebarElm = document.getElementsByClassName('sidebar-container')[0]
      sidebarElm.addEventListener('transitionend', this.__resizeHanlder)
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      if (this.autoResize) {
        window.removeEventListener('resize', this.__resizeHanlder)
      }

      const sidebarElm = document.getElementsByClassName('sidebar-container')[0]
      sidebarElm.removeEventListener('transitionend', this.__resizeHanlder)

      this.chart.dispose()
      this.chart = null
    },
    methods: {
    	showLineDatas(fileInfoId){
    		getLineData(fileInfoId).then(response=>{
	      		this.showDatas(response)
	      	}
    		);
    	},
    	showDatas(dataa){
    		if(dataa.length>0){
    			const aaa=[];
    			dataa.forEach(function(item){
    				aaa.push(item);
    				
    			});
    			this.datas=aaa;
    			this.initChart(dataa[0]);
    		}
    	},
      initChart(data) {
      	const sidebarElm = document.getElementsByClassName('chart')[0];
      	let existInstance = echarts.getInstanceByDom(sidebarElm);
				if (existInstance) {
				        echarts.dispose(sidebarElm);
				}
        this.chart = echarts.init(sidebarElm);
        const xData=data.x;
        const yData=data.y;
        
				var options={
						xAxis: {
				        type: 'category',
				        data: xData
				    },
				    legend: {
				        data:[]
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    dataZoom: [{
				        type: 'inside',
				        start: 0,
				        end: 10
				    }, {
				        start: 0,
				        end: 10,
				        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
				        handleSize: '80%',
				        handleStyle: {
				            color: '#fff',
				            shadowBlur: 3,
				            shadowColor: 'rgba(0, 0, 0, 0.6)',
				            shadowOffsetX: 2,
				            shadowOffsetY: 2
				        }
				    }],
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				        
				    ]
						
        };
        if(yData.length>0){
        	const legends=[];
        	yData.forEach(function(item){
        		const datas={};
        		datas.data=item.data;
			  		datas.name=item.name;
			  		datas.type='line';
			  		datas.smooth=true;
			  		legends.push(item.name);
			  		options.series.push(datas);
			  	});
			  	options.legend.data=legends;
        	
        }
        this.chart.setOption(options)
      },
      
      showData(index){
      	const datas=this.datas[index];
      	this.initChart(datas);
      	
      }
    }
}
</script>
