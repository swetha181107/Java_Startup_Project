package kec.allocator;
import java.io.*; import java.nio.file.*; import java.sql.*; import java.util.*;
public final class DBConnection {
 private static final Properties P = load(); private DBConnection() {}
 private static Properties load() { Properties p=new Properties(); Path f=Path.of("config","db.properties");
  try { if(Files.exists(f)) { try(InputStream in=Files.newInputStream(f)){p.load(in);} }
   else { p.setProperty("db.url",System.getenv().getOrDefault("KEC_DB_URL","jdbc:mysql://localhost:3306/kec_incubator?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC")); p.setProperty("db.user",System.getenv().getOrDefault("KEC_DB_USER","root")); p.setProperty("db.password",System.getenv().getOrDefault("KEC_DB_PASSWORD","")); } }
  catch(IOException e){throw new ExceptionInInitializerError(e);} return p; }
 public static Connection getConnection() throws Exception { try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch(ClassNotFoundException e){throw new IllegalStateException("MySQL Connector/J missing: place its JAR in lib/",e);} return DriverManager.getConnection(P.getProperty("db.url"),P.getProperty("db.user"),P.getProperty("db.password","")); }
}
