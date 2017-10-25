
$(document).ready(function(){
    searchRelease();
});

function searchRelease(){

    var contextRoot = "/" + window.location.pathname.split( '/' )[1];

    $.ajax({
        dataType : "json",
        url : "search/"+projectId+"/"+versionNumber,
        headers : {
            'Accept' : 'application/json',
            'Content-Type' : 'application/json'
        },
        type: 'POST',
        // data:$('#formSearch').serialize(),
        success : function(responce) {

            /* what  i have to put here to updte my table <table id="table_grid"> */
            // $.each( responce,function(key, card) {
            //     var htmlrow ="<tr><td>" + card.name + "</td></tr>";
            //     $('#table_grid').append(htmlrow);
            // }

        },
        error : function(){
            alert("error");
        }
    });

    // var post = function post() {
    //     return $.post(contextPath + "search/" + projectId + "/" + versionNumber).fail(function(error) {
    //         alert("ERROR: " + error.responseText);
    //     });
    // }
    //
    // post().done(function(done) {
    //     location.reload();
    // });


}


