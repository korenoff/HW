package repos;
import java.util.List;
import Models.Document;

public interface DocumentReposInt {
    Document save(Document document);
    void update(Document document);
    Document findById(long id);
    void deleteById(long id);
    List<Document> findAll();
}
