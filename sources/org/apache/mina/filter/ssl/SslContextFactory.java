package org.apache.mina.filter.ssl;

import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SslContextFactory {
    private int clientSessionCacheSize = -1;
    private int clientSessionTimeout = -1;
    private KeyManagerFactory keyManagerFactory = null;
    private String keyManagerFactoryAlgorithm = null;
    private boolean keyManagerFactoryAlgorithmUseDefault = true;
    private KeyStore keyManagerFactoryKeyStore = null;
    private char[] keyManagerFactoryKeyStorePassword = null;
    private String keyManagerFactoryProvider = null;
    private String protocol = "TLS";
    private String provider = null;
    private SecureRandom secureRandom = null;
    private int serverSessionCacheSize = -1;
    private int serverSessionTimeout = -1;
    private TrustManagerFactory trustManagerFactory = null;
    private String trustManagerFactoryAlgorithm = null;
    private boolean trustManagerFactoryAlgorithmUseDefault = true;
    private KeyStore trustManagerFactoryKeyStore = null;
    private ManagerFactoryParameters trustManagerFactoryParameters = null;
    private String trustManagerFactoryProvider = null;

    public SSLContext newInstance() throws Exception {
        KeyManager[] keyManagerArr;
        SSLContext sSLContext;
        KeyManagerFactory keyManagerFactory2 = this.keyManagerFactory;
        TrustManagerFactory trustManagerFactory2 = this.trustManagerFactory;
        if (keyManagerFactory2 == null) {
            String str = this.keyManagerFactoryAlgorithm;
            if (str == null && this.keyManagerFactoryAlgorithmUseDefault) {
                str = KeyManagerFactory.getDefaultAlgorithm();
            }
            if (str != null) {
                String str2 = this.keyManagerFactoryProvider;
                keyManagerFactory2 = str2 == null ? KeyManagerFactory.getInstance(str) : KeyManagerFactory.getInstance(str, str2);
            }
        }
        if (trustManagerFactory2 == null) {
            String str3 = this.trustManagerFactoryAlgorithm;
            if (str3 == null && this.trustManagerFactoryAlgorithmUseDefault) {
                str3 = TrustManagerFactory.getDefaultAlgorithm();
            }
            if (str3 != null) {
                String str4 = this.trustManagerFactoryProvider;
                trustManagerFactory2 = str4 == null ? TrustManagerFactory.getInstance(str3) : TrustManagerFactory.getInstance(str3, str4);
            }
        }
        TrustManager[] trustManagerArr = null;
        if (keyManagerFactory2 != null) {
            keyManagerFactory2.init(this.keyManagerFactoryKeyStore, this.keyManagerFactoryKeyStorePassword);
            keyManagerArr = keyManagerFactory2.getKeyManagers();
        } else {
            keyManagerArr = null;
        }
        if (trustManagerFactory2 != null) {
            ManagerFactoryParameters managerFactoryParameters = this.trustManagerFactoryParameters;
            if (managerFactoryParameters != null) {
                trustManagerFactory2.init(managerFactoryParameters);
            } else {
                trustManagerFactory2.init(this.trustManagerFactoryKeyStore);
            }
            trustManagerArr = trustManagerFactory2.getTrustManagers();
        }
        String str5 = this.provider;
        if (str5 == null) {
            sSLContext = SSLContext.getInstance(this.protocol);
        } else {
            sSLContext = SSLContext.getInstance(this.protocol, str5);
        }
        sSLContext.init(keyManagerArr, trustManagerArr, this.secureRandom);
        if (this.clientSessionCacheSize >= 0) {
            sSLContext.getClientSessionContext().setSessionCacheSize(this.clientSessionCacheSize);
        }
        if (this.clientSessionTimeout >= 0) {
            sSLContext.getClientSessionContext().setSessionTimeout(this.clientSessionTimeout);
        }
        if (this.serverSessionCacheSize >= 0) {
            sSLContext.getServerSessionContext().setSessionCacheSize(this.serverSessionCacheSize);
        }
        if (this.serverSessionTimeout >= 0) {
            sSLContext.getServerSessionContext().setSessionTimeout(this.serverSessionTimeout);
        }
        return sSLContext;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public void setProtocol(String str) {
        if (str != null) {
            this.protocol = str;
            return;
        }
        throw new IllegalArgumentException("protocol");
    }

    public void setKeyManagerFactoryAlgorithmUseDefault(boolean z) {
        this.keyManagerFactoryAlgorithmUseDefault = z;
    }

    public void setTrustManagerFactoryAlgorithmUseDefault(boolean z) {
        this.trustManagerFactoryAlgorithmUseDefault = z;
    }

    public void setKeyManagerFactory(KeyManagerFactory keyManagerFactory2) {
        this.keyManagerFactory = keyManagerFactory2;
    }

    public void setKeyManagerFactoryAlgorithm(String str) {
        this.keyManagerFactoryAlgorithm = str;
    }

    public void setKeyManagerFactoryProvider(String str) {
        this.keyManagerFactoryProvider = str;
    }

    public void setKeyManagerFactoryKeyStore(KeyStore keyStore) {
        this.keyManagerFactoryKeyStore = keyStore;
    }

    public void setKeyManagerFactoryKeyStorePassword(String str) {
        if (str != null) {
            this.keyManagerFactoryKeyStorePassword = str.toCharArray();
        } else {
            this.keyManagerFactoryKeyStorePassword = null;
        }
    }

    public void setTrustManagerFactory(TrustManagerFactory trustManagerFactory2) {
        this.trustManagerFactory = trustManagerFactory2;
    }

    public void setTrustManagerFactoryAlgorithm(String str) {
        this.trustManagerFactoryAlgorithm = str;
    }

    public void setTrustManagerFactoryKeyStore(KeyStore keyStore) {
        this.trustManagerFactoryKeyStore = keyStore;
    }

    public void setTrustManagerFactoryParameters(ManagerFactoryParameters managerFactoryParameters) {
        this.trustManagerFactoryParameters = managerFactoryParameters;
    }

    public void setTrustManagerFactoryProvider(String str) {
        this.trustManagerFactoryProvider = str;
    }

    public void setSecureRandom(SecureRandom secureRandom2) {
        this.secureRandom = secureRandom2;
    }

    public void setClientSessionCacheSize(int i) {
        this.clientSessionCacheSize = i;
    }

    public void setClientSessionTimeout(int i) {
        this.clientSessionTimeout = i;
    }

    public void setServerSessionCacheSize(int i) {
        this.serverSessionCacheSize = i;
    }

    public void setServerSessionTimeout(int i) {
        this.serverSessionTimeout = i;
    }
}
