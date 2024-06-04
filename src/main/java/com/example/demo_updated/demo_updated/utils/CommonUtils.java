package com.example.demo_updated.demo_updated.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    public static Map<String,String> hmapForRoleMapping =new HashMap<>();

    static{
        hmapForRoleMapping.put("admin","manager");
        hmapForRoleMapping.put("manager","worker");
    }

}
