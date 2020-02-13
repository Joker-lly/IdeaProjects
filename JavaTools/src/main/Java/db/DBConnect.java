package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DBConnect {
	private Connection conn = null;

	private static DataSource dataSource;
	public static Properties dbProps = null;
	public static String dbName;
	public static Hashtable<String, DataSource> hmDataSource = new Hashtable<String, DataSource>();
	static {
		// 项目执行使用
		// InputStream is =
		// DBConnect.class.getClassLoader().getResourceAsStream("db.properties");
		// 本地执行使用
		File file = new File("src/main/resources/db.properties");
		dbProps = new Properties();
		try {
			InputStream is = new FileInputStream(file);
			dbProps.load(is);
			dbName = dbProps.getProperty("defaultDb");
		} catch (Exception e) {
			System.out.println("不能读取属性文件. " + "请确保db.properties在CLASSPATH指定的路径中");
		}
	}

	public static DataSource  getdataSource() {
		return dataSource;
	}

	/** 构造数据库的连接和访问类 */
	public DBConnect(String dbName) {
		initializeDataSource(dbName);
	}

	/** 无参数构造方法，默认找ldtj数据源 */
	public DBConnect() {
		initializeDataSource(dbName);
	}

	private void initializeDataSource(String dbName) {
		dataSource = hmDataSource.get(dbName);
		if (dataSource == null)// 建立数据库连接
		{
			String dbPool = dbProps.getProperty(dbName + ".dbPool");
			if (dbPool.equalsIgnoreCase("weblogic")) {
				String weblogic_pool = dbProps.getProperty(dbName + ".weblogic.poolName");
				Context ctx = null;
				Hashtable<Object, Object> ht = new Hashtable<Object, Object>();
				ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
				// ht.put(Context.PROVIDER_URL, "t3://10.1.3.49:7003");
				try {
					ctx = new InitialContext(ht);
					dataSource = (DataSource) ctx.lookup(weblogic_pool);
				} catch (Exception e) {
					System.out.println("数据库连接失败");
					e.printStackTrace();
				}
			} else if (dbPool.equalsIgnoreCase("druid")) {
				try {
					Hashtable<String, String> map = new Hashtable<String, String>();
					map.put("driverClassName", dbProps.getProperty(dbName + ".driverClassName"));
					map.put("url", dbProps.getProperty(dbName + ".url"));
					map.put("username", dbProps.getProperty(dbName + ".username"));
					map.put("password", dbProps.getProperty(dbName + ".password"));
					map.put("initialSize", dbProps.getProperty(dbName + ".initialSize"));
					map.put("maxActive", dbProps.getProperty(dbName + ".maxActive"));

					map.put("timeBetweenEvictionRunsMillis", "60000");
					map.put("minEvictableIdleTimeMillis", "300000");
					if(map.get("driverClassName").indexOf("oracle") != -1){
						map.put("validationQuery", "SELECT 1 FROM DUAL");
						map.put("testWhileIdle", "true");
						map.put("testOnBorrow", "false");
						map.put("testOnReturn", "false");
					}
					// map.put("poolPreparedStatements", "true");
					// map.put("maxPoolPreparedStatementPerConnectionSize",
					// "50");
					map.put("removeAbandoned", "true");
					map.put("removeAbandonedTimeout", "1800");
					map.put("logAbandoned", "true");
					// DruidDataSourceFactory.createDataSource(map).getConnection();
					dataSource = DruidDataSourceFactory.createDataSource(map);
				} catch (Exception e) {
					dataSource = null;
					System.out.println("数据库连接失败");
					e.printStackTrace();
				}
			}
			hmDataSource.put(dbName, dataSource);
		}
		try {
			conn = hmDataSource.get(dbName).getConnection();
			// if (!dbName.substring(dbName.length() - 4,
			// dbName.length()).toUpperCase().equals(conn.getMetaData().getUserName()))
			// {
			// System.out.println(1);
			// }
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public void close() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConn(Connection conn, PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			if (null != rs) {
				rs.close();
			}
			if (null != st) {
				st.close();
			}
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConn(Connection conn, PreparedStatement pst) {
		DBConnect.closeConn(conn, pst, null, null);
	}

	public static void closePst(PreparedStatement pst) {
		DBConnect.closeConn(null, pst, null, null);
	}

	public static void main(String[] args) {
		DBConnect dbConnect = new DBConnect("jcpt");
		System.out.println(dbConnect.getConnection());
	}

}
