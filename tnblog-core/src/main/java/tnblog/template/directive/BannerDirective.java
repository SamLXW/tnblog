/**
 *
 */
package tnblog.template.directive;

import tnblog.core.data.Post;
import tnblog.core.persist.service.PostService;
import tnblog.template.DirectiveHandler;
import tnblog.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 推荐内容查询
 *
 * @author langhsu
 *
 */
@Component
public class BannerDirective extends TemplateDirective {
	@Autowired
    private PostService postService;

    @Override
    public String getName() {
        return "banner";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        List<Post> result = postService.findAllFeatured();
        handler.put(RESULTS, result).render();
    }
}
