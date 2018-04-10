function genCoursesAndGroupsInterface() {
    createCourseInterface();
}

function createCourseInterface() {
    jQuery('#workspace').empty()
        .append('<table id="courses_ui"></table>');
    jQuery('#courses_ui')
        .append('<tbody><tr id="ui"></tr></tbody>');
    jQuery('#ui')
        .append('<th><table id="courses"></table></th>')
        .append('<th><form id="course_form"></form></th>');


    jQuery('#courses')
        .append('<thead><tr id="header"></tr></thead>');
    jQuery('#header')
        .append('<th>ID</th>')
        .append('<th>TITLE</th>');
    jQuery('#course_form')
        .append('<p>Title<input id="course_title" type="text"></p>')
        .append('<input type="button" value="Add Course" onclick="return createCourse()">');
}

function processCourses(jsonResult) {
    var result = jQuery.parseJSON(jsonResult);

    jQuery('#courses').append('<tbody id="tblBody"></tbody>');
    jQuery('#tblBody').empty();

    for (var i = 0; i < result.length; i++) {
        jQuery('#tblBody').append('<tr id="cur_row' + i + '" class="item"></tr>');
        jQuery('#cur_row' + i)
            .append('<td id="crs_id">' + result[i].id + '</td>')
            .append('<td id="crs_title">' + result[i].title + '</td>');
    }
}
