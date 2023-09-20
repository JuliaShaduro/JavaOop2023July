package ru.academits.shaduro.csv;

import java.io.*;

public class Csv {
    public static void convertCsvToHtml(String inputFilePath, String outputFilePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter writer = new PrintWriter(outputFilePath)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("    <title>Convert CSV to HTML</title>");
            writer.println("    <meta charset=\"utf-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");

            boolean isNewLine = true;
            boolean isBreakLine = false;
            boolean isCharacterWriting = true;
            boolean putComma = false;

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                int lineLength = line.length();

                if (isNewLine) {
                    writer.println("    <tr>");
                    writer.print("        <td>");
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
                        }

                        writer.print("</td>");
                        writer.println();
                        writer.print("        <td>");
                        isCharacterWriting = false;

                        if (i + 1 < lineLength) {
                            if (line.charAt(i + 1) == '"') {
                                i++;
                                putComma = true;
                                isBreakLine = true;
                                isNewLine = false;
                                isCharacterWriting = true;
                                continue;
                            }
                        }

                    } else if (lineChar == '"') {
                        if (i + 1 < lineLength) {
                            if (line.charAt(i + 1) == '"') {
                                writer.print(lineChar);
                                i++;
                                continue;
                            }

                            putComma = false;
                            isBreakLine = false;
                            isNewLine = true;
                            isCharacterWriting = false;

                        } else {
                            isCharacterWriting = false;
                            isNewLine = true;
                            isBreakLine = false;
                            putComma = false;
                        }
                    }

                    if (isCharacterWriting) {
                        writeChar(writer, lineChar);
                    }

                    isCharacterWriting = true;
                }

                if (isNewLine) {
                    writer.print("</td>");
                    writer.println();
                    writer.println("    </tr>");
                }
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            System.out.println("Файл не найден.");
        }
    }

    private static void writeChar (PrintWriter writer, char symbol) {
        if (symbol == '>') {
            writer.print("gt;");
        } else if (symbol == '<') {
            writer.print("&lt;");
        } else if (symbol == '&') {
            writer.print("&amp;");
        } else {
            writer.print(symbol);
        }
    }
}