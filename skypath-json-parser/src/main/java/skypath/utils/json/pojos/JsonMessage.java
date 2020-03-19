package skypath.utils.json.pojos;

import com.google.gson.JsonElement;

import java.util.Map;

public class JsonMessage<T> {
    private T pojo;
    private Map<String, JsonElement> unknownFields;

    public T getPojo() {
        return pojo;
    }

    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

    public Map<String, JsonElement> getUnknownFields() {
        return unknownFields;
    }

    public void setUnknownFields(Map<String, JsonElement> unknownFields) {
        this.unknownFields = unknownFields;
    }
}
