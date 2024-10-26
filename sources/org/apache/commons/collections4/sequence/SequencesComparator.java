package org.apache.commons.collections4.sequence;

import java.util.List;
import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.functors.DefaultEquator;

public class SequencesComparator<T> {
    private final Equator<? super T> equator;
    private final List<T> sequence1;
    private final List<T> sequence2;
    private final int[] vDown;
    private final int[] vUp;

    public SequencesComparator(List<T> list, List<T> list2) {
        this(list, list2, DefaultEquator.defaultEquator());
    }

    public SequencesComparator(List<T> list, List<T> list2, Equator<? super T> equator2) {
        this.sequence1 = list;
        this.sequence2 = list2;
        this.equator = equator2;
        int size = list.size() + list2.size() + 2;
        this.vDown = new int[size];
        this.vUp = new int[size];
    }

    public EditScript<T> getScript() {
        EditScript<T> editScript = new EditScript<>();
        buildScript(0, this.sequence1.size(), 0, this.sequence2.size(), editScript);
        return editScript;
    }

    private Snake buildSnake(int i, int i2, int i3, int i4) {
        int i5 = i;
        while (true) {
            int i6 = i5 - i2;
            if (i6 < i4 && i5 < i3 && this.equator.equate(this.sequence1.get(i5), this.sequence2.get(i6))) {
                i5++;
            }
        }
        return new Snake(i, i5, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        if (r11[r10 - 1] < r11[r10 + 1]) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00be, code lost:
        if (r11[r12 + 1] <= r11[r12 - 1]) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0128, code lost:
        r16 = r6;
        r5 = r5 + 1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x009a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x011a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00a3 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0123 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.collections4.sequence.SequencesComparator.Snake getMiddleSnake(int r18, int r19, int r20, int r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            int r5 = r2 - r1
            int r6 = r4 - r3
            if (r5 == 0) goto L_0x0136
            if (r6 != 0) goto L_0x0014
            goto L_0x0136
        L_0x0014:
            int r7 = r5 - r6
            int r6 = r6 + r5
            int r5 = r6 % 2
            if (r5 != 0) goto L_0x001c
            goto L_0x001e
        L_0x001c:
            int r6 = r6 + 1
        L_0x001e:
            int r6 = r6 / 2
            int[] r5 = r0.vDown
            int r8 = r6 + 1
            r5[r8] = r1
            int[] r5 = r0.vUp
            int r9 = r2 + 1
            r5[r8] = r9
            r5 = 0
        L_0x002d:
            if (r5 > r6) goto L_0x012e
            int r8 = -r5
            r9 = r8
        L_0x0031:
            if (r9 > r5) goto L_0x00a6
            int r10 = r9 + r6
            if (r9 == r8) goto L_0x0051
            if (r9 == r5) goto L_0x0046
            int[] r11 = r0.vDown
            int r12 = r10 + -1
            r12 = r11[r12]
            int r13 = r10 + 1
            r11 = r11[r13]
            if (r12 >= r11) goto L_0x0046
            goto L_0x0051
        L_0x0046:
            int[] r11 = r0.vDown
            int r12 = r10 + -1
            r12 = r11[r12]
            int r12 = r12 + 1
            r11[r10] = r12
            goto L_0x0059
        L_0x0051:
            int[] r11 = r0.vDown
            int r12 = r10 + 1
            r12 = r11[r12]
            r11[r10] = r12
        L_0x0059:
            int[] r11 = r0.vDown
            r11 = r11[r10]
            int r12 = r11 - r1
            int r12 = r12 + r3
            int r12 = r12 - r9
        L_0x0061:
            if (r11 >= r2) goto L_0x0082
            if (r12 >= r4) goto L_0x0082
            org.apache.commons.collections4.Equator<? super T> r13 = r0.equator
            java.util.List<T> r14 = r0.sequence1
            java.lang.Object r14 = r14.get(r11)
            java.util.List<T> r15 = r0.sequence2
            java.lang.Object r15 = r15.get(r12)
            boolean r13 = r13.equate(r14, r15)
            if (r13 == 0) goto L_0x0082
            int[] r13 = r0.vDown
            int r11 = r11 + 1
            r13[r10] = r11
            int r12 = r12 + 1
            goto L_0x0061
        L_0x0082:
            int r11 = r7 % 2
            if (r11 == 0) goto L_0x00a3
            int r11 = r7 - r5
            if (r11 > r9) goto L_0x00a3
            int r11 = r7 + r5
            if (r9 > r11) goto L_0x00a3
            int[] r11 = r0.vUp
            int r12 = r10 - r7
            r13 = r11[r12]
            int[] r14 = r0.vDown
            r10 = r14[r10]
            if (r13 > r10) goto L_0x00a3
            r5 = r11[r12]
            int r9 = r9 + r1
            int r9 = r9 - r3
            org.apache.commons.collections4.sequence.SequencesComparator$Snake r1 = r0.buildSnake(r5, r9, r2, r4)
            return r1
        L_0x00a3:
            int r9 = r9 + 2
            goto L_0x0031
        L_0x00a6:
            int r9 = r7 - r5
            r10 = r9
        L_0x00a9:
            int r11 = r7 + r5
            if (r10 > r11) goto L_0x0128
            int r12 = r10 + r6
            int r12 = r12 - r7
            if (r10 == r9) goto L_0x00ca
            if (r10 == r11) goto L_0x00c1
            int[] r11 = r0.vUp
            int r13 = r12 + 1
            r13 = r11[r13]
            int r14 = r12 + -1
            r11 = r11[r14]
            if (r13 > r11) goto L_0x00c1
            goto L_0x00ca
        L_0x00c1:
            int[] r11 = r0.vUp
            int r13 = r12 + -1
            r13 = r11[r13]
            r11[r12] = r13
            goto L_0x00d4
        L_0x00ca:
            int[] r11 = r0.vUp
            int r13 = r12 + 1
            r13 = r11[r13]
            int r13 = r13 + -1
            r11[r12] = r13
        L_0x00d4:
            int[] r11 = r0.vUp
            r11 = r11[r12]
            int r11 = r11 + -1
            int r13 = r11 - r1
            int r13 = r13 + r3
            int r13 = r13 - r10
        L_0x00de:
            if (r11 < r1) goto L_0x0104
            if (r13 < r3) goto L_0x0104
            org.apache.commons.collections4.Equator<? super T> r14 = r0.equator
            java.util.List<T> r15 = r0.sequence1
            java.lang.Object r15 = r15.get(r11)
            r16 = r6
            java.util.List<T> r6 = r0.sequence2
            java.lang.Object r6 = r6.get(r13)
            boolean r6 = r14.equate(r15, r6)
            if (r6 == 0) goto L_0x0106
            int[] r6 = r0.vUp
            int r14 = r11 + -1
            r6[r12] = r11
            int r13 = r13 + -1
            r11 = r14
            r6 = r16
            goto L_0x00de
        L_0x0104:
            r16 = r6
        L_0x0106:
            int r6 = r7 % 2
            if (r6 != 0) goto L_0x0123
            if (r8 > r10) goto L_0x0123
            if (r10 > r5) goto L_0x0123
            int[] r6 = r0.vUp
            r11 = r6[r12]
            int[] r13 = r0.vDown
            int r14 = r12 + r7
            r13 = r13[r14]
            if (r11 > r13) goto L_0x0123
            r5 = r6[r12]
            int r10 = r10 + r1
            int r10 = r10 - r3
            org.apache.commons.collections4.sequence.SequencesComparator$Snake r1 = r0.buildSnake(r5, r10, r2, r4)
            return r1
        L_0x0123:
            int r10 = r10 + 2
            r6 = r16
            goto L_0x00a9
        L_0x0128:
            r16 = r6
            int r5 = r5 + 1
            goto L_0x002d
        L_0x012e:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Internal Error"
            r1.<init>(r2)
            throw r1
        L_0x0136:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.sequence.SequencesComparator.getMiddleSnake(int, int, int, int):org.apache.commons.collections4.sequence.SequencesComparator$Snake");
    }

    private void buildScript(int i, int i2, int i3, int i4, EditScript<T> editScript) {
        Snake middleSnake = getMiddleSnake(i, i2, i3, i4);
        if (middleSnake == null || ((middleSnake.getStart() == i2 && middleSnake.getDiag() == i2 - i4) || (middleSnake.getEnd() == i && middleSnake.getDiag() == i - i3))) {
            int i5 = i;
            int i6 = i3;
            while (true) {
                if (i5 < i2 || i6 < i4) {
                    if (i5 < i2 && i6 < i4 && this.equator.equate(this.sequence1.get(i5), this.sequence2.get(i6))) {
                        editScript.append((KeepCommand<T>) new KeepCommand(this.sequence1.get(i5)));
                        i5++;
                    } else if (i2 - i > i4 - i3) {
                        editScript.append((DeleteCommand<T>) new DeleteCommand(this.sequence1.get(i5)));
                        i5++;
                    } else {
                        editScript.append((InsertCommand<T>) new InsertCommand(this.sequence2.get(i6)));
                    }
                    i6++;
                } else {
                    return;
                }
            }
        } else {
            buildScript(i, middleSnake.getStart(), i3, middleSnake.getStart() - middleSnake.getDiag(), editScript);
            for (int start = middleSnake.getStart(); start < middleSnake.getEnd(); start++) {
                editScript.append((KeepCommand<T>) new KeepCommand(this.sequence1.get(start)));
            }
            buildScript(middleSnake.getEnd(), i2, middleSnake.getEnd() - middleSnake.getDiag(), i4, editScript);
        }
    }

    private static class Snake {
        private final int diag;
        private final int end;
        private final int start;

        public Snake(int i, int i2, int i3) {
            this.start = i;
            this.end = i2;
            this.diag = i3;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public int getDiag() {
            return this.diag;
        }
    }
}
