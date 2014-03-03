package org.jiaozhu.wx.req;

/*
 * @author: jiaozhu(0x7C4)
 * @email: gitview@gmail.com
 * @date: 2014/03/02
 * @description: 
 */
public class ImageMessage  extends BaseMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
