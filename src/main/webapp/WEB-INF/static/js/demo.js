function searchPage(pageNum, pagesize) {
    $('#start').val(pageNum);
    $(".select-pageSize").val(pagesize);
    $(".select-pageSize").find("option[value= " + pagesize + "]").attr("selected", true);
    $('#pageSize').val(pagesize);
    $('#conditions_form').attr("action", "/admin/demo/apply");
    $('#conditions_form').submit();
}

$("#search").bind("click", function () {
    var page_size = $(".select-pageSize").val();
    searchPage(1, page_size);
})