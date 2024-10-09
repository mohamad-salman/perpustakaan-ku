package ms.perpusku.cli.menu;

import java.time.Year;
import java.util.List;
import ms.perpusku.cli.menu.helper.InputTambahBukuHelper;
import ms.perpusku.domain.model.Penerbit;
import ms.perpusku.domain.model.Pengarang;
import ms.perpusku.service.api.BukuService;
import ms.perpusku.service.api.ServiceFactory;

/**
 *
 * @author MS
 */
public class ItemMenuTambahBuku extends ItemMenu {

    public ItemMenuTambahBuku(String teks) {
        super(teks);
    }

    @Override
    public void run() {
        tampilkanHeader();

        InputTambahBukuHelper helper = new InputTambahBukuHelper();

        String judul = helper.getInputJudul();
        List<Pengarang> listPengarang = helper.getInputPengarang();
        Penerbit penerbit = helper.getInputPenerbit();
        Year tahun = helper.getInputTahun();
        String kodeKategori = helper.getInputKodeKategori();
        List<String> listIsbn = helper.getInputNoIsbn();

        System.out.print("\n\nMenyimpan data buku baru");
        BukuService bukuService = ServiceFactory.getInstance().getBukuService();
        if (bukuService.simpanBuku(judul, listPengarang, penerbit, tahun, kodeKategori, listIsbn)) {
            showProgressDone();
        }
    }

    private void tampilkanHeader() {
        System.out.println("\n");
        System.out.println("-------------------------------------");
        System.out.println("        Tambah Buku");
        System.out.println("-------------------------------------");
    }

}
