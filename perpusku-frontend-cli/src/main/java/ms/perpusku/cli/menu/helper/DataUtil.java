package ms.perpusku.cli.menu.helper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author MS
 */
class DataUtil {

    static <E> Map<Long, E> listToMapKeyNoUrut(final List<E> list) {
        Map<Long, E> m = new TreeMap<>();

        for (int i = 0; i < list.size(); i++) {
            long noUrut = i + 1;
            E e = list.get(i);

            m.put(noUrut, e);
        }

        return Collections.unmodifiableMap(m);
    }
}
