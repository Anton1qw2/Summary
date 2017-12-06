package DatabasePrimitive;

/**
 * Created by Наталья on 07.07.2017.
 */
public class ObjectInfo {
    String name;

    @Override
    public String toString() {
        return
                "attributes='" + attributes + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    String attributes;
    String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ObjectInfo(String name, String attributes, String value) {

        this.name = name;
        this.attributes = attributes;
        this.value = value;
    }
}
