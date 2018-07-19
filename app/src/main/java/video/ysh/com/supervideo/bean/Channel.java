package video.ysh.com.supervideo.bean;

import java.io.Serializable;

/**
 * - @Author:  闫世豪
 * - @Time:  2018/7/18 下午3:53
 * - @Email whynightcode@gmail.com
 */
public class Channel implements Serializable {
    private int Id;
    private int name;
    private int image;

    public Channel(int id, int name, int image) {
        Id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
