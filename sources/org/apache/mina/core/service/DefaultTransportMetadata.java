package org.apache.mina.core.service;

import com.limpoxe.support.servicemanager.ServiceProvider;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Set;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.util.IdentityHashSet;

public class DefaultTransportMetadata implements TransportMetadata {
    private final Class<? extends SocketAddress> addressType;
    private final boolean connectionless;
    private final Set<Class<? extends Object>> envelopeTypes;
    private final boolean fragmentation;
    private final String name;
    private final String providerName;
    private final Class<? extends IoSessionConfig> sessionConfigType;

    public DefaultTransportMetadata(String str, String str2, boolean z, boolean z2, Class<? extends SocketAddress> cls, Class<? extends IoSessionConfig> cls2, Class<?>... clsArr) {
        if (str == null) {
            throw new IllegalArgumentException("providerName");
        } else if (str2 != null) {
            String lowerCase = str.trim().toLowerCase();
            if (lowerCase.length() != 0) {
                String lowerCase2 = str2.trim().toLowerCase();
                if (lowerCase2.length() == 0) {
                    throw new IllegalArgumentException("name is empty.");
                } else if (cls == null) {
                    throw new IllegalArgumentException("addressType");
                } else if (clsArr == null) {
                    throw new IllegalArgumentException("envelopeTypes");
                } else if (clsArr.length == 0) {
                    throw new IllegalArgumentException("envelopeTypes is empty.");
                } else if (cls2 != null) {
                    this.providerName = lowerCase;
                    this.name = lowerCase2;
                    this.connectionless = z;
                    this.fragmentation = z2;
                    this.addressType = cls;
                    this.sessionConfigType = cls2;
                    IdentityHashSet identityHashSet = new IdentityHashSet();
                    for (Class<?> add : clsArr) {
                        identityHashSet.add(add);
                    }
                    this.envelopeTypes = Collections.unmodifiableSet(identityHashSet);
                } else {
                    throw new IllegalArgumentException("sessionConfigType");
                }
            } else {
                throw new IllegalArgumentException("providerName is empty.");
            }
        } else {
            throw new IllegalArgumentException(ServiceProvider.NAME);
        }
    }

    public Class<? extends SocketAddress> getAddressType() {
        return this.addressType;
    }

    public Set<Class<? extends Object>> getEnvelopeTypes() {
        return this.envelopeTypes;
    }

    public Class<? extends IoSessionConfig> getSessionConfigType() {
        return this.sessionConfigType;
    }

    public String getProviderName() {
        return this.providerName;
    }

    public String getName() {
        return this.name;
    }

    public boolean isConnectionless() {
        return this.connectionless;
    }

    public boolean hasFragmentation() {
        return this.fragmentation;
    }

    public String toString() {
        return this.name;
    }
}
