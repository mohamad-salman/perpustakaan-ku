package ms.perpusku.cli.menu;

import java.util.List;
import ms.perpusku.cli.menu.helper.InputPengembalianHelper;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.service.api.ServiceFactory;
import ms.perpusku.service.api.SirkulasiService;

/**
 *
 * @author MS
 */
public class ItemMenuPengembalian extends ItemMenu {

    public ItemMenuPengembalian(String teks) {
        super(teks);
    }

    @Override
    public void run() {
        tampilkanHeader();

        InputPengembalianHelper helper = new InputPengembalianHelper();

        List<Buku> listBuku = helper.getInputBuku();

        System.out.print("\n\nMenyimpan data pengembalian");
        SirkulasiService sirkulasiService = ServiceFactory.getInstance().getSirkulasiService();
        if (sirkulasiService.simpanPengembalian(listBuku)) {
            showProgressDone();
        }
    }

    private void tampilkanHeader() {
        System.out.println("\n");
        System.out.println("--------------------------------------");
        System.out.println("             Pengembalian");
        System.out.println("--------------------------------------");
    }
}
