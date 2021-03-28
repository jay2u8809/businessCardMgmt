package com.jayian.businesscard.web;

import com.jayian.businesscard.service.businesscard.BusinessCardService;
import com.jayian.businesscard.service.ocr.GoogleVisionApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MyCardInfoController {

    private final BusinessCardService businessCardService;

    private final GoogleVisionApi googleVisionApi;

    // move registration business card page

    // register business card data

    // get my business card list

    // get my business card info detail

    // download business card image

    // update my business card data

    // delete my business card data

    // set rep business card info

    // get received business card list

    // detect text and data from uploaded image (business card picture)

}