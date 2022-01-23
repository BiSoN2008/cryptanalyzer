import java.util.Scanner;

public class Сryptanalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String src = "";
        String src1 = "";
        String src2 = "";
        int key = 0;
        System.out.println("Какой режим программы использовать? : \n" +
                "1. Зашифровать файл\n" +
                "2. Расшифровать файл\n" +
                "3. Брутфорс зашифрованного файла\n" +
                "4. Статический анализ");
        int number = scanner.nextInt();
        scanner.nextLine();
        switch (number) {
            case 1 -> {
                System.out.println("Введите полный путь к файлу");
                src = scanner.nextLine();
                System.out.println("Введите ключ");
                key = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Введите полный путь для зашифрованного файла");
                src1 = scanner.nextLine();
                FileMethods.writeToFile(src1, CryptoMethods.encoding(FileMethods.readFromFile(src), key));
            }
            case 2 -> {
                System.out.println("Введите полный путь к зашифрованному файлу");
                src = scanner.nextLine();
                System.out.println("Введите ключ");
                key = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Введите полный путь для расшифрованного файла");
                src1 = scanner.nextLine();
                FileMethods.writeToFile(src1, CryptoMethods.encoding(FileMethods.readFromFile(src), -key));
            }
            case 3 -> {
                System.out.println("Введите полный путь к зашифрованному файлу");
                src = scanner.nextLine();
                System.out.println("Введите полный путь для расшифрованного файла");
                src1 = scanner.nextLine();
                key = CryptoMethods.brutForceAlgorithm(FileMethods.readFromFile(src));
                System.out.println("Ключ равен = " + Math.abs(key));
                FileMethods.writeToFile(src1, CryptoMethods.encoding(FileMethods.readFromFile(src), key));
            }
            case 4 -> {
                System.out.println("Введите полный путь к зашифрованному файлу");
                src = scanner.nextLine();
                System.out.println("Введите путь файла для анализа");
                src1 = scanner.nextLine();
                System.out.println("Введите путь для расшифрованного файла");
                src2 = scanner.nextLine();
                FileMethods.writeToFile(src2, CryptoMethods.statisticalTextAnalysis(FileMethods.readFromFile(src), FileMethods.readFromFile(src1)));
            }
        }
    }

}
