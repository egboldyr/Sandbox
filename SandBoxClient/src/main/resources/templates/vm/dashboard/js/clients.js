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
        jQuery('#tblBody').append('<tr id="cur_row' + i + '"></tr>');
        jQuery('#cur_row' + i)
            .append('<td id="client_id">' + result[i].id + '</td>')
            .append('<td id="client_name">' + result[i].name + '</td>')
            .append('<td id="client_surname">' + result[i].surname + '</td>')
            .append('<td id="client_phone">' + result[i].phone + '</td>')
            .append('<td id="client_email">' + result[i].email + '</td>')
            .append('<td id="client_login"></td>')
            .append('<td id="client_action"></td>');
    }
}

function createEditClientInterface() {
    jQuery('#forms')
        .empty()
        .append('<form id="client_form" class="client_edit"></form>')
        .append('<form id="account_form" class="account_edit"></form>');
    jQuery('#client_form')
        .append('<p>Name</p>')
        .append('<input id="cl_name" type="text">')
        .append('<p>Surname</p>')
        .append('<input id="cl_surname" type="text">')
        .append('<p>Phone number</p>')
        .append('<input id="cl_phone" type="text">')
        .append('<p>Email</p>')
        .append('<input id="cl_email" type="text">')
        .append('<input type="button" value="Update" onclick="return updateClient()">');
    jQuery('#account_form')
        .append('<p>Login</p>')
        .append('<input id="acc_login" type="text">')
        .append('<p>Password</p>')
        .append('<input id="acc_password" type="text">')
        .append('<input type="button" value="Create" onclick="return createAccount()">');
}

