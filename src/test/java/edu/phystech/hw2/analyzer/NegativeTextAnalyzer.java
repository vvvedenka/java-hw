package edu.phystech.hw2.analyzer;

import java.util.List;

public class NegativeTextAnalyzer implements TextAnalyzer {
    private static final List<String> negativeEmoji = List.of(":(", ":|", ":c", "=(", "=|");

    @Override
    public Label processText(String text) {
        String[] words = text.split("\\s+");
        for (String emoji : words) {
            if (negativeEmoji.contains(emoji)) { return Label.NEGATIVE; }
        }
        return Label.OK;
    }
}
