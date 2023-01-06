package com.patterns.gamma.behavioral;

/*
* Enables the exact behavior of a system to be determined dynamically at runtime or statically at compile time.
* Many algorithms can be decomposed into high- and low- level parts. The high-level parts can be reused,
* whereas, the low-level parts of specific. These are known as a Policy (in especially C++).
*/

import java.util.List;

enum OutputFormat {
    HTML, MARKDOWN
}

interface ListStrategy {
    default void start(StringBuilder builder) {};
    void addItem(StringBuilder builder, String item);
    default void end(StringBuilder builder) {};
}

class MarkdownListStrategy implements ListStrategy {
    @Override
    public void addItem(StringBuilder builder, String item) {
        builder.append(" * ")
                .append(item)
                .append(System.lineSeparator());
    }
}

class HtmlListStrategy implements ListStrategy {
    @Override
    public void start(StringBuilder builder) {
        builder.append("<ul>")
                .append(System.lineSeparator());
    }

    @Override
    public void addItem(StringBuilder builder, String item) {
        builder.append("<li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder builder) {
        builder.append("</ul>")
                .append(System.lineSeparator());
    }
}

class TextProcessor {
    private StringBuilder builder = new StringBuilder();
    private ListStrategy strategy;

    public TextProcessor(OutputFormat format) {
        setOutputFormat(format);
    }

    public void setOutputFormat(OutputFormat format) {
        switch (format) {
            case HTML:
                strategy = new HtmlListStrategy();
                break;
            case MARKDOWN:
                strategy = new MarkdownListStrategy();
                break;
        }
    }

    public void appendList(List<String> items) {
        strategy.start(builder);
        for (String item : items) {
            strategy.addItem(builder, item);
        }
        strategy.end(builder);
    }

    public void clear() {
        builder.setLength(0);
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}

class StrategyDemo {
    public static void main(String[] args) {
        TextProcessor markdownList = new TextProcessor(OutputFormat.MARKDOWN);
        markdownList.appendList(List.of("Lorem", "ipsum", "dolor"));
        System.out.println(markdownList);

        TextProcessor htmlList = new TextProcessor(OutputFormat.HTML);
        htmlList.appendList(List.of("Lorem", "ipsum", "dolor"));
        System.out.println(htmlList);
    }
}
