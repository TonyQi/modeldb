<template>
	<div class="app-container calendar-list-container">
		<div class="filter-container">
	    <el-input style="width: 200px;" class="filter-item" v-model="listQuery.aimsName" placeholder="目标名称"> </el-input>
	    <el-button class="filter-item" type="primary" v-waves icon="search" @click="getList" >搜索</el-button>
	    <el-button class="filter-item"   style="margin-left: 10px;" type="primary" icon="edit" @click="handleCreate">添加</el-button>
	    <el-button class="filter-item"   style="margin-left: 10px;" type="primary" icon="edit" @click="handleImport">导入</el-button>
	  </div>
	  <el-table  :data="list" v-loading.body="listLoading" border fit highlight-current-row style="width: 100%">
	    <el-table-column align="center" label="目标名称"> 
	    		<template scope="scope">
	        	<span>{{scope.row.aimsName}}</span>
	        </template>
	    </el-table-column>
	    <el-table-column  align="center" label="创建时间"> 
	    		<template scope="scope">
	        	<span>{{scope.row.creDate}}</span>
	        </template>
	    </el-table-column>
	    <el-table-column  align="center" label="备注"> 
	    	<template scope="scope">
	          <span>{{scope.row.note}}</span>
	      </template>
	   	</el-table-column>
	    <el-table-column align="center" label="操作" >
	    	<template scope="scope">
	        <el-button  size="small" type="success" @click="download(scope.row.id)">导出
	        </el-button>
	        <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除
	        </el-button>
	      </template> 
	    </el-table-column>
  	</el-table>
  	<div v-show="!listLoading" class="pagination-container">
	    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next" :total="total"> </el-pagination>
	  </div>
	  <el-dialog title="添加" :visible.sync="dialogFormVisible">
	    <el-form :model="datafrom" :rules="rules" ref="datafrom" label-width="100px">
	      <el-form-item label="类型" prop="aimsName">
	       <el-input
					  placeholder="请输入内容"
					  v-model="datafrom.aimsName"
					  clearable>
					</el-input>
	      </el-form-item>
	      <el-form-item label="描述">
	        <el-input type="textarea" :autosize="{ minRows: 3, maxRows: 5}" placeholder="请输入内容" v-model="datafrom.note"> </el-input>
	      </el-form-item>
	    </el-form>
	    <div slot="footer" class="dialog-footer">
	      <el-button @click="cancel()">取 消</el-button>
	      <el-button  type="primary" @click="create()">确 定</el-button>
	    </div>
	  </el-dialog>
	  <el-dialog title="导入目标" :visible.sync="dialogUploadVisible">
	  	<el-form :model="uploadFrom" ref="uploadFrom" label-width="100px">
	      <el-form-item  label="文件" placeholder="请上传文件" prop="file">
	        <el-upload
					  class="upload-demo"
					  ref="upload"
					  action=""
					  :multiple="false"
					  :accept="fileType"
					  :http-request="uploadFile"
					  :on-remove="handleRemove"
					  :before-upload="beforeupload"
					  :file-list="fileList"
					  >
					  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
					</el-upload>
	      </el-form-item>
	    </el-form>
	  	<div slot="footer" class="dialog-footer">
	      <el-button @click="cancelUpload()">取 消</el-button>
	      <el-button  type="primary" @click="upload()">确 定</el-button>
	    </div>
	  </el-dialog>
	</div>
</template>

