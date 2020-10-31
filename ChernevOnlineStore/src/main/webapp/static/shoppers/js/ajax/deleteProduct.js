$(document).ready(function () {
    $('.deleteProduct').click(function () {
        var button = $(this);
        $.ajax({
            url: 'cart',
            data: {
                productId: button.prev().val(),
                command: 'deleteProduct'
            },
            success: function (response) {
            }
        });
    });
});