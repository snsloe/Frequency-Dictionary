import java.io.*;
import java.util.Scanner;

public class Dictionary {
    private String outputName;

    public String getOutputName() {
        return outputName;
    }

    public void existing(File file) {
        if (!file.exists()) {
            System.out.println("Файл не найден.");
            return;
        }
    }

    public boolean processFile(String inputName) {
        try {
            int symbol = 0;
            int[] symbolCountsUpper = new int[26];
            int[] symbolCountsLower = new int[26];
            File file = new File(inputName);
            existing(file);

            FileReader reader = new FileReader(file);

            while ((symbol = reader.read()) != -1) {
                if (Character.isLetter((char) symbol)) {
                    if ((int) symbol <= 90 && (int) symbol >= 65) {
                        int indexUpp = (int) symbol - 65;
                        symbolCountsUpper[indexUpp]++;
                    } else if ((int) symbol <= 122 && (int) symbol >= 97) {
                        int indexLow = (int) symbol - 97;
                        symbolCountsLower[indexLow]++;
                    }
                }
            }
            reader.close();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Вы можете оставить следующее поле пустым, тогда исходный файл будет перезаписан.");
            System.out.print("Введите имя файла для записи: ");
            String outputName = scanner.nextLine();

            if (outputName.isEmpty()) {
                outputName = inputName;
            }
            this.outputName = outputName;

            File outputFile = new File(outputName);
            if (!outputFile.exists()) {
                System.out.println("Файл " + outputName + " не существует!");
                return false;
            }

            FileWriter writer = new FileWriter(outputName);
            printCounts(symbolCountsUpper, writer, "up");
            printCounts(symbolCountsLower, writer, "low");
            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
            return false;
        }
    }

    private void printCounts(int[] symbolCounts, FileWriter writer, String flag) {
        try {
            for (int i = 0; i < symbolCounts.length; i++) {
                if (flag.equals("up")) {
                    char symbol = (char) ('A' + i);
                    writer.write(symbol + ": " + symbolCounts[i] + "\n");
                } else {
                    char symbol = (char) ('a' + i);
                    writer.write(symbol + ": " + symbolCounts[i] + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
