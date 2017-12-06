package DatabasePrimitive;

/**
 * Created by Наталья on 03.07.2017.
 */
public class ObjectTypes {
    int id;
    String name;
    String description;

    public ObjectTypes(int id, String name, String description, int parentTypeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentTypeId = parentTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(int parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    int parentTypeId;

    @Override
    public String toString() {
        return "ObjectTypes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parentTypeId=" + parentTypeId +
                '}';
    }
}
