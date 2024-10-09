package ms.perpusku.cli.menu.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import ms.perpusku.cli.input.TerminalInputTambahBuku;
import ms.perpusku.cli.shared.StringConvert;
import ms.perpusku.domain.model.Buku;
import ms.perpusku.domain.model.Penerbit;
import ms.perpusku.domain.model.Pengarang;
import ms.perpusku.service.api.BukuService;
import ms.perpusku.service.api.PenerbitService;
import ms.perpusku.service.api.PengarangService;
import ms.perpusku.service.api.ServiceFactory;

/**
 *
 * @author MS
 */
public class InputTambahBukuHelper {

    private final TerminalInputTambahBuku terminalInput;

    private final BukuService bukuService;
    private final PengarangService pengarangService;
    private final PenerbitService penerbitService;

    public InputTambahBukuHelper() {
        terminalInput = new TerminalInputTambahBuku();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        bukuService = serviceFactory.getBukuService();
        pengarangService = serviceFactory.getPengarangService();
        penerbitService = serviceFactory.getPenerbitService();
    }

    public String getInputJudul() {
        return terminalInput.getJudul();
    }

    public List<Pengarang> getInputPengarang() {
        long jumlahPengarang = terminalInput.getJumlahPengarang();

        var menuPengarang = DataUtil.listToMapKeyNoUrut(getPengarangSorted());
        tampilkanDataMenu(menuPengarang);

        return getListPengarang(jumlahPengarang, menuPengarang);
    }

    private List<Pengarang> getPengarangSorted() {
        return pengarangService.getAll().stream()
                .sorted(Comparator.comparing(Pengarang::getNama))
                .collect(Collectors.toList());
    }

    private <E> void tampilkanDataMenu(Map<Long, E> menu) {
        System.out.println("");
        tampilkanBaris();
        tampilkanData(menu);
        tampilkanBaris();
    }

    private void tampilkanBaris() {
        System.out.println("---------------------------");
    }

    private <E> void tampilkanData(Map<Long, E> menu) {
        menu.forEach((noUrut, domainObject) -> {
            try {
                Method methodGetNama = domainObject.getClass().getMethod("getNama");
                System.out.println(noUrut + ". " + methodGetNama.invoke(domainObject));
            } catch (NoSuchMethodException | SecurityException
                    | IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(InputTambahBukuHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private List<Pengarang> getListPengarang(long jumlahPengarang, Map<Long, Pengarang> menuPengarang) {
        Set<Long> noUrutBuffer = new HashSet<>();
        List<Pengarang> list = new ArrayList<>();
        int i = 0;

        while (i < jumlahPengarang) {
            long noUrut = terminalInput.getNoUrutPengarang(i, pengarangService.getTotalPengarang(), noUrutBuffer);

            noUrutBuffer.add(noUrut);
            list.add(menuPengarang.get(noUrut));

            i++;
        }
        return list;
    }

    public Penerbit getInputPenerbit() {
        var menuPenerbit = DataUtil.listToMapKeyNoUrut(getPenerbitSorted());

        tampilkanDataMenu(menuPenerbit);

        return getPenerbit(menuPenerbit);
    }

    public List<Penerbit> getPenerbitSorted() {
        return penerbitService.getAll().stream()
                .sorted(Comparator.comparing(Penerbit::getNama))
                .collect(Collectors.toList());
    }

    private Penerbit getPenerbit(Map<Long, Penerbit> menuPenerbit) {
        long totalPenerbit = penerbitService.getTotalPenerbit();
        long noUrut = terminalInput.getNoPenerbit(totalPenerbit);

        return menuPenerbit.get(noUrut);
    }

    public Year getInputTahun() {
        return Year.of(terminalInput.getTahunTerbit());
    }

    public String getInputKodeKategori() {
        var menuKategori = DataUtil.listToMapKeyNoUrut(getKategoriSorted());

        tampilkanDataMenu(menuKategori);

        return getKodeKategori(menuKategori);
    }

    public List<Buku.Kategori> getKategoriSorted() {
        return bukuService.getAllKategori().stream()
                .sorted(Comparator.comparing(Buku.Kategori::getNama))
                .collect(Collectors.toList());
    }

    private String getKodeKategori(Map<Long, Buku.Kategori> menuKategori) {
        int totalKategori = bukuService.getTotalKategori();
        String noUrut = terminalInput.getKategori(totalKategori);

        return menuKategori.get(StringConvert.toLong(noUrut)).getKode();
    }

    public List<String> getInputNoIsbn() {
        int jumlahIsbn = terminalInput.getJumlahIsbn();

        List<String> noIsbnBaru = new ArrayList<>();
        if (jumlahIsbn > 0) {
            Set<String> noIsbnYangSudahTerdaftar = getNoIsbnTerdaftar();

            int i = 0;
            while (i < jumlahIsbn) {
                String noIsbn = terminalInput.getNoIsbn(i, noIsbnYangSudahTerdaftar);

                noIsbnYangSudahTerdaftar.add(noIsbn);
                noIsbnBaru.add(noIsbn);

                i++;
            }
        }

        return noIsbnBaru;
    }

    public Set<String> getNoIsbnTerdaftar() {
        return bukuService.get().stream()
                .flatMap(mapper -> mapper.getIsbn().stream())
                .collect(Collectors.toSet());
    }
}
