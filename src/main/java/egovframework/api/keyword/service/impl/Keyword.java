package egovframework.api.keyword.service.impl;

import egovframework.api.keyword.service.KeywordDto;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_KEYWORD")
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "KEYWORD", nullable = false)
    private String keyword;

    @Column(name = "ENG_WORD", nullable = false)
    private String engWord;

    @Column(name = "ENG_ABBR_WORD")
    private String engAbbrWord;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private Timestamp updatedAt;

    public KeywordDto toDto() {
        return new KeywordDto(this.id, this.keyword, this.engWord, this.engAbbrWord, this.description);
    }

}
