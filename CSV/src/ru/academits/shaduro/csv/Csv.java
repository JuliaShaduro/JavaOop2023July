package ru.academits.shaduro.csv;

import java.io.*;
import java.util.Scanner;

public class Csv {
    public void convertCSVtoHTML(String readPath, String writePath) {
        try (Scanner scanner = new Scanner(new FileInputStream(readPath));
             PrintWriter writer = new PrintWriter(writePath)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Convert CSV to HTML</title>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");

            boolean isNewLine = true;
            boolean isBreakLine = false;
            boolean characterWriting = true;
            boolean putComma = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int lineLength = line.length();

                if (isNewLine) {
                    writer.print("<tr><td>");
                }
                if (isBreakLine) {
                    writer.print("<br/>");
                    isBreakLine = false;
                }

                for (int i = 0; i < lineLength; i++) {
                    char lineChar = line.charAt(i);

                    if (lineChar == ',') {
                        if (putComma) {
                            writer.print(lineChar);
                            continue;
                        } else {
                            writer.print("</td><td>");
                            characterWriting = false;
                            if (i + 1 < lineLength) {
                                if (line.charAt(i + 1) == '"') {
                                    i++;
                                    putComma = true;
                                    isBreakLine = true;
                                    isNewLine = false;
                                    characterWriting = true;
                                    continue;
                                }
                            }
                        }
                    } else if (lineChar == '"') {
                        if (i + 1 < lineLength) {
                            if (line.charAt(i + 1) == '"') {
                                writer.print(lineChar);
                                i++;
                                continue;
                            } else {
                                putComma = false;
                                isBreakLine = false;
                                isNewLine = true;
                                characterWriting = false;
                            }
                        } else {
                            characterWriting = false;
                            isNewLine = true;
                            isBreakLine = false;
                            putComma = false;
                        }
                    }

                    if (characterWriting) {
                        writer.print(lineChar);
                    }

                    characterWriting = true;
                }

                if (isNewLine) {
                    writer.print("</td></tr>");
                }
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Ошибки ввода/вывода.");
        }
    }
}