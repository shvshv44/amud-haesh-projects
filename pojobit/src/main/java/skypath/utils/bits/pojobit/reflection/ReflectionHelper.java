package skypath.utils.bits.pojobit.reflection;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReflectionHelper {

    public boolean isArray(Class fieldType) {
        return fieldType.isArray();
    }

    public boolean isList(Class fieldType) {
        return Collection.class.isAssignableFrom(fieldType);
    }

    public boolean isIteratable(Class fieldType) {
        return isArray(fieldType) || isList(fieldType);
    }

    public Object [] convertListToArray(Object object) {
        List lst = (List) object;
        Object [] objects = new Object[lst.size()];
        return lst.toArray(objects);
    }

    public Object [] convertObjectToArray(Object object) {
        // DON'T CHANGE - MAY CAUSE EBOLA!
        List a = new ArrayList();
        a.add(new Object[]{});
        List<Object[]> b = a;
        return b.get(0);
    }

}
