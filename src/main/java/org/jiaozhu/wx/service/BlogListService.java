package org.jiaozhu.wx.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.jiaozhu.wx.resp.Blog;

public class BlogListService {

    public static void main(String[] args) {
        parseRss();
    }

    public static List<Blog> parseRss() {

        List<Blog> list = new ArrayList<Blog>();

        String rss = "http://jiaozhu.org/rss";

        try {
            URL url = new URL(rss);
            // 读取Rss源
            XmlReader reader = new XmlReader(url);


            System.out.println("Rss源的编码格式为：" + reader.getEncoding());
            SyndFeedInput input = new SyndFeedInput();
            // 得到SyndFeed对象，即得到Rss源里的所有信息
            SyndFeed feed = input.build(reader);

            // 得到Rss新闻中子项列表
            List entries = feed.getEntries();
            // 循环得到每个子项信息
            for (int i = 0; i < 4; i++) {
                SyndEntry entry = (SyndEntry) entries.get(i);

                // 标题、连接地址、标题简介、时间是一个Rss源项最基本的组成部分
                Blog blog = new Blog();
                blog.setTitle(entry.getTitle());
                blog.setDesc(entry.getDescription().getValue());
                blog.setUrl(entry.getLink());
                blog.setPublishDate(entry.getPublishedDate());

                if(i==0){
                    blog.setPic("http://jiaozhu.org/weixin.png");
                }else{
                    blog.setPic("");
                }

                list.add(blog);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}