package org.apache.mina.handler.chain;

import com.limpoxe.support.servicemanager.ServiceProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.ClassUtils;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.chain.IoHandlerCommand;

public class IoHandlerChain implements IoHandlerCommand {
    private static volatile int nextId;
    /* access modifiers changed from: private */
    public final String NEXT_COMMAND = (IoHandlerChain.class.getName() + ClassUtils.PACKAGE_SEPARATOR_CHAR + this.id + ".nextCommand");
    private final Entry head;
    private final int id;
    private final Map<String, Entry> name2entry = new ConcurrentHashMap();
    private final Entry tail;

    public IoHandlerChain() {
        int i = nextId;
        nextId = i + 1;
        this.id = i;
        Entry entry = new Entry((Entry) null, (Entry) null, "head", createHeadCommand());
        this.head = entry;
        Entry entry2 = new Entry(entry, (Entry) null, "tail", createTailCommand());
        this.tail = entry2;
        Entry unused = this.head.nextEntry = entry2;
    }

    private IoHandlerCommand createHeadCommand() {
        return new IoHandlerCommand() {
            public void execute(IoHandlerCommand.NextCommand nextCommand, IoSession ioSession, Object obj) throws Exception {
                nextCommand.execute(ioSession, obj);
            }
        };
    }

    private IoHandlerCommand createTailCommand() {
        return new IoHandlerCommand() {
            public void execute(IoHandlerCommand.NextCommand nextCommand, IoSession ioSession, Object obj) throws Exception {
                IoHandlerCommand.NextCommand nextCommand2 = (IoHandlerCommand.NextCommand) ioSession.getAttribute(IoHandlerChain.this.NEXT_COMMAND);
                if (nextCommand2 != null) {
                    nextCommand2.execute(ioSession, obj);
                }
            }
        };
    }

    public Entry getEntry(String str) {
        Entry entry = this.name2entry.get(str);
        if (entry == null) {
            return null;
        }
        return entry;
    }

    public IoHandlerCommand get(String str) {
        Entry entry = getEntry(str);
        if (entry == null) {
            return null;
        }
        return entry.getCommand();
    }

    public IoHandlerCommand.NextCommand getNextCommand(String str) {
        Entry entry = getEntry(str);
        if (entry == null) {
            return null;
        }
        return entry.getNextCommand();
    }

    public synchronized void addFirst(String str, IoHandlerCommand ioHandlerCommand) {
        checkAddable(str);
        register(this.head, str, ioHandlerCommand);
    }

    public synchronized void addLast(String str, IoHandlerCommand ioHandlerCommand) {
        checkAddable(str);
        register(this.tail.prevEntry, str, ioHandlerCommand);
    }

    public synchronized void addBefore(String str, String str2, IoHandlerCommand ioHandlerCommand) {
        Entry checkOldName = checkOldName(str);
        checkAddable(str2);
        register(checkOldName.prevEntry, str2, ioHandlerCommand);
    }

    public synchronized void addAfter(String str, String str2, IoHandlerCommand ioHandlerCommand) {
        Entry checkOldName = checkOldName(str);
        checkAddable(str2);
        register(checkOldName, str2, ioHandlerCommand);
    }

    public synchronized IoHandlerCommand remove(String str) {
        Entry checkOldName;
        checkOldName = checkOldName(str);
        deregister(checkOldName);
        return checkOldName.getCommand();
    }

    public synchronized void clear() throws Exception {
        Iterator it = new ArrayList(this.name2entry.keySet()).iterator();
        while (it.hasNext()) {
            remove((String) it.next());
        }
    }

    private void register(Entry entry, String str, IoHandlerCommand ioHandlerCommand) {
        Entry entry2 = new Entry(entry, entry.nextEntry, str, ioHandlerCommand);
        Entry unused = entry.nextEntry.prevEntry = entry2;
        Entry unused2 = entry.nextEntry = entry2;
        this.name2entry.put(str, entry2);
    }

