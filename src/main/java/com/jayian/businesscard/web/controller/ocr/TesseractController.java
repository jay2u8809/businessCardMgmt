package com.jayian.businesscard.web.controller.ocr;

import com.jayian.businesscard.common.CommonExtends;
import com.jayian.businesscard.common.utils.FileService;
import com.jayian.businesscard.service.ocr.TesseractApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TesseractController extends CommonExtends {

    @Autowired
    private TesseractApi tesseractApi;

    @PostMapping("/api/v1/ocr/tesseract/process/")
    public ResponseEntity<?> process(@RequestParam("file") MultipartFile uploadFile) {

        String ocrResult = null;
        try {
            File file = FileService.convertMultipartToFile(uploadFile);
            ocrResult = (file == null ) ? null : tesseractApi.detectTextFromImage(file);
        } catch (IOException e) {
            logger.error("FAILED TO PROCESS TESSERACT (Controller) : {}", e.getMessage());
        }

        Map<String, String> result = new HashMap<>();
        result.put("ocrResult", ocrResult);

        return ResponseEntity.ok().body(result);
    }

}
