//У конструкторі відбувається присвоєння ключа, а також його перетворення, якщо ключ занадто великий.
//Ключі для українських букв та всіх інших символів вираховуються окремо.
//91 - це діапазон, який охоплює знаки від пробіла до z. 82 - це кількість великих та малих українських букв у char, від І до ї.
//В моїй програмі шифруються не тільки букви, а й знаки. Тому діапазон не 26, а 91.
//Вийшло трохи з костилем, зате програма вміє шифрувати тексти, в яких є і українська, і англійська мови.

class Shift {
    public Shift(int key) {
        if (key % 91 != 0) {
            this.key = key % 91;
        } else {
            this.key = key;
        }
    }

    private int key;

    public char shiftLetter(char symbol) {
        if (symbol >= ' ' && symbol <= 'z') {
            symbol += this.key;
            if (symbol > 'z') {
                symbol -= 91;
            }
            else if (symbol < ' ') {
                symbol += 91;
            }
        }
        else if (symbol >= 1025 && symbol <= 1116) {
            symbol += this.key;
            if (symbol < 1025) {
                symbol -= 91;
            }
            else if (symbol > 1116) {
                symbol += 91;
            }
        }
        return symbol;
    }
}