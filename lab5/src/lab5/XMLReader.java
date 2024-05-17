package lab5;

import lab5.exceptions.BandsFileNotFoundException;
import lab5.exceptions.IOBandsFileException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Класс {@link XMLReader} служит для чтения XML файла и создания древовидной структуры данных,
 * состоящей из объектов класса {@link Tag}
 */
public class XMLReader {
    private String inputFileName;

    public XMLReader(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public Tag read() {
        Tag tag = null;
        Tag root = tag;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inputFileName));
        } catch (FileNotFoundException ex) {
            throw new BandsFileNotFoundException("Файл "+ this.inputFileName +
                    " не найден, убедитесь, что данный файл существует либо измените переменную окружения.");
        }
        String line;
        char lastCh = ' ';
        Tag newtag = null;
        Stack<String> tagNames = new Stack<>();
        String tagName = "";
        String parentTagName = "";
        StringBuilder content = new StringBuilder();
        boolean openTagStarts = false;
        boolean openTagEnds = false;
        boolean closeTagStarts = false;
        boolean closeTagEnds = false;
        boolean contentStarts = false;

        try {
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (ch == ' ' && !contentStarts) {
                        continue;
                    }
                    if (ch == '<') {
                        lastCh = '<';
                        openTagEnds = false;
                        contentStarts = false;
                    }
                    if (ch == '/' && lastCh == '<') {
                        closeTagStarts = true;
                        lastCh = '/';
                        tag.setContent(content.toString().trim());
                        content.setLength(0);
                    }
                    if (((ch >= 'A' && ch <= 'z' || ch >= 'А' && ch <= 'я')) && (lastCh == '<')) {
                        openTagStarts = true;
                        tagName += ch;
                    }
                    if ((ch == ' ' || (ch >= '!' && ch <= 'z' || ch >= 'А' && ch <= 'я') && ch != '/' && ch != '<' && ch != '>')) {
                        if (openTagEnds && lastCh != '/') {
                            contentStarts = true;
                            lastCh = ch;
                            content.append(ch);
                        }
                    }
                    if (ch == '>' && openTagStarts && !closeTagStarts) {
                        lastCh = '>';
                        if (tag == null) {
                            tag = new Tag(tagName, null);
                            parentTagName = tagName;
                        } else {
                            if (tag.getName() == parentTagName) {
                                tag = new Tag(tagName, tag);
                                parentTagName = tagName;
                            } else {
                                tag = new Tag(tagName, tag.parent);
                                parentTagName = tagName;
                            }
                        }
                        tagName = "";
                        openTagStarts = false;
                        openTagEnds = true;
                    }
                    if (ch == '>' && closeTagStarts) {
                        lastCh = '>';
                        if (tag.parent != null) {
                            parentTagName = tag.parent.getName();
                            tag = tag.parent;
                        }
                        closeTagEnds = true;
                        closeTagStarts = false;
                    }
                }
            }
        } catch (IOException e) {
            throw new IOBandsFileException("Не удалось прочитать файл "+this.inputFileName+". Проверьте права доступа");
        }
        while(tag.parent != null){
            tag = tag.parent;
        }
        return tag;
    }
}
