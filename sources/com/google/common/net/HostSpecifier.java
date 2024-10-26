package com.google.common.net;

import com.google.common.base.Preconditions;
import java.net.InetAddress;
import java.text.ParseException;
import javax.annotation.Nullable;

public final class HostSpecifier {
    private final String canonicalForm;

    private HostSpecifier(String str) {
        this.canonicalForm = str;
    }

    public static HostSpecifier fromValid(String str) {
        HostAndPort fromString = HostAndPort.fromString(str);
        Preconditions.checkArgument(!fromString.hasPort());
        String hostText = fromString.getHostText();
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddresses.forString(hostText);
        } catch (IllegalArgumentException unused) {
        }
        if (inetAddress != null) {
            return new HostSpecifier(InetAddresses.toUriString(inetAddress));
        }
        InternetDomainName from = InternetDomainName.from(hostText);
        if (from.hasPublicSuffix()) {
            return new HostSpecifier(from.toString());
        }
        String valueOf = String.valueOf(hostText);
        throw new IllegalArgumentException(valueOf.length() != 0 ? "Domain name does not have a recognized public suffix: ".concat(valueOf) : new String("Domain name does not have a recognized public suffix: "));
    }

    public static HostSpecifier from(String str) throws ParseException {
        try {
            return fromValid(str);
        } catch (IllegalArgumentException e) {
            String valueOf = String.valueOf(str);
            ParseException parseException = new ParseException(valueOf.length() != 0 ? "Invalid host specifier: ".concat(valueOf) : new String("Invalid host specifier: "), 0);
            parseException.initCause(e);
            throw parseException;
        }
    }

    public static boolean isValid(String str) {
        try {
            fromValid(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof HostSpecifier) {
            return this.canonicalForm.equals(((HostSpecifier) obj).canonicalForm);
        }
        return false;
    }

    public int hashCode() {
        return this.canonicalForm.hashCode();
    }

    public String toString() {
        return this.canonicalForm;
    }
}
