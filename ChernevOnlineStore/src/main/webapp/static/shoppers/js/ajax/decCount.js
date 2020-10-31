$(document).ready(function () {
    $('.decCount').click(function () {
        var button = $(this);
        $.ajax({
            url: 'cart',
            data: {
                productId: button.prev().val(),
                command: 'decCount'
            },
            success: function (response) {
                var count = parseInt($('.count').text());
                var productCount = parseInt(button.closest(".input-group-prepend").next().next().val());
                if (productCount !== 0) {
                    $('.count').text(count - 1);
                    button.closest(".input-group-prepend").next().next().val(productCount - 1);
                    button.parent().parent().parent().parent().find('td.total-price').text('$' + response);
                    total();
                }
            }
        });
    });
});