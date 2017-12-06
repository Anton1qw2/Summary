package DatabasePrimitive;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

public interface DatabaseWork {

    void insert
            (Integer empno, String ename, String job, String mgr, String hiredate, BigDecimal sal, String comm, Integer deptno);
    void deleteEmployee(String id);

    Map<String, Object> find(Integer s );

    void findP( );

    void addSint( Integer sum);

    void addParams();

    void findObjects();

    void add(String object);

    void objectInfo(Integer id);
}
