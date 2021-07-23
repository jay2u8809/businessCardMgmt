package com.jayian.businesscard.service.ocr;

import com.jayian.businesscard.common.CommonExtends;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class TesseractApi extends CommonExtends {

    /**
     * Tesseract OCR
     * @param file
     * @return
     */
    public String detectTextFromImage(File file) {

        String result = null;

        // Tesseract Option Setting
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("eng");
        tesseract.setDatapath(file.getPath());

        try {
            BufferedImage image = ImageIO.read(file);
            result = tesseract.doOCR(image);
        } catch (TesseractException ignored) {
            logger.error("FAILED TO PROCESS TESSERACT OCR : {}", ignored.getMessage());
        } catch (IOException e) {
            logger.error("FAILED TO PROCESS IMAGE : {}", e.getMessage());
        }

        return result;
    }
}
