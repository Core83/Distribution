package com.core.dao;

import com.core.model.WxPayInfo;
import com.core.model.WxPayInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface WxPayInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    int countByExample(WxPayInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    int deleteByExample(WxPayInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    @Delete({
        "delete from wx_pay_info",
        "where pay_no = #{payNo,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer payNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    @Insert({
        "insert into wx_pay_info (pay_no, pay_amount, ",
        "order_id, pay_time)",
        "values (#{payNo,jdbcType=INTEGER}, #{payAmount,jdbcType=INTEGER}, ",
        "#{orderId,jdbcType=INTEGER}, #{payTime,jdbcType=TIMESTAMP})"
    })
    int insert(WxPayInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    int insertSelective(WxPayInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    List<WxPayInfo> selectByExample(WxPayInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    @Select({
        "select",
        "pay_no, pay_amount, order_id, pay_time",
        "from wx_pay_info",
        "where pay_no = #{payNo,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    WxPayInfo selectByPrimaryKey(Integer payNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    int updateByExampleSelective(@Param("record") WxPayInfo record, @Param("example") WxPayInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    int updateByExample(@Param("record") WxPayInfo record, @Param("example") WxPayInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    int updateByPrimaryKeySelective(WxPayInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_pay_info
     *
     * @mbggenerated Tue Nov 03 21:32:31 CST 2015
     */
    @Update({
        "update wx_pay_info",
        "set pay_amount = #{payAmount,jdbcType=INTEGER},",
          "order_id = #{orderId,jdbcType=INTEGER},",
          "pay_time = #{payTime,jdbcType=TIMESTAMP}",
        "where pay_no = #{payNo,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(WxPayInfo record);
}