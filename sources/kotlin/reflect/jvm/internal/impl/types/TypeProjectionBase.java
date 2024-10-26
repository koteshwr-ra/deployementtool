package kotlin.reflect.jvm.internal.impl.types;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;

public abstract class TypeProjectionBase implements TypeProjection {
    public String toString() {
        if (isStarProjection()) {
            return Marker.ANY_MARKER;
        }
        if (getProjectionKind() == Variance.INVARIANT) {
            return getType().toString();
        }
        return getProjectionKind() + StringUtils.SPACE + getType();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypeProjection)) {
            return false;
        }
        TypeProjection typeProjection = (TypeProjection) obj;
        return isStarProjection() == typeProjection.isStarProjection() && getProjectionKind() == typeProjection.getProjectionKind() && getType().equals(typeProjection.getType());
    }

    public int hashCode() {
        return (getProjectionKind().hashCode() * 31) + (isStarProjection() ? 17 : getType().hashCode());
    }
}
