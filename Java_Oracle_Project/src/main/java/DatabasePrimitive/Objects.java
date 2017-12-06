package DatabasePrimitive;

/**
 * Created by Наталья on 03.07.2017.
 */
public class Objects {
    @Override
    public String toString() {
        return "Objects{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", containerId=" + containerId +
                ", objectTypeId=" + objectTypeId +
                ", description='" + description + '\'' +
                '}';
    }

    String name;
    int id;
    int containerId;
    int objectTypeId;
    String description;

    public Objects(String name, int id, int containerId, int objectTypeId, String description) {
        this.name = name;
        this.id = id;
        this.containerId = containerId;
        this.objectTypeId = objectTypeId;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int containerId) {
        this.containerId = containerId;
    }

    public int getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(int objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
