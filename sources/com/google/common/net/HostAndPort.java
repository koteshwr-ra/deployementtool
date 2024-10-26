package com.google.common.net;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.annotation.Nullable;

public final class HostAndPort implements Serializable {
    private static final int NO_PORT = -1;
    private static final long serialVersionUID = 0;
    private final boolean hasBracketlessColons;
    private final String host;
    private final int port;

    private static boolean isValidPort(int i) {
        return i >= 0 && i <= 65535;
    }

    private HostAndPort(String str, int i, boolean z) {
        this.host = str;
        this.port = i;
        this.hasBracketlessColons = z;
    }

    public String getHostText() {
        return this.host;
    }

    public boolean hasPort() {
        return this.port >= 0;
    }

    public int getPort() {
        Preconditions.checkState(hasPort());
        return this.port;
    }

    public int getPortOrDefault(int i) {
        return hasPort() ? this.port : i;
    }

    public static HostAndPort fromParts(String str, int i) {
        Preconditions.checkArgument(isValidPort(i), "Port out of range: %s", Integer.valueOf(i));
        HostAndPort fromString = fromString(str);
        Preconditions.checkArgument(!fromString.hasPort(), "Host has a port: %s", str);
        return new HostAndPort(fromString.host, i, fromString.hasBracketlessColons);
    }

    public static HostAndPort fromHost(String str) {
        HostAndPort fromString = fromString(str);
        Preconditions.checkArgument(!fromString.hasPort(), "Host has a port: %s", str);
        return fromString;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.common.net.HostAndPort fromString(java.lang.String r9) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r9)
            java.lang.String r0 = "["
            boolean r0 = r9.startsWith(r0)
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0019
            java.lang.String[] r0 = getHostAndPortFromBracketedHost(r9)
            r4 = r0[r3]
            r0 = r0[r2]
        L_0x0016:
            r5 = r4
            r4 = 0
            goto L_0x003c
        L_0x0019:
            r0 = 58
            int r4 = r9.indexOf(r0)
            if (r4 < 0) goto L_0x0032
            int r5 = r4 + 1
            int r0 = r9.indexOf(r0, r5)
            if (r0 != r1) goto L_0x0032
            java.lang.String r4 = r9.substring(r3, r4)
            java.lang.String r0 = r9.substring(r5)
            goto L_0x0016
        L_0x0032:
            if (r4 < 0) goto L_0x0036
            r0 = 1
            goto L_0x0037
        L_0x0036:
            r0 = 0
        L_0x0037:
            r4 = 0
            r5 = r9
            r8 = r4
            r4 = r0
            r0 = r8
        L_0x003c:
            boolean r6 = com.google.common.base.Strings.isNullOrEmpty(r0)
            if (r6 != 0) goto L_0x0080
            java.lang.String r1 = "+"
            boolean r1 = r0.startsWith(r1)
            r1 = r1 ^ r2
            java.lang.Object[] r6 = new java.lang.Object[r2]
            r6[r3] = r9
            java.lang.String r7 = "Unparseable port number: %s"
            com.google.common.base.Preconditions.checkArgument(r1, r7, r6)
            int r1 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0064 }
            boolean r0 = isValidPort(r1)
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r3] = r9
            java.lang.String r9 = "Port number out of range: %s"
            com.google.common.base.Preconditions.checkArgument(r0, r9, r2)
            goto L_0x0080
        L_0x0064:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unparseable port number: "
            java.lang.String r9 = java.lang.String.valueOf(r9)
            int r2 = r9.length()
            if (r2 == 0) goto L_0x0077
            java.lang.String r9 = r1.concat(r9)
            goto L_0x007c
        L_0x0077:
            java.lang.String r9 = new java.lang.String
            r9.<init>(r1)
        L_0x007c:
            r0.<init>(r9)
            throw r0
        L_0x0080:
            com.google.common.net.HostAndPort r9 = new com.google.common.net.HostAndPort
            r9.<init>(r5, r1, r4)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.net.HostAndPort.fromString(java.lang.String):com.google.common.net.HostAndPort");
    }

    private static String[] getHostAndPortFromBracketedHost(String str) {
        Preconditions.checkArgument(str.charAt(0) == '[', "Bracketed host-port string must start with a bracket: %s", str);
        int indexOf = str.indexOf(58);
        int lastIndexOf = str.lastIndexOf(93);
        Preconditions.checkArgument(indexOf > -1 && lastIndexOf > indexOf, "Invalid bracketed host/port: %s", str);
        String substring = str.substring(1, lastIndexOf);
        int i = lastIndexOf + 1;
        if (i == str.length()) {
            return new String[]{substring, ""};
        }
        Preconditions.checkArgument(str.charAt(i) == ':', "Only a colon may follow a close bracket: %s", str);
        int i2 = lastIndexOf + 2;
        for (int i3 = i2; i3 < str.length(); i3++) {
            Preconditions.checkArgument(Character.isDigit(str.charAt(i3)), "Port must be numeric: %s", str);
        }
        return new String[]{substring, str.substring(i2)};
    }

    public HostAndPort withDefaultPort(int i) {
        Preconditions.checkArgument(isValidPort(i));
        return (hasPort() || this.port == i) ? this : new HostAndPort(this.host, i, this.hasBracketlessColons);
    }

    public HostAndPort requireBracketsForIPv6() {
        Preconditions.checkArgument(!this.hasBracketlessColons, "Possible bracketless IPv6 literal: %s", this.host);
        return this;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HostAndPort)) {
            return false;
        }
        HostAndPort hostAndPort = (HostAndPort) obj;
        if (Objects.equal(this.host, hostAndPort.host) && this.port == hostAndPort.port && this.hasBracketlessColons == hostAndPort.hasBracketlessColons) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.host, Integer.valueOf(this.port), Boolean.valueOf(this.hasBracketlessColons));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.host.length() + 8);
        if (this.host.indexOf(58) >= 0) {
            sb.append('[');
            sb.append(this.host);
            sb.append(']');
        } else {
            sb.append(this.host);
        }
        if (hasPort()) {
            sb.append(':');
            sb.append(this.port);
        }
        return sb.toString();
    }
}
