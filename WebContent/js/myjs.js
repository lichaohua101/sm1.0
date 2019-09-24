//鼠标覆盖上
function mouseOver(obj) {
	obj.style.backgroundColor = "#ccc";
}
//鼠标移开
function mouseOut(obj) {
	obj.style.backgroundColor = "white";
}
//根据id删除数据
function deleteById(sp,id) {
	if (confirm("确认删除Id为"+id+"的数据吗？")) {
		location.href = "StudentServlet?opType=delete&id="+id+"&currentPage="+sp+"";
	}
}
//全选按钮(jquery)
function checkAll() {
	$("input[name='student']").prop("checked",$("#all").prop("checked"));
}
//批量删除
function deleteMore(sp){
	//获取被选中的复选框的个数
	var num = $("input[name='student']:checked").size();
	if(num==0){
		alert("请选择要删除的记录");
		return;
	}
	if(confirm("确认删除"+num+"条数据吗？")){
		var array = new Array();
		$("input[name='student']:checked").each(function(i) {
			//循环把复选框选中的内容
			array.push($(this).val());
		});
		//把数组的元素转成字符串，用逗号隔开
		var ids = array.join();
		location.href = "StudentServlet?opType=deleteMore&currentPage="+sp+"&ids="+ids+"";
//		var array =  $("input[name='student']:checked");土办法
		/*var ids= "";
		$("input[name='student']:checked").each(function() {
			ids +=  $(this).val()+",";
		});
		ids = ids.substring(0,ids.length-1);*/
	}
}

//删除
function deleteRow(id){
				//根据当前删除按钮对象获得按钮所在行的对象
				var obj = id.parentNode.parentNode.rowIndex;
				//获取表格对象
				var table = document.getElementById("myTable");
				//更加行号删除行
				table.deleteRow(obj);
				
			}
//修改
function updateRow(id){
	//获得行
		var obj = id.parentNode.parentNode;
		//cells[]	返回包含行中所有单元格的一个数组
		var arr = obj.cells;
		//行中的单元格(innerHTML	设置或返回单元格的开始标签和结束标签之间的HTML)
		//修改
		var num = arr[1].innerHTML;
		var sal = arr[2].innerHTML;
		var password = arr[3].innerHTML;
		var sex = arr[4].innerHTML;
		var age = arr[5].innerHTML;
		var birthday = arr[6].innerHTML;
		arr[1].innerHTML="<input type='text' value='"+num+"' id='num'/>";
		arr[2].innerHTML="<input type='text' value='"+sal+"' id='sal'/>";
		arr[3].innerHTML="<input type='password' value='"+password+"' id='password'/>";
		arr[4].innerHTML="<input type='text' value='"+sex+"' id='sex'/>";
		arr[5].innerHTML="<input type='text' value='"+age+"' id='age'/>";
		arr[6].innerHTML="<input type='text' value='"+birthday+"' id='birthday'/>";
		arr[8].innerHTML="<input type='button' id='sure' value='确定' onclick='sure(this)'/><input type='button' id='cancel' value='取消' onclick='cancel(this)'/> ";
		
}
//确认
function sure(id) {
	var obj = id.parentNode.parentNode;
	//cells[]	返回包含行中所有单元格的一个数组
	var arr = obj.cells;
	//行中的单元格(innerHTML	设置或返回单元格的开始标签和结束标签之间的HTML)
	//修改
	var num = document.getElementById("num").value;
	var sal = document.getElementById("sal").value;
	var password = document.getElementById("password").value;
	var sex = document.getElementById("sex").value;
	var age = document.getElementById("age").value;
	var birthday = document.getElementById("birthday").value;
	arr[8].innerHTML="<input type='button' value='删除' onclick='deleteRow(this)'/><input type='button' id='sure' value='修改' onclick='updateRow(this)'/>";
	
	arr[1].innerHTML=num;
	arr[2].innerHTML=sal;
	arr[2].innerHTML=password;
	arr[2].innerHTML=sex;
	arr[2].innerHTML=age;
	arr[2].innerHTML=birthday;
}