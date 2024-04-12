import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.Scanner;

class Menu {
    private РеестрЖивотных reestrzh;
    private Счетчик counter;

    public Menu(РеестрЖивотных reestrzh, Счетчик counter) {
        this.reestrzh = reestrzh;
        this.counter = counter;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера ввода

            try {
                handleMenuChoice(choice, scanner);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
            }
        } while (choice != 4);
    }

    private void handleMenuChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                завестиНовоеЖивотное(scanner);
                break;
            case 2:
                показатьСписокКоманд(scanner);
                break;
            case 3:
                обучитьНовойКоманде(scanner);
                break;
            case 4:
                System.out.println("Выход из программы.");
                break;
            default:
                throw new IllegalArgumentException("Неверный выбор. Попробуйте еще раз.");
        }
    }

    public void showMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Завести новое животное");
        System.out.println("2. Показать список команд животного");
        System.out.println("3. Обучить животное новой команде");
        System.out.println("4. Выход");
        System.out.print("Ваш выбор: ");
    }

    private void завестиНовоеЖивотное(Scanner scanner) {
        System.out.print("Введите кличку животного: ");
        String кличка = scanner.nextLine();
        System.out.print("Введите возраст животного: ");
        int возраст = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера ввода
        System.out.print("Введите тип животного (Домашнее/Вьючное): ");
        String тип = scanner.nextLine();

        if (кличка.isEmpty() || тип.isEmpty()) {
            throw new IllegalArgumentException("Все поля должны быть заполнены.");
        }

        try (Счетчик counter = new Счетчик()) {
            reestrzh.завестиНовоеЖивотное(кличка, возраст, тип);
            counter.add();
            System.out.println("Новое животное успешно добавлено.");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при добавлении нового животного: " + e.getMessage());
        }
    }

    private void показатьСписокКоманд(Scanner scanner) {
        System.out.print("Введите кличку животного: ");
        String кличка = scanner.nextLine();
        if (кличка.isEmpty()) {
            throw new IllegalArgumentException("Кличка животного не может быть пустой.");
        }
        reestrzh.показатьСписокКоманд(кличка);
    }

    private void обучитьНовойКоманде(Scanner scanner) {
        System.out.print("Введите кличку животного: ");
        String кличка = scanner.nextLine();
        System.out.print("Введите новую команду: ");
        String команда = scanner.nextLine();
        if (кличка.isEmpty() || команда.isEmpty()) {
            throw new IllegalArgumentException("Кличка животного и команда не могут быть пустыми.");
        }
        reestrzh.обучитьНовойКоманде(кличка, команда);
    }
}
// Абстрактный родительский класс "Животные"
abstract class Животные {
    protected String кличка;
    protected int возраст;

    public abstract List<String> getКоманды();
    public abstract void обучитьКоманде(String команда);
}

// Класс "Домашние Животные"
class ДомашниеЖивотные extends Животные {
    private List<String> команды;

    public ДомашниеЖивотные(String кличка, int возраст) {
        this.кличка = кличка;
        this.возраст = возраст;
        this.команды = new ArrayList<>();
    }

    @Override
    public List<String> getКоманды() {
        return команды;
    }

    @Override
    public void обучитьКоманде(String команда) {
        команды.add(команда);
    }
}

// Класс "Вьючные Животные"
class ВьючныеЖивотные extends Животные {
    private List<String> команды;

    public ВьючныеЖивотные(String кличка, int возраст) {
        this.кличка = кличка;
        this.возраст = возраст;
        this.команды = new ArrayList<>();
    }

    @Override
    public List<String> getКоманды() {
        return команды;
    }

    @Override
    public void обучитьКоманде(String команда) {
        команды.add(команда);
    }
}

// Класс "Реестр Животных"
class РеестрЖивотных {
    private List<Животные> животные;

    public РеестрЖивотных() {
        this.животные = new ArrayList<>();
    }

    public void завестиНовоеЖивотное(String кличка, int возраст, String тип) {
        Животные animal;
        if (тип.equalsIgnoreCase("Домашнее")) {
            animal = new ДомашниеЖивотные(кличка, возраст);
        } else {
            animal = new ВьючныеЖивотные(кличка, возраст);
        }
        животные.add(animal);
    }

    public void показатьСписокКоманд(String кличка) {
        for (Животные animal : животные) {
            if (animal.кличка.equals(кличка)) {
                System.out.println("Команды, которые выполняет " + кличка + ": " + animal.getКоманды());
                return;
            }
        }
        System.out.println("Животное с кличкой " + кличка + " не найдено.");
    }

    public void обучитьНовойКоманде(String кличка, String команда) {
        for (Животные animal : животные) {
            if (animal.кличка.equals(кличка)) {
                animal.обучитьКоманде(команда);
                System.out.println(кличка + " обучен команде: " + команда);
                return;
            }
        }
        System.out.println("Животное с кличкой " + кличка + " не найдено.");
    }
}

// Класс "Счетчик"
class Счетчик implements AutoCloseable {
    private int count;

    public void add() {
        this.count++;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public void close() throws Exception {
        if (this.count > 0) {
            throw new Exception("Счетчик не был закрыт корректно.");
        }
    }
}
