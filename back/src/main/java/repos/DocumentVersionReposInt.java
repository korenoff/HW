package repos;

import java.util.List;
import Models.DocumentVersion;

public interface DocumentVersionReposInt {
    DocumentVersion save(DocumentVersion documentVersion);
    DocumentVersion findById(long id);
    void delete(DocumentVersion documentVersion);
    List<DocumentVersion> findAllByDocumentId(Integer documentId);
}
