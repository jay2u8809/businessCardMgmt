/**
 * 
 */





























$(document).ready(function(){
	
	$.ajaxSetup({
		
		cache: false 
	});
	
	$('#search').keyup(function(){
		
		$('#result').html('');
		$('#state').val('');
		
		var searchField = $('#search').val();
		var expression = new RegExp(searchField, "i");
		
		// Json파일 분석
		$.getJSON(resultObj, function(data) {
			$.each($.parseJSON(resultObj), function(key, value){
				alert("durlrldfjksdla");
				if (value.name1.search(expression) != -1 || value.company.search(expression) != -1) {
					
					$('#result').append('<li class="list-group-item link-class">'+ value.name1 +' | <span class="text-muted">'+value.company+'</span></li>');
				}
			});   
		});
	});
 
	$('#result').on('click', 'li', function() {
		
		var click_text = $(this).text().split('|');
		
		$('#search').val($.trim(click_text[0]));
		$("#result").html('');
	});
});