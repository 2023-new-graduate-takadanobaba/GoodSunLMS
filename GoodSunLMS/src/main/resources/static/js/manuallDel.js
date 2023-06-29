function del(index){
	$.post('./doManualDeleteAjax',{aId:index},function(){
		console.log(index);
		$("#table").load('./manualDelete table');
	});
}