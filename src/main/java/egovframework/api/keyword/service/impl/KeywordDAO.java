package egovframework.api.keyword.service.impl;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "KEYWORD")
public class KeywordDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "KEYWORD", nullable = false)
    private String keyword;

    @Column(name = "ENG_WORD", nullable = false)
    private String engWord;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getEngWord() {
        return engWord;
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

    public KeywordDAO id(Long id) {
        this.id = id;
        return this;
    }

    public KeywordDAO keyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public KeywordDAO engWord(String engWord) {
        this.engWord = engWord;
        return this;
    }

    public KeywordDAO description(String description) {
        this.description = description;
        return this;
    }

    public KeywordDAO createdAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public KeywordDAO updatedAt(Timestamp updatedAt) {
        if (updatedAt == null) updatedAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = updatedAt;
        return this;
    }

    public KeywordDAO() {
    }

    public KeywordDAO(Long id, String keyword, String engWord, String description, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.keyword = keyword;
        this.engWord = engWord;
        this.description = description;
        if (createdAt != null) {
            this.createdAt = createdAt;
        };
        this.updatedAt = updatedAt;
    }

    public KeywordDAO build() {
        return new KeywordDAO(id, keyword, engWord, description, createdAt, updatedAt);
    }

}
