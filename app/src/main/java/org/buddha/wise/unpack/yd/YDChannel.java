package org.buddha.wise.unpack.yd;

import java.io.Serializable;

/**
 * Created by Yuan Jiwei on 17/3/25.
 */

class YDChannel implements Serializable {
    private String id;
    private String docid;
    private String icon;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
