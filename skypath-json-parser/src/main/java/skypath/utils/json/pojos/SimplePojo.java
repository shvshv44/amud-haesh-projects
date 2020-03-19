package skypath.utils.json.pojos;

import com.google.gson.JsonObject;

import java.util.List;

public class SimplePojo {

    private int x;
    private int y;
    private List<Integer> objectList;
    private JsonObject jsonObject;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Integer> getObjectList() {
        return objectList;
    }

    public void setObjectList(List<Integer> objectList) {
        this.objectList = objectList;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
