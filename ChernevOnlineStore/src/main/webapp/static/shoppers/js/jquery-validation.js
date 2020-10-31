$('document').ready(function () {
    $('.js-form-validate').submit(function () {
        var form = $(this);
        var password, password_repeat;
        form.find('input[data-validate]').each(function () {
            var value = $(this).val(),
                name = $(this).attr('name'),
                line = $(this).closest('.form-group');
            is_valid();
            if (name == 'email-address') {
                is_valid(/^.+@.+\..+$/);
            }
            if (name == 'phone-number') {
                is_valid(/^\+38 \d{3} \d{3} \d{4}$/);
            }
            if (name == 'password') {
                password = $(this);
            }
            if (name == 'password-repeat') {
                password_repeat = $(this);
            }
            if (name == 'card-numbers') {
                is_valid(/^\d{4}-\d{4}-\d{4}-\d{4}$/);
            }

            function is_valid(regex) {
                if (!value || !value.match(regex)) {
                    line.addClass('some-form__line-required');
                    event.preventDefault();
                } else {
                    line.removeClass('some-form__line-required');
                }
            }
        });
        if (password.val() != password_repeat.val()) {
            password.closest('.form-group').addClass('some-form__line-required');
            password_repeat.closest('.form-group').addClass('some-form__line-required');
            event.preventDefault();
        }
    });
});