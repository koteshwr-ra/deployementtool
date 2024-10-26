package org.apache.mina.proxy.handlers.http.ntlm;

import com.alibaba.android.arouter.utils.Consts;
import com.google.common.base.Ascii;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import org.apache.commons.lang3.CharEncoding;
import org.apache.mina.proxy.utils.ByteUtilities;

public class NTLMUtilities implements NTLMConstants {
    public static final byte[] writeSecurityBuffer(short s, int i) {
        byte[] bArr = new byte[8];
        writeSecurityBuffer(s, s, i, bArr, 0);
        return bArr;
    }

    public static final void writeSecurityBuffer(short s, short s2, int i, byte[] bArr, int i2) {
        ByteUtilities.writeShort(s, bArr, i2);
        ByteUtilities.writeShort(s2, bArr, i2 + 2);
        ByteUtilities.writeInt(i, bArr, i2 + 4);
    }

    public static final void writeOSVersion(byte b, byte b2, short s, byte[] bArr, int i) {
        bArr[i] = b;
        bArr[i + 1] = b2;
        bArr[i + 2] = (byte) s;
        bArr[i + 3] = (byte) (s >> 8);
        bArr[i + 4] = 0;
        bArr[i + 5] = 0;
        bArr[i + 6] = 0;
        bArr[i + 7] = Ascii.SI;
    }

