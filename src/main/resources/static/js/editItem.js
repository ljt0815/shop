let index = {
    init: function (){
        $('#summernote').summernote({
            toolbar: [
                // [groupName, [list of button]]
                ['fontname', ['fontname']],
                ['fontsize', ['fontsize']],
                ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
                ['color', ['forecolor','color']],
                ['table', ['table']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']],
                ['insert',['picture','link','video']],
                ['view', ['fullscreen', 'help']]
            ],
            fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
            fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
            height: 300,                 // 에디터 높이
            minHeight: null,             // 최소 높이
            maxHeight: null,             // 최대 높이
            lang: "ko-KR",					// 한글 설정
            placeholder: '상품 소개',	//placeholder 설정
            callbacks: {	//여기 부분이 이미지를 첨부하는 부분
                onImageUpload : function(files) {
                    uploadSummernoteImageFile(files[0],this);
                },
                onPaste: function (e) {
                    let clipboardData = e.originalEvent.clipboardData;
                    if (clipboardData && clipboardData.items && clipboardData.items.length) {
                        let item = clipboardData.items[0];
                        if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
                            e.preventDefault();
                        }
                    }
                }
            }
        });
        $("#btn_upload").on("click", ()=>{
            $("#chooseFile").click();
        });
        $("#chooseFile").on("change", (e)=>{
            $("#preview").removeAttr("hidden");
            this.previewImg(e);
        });
        function uploadSummernoteImageFile(file, editor) {
            let data = new FormData();
            data.append("file", file);
            $.ajax({
                data : data,
                type : "POST",
                url : "/admin/uploadSummernoteImageFile",
                contentType : false,
                processData : false,
                success : function(resp) {
                    //항상 업로드된 파일의 url이 있어야 한다.
                    $(editor).summernote('insertImage', resp.data.url);
                    const image = $("<input type='hidden' value='" + resp.data.imgName + "' name='images' readonly>");
                    $("#registerForm").append(image);
                }
            });
        }
    },
    previewImg: function(e){
        let file = e.target.files[0];
        let reader = new FileReader();
        reader.onload = function(e) {
            $("#preview").attr("src", e.target.result);
        };
        reader.readAsDataURL(file);
    }
}

index.init()