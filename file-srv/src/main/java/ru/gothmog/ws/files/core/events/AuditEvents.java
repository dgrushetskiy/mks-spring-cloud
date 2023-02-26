package ru.gothmog.ws.files.core.events;

public enum AuditEvents {
    FILE_GET_RQ("FILE_GET_RQ"),
    FILE_GET_RS("FILE_GET_RS"),
    FILE_UPLOAD_RQ("FILE_UPLOAD_RQ"),
    FILE_UPLOAD_RS("FILE_UPLOAD_RS");

    public final String name;

    AuditEvents(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
