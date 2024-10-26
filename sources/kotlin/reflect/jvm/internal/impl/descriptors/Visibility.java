package kotlin.reflect.jvm.internal.impl.descriptors;

import com.limpoxe.support.servicemanager.ServiceProvider;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;

/* compiled from: Visibility.kt */
public abstract class Visibility {
    private final boolean isPublicAPI;
    private final String name;

    public abstract boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor);

    public Visibility normalize() {
        return this;
    }

    protected Visibility(String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, ServiceProvider.NAME);
        this.name = str;
        this.isPublicAPI = z;
    }

    public final boolean isPublicAPI() {
        return this.isPublicAPI;
    }

    /* access modifiers changed from: protected */
    public Integer compareTo(Visibility visibility) {
        Intrinsics.checkParameterIsNotNull(visibility, "visibility");
        return Visibilities.compareLocal(this, visibility);
    }

    public String getDisplayName() {
        return this.name;
    }

    public final String toString() {
        return getDisplayName();
    }
}
