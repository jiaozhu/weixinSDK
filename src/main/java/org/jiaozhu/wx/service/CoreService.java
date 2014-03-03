package org.jiaozhu.wx.service;

import org.jiaozhu.wx.resp.Article;
import org.jiaozhu.wx.resp.Blog;
import org.jiaozhu.wx.resp.NewsMessage;
import org.jiaozhu.wx.resp.TextMessage;
import org.jiaozhu.wx.util.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/*
 * @author: jiaozhu(0x7C4)
 * @email: gitview@gmail.com
 * @date: 2014/03/02
 * @description: 
 */
public class CoreService {
    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            String content = requestMap.get("Content");

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

                if(content.equals("help") || content.equals("帮助")){
                    respContent = getMainMenu();
                }else if (content.equals("1")){

                    NewsMessage newsMessage = new NewsMessage();

                    newsMessage.setToUserName(fromUserName);
                    newsMessage.setFromUserName(toUserName);
                    newsMessage.setCreateTime(new Date().getTime());
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    newsMessage.setFuncFlag(0);


                    //respContent ="请跳到官方网站查看详情，http://www.m4fw.com .";

                    List<Article> articleList = new ArrayList<Article>();
                    Article article1 = new Article();
                    article1.setTitle("M4FW软件介绍");
                    article1.setDescription("M4FW是一款专门针对多集群环境下的WebLogic监控软件,如果您是处于一线的运维人员，M4FW将...");
                    article1.setPicUrl("http://www.m4fw.com/static/img/m4fw_page_main_s.png");
                    article1.setUrl("http://www.m4fw.com");
                    articleList.add(article1);


                    Article article2 = new Article();
                    article2.setTitle("M4FW网站M4FW.COM上线");
                    article2.setDescription("最近几天一直在改自己的监控软件，在今天把两个模块修正的差不多之后终于搞了个域名...");
                    article2.setPicUrl("http://www.m4fw.com/static/img/m4fm_icon.png");
                    article2.setUrl("http://jiaozhu.org/archives/80.html");
                    articleList.add(article2);

                    Article article3 = new Article();
                    article3.setTitle("M4FW开发版使用视频教程");
                    article3.setDescription("WebLogic AMP 3.0.1.12 -- WebLogic Server 监控软件使用视频...");
                    article3.setPicUrl("http://static.youku.com/index/img/header/yklogo.png");
                    article3.setUrl("http://v.youku.com/v_show/id_XNjYwNTc1MjMy.html");
                    articleList.add(article3);


                    // 设置图文消息个数
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合
                    newsMessage.setArticles(articleList);
                    // 将图文消息对象转换成xml字符串
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);

                    return respMessage;

                }else if(content.equals("2")){

                    NewsMessage newsMessage = new NewsMessage();

                    newsMessage.setToUserName(fromUserName);
                    newsMessage.setFromUserName(toUserName);
                    newsMessage.setCreateTime(new Date().getTime());
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    newsMessage.setFuncFlag(0);

                    List<Article> articleList = new ArrayList<Article>();

                    List<Blog> list = BlogListService.parseRss();
                    for(Iterator iter=list.iterator();iter.hasNext();){
                        Blog blog = (Blog)iter.next();

                        Article article = new Article();
                        article.setTitle(blog.getTitle());
                        article.setDescription(blog.getDesc());
                        article.setPicUrl(blog.getPic());
                        article.setUrl(blog.getUrl());
                        articleList.add(article);

                    }

                    // 设置图文消息个数
                    newsMessage.setArticleCount(articleList.size());
                    // 设置图文消息包含的图文集合
                    newsMessage.setArticles(articleList);
                    // 将图文消息对象转换成xml字符串
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);

                    return respMessage;

                }else if(content.equals("6")){
                    respContent = TodayInHistoryService.getTodayInHistoryInfo();
                }else{
                    respContent = "您发送的是文本消息！";
                }

            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = getMainMenu();

                }
                // 取消订阅
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 自定义菜单权没有开放，暂不处理该类消息
                }
            }

            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }



    public static String getMainMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("您好，我是赵伟杰 ，请回复数字选择服务：").append("\n\n");
        buffer.append("1  M4FW介绍").append("\n");
        buffer.append("2  博客列表").append("\n");
        buffer.append("3  公交查询").append("\n");
        buffer.append("4  周边搜索").append("\n");
        buffer.append("5  天气预报").append("\n");
        buffer.append("6  历史今日").append("\n");
        buffer.append("7  聊天唠嗑").append("\n\n");
        buffer.append("回复“help”显示此帮助菜单");
        return buffer.toString();
    }
}
