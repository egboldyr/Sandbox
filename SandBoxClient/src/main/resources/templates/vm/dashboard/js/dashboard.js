/* Получение всех актиывных заявок с веб-клиента*/
function checkRequisitions() {
    createRequisitionTable();
    jQuery.ajax({
        type: 'GET',
        url: '/all_requisitions',
        success: function (response) {
            processRequisitions(response);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
}

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

            /*В случае успешного создания карточки клиента закрываем заявку*/
            /*requisitionStatusUpdate("DONE");*/
        }
    });
}

/* Получение всех клиентов которые зарегистрированы в базе */
function checkClients() {

}