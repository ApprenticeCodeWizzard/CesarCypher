//У конструкторі відбувається присвоєння ключа, а також його перетворення, якщо ключ занадто великий.
//Ключі для українських букв.
//В моїй програмі шифруються не тільки букви, а й знаки. Тому діапазон не 26, а 91.
//Вийшло не зовсім так, як задано, зате програма шифрує також знаки, та вміє шифрувати тексти, в яких є і український, і англійський текст.

class Shift {

    private int mainKey, ukrKey;

    public Shift(int key) {
        if (key % 91 != 0) { //91 - це діапазон, який охоплює знаки від пробіла до z.
            mainKey = key % 91;
        } else {
            mainKey = key;
        }
        if (key % 82 != 0) { //82 - це кількість великих та малих українських букв у char, від І до ї.
            ukrKey = key % 82;
        } else {
            ukrKey = key;
        }
    }

    public char shiftLetter(char symbol) {
        if (symbol >= ' ' && symbol <= 'z') {
            symbol += mainKey; //Зсуваємо символ на ключ.
            if (symbol > 'z') {
                symbol -= 91; //Це якщо символ вийде з діапазона.
            }
            else if (symbol < ' ') {
                symbol += 91; //Це також.
            }
        }
        else if (symbol >= 'І' && symbol <= 'ї') { //Те ж саме, тільки з українськими символами.
            symbol += ukrKey;
            if (symbol > 'ї') {
                symbol -= 82;
            }
            else if (symbol < 'І') {
                symbol += 82;
            }
        }
        return symbol;
    }
}