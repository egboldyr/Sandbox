/**
 * Created by egboldyr on 19.03.18.
 */

function createRequisition(event) {
    jQuery.ajax({
        type: 'POST',
        url: '/new_requisition',
        data: {
            name: jQuery('#name').val(),
            phone: jQuery('#phone').val(),
            email: jQuery('#email').val(),
            comment: jQuery('#comment').val()
        },
        success: function () {
            event.preventDefault();
        },
        error: function (errorThrown) {
            console.log(errorThrown);
        }
    })
}