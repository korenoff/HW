package repos;
import Models.DocumentInfo;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
import Models.Document;
import repos.DocumentReposInt;
import org.hibernate.Session;

@Repository
public class DocumentRepSess implements DocumentReposInt {
    private final SessionFactory sessionFactory;
    public DocumentRepSess(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Document save(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.save(document);
        return document;
    }

    @Override
    public void update(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.update(document);
    }

    @Override
    public Document findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Document.class, id);
    }

    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Document document = findById(id);
        if (document != null) {
            session.remove(document);
        }
    }
    @Override
    public List<Document> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Document", Document.class).list();
    }
}
