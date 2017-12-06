package DatabasePrimitive;

/**
 * Created by Наталья on 03.07.2017.
 */
public class Attributes {
    public Attributes(int id, int attributeTypeId, String name, String details) {
        this.id = id;
        this.attributeTypeId = attributeTypeId;
        this.name = name;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttributeTypeId() {
        return attributeTypeId;
    }

    public void setAttributeTypeId(int attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    int id;
    int attributeTypeId;
    String name;
    String details;

    @Override
    public String toString() {
        return "Attributes{" +
                "id=" + id +
                ", attributeTypeId=" + attributeTypeId +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
