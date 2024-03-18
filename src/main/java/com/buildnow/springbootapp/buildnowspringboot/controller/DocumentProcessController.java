package com.buildnow.springbootapp.buildnowspringboot.controller;

import com.buildnow.springbootapp.buildnowspringboot.dto.DocumentPayload;
import com.buildnow.springbootapp.buildnowspringboot.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("api")
@RequiredArgsConstructor
public class DocumentProcessController {
    private final DocumentService documentService;

    @PostMapping("process")
    public ResponseEntity<?> processDocument(DocumentPayload documentPayload) throws IOException {
        try{
            Map<String, String> result = documentService.processDocumentFromUrl(documentPayload.getUrl());
            return ResponseEntity.ok(result);
        } catch (Exception e){
            e.printStackTrace();
            // 오류 메시지를 담은 Map을 생성하여 반환
            Map<String, String> errorResponse = Collections.singletonMap("error", "Failed to process document: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);



        }
    }
}
