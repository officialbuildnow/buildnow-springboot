package com.buildnow.springbootapp.buildnowspringboot.service;

import com.google.cloud.documentai.v1.*;
import com.google.protobuf.ByteString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DocumentService {
    @Value("${documentai.projectId}")
    private String projectId;

    @Value("${documentai.location}")
    private String location;

    @Value("${documentai.processorId}")
    private String processorId;

    @Value("${documentai.processorVersion}")
    private String processorVersion;

    public Map<String, String> processDocumentFromUrl(String fileUrl) throws IOException{
        log.error(fileUrl);
        Path tempDir = Files.createTempDirectory("documentai_");
        String fileName = "temp.pdf";
        Path filePath = tempDir.resolve(fileName);

        try(InputStream in = new URL(fileUrl).openStream()){
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return processDocument(filePath.toString());

    }

    public Map<String, String> processDocument(String filePath) throws IOException {
        Map<String, String> entityMap = new HashMap<>();
        String endpoint = String.format("%s-documentai.googleapis.com:443", location);
        DocumentProcessorServiceSettings settings = DocumentProcessorServiceSettings.newBuilder().setEndpoint(endpoint).build();
        try(DocumentProcessorServiceClient client = DocumentProcessorServiceClient.create(settings)){
            String name = String.format("projects/%s/locations/%s/processors/%s/processorVersions/%s", projectId, location, processorId, processorVersion);
            byte[] data = Files.readAllBytes(Paths.get(filePath));
            ByteString content = ByteString.copyFrom(data);

            RawDocument document = RawDocument.newBuilder().setContent(content).setMimeType("application/pdf").build();
            ProcessRequest request = ProcessRequest.newBuilder().setName(name).setRawDocument(document).build();
            ProcessResponse result = client.processDocument(request);
            Document documentResponse = result.getDocument();
            List<Document.Entity> entities = documentResponse.getEntitiesList();
            for(Document.Entity entity: entities) {
                String type = entity.getType();
                String value = entity.getMentionText();
                entityMap.put(type, value);
            }


            return entityMap;
        }
    }

    private static String getText(Document.TextAnchor textAnchor, String text) {
        if (!textAnchor.getTextSegmentsList().isEmpty()) {
            int startIdx = (int) textAnchor.getTextSegments(0).getStartIndex();
            int endIdx = (int) textAnchor.getTextSegments(0).getEndIndex();
            return text.substring(startIdx, endIdx);
        }
        return "[NO TEXT]";
    }

}
