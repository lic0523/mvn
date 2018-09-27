package controller;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DbcontextHolder extends AbstractRoutingDataSource {
    
    public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    
    /**
     * ���õ�ǰ����Դ
     * @param dbType
     */
    public static void setDbType(String dbType){
        contextHolder.set(dbType);
    }
    /**
     * ��õ�ǰ����Դ
     * @return
     */
    public static String getDbType(){
        String dbType = (String)contextHolder.get();
        return dbType;
    }
    /**
     *���������
     *
     */
    public void clearContext(){
        contextHolder.remove();
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return DbcontextHolder.getDbType();
    }
}