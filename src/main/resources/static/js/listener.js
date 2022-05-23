/**
 * click listener to data-href attribute
 */
$($ => $(".clickable-row")
    .click(function () {
            window.location.replace($(this).data("href"))
        }
    ));