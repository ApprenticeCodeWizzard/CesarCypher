import java.io.*;

public class BruteForce {
    private String pathToFile, pathToFileNew;
    public BruteForce(String pathToFile, String pathToFileNew) {
        this.pathToFile = pathToFile;
        this.pathToFileNew = pathToFileNew;
    }

    void bruteForceCycle() {
        for (int bfKey = 0; bfKey < 92; bfKey++) {
            FileService fs = new FileService(pathToFile, pathToFileNew, -bfKey);//Ключ відємний, бо це дешифровка.
            fs.workWithFile();
            if (fs.checkFile()) {
                System.out.println("Your file was bruteforced. The key was "+bfKey + ".");
                if(bfKey == 0){
                    System.out.println("Файл не був зашифрований.");
                }
                return;//Брутфорс закінчено, повертаємось у main.
            }
        }
        System.out.println("This program could not bruteforce the code.");
        File file = new File(pathToFileNew);
        file.delete(); //Видаляємо вихідний файл, якщо розшифрувати не вдалося.
    }
}