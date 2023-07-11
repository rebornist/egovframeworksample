package egovframework.api.keyword.service.impl;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "KEYWORD")
public class KeywordDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HAN_WORD", nullable = false)
    private String hanWord;

    @Column(name = "ENG_WORD", nullable = false)
    private String engWord;

    @Column(name = "ENG_ABBR_WORD", nullable = false)
    private String engAbbrWord;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public String getHanWord() {
        return hanWord;
    }

    public String getEngWord() {
        return engWord;
    }

    public String getEngAbbrWord() {
        return engAbbrWord;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public KeywordDTO hanWord(String hanWord) {
        this.hanWord = hanWord;
        return this;
    }

    public KeywordDTO engWord(String engWord) {
        this.engWord = engWord;
        return this;
    }

    public KeywordDTO engAbbrWord(String engAbbrWord) {
        this.engAbbrWord = engAbbrWord;
        return this;
    }

    public KeywordDTO description(String description) {
        this.description = description;
        return this;
    }

    public KeywordDTO createdAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public KeywordDTO updatedAt(Timestamp updatedAt) {
        if (updatedAt == null) updatedAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = updatedAt;
        return this;
    }

    public KeywordDTO() {
    }

    public KeywordDTO(String hanWord, String engWord, String engAbbrWord, String description) {
        this.hanWord = hanWord;
        this.engWord = engWord;
        this.engAbbrWord = engAbbrWord;
        this.description = description;
    }

    public KeywordDTO(String hanWord, String engWord, String engAbbrWord, String description, Timestamp createdAt, Timestamp updatedAt) {
        this.hanWord = hanWord;
        this.engWord = engWord;
        this.engAbbrWord = engAbbrWord;
        this.description = description;
        if (createdAt != null) {
            this.createdAt = createdAt;
        };
        this.updatedAt = updatedAt;
    }

    public KeywordDTO build() {
        return new KeywordDTO(hanWord, engWord, engAbbrWord, description, createdAt, updatedAt);
    }

    public String toString() {
        return "KeywordDTO(id=" + this.getId() + ", hanWord=" + this.getHanWord() + ", engWord=" + this.getEngWord() + ", engAbbrWord=" + this.getEngAbbrWord() + ", description=" + this.getDescription() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ")";
    }

}
