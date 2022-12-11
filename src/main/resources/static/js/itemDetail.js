let index = {
    init: function (){
        $("#btn_delete").on("click", ()=>{
            if (confirm("정말 삭제하시겠습니까?"))
                this.delete_proc();
        })
        setTimeout(callback, 50);
    },
    delete_proc : function() {
        let id = $("#id").val();

        $.ajax({
            type: "DELETE",
            url: "/admin/deleteItem/" + id,
            //data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터 타입(MIME)
            dataType: "json" //응답이 json이라면 javascript오브젝트로 변경 (기본값 String)
        }).done(function(resp){
            if (resp.status == 200) {
                alert("상품이 삭제되었습니다.");
                location.href="/";
            }
            else
                console.log(resp);
        }).fail(function(err){
            alert(JSON.stringify(err));
        });
    }
}

index.init()