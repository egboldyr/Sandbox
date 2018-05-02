function genCoursesAndGroupsInterface() {
    createCourseInterface();
}

function createCourseInterface() {
    jQuery('#workspace').empty()
        .append('<p><span id="courses_ui"></span></p>')
        .append('<p><hr></p>')
        .append('<p><span id="groups_ui"></span></p>');

    /* Интерфейс для работы с курсами (Добавление новых курсов)*/
    jQuery('#courses_ui')
        .append('<div id="courses_tab"><table id="courses"></table>' +
                                      '<input id="prevCourses" type="button" value="Prev" onclick="prevCourses()">' +
                                      '<input id="nextCourses" type="button" value="Next" onclick="nextCourses()"></div>')
        .append('<div id="courses_form"><form id="course_form"></form></div>');
    jQuery('#courses')
        .append('<thead><tr id="header"></tr></thead>');
    jQuery('#header')
        .append('<th>ID</th>')
        .append('<th>TITLE</th>')
        .append('<th>ACTIONS</th>');
    jQuery('#course_form')
        .append('<p>Title<input id="course_title" type="text"></p>')
        .append('<input type="button" value="Add Course" onclick="return createCourse()">')
        .append('<p>Course ID<input id="crs_id" type="text" disabled="true"></p>')
        .append('<p>Title<input id="crs_title" type="text" disabled="true"></p>');

    /* Интерфейс для работы с группами */
    jQuery('#groups_ui')
        .append('<div id="groups_view"></div>')
        .append('<div id="group_students"></div>');

    jQuery('#groups_view')
        .append('<div id="groups_tab"><table id="groups"></table></div>')
        .append('<div id="groups_form"><form id="group_form"></form></div>');

    /*Интерфейс добавления/изменения групп (Создание группы и редактирование основной информации)*/
    jQuery('#groups')
        .append('<caption>GROUPS</caption>')
        .append('<thead><tr id="grp_header"></tr></thead>')
        .append('<tbody id="tblGroupsBody"></tbody>');
    jQuery('#grp_header')
        .append('<th>ID</th>')
        .append('<th>TITLE</th>')
        .append('<th>BEGIN_DATE</th>')
        .append('<th>END_DATE</th>')
        .append('<th>ACTIONS</th>');

    for (var i = 0; i < 10; i++) {
        jQuery('#tblGroupsBody')
            .append('<tr id="cur_row' + i + '" class="item"></tr>');
        jQuery('#cur_row' + i)
            .append('<td id="group_id">-</td>')
            .append('<td id="group_title">-</td>')
            .append('<td id="group_begin">-</td>')
            .append('<td id="group_end">-</td>')
            .append('<td id="group_action">-</td>');
    }

    jQuery('#group_form')
        .append('<p>Group ID<input id="grp_id" type="text" disabled="true"></p>')
        .append('<p>Title <input id="grp_title" type="text"></p>')
        .append('<p>Begin date<input id="grp_begin" type="date"></p>')
        .append('<p>End date<input id="grp_end" type="date"></p>')
        .append('<input type="button" value="Add" onclick="return createNewGroup()">');
}

function processCourses(jsonResult) {
    var result = jQuery.parseJSON(jsonResult);

    jQuery('#courses').append('<tbody id="tblBody"></tbody>');
    jQuery('#tblBody').empty();

    for (var i = 0; i < result.length; i++) {
        jQuery('#tblBody').append('<tr id="cur_row' + i + '" class="item"></tr>');
        jQuery('#cur_row' + i)
            .append('<td id="cr_id">' + result[i].id + '</td>')
            .append('<td id="cr_title">' + result[i].title + '</td>')
            .append('<td id="crs_groups"><input class="process" type="button" value="View groups" onclick="return viewGroups(this)"></td>');
    }
}

function viewGroups(btn) {
    var course = btn.parentNode.parentNode;
    jQuery('#crs_id').val(jQuery(course).find("#cr_id").html());
    jQuery('#crs_title').val(jQuery(course).find("#cr_title").html());
    checkGroups();
}