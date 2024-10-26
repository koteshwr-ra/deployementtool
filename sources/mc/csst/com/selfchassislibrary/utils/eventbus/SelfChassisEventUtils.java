package mc.csst.com.selfchassislibrary.utils.eventbus;

import org.greenrobot.eventbus.EventBus;

public class SelfChassisEventUtils {
    public static void post(SelfChassisEventMsg selfChassisEventMsg) {
    }

    public static void postSticky(SelfChassisEventMsg selfChassisEventMsg) {
    }

    public static void register(Object obj) {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(obj)) {
            eventBus.register(obj);
        }
    }

    public static void unregister(Object obj) {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(obj)) {
            eventBus.unregister(obj);
        }
    }
}
