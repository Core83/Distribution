package com.core.model;

public class WxMerPic {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_mer_pic.id
     *
     * @mbggenerated Sun Nov 08 14:27:49 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_mer_pic.gds_id
     *
     * @mbggenerated Sun Nov 08 14:27:49 CST 2015
     */
    private Integer gdsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_mer_pic.pic_url
     *
     * @mbggenerated Sun Nov 08 14:27:49 CST 2015
     */
    private String picUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_mer_pic
     *
     * @mbggenerated Sun Nov 08 14:27:49 CST 2015
     */
    public WxMerPic(Integer id, Integer gdsId, String picUrl) {
        this.id = id;
        this.gdsId = gdsId;
        this.picUrl = picUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_mer_pic.id
     *
     * @return the value of wx_mer_pic.id
     *
     * @mbggenerated Sun Nov 08 14:27:49 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_mer_pic.gds_id
     *
     * @return the value of wx_mer_pic.gds_id
     *
     * @mbggenerated Sun Nov 08 14:27:49 CST 2015
     */
    public Integer getGdsId() {
        return gdsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_mer_pic.pic_url
     *
     * @return the value of wx_mer_pic.pic_url
     *
     * @mbggenerated Sun Nov 08 14:27:49 CST 2015
     */
    public String getPicUrl() {
        return picUrl;
    }
}