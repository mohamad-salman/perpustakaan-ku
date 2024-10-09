package ms.perpusku.cli;

import ms.perpusku.cli.input.TerminalInputMenuUtama;
import ms.perpusku.cli.menu.ItemMenuKeluar;
import ms.perpusku.cli.menu.ItemMenuTambahPeminjam;
import ms.perpusku.cli.menu.ItemMenuTambahBuku;
import ms.perpusku.cli.menu.ItemMenuPeminjaman;
import ms.perpusku.cli.menu.ItemMenuPengembalian;
import ms.perpusku.cli.menu.Menu;

/**
 *
 * @author MS
 */
public class PerpusKuCLI {

    private final static Menu MAIN_MENU = buatMenu();

    public PerpusKuCLI() {
        boolean tidakKeluar = true;
        do {
            tampilkanMenu();

            int menuYangDipilih = getMenuYangDipilih();

            tidakKeluar = prosesMenu(menuYangDipilih);
        } while (tidakKeluar);
    }

    private static Menu buatMenu() {
        Menu mainMenu = new Menu();

        mainMenu.add(new ItemMenuTambahBuku("Tambah buku"));
        mainMenu.add(new ItemMenuTambahPeminjam("Tambah peminjam"));
        mainMenu.add(new ItemMenuPeminjaman("Peminjaman"));
        mainMenu.add(new ItemMenuPengembalian("Pengembalian"));
//        menu_.add(new ItemMenuTambahPengarang("Tambah pengarang"));
//        menu_.add(new ItemMenuTambahPenerbit("Tambah penerbit"));
//        menu_.add(new ItemMenuTambahKategori("Tambah kategori"));
//        menu_.add(new ItemMenuTampilkanBuku("Tampilkan koleksi buku"));
//        menu_.add(new ItemMenuTampilkanAnggota("Tnampilkan data peminjam"));*/
//        menu_.add(new ItemMenuTampilkanPengarang("Tnampilkan data pengarang"));*/
//        menu_.add(new ItemMenuTampilkanPenerbit("Tnampilkan data penerbit"));*/
//        menu_.add(new ItemMenuTampilkanPengarang("Tnampilkan data kategori"));*/
        mainMenu.add(new ItemMenuKeluar("Keluar"));

        return mainMenu;
    }

    private static void tampilkanMenu() {
        MAIN_MENU.display();
    }

    private static int getMenuYangDipilih() {
        TerminalInputMenuUtama terminalInput = new TerminalInputMenuUtama();
        int totalMenu = MAIN_MENU.getTotalMenu();

        return terminalInput.getMenu(totalMenu);
    }

    private static boolean prosesMenu(int menuYangDipilih) {
        return MAIN_MENU.proses(menuYangDipilih);
    }
}
