package com.jitlee.shop.controller.api;

import com.jitlee.shop.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ImageApiController {

    @Value("${my.tmpImgConnectPath}")
    private String connectPath;
    @Value("${my.externalTmpStorage}")
    private String externalStorage;

    @PostMapping("/uploadSummernoteImageFile")
    public ResponseDto<Map> uploadSummernote(@RequestParam("file")MultipartFile multipartFile) {

        Map<String, String> rtn = new HashMap<>();
        String originFileName = multipartFile.getOriginalFilename();
        String extension = originFileName.substring(originFileName.lastIndexOf("."));
        String savedFileName = UUID.randomUUID() + extension;
        File dest = new File(externalStorage + savedFileName);
        try {
            multipartFile.transferTo(dest);
            rtn.put("url", connectPath + savedFileName);
            rtn.put("imgName", savedFileName);
            rtn.put("responseCode", "success");
        } catch (IOException e) {
            dest.delete();
            rtn.put("responseCode", "error");
            e.printStackTrace();
        }
        return new ResponseDto<>(HttpStatus.OK.value(), rtn);
    }
}
