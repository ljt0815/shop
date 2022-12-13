let index = {
    init: function (){
        $("#btn_delete").on("click", ()=>{
            if (confirm("정말 삭제하시겠습니까?"))
                this.delete_proc();
        });
        $("#quantityUp").on("click", ()=>{
            let num = $("#quantity").val();
            $("#quantity").val(++num);
        });
        $("#quantityDown").on("click", ()=>{
            let num = $("#quantity").val();
            if (num > 1)
                $("#quantity").val(--num);
        });
        $("#quantity").keydown(function(e) {
            let a = false;
            let b = false;

            if (!(e.keyCode >= 96 && e.keyCode <= 105)) {
                a = true;
            }
            if (!(e.keyCode >= 48 && e.keyCode <= 57)) {
                b = true;
            }
            if (a && b) {
                if (e.keyCode == 8)
                    return ;
                e.preventDefault();
            }
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
    },
    upProc : function() {

    }
}

index.init()