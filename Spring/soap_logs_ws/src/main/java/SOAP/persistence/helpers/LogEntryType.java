package SOAP.persistence.helpers;

import java.util.HashMap;
import java.util.Map;

public enum LogEntryType {
    CREATE(1),
    UPDATE(2),
    DELETE(3);

    private int value;
    private static Map map = new HashMap<>();

    private LogEntryType(int value) {
        this.value = value;
    }

    static {
        for (LogEntryType type_of_change : LogEntryType.values()) {
            map.put(type_of_change.value, type_of_change);
        }
    }

    public static LogEntryType valueOf(int pageType) {
        return (LogEntryType) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
