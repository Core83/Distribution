package com.core.dao;

import com.core.model.TWxApp;
import com.core.model.TWxAppExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface TWxAppMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    int countByExample(TWxAppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    int deleteByExample(TWxAppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    @Delete({
        "delete from t_wx_app",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    @Insert({
        "insert into t_wx_app (ID, WXID, ",
        "WXNAME, APPID, APPSECRET, ",
        "TOKEN, ENCODINGAESKEY, ",
        "ENCRYPTMESSAGE)",
        "values (#{id,jdbcType=VARCHAR}, #{wxid,jdbcType=VARCHAR}, ",
        "#{wxname,jdbcType=VARCHAR}, #{appid,jdbcType=VARCHAR}, #{appsecret,jdbcType=VARCHAR}, ",
        "#{token,jdbcType=VARCHAR}, #{encodingaeskey,jdbcType=VARCHAR}, ",
        "#{encryptmessage,jdbcType=VARCHAR})"
    })
    int insert(TWxApp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    int insertSelective(TWxApp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    List<TWxApp> selectByExample(TWxAppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "ID, WXID, WXNAME, APPID, APPSECRET, TOKEN, ENCODINGAESKEY, ENCRYPTMESSAGE",
        "from t_wx_app",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    TWxApp selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TWxApp record, @Param("example") TWxAppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TWxApp record, @Param("example") TWxAppExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TWxApp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_wx_app
     *
     * @mbggenerated
     */
    @Update({
        "update t_wx_app",
        "set WXID = #{wxid,jdbcType=VARCHAR},",
          "WXNAME = #{wxname,jdbcType=VARCHAR},",
          "APPID = #{appid,jdbcType=VARCHAR},",
          "APPSECRET = #{appsecret,jdbcType=VARCHAR},",
          "TOKEN = #{token,jdbcType=VARCHAR},",
          "ENCODINGAESKEY = #{encodingaeskey,jdbcType=VARCHAR},",
          "ENCRYPTMESSAGE = #{encryptmessage,jdbcType=VARCHAR}",
        "where ID = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TWxApp record);
}