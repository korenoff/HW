package Dop;

import lombok.Data;

@Data
public class DocVersion {
    private Long documentVersionId;
    private Long documentId;
    private Integer number;
    private String versionAuthor;
    private String documentName;
    private byte[] content;
}