import java.lang.reflect.*;

/**
 Get an array of fields the object declares that contain annotations (inherited fields should be skipped).
 */
class AnnotationsUtil {

    public static String[] getFieldsContainingAnnotations(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        int length = 0;
        for (Field field : fields) {
            if (field.getAnnotations().length != 0) {
                length++;
            }
        }
        String[] answer = new String[length];
        int index = 0;
        for (Field field : fields) {
            if (field.getAnnotations().length != 0) {
                answer[index] = field.getName();
                index++;
            }
        }
        return answer;
    }

}