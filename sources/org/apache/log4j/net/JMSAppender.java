package org.apache.log4j.net;

import java.util.Properties;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class JMSAppender extends AppenderSkeleton {
    String initialContextFactoryName;
    boolean locationInfo;
    String password;
    String providerURL;
    String securityCredentials;
    String securityPrincipalName;
    String tcfBindingName;
    String topicBindingName;
    TopicConnection topicConnection;
    TopicPublisher topicPublisher;
    TopicSession topicSession;
    String urlPkgPrefixes;
    String userName;

    public void activateOptions() {
        InitialContext initialContext;
        try {
            LogLog.debug("Getting initial context.");
            if (this.initialContextFactoryName != null) {
                Properties properties = new Properties();
                properties.put("java.naming.factory.initial", this.initialContextFactoryName);
                if (this.providerURL != null) {
                    properties.put("java.naming.provider.url", this.providerURL);
                } else {
                    LogLog.warn("You have set InitialContextFactoryName option but not the ProviderURL. This is likely to cause problems.");
                }
                if (this.urlPkgPrefixes != null) {
                    properties.put("java.naming.factory.url.pkgs", this.urlPkgPrefixes);
                }
                if (this.securityPrincipalName != null) {
                    properties.put("java.naming.security.principal", this.securityPrincipalName);
                    if (this.securityCredentials != null) {
                        properties.put("java.naming.security.credentials", this.securityCredentials);
                    } else {
                        LogLog.warn("You have set SecurityPrincipalName option but not the SecurityCredentials. This is likely to cause problems.");
                    }
                }
                initialContext = new InitialContext(properties);
            } else {
                initialContext = new InitialContext();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Looking up [");
            stringBuffer.append(this.tcfBindingName);
            stringBuffer.append("]");
            LogLog.debug(stringBuffer.toString());
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) lookup(initialContext, this.tcfBindingName);
            LogLog.debug("About to create TopicConnection.");
            this.topicConnection = this.userName != null ? topicConnectionFactory.createTopicConnection(this.userName, this.password) : topicConnectionFactory.createTopicConnection();
            LogLog.debug("Creating TopicSession, non-transactional, in AUTO_ACKNOWLEDGE mode.");
            this.topicSession = this.topicConnection.createTopicSession(false, 1);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Looking up topic name [");
            stringBuffer2.append(this.topicBindingName);
            stringBuffer2.append("].");
            LogLog.debug(stringBuffer2.toString());
            LogLog.debug("Creating TopicPublisher.");
            this.topicPublisher = this.topicSession.createPublisher((Topic) lookup(initialContext, this.topicBindingName));
            LogLog.debug("Starting TopicConnection.");
            this.topicConnection.start();
            initialContext.close();
        } catch (Exception e) {
            ErrorHandler errorHandler = this.errorHandler;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Error while activating options for appender named [");
            stringBuffer3.append(this.name);
            stringBuffer3.append("].");
            errorHandler.error(stringBuffer3.toString(), e, 0);
        }
    }

    public void append(LoggingEvent loggingEvent) {
        if (checkEntryConditions()) {
            try {
                ObjectMessage createObjectMessage = this.topicSession.createObjectMessage();
                if (this.locationInfo) {
                    loggingEvent.getLocationInformation();
                }
                createObjectMessage.setObject(loggingEvent);
                this.topicPublisher.publish(createObjectMessage);
            } catch (Exception e) {
                ErrorHandler errorHandler = this.errorHandler;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Could not publish message in JMSAppender [");
                stringBuffer.append(this.name);
                stringBuffer.append("].");
                errorHandler.error(stringBuffer.toString(), e, 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkEntryConditions() {
        String str = this.topicConnection == null ? "No TopicConnection" : this.topicSession == null ? "No TopicSession" : this.topicPublisher == null ? "No TopicPublisher" : null;
        if (str == null) {
            return true;
        }
        ErrorHandler errorHandler = this.errorHandler;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(" for JMSAppender named [");
        stringBuffer.append(this.name);
        stringBuffer.append("].");
        errorHandler.error(stringBuffer.toString());
        return false;
    }

    public synchronized void close() {
        if (!this.closed) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Closing appender [");
            stringBuffer.append(this.name);
            stringBuffer.append("].");
            LogLog.debug(stringBuffer.toString());
            this.closed = true;
            try {
                if (this.topicSession != null) {
                    this.topicSession.close();
                }
                if (this.topicConnection != null) {
                    this.topicConnection.close();
                }
            } catch (Exception e) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Error while closing JMSAppender [");
                stringBuffer2.append(this.name);
                stringBuffer2.append("].");
                LogLog.error(stringBuffer2.toString(), e);
            }
            this.topicPublisher = null;
            this.topicSession = null;
            this.topicConnection = null;
        }
    }

    public String getInitialContextFactoryName() {
        return this.initialContextFactoryName;
    }

    public boolean getLocationInfo() {
        return this.locationInfo;
    }

    public String getPassword() {
        return this.password;
    }

    public String getProviderURL() {
        return this.providerURL;
    }

    public String getSecurityCredentials() {
        return this.securityCredentials;
    }

    public String getSecurityPrincipalName() {
        return this.securityPrincipalName;
    }

    public String getTopicBindingName() {
        return this.topicBindingName;
    }

    /* access modifiers changed from: protected */
    public TopicConnection getTopicConnection() {
        return this.topicConnection;
    }

    public String getTopicConnectionFactoryBindingName() {
        return this.tcfBindingName;
    }

    /* access modifiers changed from: protected */
    public TopicPublisher getTopicPublisher() {
        return this.topicPublisher;
    }

    /* access modifiers changed from: protected */
    public TopicSession getTopicSession() {
        return this.topicSession;
    }

    /* access modifiers changed from: package-private */
    public String getURLPkgPrefixes() {
        return this.urlPkgPrefixes;
    }

    public String getUserName() {
        return this.userName;
    }

    /* access modifiers changed from: protected */
    public Object lookup(Context context, String str) throws NamingException {
        try {
            return context.lookup(str);
        } catch (NameNotFoundException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not find name [");
            stringBuffer.append(str);
            stringBuffer.append("].");
            LogLog.error(stringBuffer.toString());
            throw e;
        }
    }

    public boolean requiresLayout() {
        return false;
    }

    public void setInitialContextFactoryName(String str) {
        this.initialContextFactoryName = str;
    }

    public void setLocationInfo(boolean z) {
        this.locationInfo = z;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setProviderURL(String str) {
        this.providerURL = str;
    }

    public void setSecurityCredentials(String str) {
        this.securityCredentials = str;
    }

    public void setSecurityPrincipalName(String str) {
        this.securityPrincipalName = str;
    }

    public void setTopicBindingName(String str) {
        this.topicBindingName = str;
    }

    public void setTopicConnectionFactoryBindingName(String str) {
        this.tcfBindingName = str;
    }

    public void setURLPkgPrefixes(String str) {
        this.urlPkgPrefixes = str;
    }

    public void setUserName(String str) {
        this.userName = str;
    }
}
