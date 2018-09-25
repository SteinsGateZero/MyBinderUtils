package com.steinsgatezero.coolbinderprocessor;

final class FieldInfo {
    private String fieldName;
    private String fieldTypeName;
    private String IntentName;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldTypeName() {
        return fieldTypeName;
    }

    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }

    public String getIntentName() {
        return IntentName;
    }

    public void setIntentName(String intentName) {
        IntentName = intentName;
    }
}
