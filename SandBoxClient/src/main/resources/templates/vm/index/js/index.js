function authorization(event) {
    jQuery.ajax({
        type: 'POST',
        url: '/authorization',
        data: {
            login: jQuery('#login').val(),
            password: jQuery('#password').val()
        },
        success: function () {
            event.preventDefault();
        },
        error: function (jqXhr, textStatus, errorThrown) {
            console.log( errorThrown );
        }
    });
}