$(document).ready(function () {
    $('.incCount').click(function () {
        var button = $(this);
        $.ajax({
            url: 'cart',
            data: {
                productId: button.prev().val(),
                command: 'incCount'
            },
            success: function (response) {
                var count = parseInt($('.count').text());
                $('.count').text(count + 1);
                var productCount = parseInt(button.closest(".input-group-append").prev().val());
                button.closest(".input-group-append").prev().val(productCount + 1);
                button.parent().parent().parent().parent().find('td.total-price').text('$' + response);
                total();
            }
        });
    });
});

function total() {
    var prices = $('td.total-price').text().split('$');
    var totalSum = 0;
    prices.forEach(function (item, index, array) {
        if (index > 0) {
            totalSum = totalSum + parseInt(item);
        }
    });
    $('.total').text('$' + totalSum);
}