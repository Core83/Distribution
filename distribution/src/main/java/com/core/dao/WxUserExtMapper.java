package com.core.dao;

import com.core.model.WxUserExt;
import com.core.model.WxUserExtExample;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WxUserExtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_user_ext
     *
     * @mbggenerated Mon Nov 16 15:11:41 CST 2015
     */
    int countByExample(WxUserExtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_user_ext
     *
     * @mbggenerated Mon Nov 16 15:11:41 CST 2015
     */
    int deleteByExample(WxUserExtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_user_ext
     *
     * @mbggenerated Mon Nov 16 15:11:41 CST 2015
     */
    @Insert({
        "insert into wx_user_ext (USER_ID, EXT_KEY, ",
        "EXT_VALUE)",
        "values (#{userId,jdbcType=INTEGER}, #{extKey,jdbcType=VARCHAR}, ",
        "#{extValue,jdbcType=VARCHAR})"
    })
    int insert(WxUserExt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_user_ext
     *
     * @mbggenerated Mon Nov 16 15:11:41 CST 2015
     */
    int insertSelective(WxUserExt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_user_ext
     *
     * @mbggenerated Mon Nov 16 15:11:41 CST 2015
     */
    List<WxUserExt> selectByExample(WxUserExtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_user_ext
     *
     * @mbggenerated Mon Nov 16 15:11:41 CST 2015
     */
    int updateByExampleSelective(@Param("record") WxUserExt record, @Param("example") WxUserExtExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_user_ext
     *
     * @mbggenerated Mon Nov 16 15:11:41 CST 2015
     */
    int updateByExample(@Param("record") WxUserExt record, @Param("example") WxUserExtExample example);
}