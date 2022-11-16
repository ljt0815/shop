let index = {
    init: function (){
        $("#btn--idDupChk").on("click",()=>{
            this.dupChk();
        });
        $("#btn-joinProc").on("click",()=>{
            this.joinProc();
        });
        $("#pwd").keyup(()=> {
            $("#label-pwd-alert").text("사용가능한 비밀번호입니다.");
        });
        $("#re-pwd").keyup(()=>{
            let label = $("#label-re-pwd-alert");
            if ($("#pwd").val()===$("#re-pwd").val()) {
                label.text("비밀번호가 일치 합니다.");
                label.removeClass("text-danger");
                label.addClass("text-success");
            }
            else {
                label.text("비밀번호가 일치하지 않습니다.");
                label.removeClass("text-success");
                label.addClass("text-danger");
            }
        });
        document.addEventListener('keydown',function(event) {
            if (event.keyCode === 13) {
                event.preventDefault();
            };
        }, true); //엔터키 막기
    },
    dupChk: function(){
        let data = {
            username: $("#id").val()
        }
        $.ajax({
            type: "POST",
            url: "/auth/idDupChk",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터 타입(MIME)
            dataType: "json" //응답이 json이라면 javascript오브젝트로 변경 (기본값 String)
        }).done(function(resp){
            if (resp.status == 200) {
                let label = $("#label-alert");
                if (resp.data == 1) {
                    label.removeClass("text-danger");
                    label.addClass("text-success");
                    label.text("사용 가능한 아이디입니다.");
                }
                else if (resp.data == -1) {
                    label.removeClass("text-success");
                    label.addClass("text-danger");
                    label.text("이미 사용중인 아이디 입니다.");
                }
            }
            else
                console.log(resp);
        }).fail(function(err){
            alert(JSON.stringify(err));
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
    },
    joinProc: function(){
        let data = {
            username: $("#id").val(),
            password: $("#pwd").val(),
            email: $("#email").val()
        }
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터 타입(MIME)
            dataType: "json" //응답이 json이라면 javascript오브젝트로 변경 (기본값 String)
        }).done(function(resp){
            if (resp.status == 200) {
                alert("회원가입이 완료되었습니다.");
                location.href="/";
            }
            else
                console.log(resp);
        }).fail(function(err){
            alert(JSON.stringify(err));
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
    }
}

index.init()