package itis.inf304.taskhunter.util;


import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", "dlne6ri3i");
            configMap.put("api_key", "162261861293164");
            configMap.put("api_secret", "1vXNPNDRoVgMYRApO8WPSFc78VU");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}