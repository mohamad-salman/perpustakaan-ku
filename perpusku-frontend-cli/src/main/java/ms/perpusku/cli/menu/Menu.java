package ms.perpusku.cli.menu;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author MS
 */
public class Menu {

    private final Map<Integer, ItemMenu> map;

    public Menu() {
        this.map = new TreeMap<>();
    }

    public void add(ItemMenu itemMenu) {
        if (itemMenu != null) {
            int noMenu = this.map.size() + 1;
            this.map.put(noMenu, itemMenu);
        }
    }

    public void display() {
        if (!this.map.isEmpty()) {
            System.out.println("\n");
            System.out.println("=====================================");
            System.out.println("              Perpus-Ku");
            System.out.println("=====================================");

            tampilkanDaftarItemMenu();

            System.out.println("=====================================");
        }
    }

    private void tampilkanDaftarItemMenu() {
        for (Map.Entry<Integer, ItemMenu> entry : this.map.entrySet()) {
            int noMenu = entry.getKey();
            ItemMenu itemMenu = entry.getValue();

            System.out.println(noMenu + ". " + itemMenu);
        }
    }

    public int getTotalMenu() {
        return this.map.size();
    }

    public boolean proses(int menuYangDiPilih) {
        if (menuYangDiPilih == keluar()) {
            System.out.println("\n\n------ bye! ------");
            return false;
        }

        this.map.get(menuYangDiPilih).run();
        return true;
    }

    private int keluar() {
        return getTotalMenu();
    }
}
