/**
 * 
 */
package tnblog.web.controller.api;

import tnblog.base.lang.Consts;
import tnblog.core.data.Post;
import tnblog.core.persist.service.PostService;
import tnblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/api")
public class PostJsonController extends BaseController {
	@Autowired
	private PostService postService;
	
	@RequestMapping("/posts")
	public @ResponseBody
	Page<Post> posts(HttpServletRequest request) {
		String order = ServletRequestUtils.getStringParameter(request, "ord", Consts.order.NEWEST);
		int gid = ServletRequestUtils.getIntParameter(request, "gid", 0);
		Pageable pageable = wrapPageable();
		Page<Post> page = postService.paging(pageable, gid, order);
		
		return page;
	}
}
