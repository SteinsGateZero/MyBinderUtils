package com.steinsgatezero.coolbinderprocessor;

import java.util.List;

final class InjectInfo {
    private String typeName;
    private List<FieldInfo> list;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<FieldInfo> getList() {
        return list;
    }

    public void setList(List<FieldInfo> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof InjectInfo)) return false;
        if (!((InjectInfo) obj).getTypeName().equals(this.getTypeName())) return false;
        return true;
    }
}
