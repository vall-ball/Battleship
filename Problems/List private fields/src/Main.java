import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 Get sorted list of private fields the object declares (inherited fields should be skipped).
 */
class FieldGetter {

    public List<String> getPrivateFields(Object object) {
        List<String> list = new ArrayList<>();
        for (Field f : object.getClass().getDeclaredFields()) {
            int modifiers = f.getModifiers();
            if (Modifier.isPrivate(modifiers)) {
                list.add(f.getName());
            }
        }
        Collections.sort(list);
        return list;
    }

}