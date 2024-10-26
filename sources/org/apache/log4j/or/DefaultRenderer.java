package org.apache.log4j.or;

class DefaultRenderer implements ObjectRenderer {
    DefaultRenderer() {
    }

    public String doRender(Object obj) {
        return obj.toString();
    }
}
