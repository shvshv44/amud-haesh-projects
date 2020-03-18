package skypath.utils.shaqtest.models;

import com.google.gson.JsonElement;

import java.util.Map;

public class JsonMessage <T> {

    private T pojo;
    private Map<String, JsonElement> unkownfields;

    public T getPojo() {
        return pojo;
    }

    public void setPojo(T pojo) {
        this.pojo = pojo;
    }

    public Map<String, JsonElement> getUnkownfields() {
        return unkownfields;
    }

    public void setUnkownfields(Map<String, JsonElement> unkownfields) {
        this.unkownfields = unkownfields;
    }
}
