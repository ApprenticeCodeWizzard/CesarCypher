import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CommunicateWithUser {

    Scanner scan = new Scanner(System.in);

    public String getCommand() {
        String choice;
        while (true) {
            System.out.println("Which operation do you want to run: \nPress e to encrypt\nPress d to decrypt\nPress b to bruteforce\nPress m for manual\nPress exit to exit");
            choice = scan.nextLine().toLowerCase();
            switch (choice) {
                case "e":
                    return "ENCRYPT";
                case "d":
                    return "DECRYPT";
                case "b":
                    return "BRUTE_FORCE";
                case "exit":
                    return "exit";
                case "m":
                    System.out.println("This program can encrypt, decrypt or bruteforce your .txt file using Cesar cypher. This program can work with both english and ukrainian letters.");
                    System.out.println("You can run this program in two ways: with arguments on start or without them. If running without arguments, just follow instructions on entry. Otherwise, read below:");
                    System.out.println("This program can get three parameters. The first one is \"command\" - what do you want this program to do. ENCRYPT to encrypt, DECRYPT to decrypt, BRUTE_FORCE to bruteforce.");
                    System.out.println("The second parameter \"pathToFile\" is a path to file, which this program will use. The output file will appear in the same directory and will have ENCRYPTED, DECRYPTED, or BRUTEFORCED in it's name.");
                    System.out.println("The third parameter is а key, which the program will use on your file. The key must be integer number. BRUTE_FORCE option will not require key. You must use arguments strictly in this order.");
                    break;
                default:
                    System.out.println("You typed something wrong. Please, try again.");
                    break;
            }
        }
    }

    public String getFilePath() { //Отримуємо файл у користувача.
        Path pathToFile = Path.of("something");
        while (!Files.exists(pathToFile)) { //Перевіряємо чи такий файл існує.
            System.out.println("Enter correct path to file: ");
            pathToFile = Path.of(scan.nextLine());
        }
        return pathToFile.toString();
    }

    public int getKey() {
        while (true) {
            System.out.println("Enter the key: ");
            try {
                int key = Integer.parseInt(scan.nextLine());
                return key;
            } catch (NumberFormatException e) {
                System.out.println("Please, type correct integer number.");
            }
        }
    }

    public String getPathToFileNew(String command, String pathToFile) { //Отримуємо назву вихідного файлу.
        switch (command) {
            case "ENCRYPT":
                return (pathToFile.substring(0, pathToFile.length() - 4)) + "[ENCRYPTED].txt";
            case "DECRYPT":
                return (pathToFile.substring(0, pathToFile.length() - 4)) + "[DECRYPTED].txt";
            case "BRUTE_FORCE":
                return (pathToFile.substring(0, pathToFile.length() - 4)) + "[BRUTE_FORCED].txt";
            default:
                return "noPath";
        }
    }

    public int getMatchTreshold() { //Отримуємо поріг співпадінь зі словником.
        int treshold;
        System.out.println("Match treshold is a varieble that defines how many words matched with dictionary the program requires to decide that text is bruteforced.");
        System.out.println("Default value which suits most cases is 3. The general rule is the bigger file is, the larger match treshold should be. To big treshold leads to false negative results, to small - to false positive ones.");
        while (true) {
            try {
                System.out.println("Enter match treshold: ");
                treshold = Integer.parseInt(scan.nextLine());
                if (treshold > 0 && treshold < 100) {
                    return treshold;
                } else {
                    System.out.println("Enter correct treshold. It must be integer number more then zero and less than 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, type correct integer number.");
            }
        }
    }
}