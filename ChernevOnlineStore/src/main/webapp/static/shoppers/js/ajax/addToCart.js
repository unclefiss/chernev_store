$(document).ready(function () {
    $('.addProduct').click(function () {
        var button = $(this);
        $.ajax({
            url: 'cart',
            data: {
                productId: button.prev().val(),
                command: 'addToCart'
            },
            success: function (response) {
                $('.count').text(response);
            }
        });
    });
});