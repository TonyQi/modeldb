<template>
	<div>
	  <table class="info">
	  	<tr>
	  		<td>目标名称</td>
		  	<td>{{detail.tgtname}}</td>
		  	<td>目标说明</td>
		  	<td>{{detail.tgtdescpt}}</td>	  		
	  	</tr>
	  	<tr>
	  		<td>试验名称</td>
		  	<td>{{detail.testname}}</td>
		  	<td>试验负责人</td>
		  	<td>{{detail.testleader}}</td>
	  	</tr>
	  	<tr>
	  		<td>试验单位</td>
		  	<td>{{detail.testunit}}</td>
		  	<td>任务代号</td>
		  	<td>{{detail.taskcode}}</td>
	  	</tr>
	  	<tr>
	 			<td>任务来源</td>
			  <td>{{detail.taskcode}}</td>
			  <td>数据来源</td>
			  <td>{{detail.dataorig}}</td>
	  	</tr>
	  	<tr>
	  		<td>数据类型</td>
		  	<td>{{detail.charactdatatype}}</td>
		  	<td>测量地点</td>
		  	<td>{{detail.measpla}}</td>
	  	</tr>
	  	<tr>
	  		<td>测量日期</td>
			  <td>{{detail.measdate}}</td>
			  <td>缩比</td>
			  <td>{{detail.scale}}</td>
	  	</tr>
	  	<tr>
	  		<td>定标形式</td>
			  <td>{{detail.calibform}}</td>
			  <td>定标体名称</td>
			  <td>{{detail.refstdname}}</td>
	  	</tr>
	  	<tr>
	  		<td>定标体尺寸</td>
		  	<td>{{detail.refstddim}}</td>
		  	<td>不确定度</td>
		  	<td>{{detail.uncertainty}}</td>
	  	</tr>
	  	<tr>
	  		<td>适用范围</td>
		  	<td>{{detail.suitextdata}}</td>
		  	<td>质量等级</td>
		  	<td>{{detail.datagrd}}</td>
	  	</tr>
	  	<tr>
	  		<td>用户评价</td>
			  <td>{{detail.usereval}}</td>
			  <td>备注</td>
			  <td>{{detail.note}}</td>
	  	</tr>
	  	<tr>
	  		<td>大气传输修正模式</td>
			  <td>{{detail.airmode}}</td>
			  <td>测量目标名称</td>
			  <td>{{detail.testtarname}}</td>
	  	</tr>
	  	<tr>
	  		<td>飞行航线</td>
		  	<td>{{detail.airline}}</td>
		  	<td>测量目标说明</td>
		  	<td>{{detail.testtarcomm}}</td>
	  	</tr>
	  	<tr>
	  		<td>测量波段范围</td>
		  	<td>{{detail.testbandrange}}</td>
		  	<td>地面粗糙度</td>
		  	<td>{{detail.grorgh}}</td>
	  	</tr>
	  	<tr>
	  		<td>目标照片</td>
			  <td>{{detail.tgtpho}}</td>
			  <td>经度</td>
			  <td>{{detail.lon}}</td>
	  	</tr>
	  	<tr>
	  		<td>海区海域</td>
			  <td>{{detail.seaarea}}</td>
			  <td>海况</td>
			  <td>{{detail.seacon}}</td>
	  	</tr>
	  	<tr>
	  		<td>块长</td>
			  <td>{{detail.blklng}}</td>
			  <td>目标高</td>
			  <td>{{detail.tgthigh}}</td>
	  	</tr>
	  	<tr>
	  		<td>面目标名称</td>
			  <td>{{detail.surftgtname}}</td>
			  <td>目标红外隐身措施说明</td>
			  <td>{{detail.mbhwyscssm}}</td>
	  	</tr>
	  	<tr>
	  		<td>目标发动机说明</td>
			  <td>{{detail.mbfdjgksm}}</td>
			  <td>目标工作特性说明</td>
			  <td>{{detail.mbgztxsm}}</td>
	  	</tr>
	  	<tr>
	  		<td>目标电磁隐身措施说明</td>
			  <td>{{detail.mbdcyscssm}}</td>
			  <td>目标宽</td>
			  <td>{{detail.tgtwd}}</td>
	  	</tr>
	  </table>
	</div>
</template>
<script>
	import { mapGetters } from 'vuex';
	import {
	  getFileInfoById
	} from 'api/admin/file/index';
	import {
	  getToken
	} from 'utils/auth';
	export default {
    data() {
      return {
      	id:undefined,
      	detail:{ tgtname:""
      					},
      };
    },
    methods: {
	      getDetailInfo(fileId){
	      	this.id = fileId;
	      	getFileInfoById(this.id).then(response=>{
	      		console.log(response);
	      		this.detail=response;
	      	});
	      }
    },
    created() {
	  },
	  props: ['fileInfoId']
  }
	
</script>
<style>
	table.info{
		display: table;
    border-color: grey;
    border: 1px solid #e9eaec;
    width: 100%;
    border-collapse: separate;
    line-height: 18px;
    font-size: 12px;
    word-break: break-all;
    table-layout:  fixed;
	}
  table.info tr{
		height:40px;
		text-align:center;
	}
	table.info td{
		text-align:center;
		border-bottom: 1px solid #e9eaec;
		vertical-align:center;
		text-overflow:ellipsis;
	}
	
</style>