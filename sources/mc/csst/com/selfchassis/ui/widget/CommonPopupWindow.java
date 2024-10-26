package mc.csst.com.selfchassis.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import mc.csst.com.selfchassis.R;

public class CommonPopupWindow extends FrameLayout {
    /* access modifiers changed from: private */
    public CommonPopupWindowListener commonPopupWindowListener;
    private View container;
    /* access modifiers changed from: private */
    public CharSequence[] items;
    private ImageView ivArrow;
    private Context mContext;
    /* access modifiers changed from: private */
    public int selectedPos;
    /* access modifiers changed from: private */
    public TextView selectedTextView;
    /* access modifiers changed from: private */
    public String selectedTxt;

    public interface CommonPopupWindowListener {
        void onItemSelected(String str, int i);
    }

    public int getSelectedPos() {
        return this.selectedPos;
    }

    public CommonPopupWindow(Context context) {
        this(context, (AttributeSet) null);
    }

    public CommonPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectedPos = 0;
        this.selectedTxt = "";
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CommonPopupTextArrays);
        this.items = obtainStyledAttributes.getTextArray(0);
        obtainStyledAttributes.recycle();
        this.mContext = context;
        initView(LayoutInflater.from(context).inflate(com.ciot.sentrymove.R.layout.common_popup_window, this, true));
    }

    private void initView(View view) {
        this.container = view.findViewById(com.ciot.sentrymove.R.id.container);
        TextView textView = (TextView) view.findViewById(com.ciot.sentrymove.R.id.tvText);
        this.selectedTextView = textView;
        CharSequence[] charSequenceArr = this.items;
        textView.setText((charSequenceArr == null || charSequenceArr.length == 0) ? "" : charSequenceArr[0]);
        this.ivArrow = (ImageView) view.findViewById(com.ciot.sentrymove.R.id.ivArrow);
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonPopupWindow commonPopupWindow = CommonPopupWindow.this;
                String unused = commonPopupWindow.selectedTxt = commonPopupWindow.selectedTextView.getText().toString().trim();
                CommonPopupWindow.this.show();
            }
        });
    }

    public void show() {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(com.ciot.sentrymove.R.layout.recycler_view_common_popup_window, (ViewGroup) null, false);
        final FixedPopupWindow fixedPopupWindow = new FixedPopupWindow(inflate, -2, -2, true);
        fixedPopupWindow.setAnimationStyle(-1);
        fixedPopupWindow.setOutsideTouchable(true);
        ((RecyclerView) inflate.findViewById(com.ciot.sentrymove.R.id.recyclerView)).setAdapter(new RecyclerView.Adapter() {
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new CommonPopupWindowViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(com.ciot.sentrymove.R.layout.item_common_popup_window, viewGroup, false));
            }

            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                if (viewHolder instanceof CommonPopupWindowViewHolder) {
                    CommonPopupWindowViewHolder commonPopupWindowViewHolder = (CommonPopupWindowViewHolder) viewHolder;
                    commonPopupWindowViewHolder.tvText.setText(CommonPopupWindow.this.items[i]);
                    if (commonPopupWindowViewHolder.tvText.getText().toString().equals(CommonPopupWindow.this.selectedTxt)) {
                        highLight(commonPopupWindowViewHolder.tvText, commonPopupWindowViewHolder.ivIcon);
                    } else {
                        normalStyle(commonPopupWindowViewHolder.tvText, commonPopupWindowViewHolder.ivIcon);
                    }
                }
            }

            private void highLight(TextView textView, ImageView imageView) {
                textView.setTextColor(CommonPopupWindow.this.getResources().getColor(com.ciot.sentrymove.R.color._ff0560fd));
                imageView.setImageResource(com.ciot.sentrymove.R.drawable.icon_common_popup_window_selected);
            }

            private void normalStyle(TextView textView, ImageView imageView) {
                textView.setTextColor(CommonPopupWindow.this.getResources().getColor(com.ciot.sentrymove.R.color.black));
                imageView.setImageResource(com.ciot.sentrymove.R.drawable.icon_common_popup_window_unselected);
            }

            public int getItemCount() {
                if (CommonPopupWindow.this.items == null) {
                    return 0;
                }
                return CommonPopupWindow.this.items.length;
            }

            /* renamed from: mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2$CommonPopupWindowViewHolder */
            class CommonPopupWindowViewHolder extends RecyclerView.ViewHolder {
                public ImageView ivIcon;
                public TextView tvText;

                public CommonPopupWindowViewHolder(View view) {
                    super(view);
                    setIsRecyclable(false);
                    this.tvText = (TextView) view.findViewById(com.ciot.sentrymove.R.id.tvText);
                    this.ivIcon = (ImageView) view.findViewById(com.ciot.sentrymove.R.id.ivIcon);
                    view.setOnTouchListener(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0026: INVOKE  
                          (r3v0 'view' android.view.View)
                          (wrap: mc.csst.com.selfchassis.ui.widget.-$$Lambda$CommonPopupWindow$2$CommonPopupWindowViewHolder$rSNilu_O6iIZ-R4eKqRsZhZYaMo : 0x0023: CONSTRUCTOR  (r0v7 mc.csst.com.selfchassis.ui.widget.-$$Lambda$CommonPopupWindow$2$CommonPopupWindowViewHolder$rSNilu_O6iIZ-R4eKqRsZhZYaMo) = 
                          (r1v0 'this' mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2$CommonPopupWindowViewHolder A[THIS])
                          (wrap: android.widget.PopupWindow : 0x001f: IGET  (r2v1 android.widget.PopupWindow) = 
                          (wrap: ? : ?: IGET  
                          (r1v0 'this' mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2$CommonPopupWindowViewHolder A[THIS])
                         mc.csst.com.selfchassis.ui.widget.CommonPopupWindow.2.CommonPopupWindowViewHolder.this$1 mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2)
                         mc.csst.com.selfchassis.ui.widget.CommonPopupWindow.2.val$popupWindow android.widget.PopupWindow)
                         call: mc.csst.com.selfchassis.ui.widget.-$$Lambda$CommonPopupWindow$2$CommonPopupWindowViewHolder$rSNilu_O6iIZ-R4eKqRsZhZYaMo.<init>(mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2$CommonPopupWindowViewHolder, android.widget.PopupWindow):void type: CONSTRUCTOR)
                         android.view.View.setOnTouchListener(android.view.View$OnTouchListener):void type: VIRTUAL in method: mc.csst.com.selfchassis.ui.widget.CommonPopupWindow.2.CommonPopupWindowViewHolder.<init>(mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2, android.view.View):void, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                        	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                        	at java.util.ArrayList.forEach(ArrayList.java:1259)
                        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0023: CONSTRUCTOR  (r0v7 mc.csst.com.selfchassis.ui.widget.-$$Lambda$CommonPopupWindow$2$CommonPopupWindowViewHolder$rSNilu_O6iIZ-R4eKqRsZhZYaMo) = 
                          (r1v0 'this' mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2$CommonPopupWindowViewHolder A[THIS])
                          (wrap: android.widget.PopupWindow : 0x001f: IGET  (r2v1 android.widget.PopupWindow) = 
                          (wrap: ? : ?: IGET  
                          (r1v0 'this' mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2$CommonPopupWindowViewHolder A[THIS])
                         mc.csst.com.selfchassis.ui.widget.CommonPopupWindow.2.CommonPopupWindowViewHolder.this$1 mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2)
                         mc.csst.com.selfchassis.ui.widget.CommonPopupWindow.2.val$popupWindow android.widget.PopupWindow)
                         call: mc.csst.com.selfchassis.ui.widget.-$$Lambda$CommonPopupWindow$2$CommonPopupWindowViewHolder$rSNilu_O6iIZ-R4eKqRsZhZYaMo.<init>(mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2$CommonPopupWindowViewHolder, android.widget.PopupWindow):void type: CONSTRUCTOR in method: mc.csst.com.selfchassis.ui.widget.CommonPopupWindow.2.CommonPopupWindowViewHolder.<init>(mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2, android.view.View):void, dex: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                        	... 76 more
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: mc.csst.com.selfchassis.ui.widget.-$$Lambda$CommonPopupWindow$2$CommonPopupWindowViewHolder$rSNilu_O6iIZ-R4eKqRsZhZYaMo, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                        	... 82 more
                        */
                    /*
                        this = this;
                        mc.csst.com.selfchassis.ui.widget.CommonPopupWindow.AnonymousClass2.this = r2
                        r1.<init>(r3)
                        r0 = 0
                        r1.setIsRecyclable(r0)
                        r0 = 2131296985(0x7f0902d9, float:1.8211902E38)
                        android.view.View r0 = r3.findViewById(r0)
                        android.widget.TextView r0 = (android.widget.TextView) r0
                        r1.tvText = r0
                        r0 = 2131296585(0x7f090149, float:1.821109E38)
                        android.view.View r0 = r3.findViewById(r0)
                        android.widget.ImageView r0 = (android.widget.ImageView) r0
                        r1.ivIcon = r0
                        android.widget.PopupWindow r2 = r1
                        mc.csst.com.selfchassis.ui.widget.-$$Lambda$CommonPopupWindow$2$CommonPopupWindowViewHolder$rSNilu_O6iIZ-R4eKqRsZhZYaMo r0 = new mc.csst.com.selfchassis.ui.widget.-$$Lambda$CommonPopupWindow$2$CommonPopupWindowViewHolder$rSNilu_O6iIZ-R4eKqRsZhZYaMo
                        r0.<init>(r1, r2)
                        r3.setOnTouchListener(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: mc.csst.com.selfchassis.ui.widget.CommonPopupWindow.AnonymousClass2.CommonPopupWindowViewHolder.<init>(mc.csst.com.selfchassis.ui.widget.CommonPopupWindow$2, android.view.View):void");
                }

                public /* synthetic */ boolean lambda$new$0$CommonPopupWindow$2$CommonPopupWindowViewHolder(final PopupWindow popupWindow, View view, MotionEvent motionEvent) {
                    final int adapterPosition = getAdapterPosition();
                    if (motionEvent.getAction() == 0) {
                        String unused = CommonPopupWindow.this.selectedTxt = CommonPopupWindow.this.items[adapterPosition].toString();
                        int unused2 = CommonPopupWindow.this.selectedPos = adapterPosition;
                        AnonymousClass2.this.notifyDataSetChanged();
                        CommonPopupWindow.this.getHandler().postDelayed(new Runnable() {
                            public void run() {
                                CommonPopupWindow.this.selectedTextView.setText(CommonPopupWindow.this.items[adapterPosition]);
                                if (CommonPopupWindow.this.commonPopupWindowListener != null) {
                                    CommonPopupWindow.this.commonPopupWindowListener.onItemSelected(CommonPopupWindow.this.items[adapterPosition].toString(), adapterPosition);
                                }
                                popupWindow.dismiss();
                            }
                        }, 100);
                        return true;
                    }
                    motionEvent.getAction();
                    return true;
                }
            }
        });
        inflate.measure(0, 0);
        fixedPopupWindow.showAsDropDown(this.container, (-(inflate.getMeasuredWidth() - this.container.getMeasuredWidth())) / 2, 20);
    }

    public void setCommonPopupWindowListener(CommonPopupWindowListener commonPopupWindowListener2) {
        this.commonPopupWindowListener = commonPopupWindowListener2;
    }
}
