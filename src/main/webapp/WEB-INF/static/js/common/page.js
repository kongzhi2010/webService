$(function() {
	$("#pageSize").on("change", function() {
		var pagesize = $(this).val();
		searchPage(1, pagesize);
	});
});

function toPage(pageNum) {
	var pagesize = $("#pageSize").val();
	searchPage(pageNum, pagesize);
}