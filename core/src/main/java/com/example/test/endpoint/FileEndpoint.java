package com.example.test.endpoint;

import com.example.test.domain.api.Response;
import com.example.test.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileEndpoint {

    private final FileService fileService;

    @GetMapping("/{uid}")
    public Response uploadFile(@RequestParam String uid) {
        return Response.from(() -> fileService.getFile(uid));
    }

    @PostMapping("/upload")
    public Response uploadFile(@RequestBody MultipartFile file) {
        return Response.from(() -> fileService.saveFile(file));
    }

}
