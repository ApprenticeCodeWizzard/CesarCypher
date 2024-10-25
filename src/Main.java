import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        String pathToFile = "noPath";
        String pathToFileNew = "noPath";
        int key = 0;
        String command = "no";

        CommunicateWithUser cwu = new CommunicateWithUser();
        //Створили клас, методи якого отримують інформацію від користувача.

        if (args.length != 0 && (args[0].equals("ENCRYPT") || args[0].equals("DECRYPT") || args[0].equals("BRUTE_FORCE"))) {//Якщо програма запущена за аргументами, беремо аргумент.
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
        //Отримали шлях до файла, з якого беремо текст.

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
                FileService fileservice = new FileService(pathToFile, pathToFileNew, key);
                fileservice.workWithFile();
                break;
            case "DECRYPT":
                key = -key;//При дешифровці зсув іде у зворотньому порядку.
                FileService fileservice2 = new FileService(pathToFile, pathToFileNew, key);
                fileservice2.workWithFile();
                break;
            case "BRUTE_FORCE":
                BruteForce bf = new BruteForce(pathToFile, pathToFileNew);
                bf.bruteForceCycle();
                break;
            default:
                System.out.println("You typed something wrong. Please, try again.");
                return;
        }
    }
}