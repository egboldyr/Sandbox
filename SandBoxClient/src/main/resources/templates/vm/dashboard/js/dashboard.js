/* Создание таблицы и получение первой порции данных*/
function checkRequisitions() {
    createRequisitionTable();
    jQuery.ajax({
        type: 'POST',
        url: '/part_requisitions',
        data: {
            action: "UPLOAD",
            amount: jQuery('#amount').val()
        },
        success: function (response) {
            processRequisitions(response);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
}

function prevRequisitions() {
    checkRequisitionsByPart("PREV");
}

function nextRequisitions() {
    checkRequisitionsByPart("NEXT");
}

/*Получение активных заявок частями (по 10 записей)
* при первой загрузке интерфейса стартуем с 1 записи и тянем первые
* 10 записей, а далее выдача информации привязана к кнопкам <Prev>-<Next>*/
function checkRequisitionsByPart(text) {
    jQuery.ajax({
        type: 'POST',
        url: '/part_requisitions',
        data: {
            action: text,
            amount:   jQuery('#amount').val()
        },
        success: function (response) {
            processRequisitions(response);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
}
/**********************************************************************************************************************/
/* Изменение статуса заявки:
* - PROCESS - заявка в обработке
* - DONE    - заявка обработана*/
function requisitionStatusUpdate(reqId, newStatus) {
    jQuery.ajax({
        type: 'POST',
        url: '/update_status',
        data: {
            req_id: reqId,
            status : newStatus
        },
        success: function () {
            console.log(reqId + " status updated.")
        }
    });
}
/**********************************************************************************************************************/
/* Создание нового клиента для формы обработки заявок:*/
function createClient() {
    jQuery.ajax({
        type: 'POST',
        url: '/new_client',
        data: {
            name: jQuery('#name').val(),
            surname: jQuery('#surname').val(),
            phone: jQuery('#phone').val(),
            email: jQuery('#email').val()
        },
        success: function () {
            jQuery('#name').val('');
            jQuery('#surname').val('');
            jQuery('#phone').val('');
            jQuery('#email').val('');

            requisitionStatusUpdate(jQuery("#r_id").html(), "COMPLETE");
        }
    });
}

function updateClient() {
    jQuery.ajax({
        type: 'POST',
        url: '/update_client',
        data : {
            id: jQuery('#cl_id').val(),
            name: jQuery('#cl_name').val(),
            surname: jQuery('#cl_surname').val(),
            phone: jQuery('#cl_phone').val(),
            email: jQuery('#cl_email').val()
        },
        success : function () {
            jQuery("tr.item").each(function() {
                if (jQuery(this).find("#client_id").html() === jQuery('#cl_id').val()) {
                    jQuery(this).find("#client_name").html(jQuery('#cl_name').val());
                    jQuery(this).find("#client_surname").html(jQuery('#cl_surname').val());
                    jQuery(this).find("#client_phone").html(jQuery('#cl_phone').val());
                    jQuery(this).find("#client_email").html(jQuery('#cl_email').val());
                }
            });
        }
    })
}
/**********************************************************************************************************************/
function prevClients() {
    checkClientsByPart("PREV");
}

function nextClients() {
    checkClientsByPart("NEXT");
}

function checkClients() {
    genClientInterface();
    checkCoursesByPart("UPLOAD");
}

function checkClientsByPart(text) {
    jQuery.ajax({
        type: 'POST',
        url: '/part_clients',
        data: {
            action: text
        },
        success: function (response) {
            processClients(response);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
}
/**********************************************************************************************************************/
function createAccount() {
    jQuery.ajax({
        type: 'POST',
        url: '/new_account',
        data: {
            id: jQuery('#cl_id').val(),
            login: jQuery('#acc_login').val(),
            password: jQuery('#acc_password').val()
        },
        success: function () {
            jQuery("tr.item").each(function() {
                if (jQuery(this).find("#client_id").html() === jQuery('#cl_id').val()) {
                    jQuery(this).find("#client_login").html(jQuery('#acc_login').val());
                    jQuery('#acc_login').val('');
                    jQuery('#acc_password').val('');
                }
            });
        }
    });
}
/**********************************************************************************************************************/
function checkCoursesAndGroups() {
    genCoursesAndGroupsInterface();
    getCourseData();
}

function getCourseData() {
    checkCoursesByPart("UPLOAD");
}

function prevCourses() {
    checkCoursesByPart("PREV");
}

function nextCourses() {
    checkCoursesByPart("NEXT");
}

function checkCoursesByPart(text) {
    jQuery.ajax({
        type: 'POST',
        url: '/part_courses',
        data: {
            action: text
        },
        success: function (response) {
            processCourses(response);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
}

function createCourse() {
    jQuery.ajax({
        type: 'POST',
        url: '/new_course',
        data: {
            title: jQuery('#course_title').val()
        },
        success: function () {
            jQuery('#course_title').val('');
        }
    });
}
/**********************************************************************************************************************/
