package com.Code_Review_System.iO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AIResponse {
    private List<Candidate> candidates;

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public AIResponse(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public AIResponse() {
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Candidate {
        private Content content;

        public Content getContent() {
            return content;
        }

        public void setContent(Content content) {
            this.content = content;
        }

        public Candidate(Content content) {
            this.content = content;
        }

        public Candidate() {
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Content {
        private List<Part> parts;

        public List<Part> getParts() {
            return parts;
        }

        public void setParts(List<Part> parts) {
            this.parts = parts;
        }

        public Content(List<Part> parts) {
            this.parts = parts;
        }

        public Content() {
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Part {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Part(String text) {
            this.text = text;
        }

        public Part() {
        }
    }

}
