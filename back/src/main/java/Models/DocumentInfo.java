package Models;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "documentinfo")

public class DocumentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regcardid", nullable = false)
    private long regCardId;
    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "documentid")
    private long documentId;

    @Column(name = "documentintronumber")
    private String documentIntroNumber;

    @Column(name = "documentexternnumber")
    private String documentExternNumber;

    @Column(name = "dateintro")
    private Date dateIntro;

    @Column(name = "dateextern")
    private Date dateExtern;

    public long getRegCardId() {
        return regCardId;
    }

    public void setRegCardId(long regCardId) {
        this.regCardId = regCardId;
    }

    public long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(long documentId) {
        this.documentId = documentId;
    }

    public String getDocumentIntroNumber() {
        return documentIntroNumber;
    }

    public void setDocumentIntroNumber(String documentIntroNumber) {
        this.documentIntroNumber = documentIntroNumber;
    }

    public String getDocumentExternNumber() {
        return documentExternNumber;
    }

    public void setDocumentExternNumber(String documentExternNumber) {
        this.documentExternNumber = documentExternNumber;
    }

    public Date getDateIntro() {
        return dateIntro;
    }

    public void setDateIntro(Date dateIntro) {
        this.dateIntro = dateIntro;
    }

    public Date getDateExtern() {
        return dateExtern;
    }

    public void setDateExtern(Date dateExtern) {
        this.dateExtern = dateExtern;
    }
}



