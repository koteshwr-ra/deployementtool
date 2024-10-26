package mc.csst.com.selfchassislibrary.utils;

import java.util.UUID;
import mc.csst.com.selfchassis.ui.fragment.set.map.MapFragment;

public class OtherUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace(MapFragment.SLASH, "");
    }
}
