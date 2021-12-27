package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.utils.MediaTypeHelper;
import com.eventidge.eventidgeapi.api.utils.ResourceUriHelper;
import com.eventidge.eventidgeapi.domain.exception.NotFoundException;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupQrCode;
import com.eventidge.eventidgeapi.domain.model.user.UserPhoto;
import com.eventidge.eventidgeapi.domain.service.FileStorageService;
import com.eventidge.eventidgeapi.domain.service.QrCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/meetups/qrcode")
public class MeetupQrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping(value = "/{meetupTag}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> download(@PathVariable String meetupTag, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
            MeetupQrCode meetupQrCode = qrCodeService.findOrFail(meetupTag);
            MediaType qrCodeMediaType = MediaType.parseMediaType(meetupQrCode.getContentType());
            List<MediaType> acceptedMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            MediaTypeHelper.checkMediaTypes(qrCodeMediaType, acceptedMediaTypes);

            FileStorageService.FileRecovered qrCodeRecovered = fileStorageService.toRecover(meetupQrCode.getFileName());

            return ResponseEntity
                    .status(HttpStatus.FOUND)
//                    .contentLength(meetupQrCode.getLength())
                    .contentType(qrCodeMediaType)
                    .header(HttpHeaders.LOCATION, qrCodeRecovered.getUrl())
                    .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                    .build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
