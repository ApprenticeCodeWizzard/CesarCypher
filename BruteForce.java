import java.io.*;

public class BruteForce {
    private String pathToFile, pathToFileNew;
    private int matchTreshold;
    public BruteForce(String pathToFile, String pathToFileNew, int matchTreshold) {
        this.pathToFile = pathToFile;
        this.pathToFileNew = pathToFileNew;
        this.matchTreshold = matchTreshold;
    }

    void bruteForceCycle() {
        for (int bfKey = 0; bfKey < 92; bfKey++) { //Пробуємо зсув букв на кожен ключ починаючи від 0.
            FileService fs = new FileService(pathToFile, pathToFileNew, -bfKey);//Ключ відємний, бо це дешифровка.
            fs.workWithFile();
            if (fs.checkFile(matchTreshold)) { //Перевіряємо по словнику, чи є файл розшифрованим.
                System.out.println("\nYour file was bruteforced. The key was "+bfKey + ".");
                if(bfKey == 0){
                    System.out.println("The file was not encrypted.");
                }
                return;//Брутфорс закінчено, повертаємось у main.
            }
        }
        System.out.println("\nThis program could not bruteforce your file.");
        File file = new File(pathToFileNew);
        file.delete(); //Видаляємо вихідний файл, якщо розшифрувати не вдалося.
    }
}