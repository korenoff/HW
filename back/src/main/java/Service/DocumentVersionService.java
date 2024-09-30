package Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repos.DocumentVersionReposInt;
import repos.DocumentVersionReposInt;
import Dop.CreateDocVersion;
import Dop.DocVersion;
import Models.Document;
import Models.DocumentVersion;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentVersionService {
    private final DocumentVersionReposInt documentVersionReposInt;

    private Integer numberCount;

    private DocVersion mapToDTO(DocumentVersion documentVersion) {
        DocVersion docVersion = new DocVersion();
        docVersion.setNumber(numberCount++);
        docVersion.setDocumentVersionId(documentVersion.getDocumentVersionId());
        docVersion.setDocumentId(documentVersion.getDocumentId());
        docVersion.setVersionAuthor(documentVersion.getVersionAuthor());
        return docVersion;
    }
    public DocumentVersionService(DocumentVersionReposInt documentVersionReposInt) {
        this.documentVersionReposInt = documentVersionReposInt;
    }

    @Transactional
    public void saveDocumentVersion(CreateDocVersion createDocVersion, long documentId) {
        DocumentVersion documentVersion = new DocumentVersion();
        byte[] bytes = createDocVersion.getContent();
        documentVersion.setVersionAuthor(createDocVersion.getVersionAuthor());
        documentVersion.setDocumentId(documentId);
        documentVersionReposInt.save(documentVersion);
    }



    @Transactional
    public DocumentVersion findById(Long id) {
        return documentVersionReposInt.findById(id);
    }

    @Transactional
    public void saveDocumentVersion(DocumentVersion documentVersion) {
        documentVersionReposInt.save(documentVersion);
    }

    @Transactional
    public List<DocVersion> getDocumentVersionById(long documentVersionId) {
        int documentId = (int) documentVersionId; // Преобразование long в integer
        return documentVersionReposInt.findAllByDocumentId(documentId)
                .stream()
                .sorted(Comparator.comparing(DocumentVersion::getDocumentId))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}