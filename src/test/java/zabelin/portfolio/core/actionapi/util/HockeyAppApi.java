package zabelin.portfolio.core.actionapi.util;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HockeyAppApi {
    private String m_user;
    private String m_password;
    private final String m_url;
    private String token;

    public HockeyAppApi(String m_user, String m_password, String m_url) {
        this.m_user = m_user;
        this.m_password = m_password;
        this.m_url = m_url;
    }

    public HockeyAppApi(String token, String m_url) {
        this.token = token;
        this.m_url = m_url;
    }

    private static String getAuthorization(String user, String password) {
        return getBase64((user + ":" + password).getBytes(StandardCharsets.UTF_8));
    }

    private static String getBase64(byte[] buffer) {
        char[] map = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < buffer.length; ++i) {
            byte b0 = buffer[i++];
            byte b1 = 0;
            byte b2 = 0;
            int bytes = 3;
            if (i < buffer.length) {
                b1 = buffer[i++];
                if (i < buffer.length) {
                    b2 = buffer[i];
                } else {
                    bytes = 2;
                }
            } else {
                bytes = 1;
            }

            int total = b0 << 16 | b1 << 8 | b2;
            switch (bytes) {
                case 1:
                    sb.append(map[total >> 18 & 63]);
                    sb.append(map[total >> 12 & 63]);
                    sb.append('=');
                    sb.append('=');
                    break;
                case 2:
                    sb.append(map[total >> 18 & 63]);
                    sb.append(map[total >> 12 & 63]);
                    sb.append(map[total >> 6 & 63]);
                    sb.append('=');
                    break;
                case 3:
                    sb.append(map[total >> 18 & 63]);
                    sb.append(map[total >> 12 & 63]);
                    sb.append(map[total >> 6 & 63]);
                    sb.append(map[total & 63]);
            }
        }

        return sb.toString();
    }

    public String getUser() {
        return this.m_user;
    }

    public void setUser(String user) {
        this.m_user = user;
    }

    public String getPassword() {
        return this.m_password;
    }

    public void setPassword(String password) {
        this.m_password = password;
    }

    public Object sendGet(String uri) throws Exception {
        return this.sendRequest("GET", uri, null, null);
    }

    public Object sendGet(String uri, String fileName) throws Exception {
        return this.sendRequest("GET", uri, null, fileName);
    }

    public Object getAppVersion(String appId) throws Exception {
        return this.sendGet(String.format("apps/%s/app_versions/", appId));
    }

    public Object sendPost(String uri, Object data) throws Exception {
        return this.sendRequest("POST", uri, data, null);
    }

    private Object sendRequest(String method, String uri, Object data, String fileName) throws Exception {
        URL url = new URL(this.m_url + uri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.addRequestProperty("Content-Type", "application/json");
        if (this.token.isEmpty()) {
            String auth = getAuthorization(this.m_user, this.m_password);
            conn.addRequestProperty("Authorization", "Basic " + auth);
        } else {
            conn.addRequestProperty("X-HockeyAppToken", this.token);
        }

        if (method == "POST" && data != null) {
            byte[] block = (new JSONObject(data)).toString().getBytes(StandardCharsets.UTF_8);
            conn.setDoOutput(true);
            OutputStream ostream = conn.getOutputStream();
            ostream.write(block);
            ostream.flush();
        }

        int status = conn.getResponseCode();
        InputStream istream;
        if (status != 200) {
            istream = conn.getErrorStream();
            if (istream == null) {
                throw new RuntimeException("TestRail API return HTTP " + status + " (No additional error message received)");
            }
        } else {
            istream = conn.getInputStream();
        }

        String text = "";
        String error;
        if (istream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(istream, StandardCharsets.UTF_8));
            if (fileName != null) {
                BufferedInputStream inputStream = new BufferedInputStream(istream);
                this.saveUrl(fileName, inputStream);
            } else {
                while ((error = reader.readLine()) != null) {
                    text = text + error;
                    text = text + System.getProperty("line.separator");
                }
            }

            reader.close();
        }

        JSONObject result = new JSONObject(text);
        if (status != 200) {
            error = "No additional error message received";
            if (result.has("error")) {
                error = result.getString("error");
            }

            throw new RuntimeException(String.format("TestRail API returned HTTP %s (%s)", status, error));
        } else {
            return result;
        }
    }

    public void saveUrl(String filename, BufferedInputStream in) throws IOException {
        FileOutputStream fout = null;

        try {
            fout = new FileOutputStream(filename);
            byte[] data = new byte[10240];

            int count;
            while ((count = in.read(data, 0, 10240)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }

            if (fout != null) {
                fout.close();
            }

        }

    }
}
