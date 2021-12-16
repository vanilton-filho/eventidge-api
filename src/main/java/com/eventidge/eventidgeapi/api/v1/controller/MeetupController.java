package com.eventidge.eventidgeapi.api.v1.controller;

import com.eventidge.eventidgeapi.api.utils.ResourceUriHelper;
import com.eventidge.eventidgeapi.api.v1.model.MeetupModel;
import com.eventidge.eventidgeapi.api.v1.model.input.EventModelInput;
import com.eventidge.eventidgeapi.api.v1.serializers.MeetupSerializer;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupQrCode;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.service.MeetupService;
import com.eventidge.eventidgeapi.domain.service.QrCodeService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/v1/meetups")
public class MeetupController {

    @Autowired
    private MeetupService meetupService;

    @Autowired
    private MeetupSerializer meetupSerializer;

    @Autowired
    private QrCodeService qrCodeService;

    @GetMapping("/{tagOrCode}")
    public ResponseEntity<MeetupModel> getByTagOrCode(@RequestParam("type") String type, @PathVariable String tagOrCode) {
        Meetup meetup  = null;
        if (type.equals("tag")) {
            meetup = meetupService.getByTag(tagOrCode);
        } else if (type.equals("code")) {
            meetup = meetupService.getByCode(tagOrCode);
        }

        var meetupModel = meetupSerializer.toModel(meetup);
        return ResponseEntity.ok(meetupModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MeetupModel> create(@RequestBody @Valid EventModelInput eventModelInput) throws Exception {
        Meetup meetup = meetupSerializer.toDomainObject(eventModelInput);

        meetup.setResponsible(new User());
        meetup.getResponsible().setId(1L); // TODO: Deixaremos fixo por enquanto até impl segurança na API
        Meetup meetupCreated = meetupService.save(meetup);

        String textToEncode = ResourceUriHelper.addUriResponseHeader("http://api.eventidge.local:8080/v1/meetups-registrations", meetup.getTag());
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                    barcodeWriter.encode(textToEncode, BarcodeFormat.QR_CODE, 300, 300);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "PNG", outputStream);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        qrCodeService.save(meetupCreated, inputStream);

        return ResponseEntity.ok(meetupSerializer.toModel(meetupCreated));
    }

}
