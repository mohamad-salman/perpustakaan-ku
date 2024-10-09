package ms.perpusku.cli.menu;

import java.util.List;
import ms.perpusku.cli.menu.helper.InputPeminjamanHelper;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.service.api.ServiceFactory;
import ms.perpusku.service.api.SirkulasiService;

/**
 *
 * @author MS
 */
public class ItemMenuPeminjaman extends ItemMenu {

    public ItemMenuPeminjaman(String teks) {
        super(teks);
    }

    @Override
    public void run() {
        tampilkanHeader();

        InputPeminjamanHelper helper = new InputPeminjamanHelper();

        long idPeminjam = helper.getInputIdPeminjam();
        List<Buku> listBuku = helper.getInputBuku();

        System.out.print("\n\nMenyimpan data peminjaman");
        SirkulasiService peminjamanService = ServiceFactory.getInstance().getSirkulasiService();
        if (peminjamanService.simpanPeminjaman(idPeminjam, listBuku)) {
            showProgressDone();
        }
    }

    private void tampilkanHeader() {
        System.out.println("\n");
        System.out.println("-------------------------------------");
        System.out.println("             Peminjaman");
        System.out.println("-------------------------------------");
    }

}
