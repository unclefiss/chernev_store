function validation() {
    var inputs = document.querySelector('.js-form-validate').elements;
    var valid = true;
    var password, password_repeat;
    for (var i = 0; i < inputs.length; i++) {
        var input = inputs.item(i);
        valid = isValid(input);
        if (input.name == 'email-address') {
            valid = isValid(input, /^.+@.+\..+$/);
        }
        if (input.name == 'phone-number') {
            valid = isValid(input, /^\+38 \d{3} \d{3} \d{4}$/);
        }
        if (input.name == 'password') {
            password = input
        }
        if (input.name == 'password-repeat') {
            password_repeat = input
        }
    }
    if (password.value != password_repeat.value) {
        valid = false;
        password.parentNode.classList.add('some-form__line-required');
        password_repeat.parentNode.classList.add('some-form__line-required');
    }
    if (valid == false) {
        event.preventDefault();
    }
    return valid;
}

function isValid(input, regex) {
    var valid = true;
    if (!input.value || !(input.value.match(regex))) {
        input.parentNode.classList.add('some-form__line-required');
        valid = false;
    } else {
        input.parentNode.classList.remove('some-form__line-required');
    }
    return valid;
}