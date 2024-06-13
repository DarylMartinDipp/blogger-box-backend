package com.dauphine.blogger.dto;

import java.util.UUID;

public class ElementRequest {
    private String title;
    private String content;
    private UUID category_id;

    public ElementRequest(String title, String content, UUID category_id) {
        this.title = title;
        this.content = content;
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public UUID getCategory_id() {
        return category_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory_id(UUID category_id) {
        this.category_id = category_id;
    }
}
