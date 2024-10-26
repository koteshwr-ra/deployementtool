package kotlin.reflect.jvm.internal.impl.descriptors;

import com.ciot.realm.db.Person;
import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: ModuleDescriptor.kt */
public interface ModuleDescriptor extends DeclarationDescriptor {
    KotlinBuiltIns getBuiltIns();

    PackageViewDescriptor getPackage(FqName fqName);

    Collection<FqName> getSubPackagesOf(FqName fqName, Function1<? super Name, Boolean> function1);

    boolean shouldSeeInternalsOf(ModuleDescriptor moduleDescriptor);

    /* compiled from: ModuleDescriptor.kt */
    public static final class DefaultImpls {
        public static DeclarationDescriptor getContainingDeclaration(ModuleDescriptor moduleDescriptor) {
            return null;
        }

        public static <R, D> R accept(ModuleDescriptor moduleDescriptor, DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
            Intrinsics.checkParameterIsNotNull(declarationDescriptorVisitor, Person.VISITOR);
            return declarationDescriptorVisitor.visitModuleDeclaration(moduleDescriptor, d);
        }
    }
}
