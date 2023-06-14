function gen(month){
	$.post('./genReport',{month:month}, function(){
		console.log(month);
		alert("done")
		})
		.fail(function(xhr, status, error){
			alert("error");
		});	
}