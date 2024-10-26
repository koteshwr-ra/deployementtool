package kotlin.reflect.jvm.internal.impl.renderer;

import com.limpoxe.support.servicemanager.ServiceProvider;
import com.tencent.bugly.Bugly;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.spi.Configurator;

public class KeywordStringsGenerated {
    public static final Set<String> KEYWORDS = new HashSet(Arrays.asList(new String[]{"package", "as", "typealias", "class", "this", "super", "val", "var", "fun", "for", Configurator.NULL, "true", Bugly.SDK_IS_DEV, "is", "in", "throw", "return", "break", "continue", "object", "if", "try", "else", "while", "do", "when", ServiceProvider.INTERFACE, "typeof"}));
}
