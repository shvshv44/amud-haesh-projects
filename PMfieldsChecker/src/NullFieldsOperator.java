import annotations.NullCheckableObject;

import java.lang.reflect.Field;
import java.util.Arrays;

public class NullFieldsOperator {

    private final String SEPARATOR_STRING = ".";
    private NullFieldHandler nullFieldHandler;

    public NullFieldsOperator(NullFieldHandler nullFieldHandler) {
        this.nullFieldHandler = nullFieldHandler;
    }

    public void checkNullFields(Object object, String path) throws IllegalAccessException {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                path += field.getName() + SEPARATOR_STRING;
                field.setAccessible(true);
                if (field.get(object) == null) {
                    nullFieldHandler.handleNullField(path.substring(0, path.length() - SEPARATOR_STRING.length()));
                    path = path.substring(0, path.length() - field.getName().length() - SEPARATOR_STRING.length());
                } else {
                    if(field.getType().isArray() || field.get(object) instanceof Iterable<?> ||
                       (isObjectNeedToBeChecked(field.get(object).getClass()))) {
                        handleInnerObject(field, object, path);
                    }
                    path = "";
                }
            }
    }

    private void handleInnerObject(Field field, Object object, String path) throws IllegalAccessException {
            if (field.get(object) instanceof Iterable<?>) {
                Iterable<?> iterable = (Iterable<?>) field.get(object);
                handleIterableObject(iterable, path);
            } else if(field.get(object).getClass().isArray()){
                Iterable<?> iterable = Arrays.asList((Object[])field.get(object));
                handleIterableObject(iterable, path);
            } else {
                checkNullFields(field.get(object), path);
            }
    }

    private boolean isObjectNeedToBeChecked(Class classToCheck){
      return classToCheck.isAnnotationPresent(NullCheckableObject.class);
    }

    private void handleIterableObject(Iterable<?> iterable,  String path) throws IllegalAccessException {
        int index = 0;
        for (Object iterableElement : iterable) {
            path = path.substring(0, path.length() - SEPARATOR_STRING.length());
            path += "[" + index + "]" + SEPARATOR_STRING;
            if (iterableElement == null) {
                nullFieldHandler.handleNullField(path.substring(0, path.length() - SEPARATOR_STRING.length()));
                path = path.substring(0, path.length() - (String.valueOf(index).length() + 2));
            } else {
                    if (isObjectNeedToBeChecked(iterableElement.getClass())) {
                        checkNullFields(iterableElement, path);
                    } else {
                        path = path.substring(0, path.length() - (String.valueOf(index).length() + 2));
                    }
            }
            index++;
        }
    }
}
