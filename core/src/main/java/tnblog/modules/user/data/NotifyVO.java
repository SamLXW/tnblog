package tnblog.modules.user.data;

import tnblog.modules.blog.data.PostVO;
import tnblog.modules.user.entity.Notify;

/**
 * @author langhsu on 2015/8/31.
 */
public class NotifyVO extends Notify {
    // extend
    private UserVO from;
    private PostVO post;

    public UserVO getFrom() {
        return from;
    }

    public void setFrom(UserVO from) {
        this.from = from;
    }

    public PostVO getPost() {
        return post;
    }

    public void setPost(PostVO post) {
        this.post = post;
    }
}
