import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя входного файла: ");
        String inputName = scanner.nextLine();
        Dictionary dictionary = new Dictionary();
        boolean fileWritten = dictionary.processFile(inputName);

        if (fileWritten) {
            System.out.println("Результаты сохранены в выходной файл: " + dictionary.getOutputName());
        }
    }
}
