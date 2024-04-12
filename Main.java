public class Main {
    public static void main(String[] args) {
        РеестрЖивотных reestrzh = new РеестрЖивотных();
        Счетчик counter = new Счетчик();

        Menu menu = new Menu(reestrzh, counter);
        menu.start();
    }
}