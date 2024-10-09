package ms.perpusku.cli.menu;

/**
 *
 * @author MS
 */
public class ItemMenuKeluar extends ItemMenu {

    public ItemMenuKeluar(String teks) {
        super(teks);
    }

    @Override
    public void run() {
        System.out.println("ini tidak akan di eksekusi");
    }

}
