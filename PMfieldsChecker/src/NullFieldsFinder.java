import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NullFieldsFinder {

    private final String SEPARATOR_STRING = ".";
    private String path;
    private NullFieldHandler nullFieldHandler;

    public NullFieldsFinder(NullFieldHandler nullFieldHandler) {
        this.path = "";
        this.nullFieldHandler = nullFieldHandler;
    }

    public void checkNullFields(Object object) throws IllegalAccessException {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                path += field.getName() + SEPARATOR_STRING;
                field.setAccessible(true);
                if (field.get(object) == null) {
                    nullFieldHandler.handleNullField(path.substring(0, path.length() - SEPARATOR_STRING.length()));
                    path = path.substring(0, path.length() - field.getName().length() - SEPARATOR_STRING.length());
                } else {
                    handleInnerObject(field, object);
                }
            }
        path = "";
    }

    private void handleInnerObject(Field field, Object object) throws IllegalAccessException {
        if (isFieldNeedToBeChecked(field.get(object))) {
            if (field.get(object) instanceof Iterable<?>) {
                Iterable<?> iterable = (Iterable<?>) field.get(object);
                handleIterableObject(iterable);
            } else if(field.get(object).getClass().isArray()){
                List<?> arrayAsList = Arrays.asList((Object[])field.get(object));
                Iterable<?> iterable = arrayAsList;
                handleIterableObject(iterable);
            } else {
                checkNullFields(field.get(object));
            }
        }
    }

    private boolean isFieldNeedToBeChecked(Object object){
      return !(object instanceof String || object instanceof Integer || object instanceof Double ||
               object instanceof Long || object instanceof Short|| object instanceof Boolean);
    }

    private void handleIterableObject(Iterable<?> iterable) throws IllegalAccessException {
        int index = 0;
        for (Object iterableElement : iterable) {
            path = path.substring(0, path.length() - SEPARATOR_STRING.length());
            path += "[" + index + "]" + SEPARATOR_STRING;
            if (iterableElement == null) {
                nullFieldHandler.handleNullField(path.substring(0, path.length() - SEPARATOR_STRING.length()));
                path = path.substring(0, path.length()-(String.valueOf(index).length() + 2));
            } else {
                if (isFieldNeedToBeChecked(iterableElement)) {
                    checkNullFields(iterableElement);
                } else {
                    path = path.substring(0, path.length()-(String.valueOf(index).length() + 2));
                }
            }
            index++;
        }
    }
}
