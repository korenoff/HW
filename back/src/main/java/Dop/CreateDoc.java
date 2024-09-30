package Dop;

import lombok.Data;
import java.io.Serializable;

@Data
public class CreateDoc {
    private String documentName;
    private String author;
    private String documentIntroNumber;
    private byte[] content;
}
