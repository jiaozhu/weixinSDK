package org.jiaozhu.wx.resp;

import java.util.Date;

/*
 * @author: jiaozhu(0x7C4)
 * @email: gitview@gmail.com
 * @date: 2014/03/03
 * @description: 
 */
public class Blog {
    private String title;
    private String url;
    private String desc;
    private Date publishDate;

    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
