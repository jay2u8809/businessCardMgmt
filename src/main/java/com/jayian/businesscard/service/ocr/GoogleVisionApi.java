package com.jayian.businesscard.service.ocr;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import com.jayian.businesscard.common.dto.CommonExtends;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleVisionApi extends CommonExtends {

    /**
     * make Image Data
     * @param filePath
     * @return
     * @throws IOException
     */
    private Image generateImage(String filePath) throws IOException {

        logger.debug("File Path : {}", filePath);

        Image image;

        // Image from GCS
        if (filePath.startsWith("gs://")) {

            ImageSource imgSource = ImageSource.newBuilder()
                                        .setGcsImageUri(filePath)
                                        .build();
            image = Image.newBuilder()
                        .setSource(imgSource)
                        .build();
        }
        // Image from Local
        else {

            ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));
            image = Image.newBuilder().setContent(imgBytes).build();
        }

        return image;
    }


    /**
     * detect text from image
     * @param filePath
     * @return
     * @throws Exception
     */
    public String detectTextFromImage(String filePath) throws Exception {

        logger.debug("File Path : {}", filePath);

        String detectText = null;

        Image image = this.generateImage(filePath);

        Feature feature = Feature.newBuilder()
                            .setType(Feature.Type.TEXT_DETECTION)
                            .build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                            .addFeatures(feature)
                            .setImage(image)
                            .build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {

            BatchAnnotateImagesResponse response = client.batchAnnotateImages(Collections.singletonList(request));
            List<AnnotateImageResponse> responseList = response.getResponsesList();

            for (AnnotateImageResponse res : responseList) {

                if (res.hasError()) {
                    detectText = res.getError().getMessage();
	                logger.error("Detect OCR Error : {}", detectText);
	                break;
                }
                // For full list of available annotations, see http://g.co/cloud/vision/docs
                detectText = res.getTextAnnotationsList().get(0).getDescription();
	            logger.debug("Detect OCR Text : {}", detectText);
            }
        }

        return detectText;
    }

}

