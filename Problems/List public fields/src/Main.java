
import java.lang.reflect.*;

/**
 Get list of public fields the object declares (inherited fields should be skipped).
 */
class FieldGetter {

    public String[] getPublicFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        int length = 0;
        for (Field f : fields) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers)) {
                length++;
            }
        }
        String[] answer = new String[length];
        int i = 0;
        for (Field f : fields) {
            int modifiers = f.getModifiers();
            if (Modifier.isPublic(modifiers)) {
                answer[i] = f.getName();
                i++;
            }
        }
        return answer;
    }

}