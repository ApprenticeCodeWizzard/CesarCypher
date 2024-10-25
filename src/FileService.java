import java.io.*;

//В цьому класі відбувається читання з файла, створення нового файлу та запис у файл.
public class FileService {
    private String pathToFile;
    private String pathToFileNew;
    private int key;

    public FileService (String pathToFile, String pathToFileNew, int key){
        this.pathToFile = pathToFile;
        this.pathToFileNew = pathToFileNew;
        this.key = key;
    }
    public void workWithFile(){
        int symbol;
        try(FileReader fr = new FileReader(pathToFile);
            FileWriter fw = new FileWriter(pathToFileNew)) {
            Shift shift = new Shift(key);
            while ((symbol = fr.read()) != -1) {
                symbol = shift.shiftLetter((char)symbol);
                fw.write(symbol);
                System.out.print((char) symbol);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public boolean checkFile(){
        String[] engDictionary = {"the", "for", "when", "where", "one", "all", "that", "his", "her", "and", "you", "your", "this", "but", "get", "with", "have", "had", "has", "just", "can", "can't", "know", "here", "what", "your", "think", "down", "not"};
        String[] ukrDictionary = {"так", "він", "ваш", "його", "вона", "вони", "був", "бути", "мав", "мати", "для", "робити", "робив", "сказати", "сказав", "йому", "від", "але", "мій", "твій", "твоя", "йшов", "йти", "один", "тому", "міг", "могли", "або"};
        String line;
        int wordCounter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(pathToFileNew));) {
            while ((line = br.readLine()) != null) {
                line = line.toLowerCase();
                for (String checkWord: engDictionary) {
                    if (line.contains(checkWord)) {
                        wordCounter++;
                    }
                }
                for (String checkWord: ukrDictionary) {
                    if (line.contains(checkWord)) {
                        wordCounter++;
                    }
                }
                if (wordCounter >= 5) {//Якщо одне слово знайшлось, це може бути співпадіння. Якщо 5 і більше - це точно воно.
                    return true;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
