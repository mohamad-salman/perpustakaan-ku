package ms.perpusku.cli.menu;

import ms.perpusku.cli.menu.helper.InputTambahPeminjamHelper;
import ms.perpusku.service.api.PeminjamService;
import ms.perpusku.service.api.ServiceFactory;

/**
 *
 * @author MS
 */
public class ItemMenuTambahPeminjam extends ItemMenu {

    public ItemMenuTambahPeminjam(String teks) {
        super(teks);
    }

    @Override
    public void run() {
        tampilkanHeader();

        InputTambahPeminjamHelper helper = new InputTambahPeminjamHelper();
        String nama = helper.getInputNama();
        String telp = helper.getInputTelp();

        System.out.print("\n\nMenyimpan data peminjam baru");
        PeminjamService peminjamService = ServiceFactory.getInstance().getPeminjamService();
        if (peminjamService.simpanPeminjam(nama, telp)) {
            showProgressDone();
        }
    }

    private void tampilkanHeader() {
        System.out.println("\n\n-------------------------------------");
        System.out.println("            Tambah Peminjam");
        System.out.println("-------------------------------------");
    }

}
