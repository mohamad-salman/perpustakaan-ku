package ms.perpusku.cli.shared;

/**
 *
 * @author MS
 */
public enum Konfirmasi {
    YA("y"), TIDAK("t");

    private final String value;

    private Konfirmasi(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
