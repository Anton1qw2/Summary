package DatabasePrimitive;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class DatabaseWorkNet implements DatabaseWork {
    private JdbcTemplate jdbc;

    public DatabaseWorkNet(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public void insert(Integer empno, String ename, String job, String mgr, String hiredate, BigDecimal sal, String comm, Integer deptno) {
        jdbc.update("Insert into emp_java values(?, ?, ?, ?, TO_DATE(?, 'DD-MON-YYYY'), ?, ?, ?)",
                empno, ename, job, mgr, hiredate, sal, comm, deptno );
    }

    public void deleteEmployee(String param) {
        System.out.println();
    }



  public Map<String, Object> find(Integer s ) {

             return jdbc.queryForMap("SELECT LASTSS_FREEPLANNED(?) FROM dual",s);
    }

    @Override
    public void findP( ) {
       List<ObjectTypes> d =  jdbc.query("SELECT * FROM LASTSS_OBJECT_TYPES "
               , new Object[]{}, new RowMapper<ObjectTypes>() {
                   @Override
                   public ObjectTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
                       return new ObjectTypes(rs.getInt("ID"), rs.getString("NAME"),rs.getString("DESCRIPTION"),
                               rs.getInt("PARENT_TYPE_ID"));
                   }
               });
       for (ObjectTypes ot : d  ) {
           System.out.println(ot);
       }
    }

    @Override
    public void addSint(Integer sum) {
            List<ObjectTypes> d =  jdbc.query("SELECT * FROM LASTSS_OBJECT_TYPES "
                    , new Object[]{}, new RowMapper<ObjectTypes>() {
                        @Override
                        public ObjectTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return new ObjectTypes(rs.getInt("ID"), rs.getString("NAME"),rs.getString("DESCRIPTION"),
                                    rs.getInt("PARENT_TYPE_ID"));
                        }
                    });


            for (ObjectTypes ot : d  ) {
                String objectName;
                String des;
                RandomText randomText = new RandomText();
                Random random = new Random();


                for (int i=0 ; i<sum; i++){
                    objectName = "Test"+ot.getName()+"Object"+i+randomText.CreatePassword(3);
                    des = randomText.CreatePassword(random.nextInt(40)+20);
                    jdbc.update("insert into LASTSS_objects (NAME, OBJECT_TYPE_ID, DESCRIPTION) values (?,?,?)",
                            objectName, ot.getId(), des);

                }

            }
        for (ObjectTypes ot : d  ) {
                if (ot.getParentTypeId()>0){
                    List<Integer> child =  jdbc.query("SELECT ID FROM LASTSS_OBJECTS where OBJECT_TYPE_ID = ? "
                            , new Object[]{ot.getId()}, new RowMapper<Integer>() {
                                @Override
                                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                                    return new Integer(rs.getInt("Id"));
                                }
                            });
                        List<Integer> parent =  jdbc.query("SELECT ID FROM LASTSS_OBJECTS where OBJECT_TYPE_ID = ? "
                                , new Object[]{ot.getParentTypeId()}, new RowMapper<Integer>() {
                                    @Override
                                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                                        return new Integer(rs.getInt("ID"));
                                    }
                                });
                        Random random = new Random();
                    for (Integer o : child){
                        jdbc.update("UPDATE LASTSS_OBJECTS set CONTAINER_ID = ? " +
                                "where id = ?", parent.get(random.nextInt(parent.size())), o );
                    }

                }



            }
    }


    @Override
    public void addParams(){
        Random random = new Random();
        RandomText randomText = new RandomText();
        String zapros = "INSERT INTO LASTSS_PARAMS VALUES (?,?,?)";
        List<AddParams> d =  jdbc.query("SELECT ID, OBJECT_TYPE_ID FROM LASTSS_OBJECTs"
                , new Object[]{}, new RowMapper<AddParams>() {
                    @Override
                    public AddParams mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new AddParams(rs.getInt("ID"), rs.getInt("OBJECT_TYPE_ID"));
                    }
                });
        for (AddParams o : d){
            List<AddParams> params =
                    jdbc.query("SELECT Attribute_type_id, LASTSS_ATTRIBUTES.NAME, LASTSS_ATTRIBUTES_BINDS.ATTRIBUTE_ID FROM LASTSS_ATTRIBUTES_BINDS, LASTSS_ATTRIBUTES where LASTSS_ATTRIBUTES_BINDS.ATTRIBUTE_ID=LASTSS_ATTRIBUTES.ID and LASTSS_ATTRIBUTES_BINDS.OBJECT_TYPE_ID= ?"
                            , new Object[]{o.getType()}, new RowMapper<AddParams>() {
                                @Override
                                public AddParams mapRow(ResultSet rs, int rowNum) throws SQLException {
                                    return new AddParams(rs.getString("NAME"),
                                            rs.getInt("ATTRIBUTE_ID"), rs.getInt("ATTRIBUTE_TYPE_ID"));
                                }
                            });
            for (AddParams p : params){
                switch (p.getName()){
                    case "Rack Unit Size":
                        jdbc.update(zapros,
                                o.getId(), p.getId(), random.nextInt(30)+20 );
                        break;
                    case "Rack Width":
                        jdbc.update(zapros, o.getId(), p.getId(), random.nextInt(20)+472);
                        break;
                    case "Rack Depth":
                        jdbc.update(zapros, o.getId(), p.getId(), random.nextInt(200)+400);
                        break;
                    case "Rack Height":
                        jdbc.update(zapros, o.getId(), p.getId(), random.nextInt(400)+1600);
                        break;
                    case "Width":
                        jdbc.update(zapros, o.getId(), p.getId(),random.nextInt(40)+300);
                        break;
                    case "Depth":
                        jdbc.update(zapros, o.getId(), p.getId(), random.nextInt(200)+200);
                        break;
                    case "Height":
                        jdbc.update(zapros, o.getId(), p.getId(), random.nextInt(200)+100);
                        break;
                    case "Rack Side":
                        if (random.nextBoolean()){
                            jdbc.update(zapros, o.getId(), p.getId(), "front");
                        }else{
                            jdbc.update(zapros, o.getId(), p.getId(), "back");
                        }
                        break;
                    case "Serial No":
                        jdbc.update(zapros, o.getId(), p.getId(), randomText.CreatePassword(10));
                        break;
                    case "Readiness":
                        if (random.nextBoolean()){
                            jdbc.update(zapros, o.getId(), p.getId(), "Used");
                        }else{
                            jdbc.update(zapros, o.getId(), p.getId(), "Planned");
                        }
                        break;
                    case "Rack Position":
                        jdbc.update(zapros, o.getId(), p.getId(), random.nextInt(20)+1);
                        break;
                    default:
                        if (p.getType()==1){
                            jdbc.update(zapros, o.getId(), p.getId(), random.nextInt());
                        }else
                        {
                            jdbc.update(zapros, o.getId(), p.getId(), randomText.CreatePassword(100));
                        }
                        break;


                }

            }
        }

    }

    @Override
    public void findObjects() {
            List<Objects> d =  jdbc.query("SELECT * FROM LASTSS_OBJECTS "
                    , new Object[]{}, new RowMapper<Objects>() {
                        @Override
                        public Objects mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return new Objects(rs.getString("NAME"), rs.getInt("ID"),
                                    rs.getInt("CONTAINER_ID"), rs.getInt("OBJECT_TYPE_ID"),
                                    rs.getString("DESCRIPTION"));
                    }
                    });
            for (Objects ot : d  ) {
                System.out.println(ot);
            }
        }

    @Override
    public void add(String object) {
//        List<Integer> d =  jdbc.query("SELECT ID FROM UNCT_OBJECT_TYPES where NAME = ?"
//                , new Integer[]{object}, new RowMapper<Integer>() {
//                    @Override
//                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
//          F              return new Integer(rs.getInt("ID")));
//                    }
//                });
        Integer d = 0;
        try{
            d = jdbc.queryForObject("SELECT ID FROM LASTSS_OBJECT_TYPES where NAME = ?", Integer.class, object);
        }catch (EmptyResultDataAccessException e){
            System.out.println("Данного объектного типа не существует, проверьте правильность команды");
        }
        String name;
        String[] params= null;
            if (d>0) {
                System.out.println("Введите имя и описание объекта");
                BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
                try {
                    name = reader.readLine();
                    params = name.split(" ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (params.length == 2 ){
                    jdbc.update("INSERT INTO LASTSS_OBJECTS (NAME, DESCRIPTION, OBJECT_TYPE_ID) values (?,?,?)",
                            params[0], params[1], d);
                    List<AddParams> paramsList =
                            jdbc.query("SELECT Attribute_type_id, LASTSS_ATTRIBUTES.NAME, LASTSS_ATTRIBUTES_BINDS.ATTRIBUTE_ID FROM LASTSS_ATTRIBUTES_BINDS, LASTSS_ATTRIBUTES where LASTSS_ATTRIBUTES_BINDS.ATTRIBUTE_ID=LASTSS_ATTRIBUTES.ID and LASTSS_ATTRIBUTES_BINDS.ACTIVE='On' and LASTSS_ATTRIBUTES_BINDS.OBJECT_TYPE_ID= ?"
                                    , new Object[]{d}, new RowMapper<AddParams>() {
                                        @Override
                                        public AddParams mapRow(ResultSet rs, int rowNum) throws SQLException {
                                            return new AddParams(rs.getString("NAME"),
                                                    rs.getInt("ATTRIBUTE_ID"), rs.getInt("ATTRIBUTE_TYPE_ID"));
                                        }
                                    });
                    for (AddParams params1 : paramsList){
                        System.out.println("Введите: "+params1.getName());
                        try {
                            name = reader.readLine();
                            jdbc.update("INSERT INTO LASTSS_PARAMS VALUES (?,?,?, CURRENT_DATE )",
                                    jdbc.queryForObject("SELECT ID FROM LASTSS_OBJECTS where name =?", Integer.class,
                                            params[0]), params1.getId(), name);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    System.out.println("Добавление прошло успешно");
                }
            }else{
                System.out.println("Что то пошло не так, но это не точно ");
            }
    }

    @Override
    public void objectInfo(Integer id) {
        List<ObjectInfo> d =  jdbc.query("Select LASTSS_OBJECTS.NAME, LASTSS_ATTRIBUTES.NAME, LASTSS_PARAMS.TEXT_VALUE FROM LASTSS_ATTRIBUTES, LASTSS_PARAMS, LASTSS_OBJECTS where LASTSS_PARAMS.OBJECT_ID= LASTSS_OBJECTS.ID and LASTSS_PARAMS.ATTRIBUTE_ID= LASTSS_ATTRIBUTES.ID and LASTSS_OBJECTS.ID = ?"
                , new Object[]{id.toString()}, new RowMapper<ObjectInfo>() {
                    @Override
                    public ObjectInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new ObjectInfo(rs.getString(1), rs.getString(2),
                                rs.getString(3));
                    }
                });
        System.out.println(d.get(0).getName()+":");
        for (ObjectInfo ot : d  ) {
            System.out.println(ot);
        }

    }
}


