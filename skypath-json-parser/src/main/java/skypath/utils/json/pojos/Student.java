package skypath.utils.json.pojos;

import java.util.Map;

public class Student {
    String id;
    String name;
    Map<String, String> otherFields;

    public Student(String id, String name, Map<String, String> otherFields) {
        this.id = id;
        this.name = name;
        this.otherFields = otherFields;
    }
}
