package com.phishing.scanner.phiscanner;

import java.util.Stack;

public class ScannerPhi {

    private long score;
    private String content;
    private Stack<String> suspeitas;

    public Stack<String> getSuspeitas() {
        return this.suspeitas;
    }

    public void setSuspeitas(Stack<String> suspeitas2) {
        this.suspeitas = suspeitas2;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
