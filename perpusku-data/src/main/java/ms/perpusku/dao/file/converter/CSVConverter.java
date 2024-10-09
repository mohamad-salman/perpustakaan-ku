package ms.perpusku.dao.file.converter;

/**
 *
 * @author MS
 */

public interface CSVConverter<T> {
    default String[] getRecordPart(String recordPart) {
        return recordPart.contains(",")
                ? recordPart.split(",") : new String[]{recordPart};
    }
    
    T csvToDomainObject(String record);
    String domainObjectToCSV(T t);
}
