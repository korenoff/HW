    package repos;

    import org.hibernate.SessionFactory;
    import org.springframework.stereotype.Repository;
    import Models.DocumentInfo;
    import org.hibernate.Session;

    @Repository
    public class DocumentInfoRepSess implements DocumentInfoReposInt {
        private final SessionFactory sessionFactory;
        public DocumentInfoRepSess(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }
        @Override
        public DocumentInfo save(DocumentInfo documentInfo) {
            Session session = sessionFactory.getCurrentSession();
            session.persist(documentInfo);
            return documentInfo;
        }
        @Override
        public void update(DocumentInfo documentInfo) {
            Session session = sessionFactory.getCurrentSession();
            session.merge(documentInfo);
        }
        @Override
        public DocumentInfo findById(long id) {
            Session session = sessionFactory.getCurrentSession();
            return session.find(DocumentInfo.class, id);
        }
        @Override
        public void deleteById(long id) {
            Session session = sessionFactory.getCurrentSession();
            DocumentInfo documentInfo = findById(id);
            if (documentInfo != null) {
                session.remove(documentInfo);
            }
        }
        public DocumentInfo findByRegCardId(Long regCardId) {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery("SELECT d FROM DocumentInfo d WHERE d.regCardId = :regCardId", DocumentInfo.class)
                    .setParameter("regCardId", regCardId)
                    .getSingleResult();
        }
    }
