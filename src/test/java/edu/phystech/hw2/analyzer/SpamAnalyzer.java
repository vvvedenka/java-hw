package edu.phystech.hw2.analyzer;

import java.util.List;
public class SpamAnalyzer implements TextAnalyzer {
    private final List<String> keywords;

    public SpamAnalyzer(List<String> keywords) { this.keywords = keywords; }

    @Override
    public Label processText(String text) {
        return keywords.stream().anyMatch(text::contains) ? Label.SPAM : Label.OK;
    }
}