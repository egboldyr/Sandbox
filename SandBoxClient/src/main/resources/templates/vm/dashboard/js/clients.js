function genClientInterface() {
    createClientsTable();
    createEditClientInterface();

}

function createClientsTable() {
    jQuery('#workspace')
        .empty()
        .append('<table id="clients">')
        .append('<div class="nav_pnl"><input type="button" value="Prev" onclick="prevClients()">' +
                                     '<input type="button" value="Next" onclick="nextClients()"></div>')
        .append('<div id="forms"></div>');
    jQuery('#clients')
        .append('<caption>Clients</caption>')
        .append('<thead><tr id="header"></tr></thead>');
    jQuery('#header')
        .append('<th>ID</th>')
        .append('<th>NAME</th>')
        .append('<th>SURNAME</th>')
        .append('<th>PHONE</th>')
        .append('<th>EMAIL</th>')
        .append('<th>ACCOUNT_LOGIN</th>')
        .append('<th>ACTIONS</th>');
}

function processClients(jsonResult) {
    var result = jQuery.parseJSON(jsonResult);
    jQuery('#clients').append('<tbody id="tblBody"></tbody>');
    jQuery('#tblBody').empty();

    for (var i = 0; i < result.length; i++) {
        jQuery('#tblBody').append('<tr id="cur_row' + i + '" class="item"></tr>');
        jQuery('#cur_row' + i)
            .append('<td id="client_id">' + result[i].id + '</td>')
            .append('<td id="client_name">' + result[i].name + '</td>')
            .append('<td id="client_surname">' + result[i].surname + '</td>')
            .append('<td id="client_phone">' + result[i].phone + '</td>')
            .append('<td id="client_email">' + result[i].email + '</td>')
            .append('<td id="client_login"></td>')
            .append('<td id="client_action"><input class="process" type="button" value="Edit Client" onclick="return editClient(this)"></td>');
    }
}

function createEditClientInterface() {
    jQuery('#forms')
        .empty()
        .append('<form id="client_form" class="client_edit"></form>')
        .append('<form id="account_form" class="account_edit"></form>');
    jQuery('#client_form')
        .append('<p>Client ID <input id="cl_id" type="text" disabled="true"></p>')
        .append('<p>Name <input id="cl_name" type="text"></p>')
        .append('<p>Surname <input id="cl_surname" type="text"></p>')
        .append('<p>Phone number <input id="cl_phone" type="text"></p>')
        .append('<p>Email <input id="cl_email" type="text"></p>')
        .append('<input type="button" value="Update" onclick="return updateClient()">');
    jQuery('#account_form')
        .append('<p>Login<input id="acc_login" type="text"></p>')
        .append('<p>Password<input id="acc_password" type="text"></p>')
        .append('<input type="button" value="Create" onclick="return createAccount()">');
}

function editClient(btn) {
    var client = btn.parentNode.parentNode;
    jQuery('#cl_id').val(jQuery(client).find("#client_id").html());
    jQuery('#cl_name').val(jQuery(client).find("#client_name").html());
    jQuery('#cl_surname').val(jQuery(client).find("#client_surname").html());
    jQuery('#cl_phone').val(jQuery(client).find("#client_phone").html());
    jQuery('#cl_email').val(jQuery(client).find("#client_email").html());
}

