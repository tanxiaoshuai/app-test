package cn.wodesh.dao;

import cn.wodesh.dao.sql.TemplateSQL;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * Created by TS on 2018/2/25.
 */
public interface TemplateDao<T> {

    @InsertProvider(type = TemplateSQL.class , method = "save")
    void save(T t) throws Exception;

    @DeleteProvider(type = TemplateSQL.class , method = "delete")
    void delete(T t) throws Exception;

    @DeleteProvider(type = TemplateSQL.class , method = "deleteById")
    void deleteById(Object id , Class<T> c) throws Exception;

    @DeleteProvider(type = TemplateSQL.class , method = "deleteBySQLRequire")
    void deleteBySQLRequire(String sql , Class<T> c) throws Exception;

    @DeleteProvider(type = TemplateSQL.class , method = "bySQL")
    void deleteBySQL(String sql) throws Exception;

    @SelectProvider(type = TemplateSQL.class , method = "findById")
    T findById(Object id , Class<T> c) throws Exception;

    @SelectProvider(type = TemplateSQL.class , method = "findBySQLRequire")
    T findBySQLRequireToBean(String sql , Class<T> c) throws Exception;

    @SelectProvider(type = TemplateSQL.class , method = "findBySQLRequire")
    List<T> findBySQLRequireToList(String sql , Class<T> c) throws Exception;

    @SelectProvider(type = TemplateSQL.class , method = "bySQL")
    T findBySQLToBean(String sql) throws Exception;

    @SelectProvider(type = TemplateSQL.class , method = "bySQL")
    List<T> findBySQLToList(String sql) throws Exception;

    @UpdateProvider(type = TemplateSQL.class , method = "updateById")
    void updateById(T t) throws Exception;

}
