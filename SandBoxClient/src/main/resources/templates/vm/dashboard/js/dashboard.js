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
    jQuery.ajax({
        type: 'POST',
        url: '/part_clients',
        data: {
            action: "UPLOAD"
        },
        success: function (response) {
            processClients(response);
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    });
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
    
}
