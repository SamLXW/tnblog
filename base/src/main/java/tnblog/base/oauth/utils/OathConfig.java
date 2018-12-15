package tnblog.base.oauth.utils;

import tnblog.base.oauth.APIConfig;















public class OathConfig
{
  public static String getValue(String key)
  {
    return APIConfig.getInstance().getValue(key);
  }
}
