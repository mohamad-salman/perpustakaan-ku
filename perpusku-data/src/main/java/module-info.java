module ms.perpusku.data {
    requires transitive ms.perpusku.domain;   
    
    requires java.logging;
    requires java.sql;
    
    exports ms.perpusku.dao;
}
