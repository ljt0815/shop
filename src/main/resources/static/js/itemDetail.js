let index = {
    init: function (){
        if ($("#productImg").width() < $("#productImg").height()) {
            $("#productImg").attr('style', 'height: 100%');
        }
        else {
            $("#productImg").parent().removeClass("d-flex justify-content-center")
            $("#productImg").parent().attr('style', 'display: table-cell; vertical-align: middle; width: 410px; height: 410px; overflow: hidden; border: 1px solid black;');
            $("#productImg").attr('style', 'width: 100%;');
        };
    }
}

index.init()