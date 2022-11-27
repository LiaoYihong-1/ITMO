package Lab;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ClientInformation implements Serializable {
    private String ip;
    private int port;
    private String Database;
    private String user;
    private String password;
    private String hash;
    private boolean create;

    public ClientInformation(String user, String password,boolean choose){
        this.Database = "studs";
        this.user = user;
        this.password = password;
        this.port = 5432;
        this.ip = "pg";
        this.hash = setHash(password);
        this.create = choose;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return Database;
    }

    public String getUser() {
        return user;
    }

    private String setHash(String s){
        String result = null;
        if(s!=null&&s.length()>0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
                messageDigest.update(s.getBytes());
                byte [] buffer = messageDigest.digest();
                result = new String (buffer,0,buffer.length);
                this.hash = result;
            }catch (NoSuchAlgorithmException A){
                A.printStackTrace();
            }
        }
        return result;
    }

    public boolean isCreate() {
        return create;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "InputNew{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", Database='" + Database + '\'' +
                ", user='" + user + '\'' +
                ", password=" + password +
                '}';
    }
}
