package Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documentid", nullable = false)
    private long documentId;

    @Column(name = "documentname")
    private String documentName;

    @Column(name = "author")
    private String author;

    @OneToOne
    @JoinColumn(name = "infoDocId")
    private DocumentInfo documentInfo;
    @OneToMany(mappedBy = "document")
    private List<DocumentVersion> documentVersion;



    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }
    public Long getDocumentId() {
        return documentId;
    }
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public DocumentInfo getInfoDoc() {
        return documentInfo;
    }

    public void setInfoDoc(DocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }
    public List<DocumentVersion> getDocumentVersion() {
        return documentVersion;
    }

    public void setDocumentVersion(List<DocumentVersion> versionDocs) {
        this.documentVersion = documentVersion;
    }
}
