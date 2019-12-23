$(document).ready(function () {
    registerSearch();
    registerTemplate();
});

function registerSearch() {
    $("#search").submit(function (ev) {
        ev.preventDefault();
        $.get($(this).attr('action'), {q: $("#q").val(), MaxTweets: $("#MaxTweets").val()}, function (data) {
            $("#resultsBlock").html(Mustache.render(template, data));
        });
    });
}

function registerTemplate() {
    template = $("#template").html();
    Mustache.parse(template);
}
