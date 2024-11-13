import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        String pathToFile = "noPath"; //Файл, з якого програма читатиме текс.
        String pathToFileNew = "noPath"; //Файл, в який буде записаний результат. Якщо він вже існує, то буде переписаний.
        int key = 0;
        String command = "no";

        CommunicateWithUser cwu = new CommunicateWithUser(); //Створили клас, методи якого отримують інформацію від користувача.

        if (args.length != 0 && (args[0].equals("ENCRYPT") || args[0].equals("DECRYPT") || args[0].equals("BRUTE_FORCE"))) {
            //Якщо програма запущена за аргументами, беремо аргумент.
            command = args[0];
        } else {
            command = cwu.getCommand();
        }
        if(command.equals("exit")){
            return;
        }
        //Отримали від користувача інформацію, що саме робити з файлом.

        if (args.length > 1 && Files.exists(Path.of(args[1]))) {
            pathToFile = args[1];
        } else {
            pathToFile = cwu.getFilePath();
        }
        //Отримали шлях до файла, з якого беремо текст. Якщо програма запущена з аргументами, беремо з них, якщо ні - питаємо користувача.

        pathToFileNew = cwu.getPathToFileNew(command, pathToFile);
        //Отримали назву файла, в який будемо записувати.

        if(!command.equals("BRUTE_FORCE")){
            if (args.length > 2) {
                try {
                    key = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    key = cwu.getKey();
                }
            } else {
                key = cwu.getKey();
            }
        }
        //Отримали від користувача ключ (якщо опція не брутфорс).

        //Тепер починаємо
        switch (command){
            case "ENCRYPT":
                FileService fileservice = new FileService(pathToFile, pathToFileNew, key); //Ствоюємо клас для шифрування/дешифрування.
                fileservice.workWithFile();
                System.out.println("\nFile encrypted.");
                break;
            case "DECRYPT":
                key = -key;//При дешифровці зсув іде у зворотньому порядку.
                FileService fileservice2 = new FileService(pathToFile, pathToFileNew, key);
                fileservice2.workWithFile();
                System.out.println("\nFile decrypted.");
                break;
            case "BRUTE_FORCE":
                int matchThreshold = cwu.getMatchThreshold(); //Отримуємо скільки співпадінь зі словником треба, щоб вважати файл розшифрованим.
                BruteForce bf = new BruteForce(pathToFile, pathToFileNew, matchThreshold); //Створюємо клас для брутфорсу.
                bf.bruteForceCycle();
                break;
            default:
                System.out.println("You typed something wrong. Please, try again.");
        }
    }
}