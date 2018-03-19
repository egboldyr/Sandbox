function createRequisitionTable() {
    jQuery('#workspace').empty();

    jQuery('#workspace').append('<table id="requisitions">');
    jQuery('#requisitions').append('<caption>Requisitions</caption>')
    jQuery('#requisitions').append('<thead><tr id="header"></tr></thead>');

    jQuery('#header')
        .append('<th>ID</th>')
        .append('<th>STATUS</th>')
        .append('<th>NAME</th>')
        .append('<th>PHONE</th>')
        .append('<th>EMAIL</th>')
        .append('<th>COMMENT</th>')
        .append('<th>CREATION_DATE</th>')
        .append('<th>ACTIONS</th>');
}

function createRequisitionProcessForm() {
    jQuery('#workspace').append('<div id="requisition_form"></div>');

}

function processRequisitions(jsonResult) {
    console.log(jsonResult);

    var result = jQuery.parseJSON(jsonResult);
    jQuery('#requisitions').append('<tbody id="tblBody"></tbody>');
    for (var i = 0; i < result.length; i++) {
        jQuery('#tblBody')
            .append('<tr>')
            .append('<td>' + result[i].id + '</td>')
            .append('<td>' + result[i].status + '</td>')
            .append('<td>' + result[i].name + '</td>')
            .append('<td>' + result[i].phone + '</td>')
            .append('<td>' + result[i].email + '</td>')
            .append('<td>' + result[i].comment + '</td>')
            .append('<td>' + result[i].date + '</td>');

        if (result[i].status == 'NEW') {
            jQuery('#tblBody')
                .append('<td><input class="process" type="button" value="Process" onclick="createRequisitionProcessForm()"></td>')
        }
    }
}
