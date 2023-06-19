function gen(){
	let month = $("#genMM").val().split("-")[1];
	$.post('./genReport',{month:month}, function(){
		console.log(month);
		alert("done")
		})
		.fail(function(xhr, status, error){
			alert("error");
		});	
}