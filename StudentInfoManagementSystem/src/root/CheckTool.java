package root;

import java.util.regex.Pattern;

public class CheckTool {
    public static boolean CheckId(String id) {
        if(id == null) return false;
        if(id.length()!=12) return false;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(id).matches();
    }
}
