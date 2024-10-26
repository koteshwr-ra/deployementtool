package org.apache.commons.collections4.sequence;

import java.util.ArrayList;
import java.util.List;

public class EditScript<T> {
    private final List<EditCommand<T>> commands = new ArrayList();
    private int lcsLength = 0;
    private int modifications = 0;

    public void append(KeepCommand<T> keepCommand) {
        this.commands.add(keepCommand);
        this.lcsLength++;
    }

    public void append(InsertCommand<T> insertCommand) {
        this.commands.add(insertCommand);
        this.modifications++;
    }

    public void append(DeleteCommand<T> deleteCommand) {
        this.commands.add(deleteCommand);
        this.modifications++;
    }

    public void visit(CommandVisitor<T> commandVisitor) {
        for (EditCommand<T> accept : this.commands) {
            accept.accept(commandVisitor);
        }
    }

    public int getLCSLength() {
        return this.lcsLength;
    }

    public int getModifications() {
        return this.modifications;
    }
}
