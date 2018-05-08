function processGroups(jsonResult) {
    var groups = jQuery.parseJSON(jsonResult);

    jQuery('#tblGroupsBody').empty();
    for (var i = 0; i < groups.length; i++) {
        jQuery('#tblGroupsBody')
            .append('<tr id="grp_row' + i + '" class="item"></tr>');
        jQuery('#grp_row' + i)
            .append('<td id="group_id">'+ groups[i].id + '</td>')
            .append('<td id="group_title">' + groups[i].title + '</td>')
            .append('<td id="group_begin">' + groups[i].begin_date + '</td>')
            .append('<td id="group_end">' + groups[i].end_date + '</td>')

            //TODO: Поправить дизайн кнопок на UI (вертикально выглятит не очень)

            .append('<td id="group_action"><input class="process" type="button" value="Edit" onclick="return editGroup(this)">' +
                                          '<input class="close" type="button" value="Close" onclick="return closeGroup(this)"></td>');
    }

}