    public static final byte[] getOsVersion() {
        String readLine;
        String property = System.getProperty("os.name");
        if (property == null || !property.toUpperCase().contains("WINDOWS")) {
            return DEFAULT_OS_VERSION;
        }
        byte[] bArr = new byte[8];
        try {
            Process exec = Runtime.getRuntime().exec("cmd /C ver");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            exec.waitFor();
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null || readLine.length() == 0) {
                    bufferedReader.close();
                }
                readLine = bufferedReader.readLine();
                break;
            } while (readLine.length() == 0);
            bufferedReader.close();
            if (readLine != null) {
                int indexOf = readLine.toLowerCase().indexOf("version");
                if (indexOf != -1) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine.substring(indexOf + 8, readLine.indexOf(93)), Consts.DOT);
                    if (stringTokenizer.countTokens() == 3) {
                        writeOSVersion(Byte.parseByte(stringTokenizer.nextToken()), Byte.parseByte(stringTokenizer.nextToken()), Short.parseShort(stringTokenizer.nextToken()), bArr, 0);
                        return bArr;
                    }
                    throw new Exception();
                }
                throw new Exception();
            }
            throw new Exception();
        } catch (Exception unused) {
            try {
                String property2 = System.getProperty("os.version");
                writeOSVersion(Byte.parseByte(property2.substring(0, 1)), Byte.parseByte(property2.substring(2, 3)), 0, bArr, 0);
            } catch (Exception unused2) {
                return DEFAULT_OS_VERSION;
            }
        }
    }

    public static final byte[] createType1Message(String str, String str2, Integer num, byte[] bArr) {
        if (bArr != null && bArr.length != 8) {
            throw new IllegalArgumentException("osVersion parameter should be a 8 byte wide array");
        } else if (str == null || str2 == null) {
            throw new IllegalArgumentException("workStation and domain must be non null");
        } else {
            int intValue = num != null ? num.intValue() | 8192 | 4096 : NTLMConstants.DEFAULT_FLAGS;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(NTLM_SIGNATURE);
                byteArrayOutputStream.write(ByteUtilities.writeInt(1));
                byteArrayOutputStream.write(ByteUtilities.writeInt(intValue));
                byte[] oEMStringAsByteArray = ByteUtilities.getOEMStringAsByteArray(str2);
                byte[] oEMStringAsByteArray2 = ByteUtilities.getOEMStringAsByteArray(str);
                int i = bArr != null ? 40 : 32;
                byteArrayOutputStream.write(writeSecurityBuffer((short) oEMStringAsByteArray.length, oEMStringAsByteArray2.length + i));
                byteArrayOutputStream.write(writeSecurityBuffer((short) oEMStringAsByteArray2.length, i));
                if (bArr != null) {
                    byteArrayOutputStream.write(bArr);
                }
                byteArrayOutputStream.write(oEMStringAsByteArray2);
                byteArrayOutputStream.write(oEMStringAsByteArray);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (IOException unused) {
                return null;
            }
        }
    }

    public static final int writeSecurityBufferAndUpdatePointer(ByteArrayOutputStream byteArrayOutputStream, short s, int i) throws IOException {
        byteArrayOutputStream.write(writeSecurityBuffer(s, i));
        return i + s;
    }

    public static final byte[] extractChallengeFromType2Message(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        System.arraycopy(bArr, 24, bArr2, 0, 8);
        return bArr2;
    }

    public static final int extractFlagsFromType2Message(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 20, bArr2, 0, 4);
        ByteUtilities.changeWordEndianess(bArr2, 0, 4);
        return ByteUtilities.makeIntFromByte4(bArr2);
    }

    public static final byte[] readSecurityBufferTarget(byte[] bArr, int i) {
        byte[] bArr2 = new byte[8];
        System.arraycopy(bArr, i, bArr2, 0, 8);
        ByteUtilities.changeWordEndianess(bArr2, 0, 8);
        int makeIntFromByte2 = ByteUtilities.makeIntFromByte2(bArr2);
        int makeIntFromByte4 = ByteUtilities.makeIntFromByte4(bArr2, 4);
        byte[] bArr3 = new byte[makeIntFromByte2];
        System.arraycopy(bArr, makeIntFromByte4, bArr3, 0, makeIntFromByte2);
        return bArr3;
    }

    public static final String extractTargetNameFromType2Message(byte[] bArr, Integer num) throws UnsupportedEncodingException {
        byte[] readSecurityBufferTarget = readSecurityBufferTarget(bArr, 12);
        if (ByteUtilities.isFlagSet(num == null ? extractFlagsFromType2Message(bArr) : num.intValue(), 1)) {
            return new String(readSecurityBufferTarget, CharEncoding.UTF_16LE);
        }
        return new String(readSecurityBufferTarget, "ASCII");
    }

    public static final byte[] extractTargetInfoFromType2Message(byte[] bArr, Integer num) {
        if (!ByteUtilities.isFlagSet(num == null ? extractFlagsFromType2Message(bArr) : num.intValue(), 8388608)) {
            return null;
        }
        return readSecurityBufferTarget(bArr, 40);
    }

    public static final void printTargetInformationBlockFromType2Message(byte[] bArr, Integer num, PrintWriter printWriter) throws UnsupportedEncodingException {
        int extractFlagsFromType2Message = num == null ? extractFlagsFromType2Message(bArr) : num.intValue();
        byte[] extractTargetInfoFromType2Message = extractTargetInfoFromType2Message(bArr, Integer.valueOf(extractFlagsFromType2Message));
        if (extractTargetInfoFromType2Message == null) {
            printWriter.println("No target information block found !");
            return;
        }
        int i = 0;
        while (extractTargetInfoFromType2Message[i] != 0) {
            printWriter.print("---\nType " + extractTargetInfoFromType2Message[i] + ": ");
            byte b = extractTargetInfoFromType2Message[i];
            if (b == 1) {
                printWriter.println("Server name");
            } else if (b == 2) {
                printWriter.println("Domain name");
            } else if (b == 3) {
                printWriter.println("Fully qualified DNS hostname");
            } else if (b == 4) {
                printWriter.println("DNS domain name");
            } else if (b == 5) {
                printWriter.println("Parent DNS domain name");
            }
            byte[] bArr2 = new byte[2];
            System.arraycopy(extractTargetInfoFromType2Message, i + 2, bArr2, 0, 2);
            ByteUtilities.changeByteEndianess(bArr2, 0, 2);
            int makeIntFromByte2 = ByteUtilities.makeIntFromByte2(bArr2, 0);
            printWriter.println("Length: " + makeIntFromByte2 + " bytes");
            printWriter.print("Data: ");
            if (ByteUtilities.isFlagSet(extractFlagsFromType2Message, 1)) {
                printWriter.println(new String(extractTargetInfoFromType2Message, i + 4, makeIntFromByte2, CharEncoding.UTF_16LE));
            } else {
                printWriter.println(new String(extractTargetInfoFromType2Message, i + 4, makeIntFromByte2, "ASCII"));
            }
            i += makeIntFromByte2 + 4;
            printWriter.flush();
        }
    }

    public static final byte[] createType3Message(String str, String str2, byte[] bArr, String str3, String str4, Integer num, byte[] bArr2) {
        if (bArr == null || bArr.length != 8) {
            throw new IllegalArgumentException("challenge[] should be a 8 byte wide array");
        } else if (bArr2 == null || bArr2.length == 8) {
            int intValue = num != null ? num.intValue() : NTLMConstants.DEFAULT_FLAGS;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(NTLM_SIGNATURE);
                byteArrayOutputStream.write(ByteUtilities.writeInt(3));
                byte[] lMResponse = NTLMResponses.getLMResponse(str2, bArr);
                byte[] nTLMResponse = NTLMResponses.getNTLMResponse(str2, bArr);
                boolean isFlagSet = ByteUtilities.isFlagSet(intValue, 1);
                byte[] encodeString = ByteUtilities.encodeString(str3, isFlagSet);
                byte[] encodeString2 = ByteUtilities.encodeString(str, isFlagSet);
                byte[] encodeString3 = ByteUtilities.encodeString(str4, isFlagSet);
                int i = bArr2 != null ? 72 : 64;
                writeSecurityBufferAndUpdatePointer(byteArrayOutputStream, (short) nTLMResponse.length, writeSecurityBufferAndUpdatePointer(byteArrayOutputStream, (short) lMResponse.length, encodeString.length + i + encodeString2.length + encodeString3.length));
                writeSecurityBufferAndUpdatePointer(byteArrayOutputStream, (short) encodeString3.length, writeSecurityBufferAndUpdatePointer(byteArrayOutputStream, (short) encodeString2.length, writeSecurityBufferAndUpdatePointer(byteArrayOutputStream, (short) encodeString.length, i)));
                byteArrayOutputStream.write(new byte[]{0, 0, 0, 0, -102, 0, 0, 0});
                byteArrayOutputStream.write(ByteUtilities.writeInt(intValue));
                if (bArr2 != null) {
                    byteArrayOutputStream.write(bArr2);
                }
                byteArrayOutputStream.write(encodeString);
                byteArrayOutputStream.write(encodeString2);
                byteArrayOutputStream.write(encodeString3);
                byteArrayOutputStream.write(lMResponse);
                byteArrayOutputStream.write(nTLMResponse);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new IllegalArgumentException("osVersion should be a 8 byte wide array");
        }
    }
}
