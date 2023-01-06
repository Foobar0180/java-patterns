package com.patterns.solid;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Journal {
    private final List<String> entries = new ArrayList<>();
    private int count = 0;

    public void addEntry(String text) {
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }

//    public void saveToFile(com.patterns.solid.Journal j, String filename, boolean overwriteOriginal) throws FileNotFoundException {
//        if (overwriteOriginal || new File(filename).exists()) {
//            try (PrintStream out = new PrintStream(filename)) {
//                out.println(toString());
//            }
//        }
//    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class JournalRepository {
    public void saveToFile(Journal j, String filename, boolean overwriteOriginal) throws FileNotFoundException {
        if (overwriteOriginal || new File(filename).exists()) {
            try (PrintStream out = new PrintStream(filename)) {
                out.println(j.toString());
            }
        }
    }
}

class SRPDemo {
    public static void main(String[] args) throws FileNotFoundException {
        Journal j = new Journal();
        j.addEntry("Today is the first of August. It is hot, steamy and wet. It is raining. I am tempted to " +
                "write a poem. But I remember what it said on one rejection slip: After a heavy rainfall, poems " +
                "titled RAIN pour in from across the nation. -- Sylvia Plath");

        j.addEntry("People rush off to meaningless jobs day after day, you see them coughing in the subways at " +
                "dawn. They squander their souls on things like “rent,” “decent clothes,” “gas and electricity,” " +
                "“insurance,” behaving like peasants who have just come out of the fields and are so dreadful tickled " +
                "because they can buy baubles and doodads in stores. -- Jack Kerouac");
        System.out.println(j);

        JournalRepository repo = new JournalRepository();
        String filename = "journal.txt";
        repo.saveToFile(j, filename, true);
    }
}
