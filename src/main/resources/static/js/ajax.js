/**
 * Submitting form with jquery ajax
 * @param formId String form id to selecting is required
 * @param formAction String form action to send request is required
 * @param formMethod String form method is required
 */
function submitForm(formId, formAction, formMethod) {
    let fd = new FormData(document.getElementById(formId))

    $.ajax({
        url: formAction,
        data: fd,
        cache: false,
        contentType: false,
        processData: false,
        method: formMethod,
        type: formMethod, // For jQuery < 1.9
        success: response => {
                console.log(response)
            if (response.hasOwnProperty('message')) {
                alert(response.message)
            }
            if (response.hasOwnProperty('redirect')) {
                window.location.href = response.redirect
            }
        },
        error: (jqXHR, exception) => {
            if (jqXHR.responseText.length) {
                let errs = JSON.parse(jqXHR.responseText)
                let inputs = $(":input")

                for (const inp of inputs) {
                    inp.classList.remove('is-invalid', 'is-valid')
                    inp.removeAttribute('title')
                }

                for (const inp of inputs) {
                    let name = inp.name
                    if (errs.hasOwnProperty(name)) {
                        console.log(1)
                        $(inp).addClass('is-invalid')
                        $(inp).tooltip({
                            'trigger': 'hover focus',
                            'placement': 'right',
                            'animation': true,
                            'title': errs[name]
                        });
                    } else {
                        console.log(2)
                        $(inp).tooltip('dispose')
                        $(inp).addClass('is-valid')
                    }
                }
                if (errs.hasOwnProperty('message'))
                    alert(errs['message'])
            }
        }
    });
}