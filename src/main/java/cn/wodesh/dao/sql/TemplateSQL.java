package cn.wodesh.dao.sql;

import cn.wodesh.bean.UserBean;
import cn.wodesh.dao.annotation.FieldName;
import cn.wodesh.dao.annotation.ID;
import cn.wodesh.dao.annotation.TableName;
import cn.wodesh.exception.FinalException;
import com.alibaba.fastjson.JSONArray;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TS on 2018/2/25.
 */
public class TemplateSQL {

    /**
     * 添加数据sql
     * @param t
     * @param <T>
     * @return
     */
    public static  <T> String save(T t){
        StringBuffer sb = new StringBuffer();
        StringBuffer sbv = new StringBuffer();
        sb.append("insert into ");
        Class clazz = t.getClass();
        sb.append(tableName(clazz));
        sb.append(" (");
        Field[] fields = clazz.getDeclaredFields();
        for(Field f : fields){
            boolean fieldAnn = f.isAnnotationPresent(FieldName.class);
            boolean idAnn = f.isAnnotationPresent(ID.class);
            if(idAnn) {
                ID id = f.getAnnotation(ID.class);
                if (id.increment())
                    continue;
            }
            String name = null;
            if(fieldAnn) {
                FieldName fieldName = f.getAnnotation(FieldName.class);
                String n = fieldName.name();
                name = !"default".equals(n) ? n : f.getName();
            }else {
                continue;
            }
            sb.append(name);
            sb.append(",");
            sbv.append("#{");
            sbv.append(f.getName());
            sbv.append("},");
        }
        String sqlstart = sb.substring(0 , sb.length() - 1);
        sb = new StringBuffer();
        sb.append(sqlstart);
        sb.append(") values (");
        sb.append(sbv.substring(0 , sbv.length() - 1));
        sb.append(")");
        return sb.toString();
    }

    /**
     * 按对象删除
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> String delete(T t) throws Exception {
        StringBuffer sb = new StringBuffer();
        Class clazz = t.getClass();
        Field f = fieldId(clazz);
        String idName = f.isAnnotationPresent(FieldName.class) ?
                f.getAnnotation(FieldName.class).name() : f.getName();
        f.setAccessible(true);
        String idvalue = (String) f.get(t);
        sb.append("delete from ");
        sb.append(tableName(clazz));
        sb.append(" where ");
        sb.append(idName);
        sb.append(" = '");
        sb.append(idvalue);
        sb.append("'");
        return sb.toString();
    }

    /**
     * 按id删除
     * @param id
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String deleteById(Object id , Class<T> c){
        StringBuffer sb = new StringBuffer();
        Field f = fieldId(c);
        String idName = f.isAnnotationPresent(FieldName.class) ?
                f.getAnnotation(FieldName.class).name() : f.getName();
        sb.append("delete from ");
        sb.append(tableName(c));
        sb.append(" where ");
        sb.append(idName);
        sb.append(" = '");
        sb.append(id);
        sb.append("'");
        return sb.toString();
    }

    /**
     * 按条件 删除
     * @param sql
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String deleteBySQLRequire(String sql , Class<T> c){
        StringBuffer sb = new StringBuffer();
        sb.append("delete from ");
        sb.append(tableName(c));
        sb.append(" where ");
        sb.append(sql);
        return sb.toString();
    }

    /**
     * 根据id查询
     * @param id
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String findById(Object id , Class<T> c){
        StringBuffer sb = new StringBuffer();
        Field f = fieldId(c);
        String idName = f.isAnnotationPresent(FieldName.class) ?
                f.getAnnotation(FieldName.class).name() : f.getName();
        sb.append("select * from ");
        sb.append(tableName(c));
        sb.append(" where ");
        sb.append(idName);
        sb.append(" = '");
        sb.append(id);
        sb.append("'");
        return sb.toString();
    }

    /**
     * 根据sql条件查询对象
     * @param sql
     * @param c
     * @param <T>
     * @return
     */
    public static <T> String findBySQLRequire(String sql , Class<T> c){
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ");
        sb.append(tableName(c));
        sb.append(" where ");
        sb.append(sql);
        return sb.toString();
    }

    /**
     * 原生sql
     * @param sql
     * @return
     */
    public static String bySQL(String sql){
        return sql;
    }

    public static <T> String updateById(T t){
        String id , name = null;
        Class c = t.getClass();
        StringBuffer sb = new StringBuffer();
        StringBuffer sbv = new StringBuffer();
        sb.append("update ");
        sb.append(tableName(c));
        sb.append(" set ");
        Field [] fed = c.getDeclaredFields();
        for(Field f : fed){
            boolean boid = f.isAnnotationPresent(ID.class);
            boolean fieldbo= f.isAnnotationPresent(FieldName.class);
            name = f.getAnnotation(FieldName.class).name();
            if(boid){
                if(fieldbo)
                    throw new FinalException("false" , "Id未定义为数据库对相应字段");
                id = "default".equals(f.getAnnotation(FieldName.class)) ?
                        f.getName() : name;
                continue;
            }
            if(fieldbo){
                sbv.append("default".equals(f.getAnnotation(FieldName.class)) ?
                        f.getName() : name);
                sbv.append("=");
            }


        }
        return null;
    }

    /**
     * 返回表名
     * @param c
     * @return
     */
    public static String tableName(Class c){
        boolean tableAnn = c.isAnnotationPresent(TableName.class);
        return tableAnn ?
                ((TableName) c.getAnnotation(TableName.class)).name() :
                c.getSimpleName();
    }


    public static Field fieldId(Class c){
        Field[] f = c.getDeclaredFields();
        int count = 0;
        for(Field s : f){
            boolean b = s.isAnnotationPresent(ID.class);
            if(b)
                return s;
            count++;
        }
        if(count == f.length){
            throw new NullPointerException("实体类" + c.getSimpleName() + "未定义ID注解");
        }
        return null;
    }



    public static void main(String[] args) throws Exception {
        UserBean bean = new UserBean();
        long s = System.currentTimeMillis();
        System.out.println(findById("woshoid" , UserBean.class));
        System.out.println(System.currentTimeMillis() - s);

    }
}
