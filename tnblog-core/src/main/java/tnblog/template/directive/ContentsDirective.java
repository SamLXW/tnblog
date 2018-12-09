/**
 *
 */
package tnblog.template.directive;

import tnblog.base.lang.Consts;
import tnblog.core.data.Post;
import tnblog.core.persist.service.PostService;
import tnblog.template.DirectiveHandler;
import tnblog.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * 文章内容查询
 *
 * 示例：
 * 	请求：http://mtons.com/index?ord=newest&pn=2
 *  使用：@contents group=x pn=pn ord=ord
 *
 * @author langhsu
 *
 */
@Component
public class ContentsDirective extends TemplateDirective {
	@Autowired
    private PostService postService;

    @Override
    public String getName() {
        return "contents";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        Integer pn = handler.getInteger("pn", 1);
        Integer channelId = handler.getInteger("channelId", 0);
        String order = handler.getString("order", Consts.order.NEWEST);

        Pageable pageable = new PageRequest(pn - 1, 15);
        Page<Post> result = postService.paging(pageable, channelId, order);

        handler.put(RESULTS, result).render();
    }
}
