package DatabasePrimitive;

/**
 * Created by Наталья on 04.07.2017.
 */
public class AddParams {
    public AddParams(int id, int type) {
        this.id = id;
        this.type = type;

    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AddParams(String name, int id, int type) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
    int id;
    int type;
}
