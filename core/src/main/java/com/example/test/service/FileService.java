package com.example.test.service;

import com.example.test.common.exception.ServiceException;
import com.example.test.dao.FileDao;
import com.example.test.domain.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FileService {

    private final FileDao fileDao;

    public File saveFile(MultipartFile file) {
        log.trace("Saving a new file with name: {}.", file.getName());
        File attachment;
        try {
            attachment = fileDao.save(File.builder()
                    .name(file.getName())
                    .content(file.getBytes())
                    .uid(UUID.randomUUID().toString())
                    .build());
        } catch (IOException ex) {
            throw new ServiceException("Unexpected error occurred while trying to read byte content.");
        }
        log.trace("Saving a new file with uid: {} has completed successfully.", attachment.getUid());
        return attachment;
    }

    public File getFile(String uid) {
        log.trace("Getting a new file with name: {}.", uid);
        File file = fileDao.getByUid(uid).orElseThrow(() -> new ServiceException("There is no file with uid " + uid));
        log.trace("Getting a new file with uid: {} has completed successfully.", uid);
        return file;
    }

}
