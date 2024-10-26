package org.apache.mina.filter.firewall;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlacklistFilter extends IoFilterAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlacklistFilter.class);
    private final List<Subnet> blacklist = new CopyOnWriteArrayList();

    public void setBlacklist(InetAddress[] inetAddressArr) {
        if (inetAddressArr != null) {
            this.blacklist.clear();
            for (InetAddress block : inetAddressArr) {
                block(block);
            }
            return;
        }
        throw new IllegalArgumentException("addresses");
    }

    public void setSubnetBlacklist(Subnet[] subnetArr) {
        if (subnetArr != null) {
            this.blacklist.clear();
            for (Subnet block : subnetArr) {
                block(block);
            }
            return;
        }
        throw new IllegalArgumentException("Subnets must not be null");
    }

    public void setBlacklist(Iterable<InetAddress> iterable) {
        if (iterable != null) {
            this.blacklist.clear();
            for (InetAddress block : iterable) {
                block(block);
            }
            return;
        }
        throw new IllegalArgumentException("addresses");
    }

    public void setSubnetBlacklist(Iterable<Subnet> iterable) {
        if (iterable != null) {
            this.blacklist.clear();
            for (Subnet block : iterable) {
                block(block);
            }
            return;
        }
        throw new IllegalArgumentException("Subnets must not be null");
    }

    public void block(InetAddress inetAddress) {
        if (inetAddress != null) {
            block(new Subnet(inetAddress, 32));
            return;
        }
        throw new IllegalArgumentException("Adress to block can not be null");
    }

    public void block(Subnet subnet) {
        if (subnet != null) {
            this.blacklist.add(subnet);
            return;
        }
        throw new IllegalArgumentException("Subnet can not be null");
    }

    public void unblock(InetAddress inetAddress) {
        if (inetAddress != null) {
            unblock(new Subnet(inetAddress, 32));
            return;
        }
        throw new IllegalArgumentException("Adress to unblock can not be null");
    }

    public void unblock(Subnet subnet) {
        if (subnet != null) {
            this.blacklist.remove(subnet);
            return;
        }
        throw new IllegalArgumentException("Subnet can not be null");
    }

    public void sessionCreated(IoFilter.NextFilter nextFilter, IoSession ioSession) {
        if (!isBlocked(ioSession)) {
            nextFilter.sessionCreated(ioSession);
        } else {
            blockSession(ioSession);
        }
    }

    public void sessionOpened(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        if (!isBlocked(ioSession)) {
            nextFilter.sessionOpened(ioSession);
        } else {
            blockSession(ioSession);
        }
    }

    public void sessionClosed(IoFilter.NextFilter nextFilter, IoSession ioSession) throws Exception {
        if (!isBlocked(ioSession)) {
            nextFilter.sessionClosed(ioSession);
        } else {
            blockSession(ioSession);
        }
    }

    public void sessionIdle(IoFilter.NextFilter nextFilter, IoSession ioSession, IdleStatus idleStatus) throws Exception {
        if (!isBlocked(ioSession)) {
            nextFilter.sessionIdle(ioSession, idleStatus);
        } else {
            blockSession(ioSession);
        }
    }

    public void messageReceived(IoFilter.NextFilter nextFilter, IoSession ioSession, Object obj) {
        if (!isBlocked(ioSession)) {
            nextFilter.messageReceived(ioSession, obj);
        } else {
            blockSession(ioSession);
        }
    }

    public void messageSent(IoFilter.NextFilter nextFilter, IoSession ioSession, WriteRequest writeRequest) throws Exception {
        if (!isBlocked(ioSession)) {
            nextFilter.messageSent(ioSession, writeRequest);
        } else {
            blockSession(ioSession);
        }
    }

    private void blockSession(IoSession ioSession) {
        LOGGER.warn("Remote address in the blacklist; closing.");
        ioSession.close(true);
    }

    private boolean isBlocked(IoSession ioSession) {
        SocketAddress remoteAddress = ioSession.getRemoteAddress();
        if (!(remoteAddress instanceof InetSocketAddress)) {
            return false;
        }
        InetAddress address = ((InetSocketAddress) remoteAddress).getAddress();
        for (Subnet inSubnet : this.blacklist) {
            if (inSubnet.inSubnet(address)) {
                return true;
            }
        }
        return false;
    }
}
