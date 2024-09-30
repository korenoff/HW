package Dop;

import lombok.Data;
@Data
public class CreateDocVersion {
    private Long documentId;
    private String versionAuthor;
    private byte[] content;
}