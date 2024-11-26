package com.example.movie.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@RequestMapping("/upload")
@Controller
public class UploadController {

    @Value("${com.example.movie.upload.path}")
    private String uploadPath;

    @GetMapping("/upload")
    public void getUpload() {
        log.info("업로드 폼 요청");

    }

    @PostMapping("/upload")
    public void postUpload(MultipartFile[] uploadFiles) {

        for (MultipartFile multipartFile : uploadFiles) {
            log.info("OriginalFilename {}", multipartFile.getOriginalFilename());
            log.info("Size {}", multipartFile.getSize());
            log.info("ContentType {}", multipartFile.getContentType()); // image/png

            // 이미지 파일 여부 확인
            if (!multipartFile.getContentType().startsWith("image")) {
                return;
            }

            // 사용자 파일명
            String originName = multipartFile.getOriginalFilename();

            // 오늘날짜 폴더명
            String saveFolderPath = makeFolder();

            // 파일 저장 - uuid(중복파일 파일명처리)
            String uuid = UUID.randomUUID().toString();
            // upload/2024/11/26/uuid_1.jpg
            // File.separator = 운영체제별 폴더 구분 패스(윈도우의 /)
            String saveName = uploadPath + File.separator + saveFolderPath + File.separator + uuid + "_" + originName;
            Path savePath = Paths.get(saveName);
            try {
                // 폴더 저장 코드
                multipartFile.transferTo(savePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private String makeFolder() {
        // sysdate 구하기
        LocalDate today = LocalDate.now();
        log.info("today {}", today); // 2024-11-26
        String dateStr = today.format(DateTimeFormatter.ofPattern("YYYY/MM/dd"));
        File dirs = new File(uploadPath, dateStr); // (부모폴더,자식폴더)
        if (!dirs.exists()) {
            dirs.mkdirs();// 실제 폴더 생성
        }
        // 오늘 날짜로 폴더 생성 //폴더 구조 / or \\
        // 날짜나 시간, 숫자 특정 포맷을 지정해 보고 싶을때 ->Formatter
        // SimpleDateFormat sdf = new SimpleDateFormat("YYYY/mm/dd");
        // sdf.format(new Date());
        return dateStr;
    }

}
