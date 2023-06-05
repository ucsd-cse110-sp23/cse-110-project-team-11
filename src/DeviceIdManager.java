import java.util.UUID;
import java.util.prefs.Preferences;

public class DeviceIdManager {
    private static final String DEVICE_ID_KEY = "device_id";
    private Preferences preferences;

    public DeviceIdManager() {
        preferences = Preferences.userNodeForPackage(DeviceIdManager.class);
    }

    public String getDeviceId() {
        String deviceId = preferences.get(DEVICE_ID_KEY, null);
        if(deviceId == null) {
            deviceId = UUID.randomUUID().toString();
            preferences.put(DEVICE_ID_KEY, deviceId);
        }
        return deviceId;
    }
}



