package Helpers.FileManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileManager {
    public static final Logger logger = LogManager.getLogger(FileManager.class.getName());
    protected FileWriter writer;
    protected File file;

    public void create() throws IOException {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd_hh.mm.ss");

        file = new File(String.format("books/bookList_%s.csv", formatForDateNow.format(dateNow)));

        if (file.createNewFile())
            logger.debug("File is created!");

    }

    public void writeHeader() throws IOException {
        writer = new FileWriter(file, true);
        writer.write("Link;Name;Author;Price;Description");
        writer.write("\n");
        writer.close();
    }

    public void writeNewLine (ArrayList<String> array) throws IOException {
            writer = new FileWriter(file, true);
            for (String s : array) {
                writer.write(s);
                writer.write("\n");
            }
            writer.close();
    }
}