    private void deregister(Entry entry) {
        Entry access$300 = entry.prevEntry;
        Entry access$100 = entry.nextEntry;
        Entry unused = access$300.nextEntry = access$100;
        Entry unused2 = access$100.prevEntry = access$300;
        this.name2entry.remove(entry.name);
    }

    private Entry checkOldName(String str) {
        Entry entry = this.name2entry.get(str);
        if (entry != null) {
            return entry;
        }
        throw new IllegalArgumentException("Unknown filter name:" + str);
    }

    private void checkAddable(String str) {
        if (this.name2entry.containsKey(str)) {
            throw new IllegalArgumentException("Other filter is using the same name '" + str + "'");
        }
    }

    public void execute(IoHandlerCommand.NextCommand nextCommand, IoSession ioSession, Object obj) throws Exception {
        if (nextCommand != null) {
            ioSession.setAttribute(this.NEXT_COMMAND, nextCommand);
        }
        try {
            callNextCommand(this.head, ioSession, obj);
        } finally {
            ioSession.removeAttribute(this.NEXT_COMMAND);
        }
    }

    /* access modifiers changed from: private */
    public void callNextCommand(Entry entry, IoSession ioSession, Object obj) throws Exception {
        entry.getCommand().execute(entry.getNextCommand(), ioSession, obj);
    }

    public List<Entry> getAll() {
        ArrayList arrayList = new ArrayList();
        for (Entry access$100 = this.head.nextEntry; access$100 != this.tail; access$100 = access$100.nextEntry) {
            arrayList.add(access$100);
        }
        return arrayList;
    }

    public List<Entry> getAllReversed() {
        ArrayList arrayList = new ArrayList();
        for (Entry access$300 = this.tail.prevEntry; access$300 != this.head; access$300 = access$300.prevEntry) {
            arrayList.add(access$300);
        }
        return arrayList;
    }

    public boolean contains(String str) {
        return getEntry(str) != null;
    }

    public boolean contains(IoHandlerCommand ioHandlerCommand) {
        for (Entry access$100 = this.head.nextEntry; access$100 != this.tail; access$100 = access$100.nextEntry) {
            if (access$100.getCommand() == ioHandlerCommand) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Class<? extends IoHandlerCommand> cls) {
        for (Entry access$100 = this.head.nextEntry; access$100 != this.tail; access$100 = access$100.nextEntry) {
            if (cls.isAssignableFrom(access$100.getCommand().getClass())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        boolean z = true;
        for (Entry access$100 = this.head.nextEntry; access$100 != this.tail; access$100 = access$100.nextEntry) {
            if (!z) {
                sb.append(", ");
            } else {
                z = false;
            }
            sb.append('(');
            sb.append(access$100.getName());
            sb.append(':');
            sb.append(access$100.getCommand());
            sb.append(')');
        }
        if (z) {
            sb.append("empty");
        }
        sb.append(" }");
        return sb.toString();
    }

    public class Entry {
        private final IoHandlerCommand command;
        /* access modifiers changed from: private */
        public final String name;
        private final IoHandlerCommand.NextCommand nextCommand;
        /* access modifiers changed from: private */
        public Entry nextEntry;
        /* access modifiers changed from: private */
        public Entry prevEntry;

        private Entry(Entry entry, Entry entry2, String str, IoHandlerCommand ioHandlerCommand) {
            if (ioHandlerCommand == null) {
                throw new IllegalArgumentException("command");
            } else if (str != null) {
                this.prevEntry = entry;
                this.nextEntry = entry2;
                this.name = str;
                this.command = ioHandlerCommand;
                this.nextCommand = new IoHandlerCommand.NextCommand(IoHandlerChain.this) {
                    public void execute(IoSession ioSession, Object obj) throws Exception {
                        IoHandlerChain.this.callNextCommand(Entry.this.nextEntry, ioSession, obj);
                    }
                };
            } else {
                throw new IllegalArgumentException(ServiceProvider.NAME);
            }
        }

        public String getName() {
            return this.name;
        }

        public IoHandlerCommand getCommand() {
            return this.command;
        }

        public IoHandlerCommand.NextCommand getNextCommand() {
            return this.nextCommand;
        }
    }
}
