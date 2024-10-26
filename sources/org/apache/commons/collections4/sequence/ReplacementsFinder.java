package org.apache.commons.collections4.sequence;

import java.util.ArrayList;
import java.util.List;

public class ReplacementsFinder<T> implements CommandVisitor<T> {
    private final ReplacementsHandler<T> handler;
    private final List<T> pendingDeletions = new ArrayList();
    private final List<T> pendingInsertions = new ArrayList();
    private int skipped = 0;

    public ReplacementsFinder(ReplacementsHandler<T> replacementsHandler) {
        this.handler = replacementsHandler;
    }

    public void visitInsertCommand(T t) {
        this.pendingInsertions.add(t);
    }

    public void visitKeepCommand(T t) {
        if (!this.pendingDeletions.isEmpty() || !this.pendingInsertions.isEmpty()) {
            this.handler.handleReplacement(this.skipped, this.pendingDeletions, this.pendingInsertions);
            this.pendingDeletions.clear();
            this.pendingInsertions.clear();
            this.skipped = 1;
            return;
        }
        this.skipped++;
    }

    public void visitDeleteCommand(T t) {
        this.pendingDeletions.add(t);
    }
}
