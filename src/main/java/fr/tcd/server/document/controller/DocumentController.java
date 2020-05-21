package fr.tcd.server.document.controller;

import fr.tcd.server.document.dto.DocumentDTO;
import fr.tcd.server.document.model.DocumentModel;
import fr.tcd.server.document.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity createDocument(@Valid @RequestBody DocumentDTO documentDTO,
                                         @RequestHeader("Authorization") String authorization,
                                         @RequestParam(required = false, name = "file") MultipartFile file) {

        //TODO: Do a Switch on Type of data between file or link and process
        //TODO: DocumentService.createDocument(documentDTO);
        //TODO: Get the user id from the authorization and add it to the created Document
        /*DocumentModel newDocumentModelId = processNewTask(documentDTO);
        if(newDocumentModelId != null) {
            return new ResponseEntity<>(newDocumentModelId, HttpStatus.CREATED);
        }
        */
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping
    public ResponseEntity newTask(@RequestParam("file") MultipartFile file, @Valid @RequestBody DocumentDTO documentDTO) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.UNAUTHORIZED);
        }

        content = loadFileContent(file); // Définir la fonction et vérifier le format
        documentDTO.data.put("value", content);
        DocumentModel newDocumentModelId = processNewTask(documentDTO);

        if(newDocumentModelId != null) {
            return new ResponseEntity<>(newDocumentModelId, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    @PostMapping("/scan")
    public ResponseEntity singleFileUpload(@RequestParam("file") MultipartFile file,
                                           RedirectAttributes redirectAttributes) {

        String url = "https://api.ocr.space/parse/image?language=fre";
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.UNAUTHORIZED);
        }

        Path filePath;
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();

            File directory = new File(UPLOAD_FOLDER);
            if(!directory.exists()) {
                directory.mkdir();
            }

            filePath = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(filePath, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occured during the download of your file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", ocrToken);
        headers.set("filetype", "PNG");

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(filePath));

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String, Object>>() {
        });

        Map<String, Object> final_response = response.getBody();
        ArrayList<Map<String, Object>> ParsedResults = (ArrayList) final_response.get("ParsedResults");

        Map<String, Object> parsedResult = ParsedResults.get(0);

        String result_ocr = (String) parsedResult.get("ParsedText");

        return new ResponseEntity<>(result_ocr, HttpStatus.OK);
    }
    */
    // ============== NON-API ==============

}
