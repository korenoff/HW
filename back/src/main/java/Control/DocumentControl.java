package Control;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import Service.DocumentService;
import Service.DocumentVersionService;
import Service.DocumentInfoService;
import Dop.*;
import Models.*;

@RestController
@RequestMapping("/api/doc")
public class DocumentControl {
    private final DocumentService documentService;
    private final DocumentInfoService documentInfoService;
    private final DocumentVersionService documentVersionService;
    public DocumentControl(DocumentService documentService , DocumentInfoService documentInfoService , DocumentVersionService documentVersionService) {
        this.documentService = documentService;
        this.documentInfoService = documentInfoService;
        this.documentVersionService = documentVersionService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateDocInfo> createDoc(@ModelAttribute CreateDoc createDoc) {
        CreateDocInfo createDocInfo = new CreateDocInfo();
        createDocInfo.setDocumentId(documentService.createDoc(createDoc));
        return ResponseEntity.ok(createDocInfo);
    }

    @PostMapping("/remove")
    public ResponseEntity<String> RemoveDocVersion(@RequestBody RemoveDocVersion removeDocVersion) {
        documentInfoService.RemoveDocVersion(documentService.findDocument(removeDocVersion.getDocumentId()), removeDocVersion);
        return ResponseEntity.ok(removeDocVersion.getDocumentExternNumber());
    }

    @PostMapping("/newversiondoc")
    public ResponseEntity<String> newVersionDoc(@ModelAttribute CreateDocVersion createDocVersion) {
        long documentId = createDocVersion.getDocumentId(); // Получение идентификатора документа
        documentVersionService.saveDocumentVersion(createDocVersion, documentId);
        return ResponseEntity.ok(HttpHeaders.ACCEPT);
    }

    @GetMapping("/versiondoc/{id}")
    public ResponseEntity<List<DocVersion>> getAllVersionDocByDocId(@PathVariable("id") Long docId) {
        return ResponseEntity.ok(documentVersionService.getDocumentVersionById(docId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> findDocument(@PathVariable("id") Long id) {
        return ResponseEntity.ok(documentService.findDocument(id));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CreateDocInfo>> findAllDocuments() {
        return ResponseEntity.ok(documentService.findAllDocs());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity.BodyBuilder deleteDoc(@PathVariable("id") Long id) {
        documentService.deleteDoc(id);
        return ResponseEntity.ok();
    }

    @GetMapping("/versiondocfile/{id}")
    public ResponseEntity<byte[]> getFileByVersionDocId(@PathVariable("id") Long docId) {// Создание экземпляра DocumentVersionService
        DocumentVersion documentVersion = documentVersionService.findById(docId);
        HttpHeaders header = new HttpHeaders();
        header.setContentLength(documentVersion.getContent().length);
        header.set("Content-Disposition", "attachment; filename=" + documentVersion.getVersionAuthor());
        return new ResponseEntity<>(documentVersion.getContent(), header, HttpStatus.OK);
    }

}
