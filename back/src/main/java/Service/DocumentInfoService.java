package Service;

import org.springframework.stereotype.Service;
import repos.DocumentInfoReposInt;
import Dop.RemoveDocVersion;
import Models.Document;
import Models.DocumentInfo;
import javax.transaction.Transactional;
import java.util.Date;

@Service
public class DocumentInfoService {
    private final DocumentInfoReposInt documentInfoReposInt;

    public DocumentInfoService(DocumentInfoReposInt documentInfoReposInt) {
        this.documentInfoReposInt = documentInfoReposInt;
    }

    @Transactional
    public void RemoveDocVersion(Document doc, RemoveDocVersion removeDocVersion) {
        DocumentInfo documentInfo = doc.getInfoDoc();
        documentInfo.setDocumentExternNumber(removeDocVersion.getDocumentExternNumber());
        documentInfo.setDateExtern(new Date());
        documentInfoReposInt.update(documentInfo);
    }

    @Transactional
    public DocumentInfo saveInfoDoc(DocumentInfo doc) {
        return documentInfoReposInt.save(doc);
    }

    @Transactional
    public void deleteInfoDoc(Long id) {
        documentInfoReposInt.deleteById(id);
    }

    @Transactional
    public DocumentInfo findInfoDoc(Long id) {
        return documentInfoReposInt.findById(id);
    }

    @Transactional
    public DocumentInfo findInfoDocByDocId(Long docId) {
        return documentInfoReposInt.findById(docId);
    }
}