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