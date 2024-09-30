package Dop;

import lombok.Data;

@Data
public class CreateDocInfo  {
    private Long regCardId;

    private Long documentId;

    private String documentName;

    private String author;

    private String documentIntroNumber;

    private String documentExternNumber;

    private String dateIntro;

    private String dateExtern;
}