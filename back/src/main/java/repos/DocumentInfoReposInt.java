package repos;

import java.util.List;
import Models.DocumentInfo;

public interface DocumentInfoReposInt {
    DocumentInfo save(DocumentInfo documentInfo);
    void update(DocumentInfo documentInfo);
    DocumentInfo findById(long id);
    void deleteById(long id);
    DocumentInfo findByRegCardId(Long regCardId);
}
