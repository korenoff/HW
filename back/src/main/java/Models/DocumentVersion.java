package Models;
import javax.persistence.*;

@Entity
@Table(name = "documentversion")


public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documentVersionId", nullable = false)
    private long documentVersionId;

    @Column(name = "documentid")
    private long documentId;

    @Column(name = "versionauthor")
    private String versionAuthor;

    @Lob
    @Column(name = "content")
    private byte[] content;

    public long getDocumentVersionId() {
        return documentVersionId;
    }

    public void setDocumentVersionId(long documentVersionId) {
        this.documentVersionId = documentVersionId;
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public String getVersionAuthor() {
        return versionAuthor;
    }

    public void setVersionAuthor(String versionAuthor) {
        this.versionAuthor = versionAuthor;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}


