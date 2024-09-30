package Service;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import Models.Document;
import Models.DocumentVersion;
import Models.DocumentInfo;
import repos.DocumentReposInt;
import repos.DocumentVersionReposInt;
import repos.DocumentInfoReposInt;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import Dop.CreateDocInfo;
import Dop.CreateDoc;
import Dop.CreateDocVersion;
import Dop.DocVersion;
import Dop.RemoveDocVersion;

@Service
public class DocumentService {
    private final DocumentReposInt documentReposInt;
    private final DocumentVersionService documentVersionService;
    private final DocumentInfoService documentInfoService;

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH-mm-ss");

    private CreateDocInfo mapToDto(Document doc) {
        CreateDocInfo createDocInfo = new CreateDocInfo();
        createDocInfo.setDocumentId(doc.getDocumentId());
        createDocInfo.setDocumentId(doc.getInfoDoc().getDocumentId());
        createDocInfo.setDocumentName(doc.getDocumentName());
        createDocInfo.setAuthor(doc.getAuthor());
        createDocInfo.setDocumentIntroNumber(doc.getInfoDoc().getDocumentIntroNumber());
        createDocInfo.setDocumentExternNumber(doc.getInfoDoc().getDocumentExternNumber());
        createDocInfo.setDateIntro(format.format(doc.getInfoDoc().getDateIntro()));
        createDocInfo.setDateExtern(doc.getInfoDoc().getDateExtern() == null ? "" : format.format(doc.getInfoDoc().getDateExtern()));
        createDocInfo.setDocumentExternNumber(doc.getInfoDoc().getDocumentExternNumber());
        return createDocInfo;
    }

    public DocumentService(DocumentReposInt documentReposInt, DocumentInfoService documentInfoService, DocumentVersionService documentVersionService) {
        this.documentReposInt = documentReposInt;
        this.documentInfoService = documentInfoService;
        this.documentVersionService = documentVersionService;
    }

    @Transactional
    public long createDoc(CreateDoc createDocData) {
        Document doc = new Document();
        DocumentInfo documentInfo = new DocumentInfo();
        DocumentVersion documentVersion = new DocumentVersion();
        byte[] bytes = createDocData.getContent();
        doc.setDocumentName(createDocData.getDocumentName());
        doc.setAuthor(createDocData.getAuthor());
        doc.setDocumentId(saveDoc(doc));
        documentInfo.setDocumentId(doc.getDocumentId());
        documentInfo.setDocumentIntroNumber(createDocData.getDocumentIntroNumber());
        documentInfo.setDateExtern(new Date());
        documentInfoService.saveInfoDoc(documentInfo);
        doc.setInfoDoc(documentInfo);
        documentReposInt.update(doc);
        documentVersion.setVersionAuthor(createDocData.getAuthor());
        documentVersion.setDocumentId(doc.getDocumentId());
        documentVersionService.saveDocumentVersion(documentVersion);
        return doc.getDocumentId();
    }

    @Transactional
    public List<CreateDocInfo> findAllDocs() {
        return documentReposInt.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Transactional
    public long saveDoc(Document doc) {
        return doc.getDocumentId();
    }


    @Transactional
    public void update(Document document) {
        documentReposInt.update(document);
    }

    @Transactional
    public void deleteDoc(long id) {
        documentReposInt.deleteById(id);
    }

    @Transactional
    public Document findDocument(Long id) {
        return documentReposInt.findById(id);
    }
}