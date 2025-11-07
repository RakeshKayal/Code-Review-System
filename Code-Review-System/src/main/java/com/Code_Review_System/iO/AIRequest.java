package com.Code_Review_System.iO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AIRequest {

    private  String  content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AIRequest(String content) {
        this.content = content;
    }

    public AIRequest() {
    }
}
