$(document).ready(function () {
    $("input[name='count']").change(function () {
        var input = $(this);
        $.ajax({
            url: 'cart',
            data: {
                productId: input.prev().val(),
                command: 'setCount',
                count: input.val()
            },
            success: function (response) {
                var responseParts = response.split(' ');
                $('.count').text(responseParts[1]);
                input.parent().parent().parent().find('td.total-price').text('$' + responseParts[0]);
                total();
            }
        });
    });
    $("form[name='formCount']").submit(function () {
        var input = $(this).children();
        $.ajax({
            url: 'cart',
            data: {
                productId: input.prev().val(),
                command: 'setCount',
                count: input.val()
            },
            success: function (response) {
                var responseParts = response.split(' ');
                $('.count').text(responseParts[1]);
                input.parent().parent().parent().find('td.total-price').text('$' + responseParts[0]);
                total();
            }
        });
    });
});

