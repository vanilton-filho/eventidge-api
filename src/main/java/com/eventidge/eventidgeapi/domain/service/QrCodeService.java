package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.NotFoundException;
import com.eventidge.eventidgeapi.domain.model.meetup.Meetup;
import com.eventidge.eventidgeapi.domain.model.meetup.MeetupQrCode;
import com.eventidge.eventidgeapi.domain.repository.MeetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.InputStream;

@Service
public class QrCodeService {

    @Autowired
    private MeetupRepository meetupRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public MeetupQrCode findOrFail(String tag) {
        return meetupRepository.findQrCodeByMeetupId(tag)
                .orElseThrow(() -> new NotFoundException("Qr Code not exists"));
    }

    @Transactional
    public void save(Meetup meetup, InputStream inputStream) throws Exception {
        String fileName = fileStorageService.generateFileName(meetup.getTag());

        MeetupQrCode meetupQrCode = new MeetupQrCode();
        meetupQrCode.setMeetup(meetup);
        meetupQrCode.setFileName(fileName + ".png");
        meetupQrCode.setContentType("image/png");
        meetupQrCode = meetupRepository.save(meetupQrCode);
        meetupRepository.flush();

        FileStorageService.NewFile newQrCode = FileStorageService.NewFile.builder()
                        .fileName(meetupQrCode.getFileName())
                        .contentType("image/png")
                        .inputStream(inputStream)
                        .build();
        fileStorageService.toStore(newQrCode);

    }

    @Transactional
    public void delete(String meetupTag) {
        var meetupQrCode = findOrFail(meetupTag);

        meetupRepository.delete(meetupQrCode);
        meetupRepository.flush();
        fileStorageService.remove(meetupQrCode.getFileName());
    }

}
