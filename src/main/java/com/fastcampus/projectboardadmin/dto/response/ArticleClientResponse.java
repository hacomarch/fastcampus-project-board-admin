package com.fastcampus.projectboardadmin.dto.response;

import com.fastcampus.projectboardadmin.dto.ArticleDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ArticleClientResponse(
        //api를 호출해서 응답을 받으면 _embedded 안에 articles에 대한 정보가 들어있다. 그래서 그것을 가져와야 함. page 정보도 따로 가져와야 함
        @JsonProperty("_embedded") Embedded embedded,
        @JsonProperty("page") Page page
) {

    public static ArticleClientResponse empty() {
        return new ArticleClientResponse(
                new Embedded(List.of()),
                new Page(1, 0, 1, 0)
        );
    }

    public static ArticleClientResponse of(List<ArticleDto> articles) {
        return new ArticleClientResponse(
                new Embedded(articles),
                new Page(articles.size(), articles.size(), 1, 0)
        );
    }

    public List<ArticleDto> articles() { return this.embedded().articles(); }

    public record Embedded(List<ArticleDto> articles) {}

    public record Page(
            int size,
            long totalElements,
            int totalPages,
            int number
    ) {}

}