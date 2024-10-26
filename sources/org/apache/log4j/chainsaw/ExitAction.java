package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.apache.log4j.Logger;

class ExitAction extends AbstractAction {
    public static final ExitAction INSTANCE = new ExitAction();
    private static final Logger LOG;
    static /* synthetic */ Class class$org$apache$log4j$chainsaw$ExitAction;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class cls = class$org$apache$log4j$chainsaw$ExitAction;
        if (cls == null) {
            cls = class$("org.apache.log4j.chainsaw.ExitAction");
            class$org$apache$log4j$chainsaw$ExitAction = cls;
        }
        LOG = Logger.getLogger(cls);
    }

    private ExitAction() {
    }

    public void actionPerformed(ActionEvent actionEvent) {
        LOG.info("shutting down");
        System.exit(0);
    }
}
