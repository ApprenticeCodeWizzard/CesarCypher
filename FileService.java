import java.io.*;

//В цьому класі відбувається читання з файла, створення нового файлу та запис у файл.

public class FileService {
    private String pathToFile;
    private String pathToFileNew;
    private int key;
    static final String[] engDictionary = {" the ", " for ", " when ", " where ", " one ", " all ", " that ", " his ", " her ", " and ", " you ", " your ", " this ", " but ", " get ", " with ", " have ", " had ", " has ", " just ", " can ", " can't ", " know ", " here ", " what ", " your ", " think ", " down ", " not "};
    static final String[] ukrDictionary = {" так ", " він ", " ваш ", " його ", " вона ", " вони ", " був ", " бути ", " мав ", " мати ", " для ", " робити ", " робив ", " сказати ", " сказав ", " йому ", " від ", " але ", " мій ", " твій ", " твоя ", " йшов ", " йти ", " один ", " тому ", " міг ", " могли ", " або "};

    public FileService (String pathToFile, String pathToFileNew, int key){
        this.pathToFile = pathToFile;
        this.pathToFileNew = pathToFileNew;
        this.key = key;
    }
    public void workWithFile(){
        int symbol; //Змнінна, яка зсувається.
        Shift shift = new Shift(key); //Клас для зсуву одного символа на key позицій.
        try(FileReader fr = new FileReader(pathToFile);
            FileWriter fw = new FileWriter(pathToFileNew)) {
            while ((symbol = fr.read()) != -1) {
                symbol = shift.shiftLetter((char)symbol); //Відбувається зсув.
                fw.write(symbol); //Записуємо у вихідний файл.
                //System.out.print((char) symbol); //Для перевірки.
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public boolean checkFile(int matchTreshold){
        String line; //Перемінна для перевірки.
        int wordCounter = 0; //Рахує кількість співпадінь зі словником.
        try(BufferedReader br = new BufferedReader(new FileReader(pathToFileNew))) { //Відкриваємо файл.
            while ((line = br.readLine()) != null) { //Читаємо до кінця файла, записуємо у змінну line.
                line = line.toLowerCase(); //Щоб слова з великої літери теж рахувались.
                for (String checkWord: engDictionary) {
                    if (line.contains(checkWord)) { //Перевіряємо наявність фрагменту у словнику.
                        wordCounter++;
                    }
                }
                for (String checkWord: ukrDictionary) {
                    if (line.contains(checkWord)) {
                        wordCounter++;
                    }
                }
                if (wordCounter >= matchTreshold) {//Якщо набрано достатньо співпадіннь зі словником, повертає true.
                    return true;
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return false; //Якщо текст прочитано, але співпадінь зі словником замало.
    }
}
