package ms.perpusku.cli.menu;

/**
 *
 * @author MS
 */
public abstract class ItemMenu {

    protected final String teks;

    public ItemMenu(String teks) {
        this.teks = teks;
    }

    protected abstract void run();

    @Override
    public String toString() {
        return teks;
    }

    private void delay(int detik) {
        try {
            Thread.sleep(detik * 1_000);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected void showProgressDone() {
        delay(1);
        System.out.print(".");
        delay(1);
        System.out.print(".");
        delay(1);
        System.out.print(".");
        delay(2);
        System.out.println(" done!");
        delay(1);
    }

}