<script>
import {
  upload,
  add,
  queryList,
  delObj,
  downFile,
  getByName
} from 'api/admin/aims/index';
import { mapGetters } from 'vuex';
import {
  getToken
} from 'utils/auth';
  export default {
  	components: {  },
    data() {
    	var validateName=(rule,value,callback)=> {
	        if (value === '') {
	          callback(new Error('请输入名称'));
	        } else {
	        	var para={"name":value};
	          getByName(para).then(response => {	          
					  	var list=response;
					  	if(list.length>0){
					  		callback(new Error('名称已存在'));
					  	}else{
					  		callback();	
					  	}
		        });
	        }
	      };
      return {
      	list:[],
      	listQuery: {
	        page: 1,
	        limit: 20,
	        name: undefined
	      },
	      listLoading: false,
	      total:0,
	      dialogFormVisible: false,
	      dialogUploadVisible: false,
	      datafrom:{},
	      uploadFrom:{},
	      files:[],
	      rules:{
      		aimsName:[
      			{
	            validator: validateName,
	            trigger: 'blur'
          	}
      		]
      	},
      };
    },
    methods: {
	      getList(){
	      	this.listLoading=true;
	      	queryList(this.listQuery).then(response => {	          
				  	this.list=response.data.rows;
				  	console.log(response.data.rows);
	          this.total = response.data.total;
	          this.listLoading = false;
	        });
	      },
	      handleSizeChange(val) {
		      this.listQuery.limit = val;
		      this.getList();
		    },
		    handleCurrentChange(val) {
		      this.listQuery.page = val;
		      this.getList();
		    },
		    handleCreate(){
		    	this.dialogFormVisible=true;
		    },
		    cancel(){
		    	this.dialogFormVisible=false;
		    },
		    create(){
		    	this.$refs['datafrom'].validate((valid) => {
	          if (valid) {
		          add(this.datafrom)
		            .then(() => {
		              this.dialogFormVisible = false;
		              this.getList();
		              this.$notify({
		                title: '成功',
		                message: '创建成功',
		                type: 'success',
		                duration: 2000
		              });
		            });
		        } else {
		          return false;
		        }
	        });
		    	
		    },
		    download(id){
		    	const token=getToken();
		    	window.open('/api/admin/aims/exportaims?id='+id+"&token="+token);
		    },
		    handleDelete(row) {
		      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
		        confirmButtonText: '确定',
		        cancelButtonText: '取消',
		        type: 'warning'
		      })
		        .then(() => {
		          delObj(row.id)
		            .then(() => {
		              this.$notify({
		                title: '成功',
		                message: '删除成功',
		                type: 'success',
		                duration: 2000
		              });
		              const index = this.list.indexOf(row);
		              this.list.splice(index, 1);
		            });
		        });
		    },
		    handleImport(){
		    	this.dialogUploadVisible=true;
		    },
		    cancelUpload(){
		    	this.dialogUploadVisible=false;
		    },
		    upload(){
		    	var form=new FormData();
		    	for(let i=0;i<this.files.length;i++){
        		form.append('file',this.files[i]);
        	}
        	const config = {
		        headers: {'Content-Type': 'multipart/form-data'}
		      };
		      upload(form).then(response => {
				        	this.param=[];
				        	this.datafrom={
					      		datatype:"",
					      		secret:"",
					      		description:""
					      	};
					      	this.$refs.upload.clearFiles();
				        	this.dialogUploadVisible=false;
				        	this.getList();
			        });
		    },
		    uploadFile (req) {
		    	
		    },
		    //阻止upload的自己上传，进行再操作
        beforeupload(file) {
            console.log(file);
            this.files.push(file);
            return true;
        },
        handleRemove(file, fileList) {
	        let files=this.file;
	        let index=-1;
	        for(let i=0;i<files.length;i++){
	        	let uid=files[i].uid;
	        	if(file.uid===uid){
	        		index=i;
	        		break;	
	        	}	        	
	        }
	        if(index!=-1){
	        	files.splice(index,1);
	        	this.param=files;	
	        
	        }
	        return true;
	      },
    },
    created() {
    	this.getList();
	  }
  }
  
  function getDictText(array,valueText){
  	const map={};
  	array.forEach(function(item){
  		map[item.value]=item.label;
  	});
  	return map[valueText];
  	
  }
  Date.prototype.format = function(fmt) { 
     var o = { 
        "M+" : this.getMonth()+1,                 //月份 
        "d+" : this.getDate(),                    //日 
        "h+" : this.getHours(),                   //小时 
        "m+" : this.getMinutes(),                 //分 
        "s+" : this.getSeconds(),                 //秒 
        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
        "S"  : this.getMilliseconds()             //毫秒 
    }; 
    if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
     for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
         }
     }
    return fmt; 
}

 
function Base64() {
 
	// private property
	const _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
 
	// public method for encoding
	this.encode = function (input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output +
			_keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
			_keyStr.charAt(enc3) + _keyStr.charAt(enc4);
		}
		return output;
	}
 
	// public method for decoding
	this.decode = function (input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		while (i < input.length) {
			enc1 = _keyStr.indexOf(input.charAt(i++));
			enc2 = _keyStr.indexOf(input.charAt(i++));
			enc3 = _keyStr.indexOf(input.charAt(i++));
			enc4 = _keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = utf8_decode(output);
		return output;
	}
 
	// private method for UTF-8 encoding
}
  function utf8_encode(string) {
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";
		for (var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
 
		}
		return utftext;
	}
 
	// private method for UTF-8 decoding
	 function utf8_decode(utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while ( i < utftext.length ) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}

</script>
<style>
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
    text-align:center;
    vertical-align: middle;
  }
  .bg-purple {
    background: #d3dce6;
    text-align:center;
  	vertical-align: middle;
  }
  .bg-purple-light {
    background: #e5e9f2;
    text-align:center;
    vertical-align: middle;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>