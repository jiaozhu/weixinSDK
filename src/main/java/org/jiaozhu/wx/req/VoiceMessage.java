package org.jiaozhu.wx.req;

/*
 * @author: jiaozhu(0x7C4)
 * @email: gitview@gmail.com
 * @date: 2014/03/02
 * @description: 
 */
public class VoiceMessage extends BaseMessage  {
    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
