function genClientInterface() {
    createClientsTable();
    createEditClientInterface();

}

function createClientsTable() {
    jQuery('#workspace')
        .empty()
        .append('<table id="clients">')
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

function createEditClientInterface() {
    jQuery('#forms')
        .append('<form id="client_form" class="client_edit"></form>')
        .append('<form id="account_form" class="account_form"></form>');
    jQuery('#client_form')
        .append('<p>Name</p>')
        .append('<input id="cl_name" type="text">')
        .append('<p>Surname</p>')
        .append('<input id="cl_surname" type="text">')
        .append('<p>Phone number</p>')
        .append('<input id="cl_phone" type="text">')
        .append('<p>Email</p>')
        .append('<input id="cl_email" type="text">')
        .append('<input id="cl_phone" type="button" value="Update" onclick="return updateClient()">');
}

