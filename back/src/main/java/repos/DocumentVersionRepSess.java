package repos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import org.springframework.stereotype.Repository;
import Models.DocumentVersion;

@Repository
public class DocumentVersionRepSess implements DocumentVersionReposInt {
    private final SessionFactory sessionFactory;

    public DocumentVersionRepSess(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public DocumentVersion save(DocumentVersion documentVersion) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(documentVersion);
        return documentVersion;
    }

    @Override
    public DocumentVersion findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(DocumentVersion.class, id);
    }

    @Override
    public void delete(DocumentVersion documentVersion) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(documentVersion);
    }

    @Override
    public List<DocumentVersion> findAllByDocumentId(Integer documentId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                        "SELECT dv FROM DocumentVersion dv WHERE dv.documentId = :documentId",
                        DocumentVersion.class
                )
                .setParameter("documentId", documentId)
                .getResultList();
    }


}
