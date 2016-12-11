$(function() {
	$("#web-sidebar-whole-id a").click(function() {
		$("#web-sidebar-whole-id li[class='active']").removeClass("active");
		$(this).parent().addClass("active"); 
	});
});