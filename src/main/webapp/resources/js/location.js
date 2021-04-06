function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
   // var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+$("#contextPath").val());
}
function loadAreas(){
	var url=getRootPath();
	var jsonObj=null;
		$.ajax({
			url:url+"/region",
			type:"POST",
			async:false,//同步执行 就会重上往下了
			traditional: true,//防止深度序列化 数组传参
			success : function(data) {
				data=eval('('+data+')');
				jsonObj=data.regionList;
			}//function
		 })//ajax
		 return jsonObj;
}
function assembly(){
	var dataList=loadAreas();
	var proStr='';
	var cityStr='';
	var disStr='';
	var jsonObj='{';
	$.each(dataList,function(i,item){
		var citys = item.citys;
		//省	'0':{1:'北京市',22:'天津市'}
		if(i==0){
			proStr+="'0':{"+item.id+":'"+item.name+"'";
		}else{
			proStr+=","+item.id+":'"+item.name+"'";
		}
		//市	'0,1':{2:'北京市'},
		$.each(citys,function(c,city){
			var districts = city.districts;
			if(c==0){
				cityStr+=",'0,"+item.id+"':{"+city.id+":'"+city.name+"'";
			}else{
				cityStr+=","+city.id+":'"+city.name+"'";
			}
			//区	'0,1,2':{3:'东城区',4:'西城区'}
			$.each(districts,function(d,district){
				if(d==0){
					disStr+=",'0,"+item.id+","+city.id+"':{"+district.id+":'"+district.name+"'";
				}else{
					disStr+=","+district.id+":'"+district.name+"'";
				}
			})//区 end
			disStr+="}";
		})//市 end
		cityStr+="}";
	})//省 end
	proStr+="}";
	jsonObj+=proStr;
	jsonObj+=cityStr;
	jsonObj+=disStr;
	jsonObj+="}";
	return jsonObj;
}


function Location() {
	 //数据加载
	 var jsonObj=assembly();
	 jsonObj=eval('('+jsonObj+')')
	 this.items	= jsonObj;
	
}
Location.prototype.find	= function(id) {
	if(typeof(this.items[id]) == "undefined")
		return false;
	return this.items[id];
}

Location.prototype.fillOption	= function(el_id , loc_id , selected_id) {
	var el	= $('#'+el_id); 
	var json	= this.find(loc_id); 
	if (json) {
		var index	= 1;
		var selected_index	= 0;
		$.each(json , function(k , v) {
			var option	= $('<option value="'+k+'">'+v+'</option>');
			if (k == selected_id) {
				selected_index	= index;
				//option.attr("selected",true);
			}
			el.append(option);
			index++;
		})
		
//		el.attr('selectedIndex' , selected_index);
	}
	el.select2("val", "");
}

