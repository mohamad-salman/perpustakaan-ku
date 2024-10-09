package ms.perpusku.cli.input.validator;

/**
 *
 * @author MS
 */
public class DefaultValidator implements Validator {

    private final KondisiCekInputBuilder kondisi;

    private DefaultValidator(KondisiCekInputBuilder kondisiInputBuilder) {
        kondisi = kondisiInputBuilder;
    }

    public static DefaultValidator periksaInputDenganKondisi(KondisiCekInputBuilder kondisi) {
        return new DefaultValidator(kondisi);
    }

    @Override
    public void validasi(String input) throws Exception {
        kondisi.proses(input);
    }

}
