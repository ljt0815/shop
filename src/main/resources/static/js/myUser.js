let index = {
    init: function (){
        $("#btn--idDupChk").on("click",()=>{
            this.dupChk();
        });
    },
    dupChk: function(){
        let data = {
            email: $("#email").val()
        }

        $.ajax({
            type: "POST",
            url: "/auth/idDupChk",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터 타입(MIME)
            dataType: "json" //응답이 json이라면 javascript오브젝트로 변경 (기본값 String)
        }).done(function(resp){
            if (resp.status == 200) {
                if (resp.data == 1)
                    console.log("jjjj");
            }
            else
                console.log(resp);
        }).fail(function(err){
            alert(JSON.stringify(err));
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
    }
}

index.init()