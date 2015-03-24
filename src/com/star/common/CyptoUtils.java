package com.star.common;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class CyptoUtils
{
  public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

  private static String byte2hex(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; ; i++)
    {
      if ((paramArrayOfByte == null) || (i >= paramArrayOfByte.length))
        return localStringBuilder.toString().toUpperCase();
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() == 1)
        localStringBuilder.append('0');
      localStringBuilder.append(str);
    }
  }

  public static String decode(String paramString1, String paramString2)
  {
    if (paramString2 == null)
      return null;
    try
    {
      DESKeySpec localDESKeySpec = new DESKeySpec(paramString1.getBytes());
      SecretKey localSecretKey = SecretKeyFactory.getInstance("DES").generateSecret(localDESKeySpec);
      Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      localCipher.init(2, localSecretKey, new IvParameterSpec("12345678".getBytes()));
      String str = new String(localCipher.doFinal(hex2byte(paramString2.getBytes())));
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString2;
  }

  public static String encode(String paramString1, String paramString2)
  {
    if (paramString2 == null)
      return null;
    try
    {
      DESKeySpec localDESKeySpec = new DESKeySpec(paramString1.getBytes());
      SecretKey localSecretKey = SecretKeyFactory.getInstance("DES").generateSecret(localDESKeySpec);
      Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      localCipher.init(1, localSecretKey, new IvParameterSpec("12345678".getBytes()));
      String str = byte2hex(localCipher.doFinal(paramString2.getBytes()));
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString2;
  }

  private static byte[] hex2byte(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length % 2 != 0)
      throw new IllegalArgumentException();
    byte[] arrayOfByte = new byte[paramArrayOfByte.length / 2];
    for (int i = 0; ; i += 2)
    {
      if (i >= paramArrayOfByte.length)
        return arrayOfByte;
      String str = new String(paramArrayOfByte, i, 2);
      arrayOfByte[(i / 2)] = ((byte)Integer.parseInt(str, 16));
    }
  }
}

/* Location:           F:\China\apktool\fdr.jar
 * Qualified Name:     com.lyl.p2p.android.common.CyptoUtils
 * JD-Core Version:    0.6.2
 */