package com.bdqn.spz.generator;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author ʩ����
 *
 */
public class Database extends GeneratorEntity {
	private String sql=ConfigManager.getInstance().getValue("sql");
	public Database() {
		BaseDao baseDao=new BaseDao();
		try {
			List<Object> tableNames=baseDao.getList(sql);
			for (Object tableName : tableNames) {
				this.tableName=tableName.toString();
				getEntity();
			}
			System.out.println("���ɳɹ�");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			baseDao.closeConnection();
		}
	}
	
	public static void main(String[] args) {
		new Database();
	}

}
