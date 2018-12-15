/*
+--------------------------------------------------------------------------
|   tnblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package tnblog.web.controller.site.user;

import tnblog.modules.blog.data.CommentVO;
import tnblog.modules.blog.data.FavorVO;
import tnblog.modules.blog.data.FeedsVO;
import tnblog.modules.blog.data.PostVO;
import tnblog.modules.blog.service.CommentService;
import tnblog.modules.blog.service.FavorService;
import tnblog.modules.blog.service.FeedsService;
import tnblog.modules.blog.service.PostService;
import tnblog.modules.user.data.AccountProfile;
import tnblog.modules.user.data.BadgesCount;
import tnblog.modules.user.data.NotifyVO;
import tnblog.modules.user.data.UserVO;
import tnblog.modules.user.service.FollowService;
import tnblog.modules.user.service.NotifyService;
import tnblog.modules.user.service.UserService;
import tnblog.shiro.authc.AccountSubject;
import tnblog.web.controller.BaseController;
import tnblog.web.controller.site.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户主页
 * @author langhsu
 *
 */
@Controller
public class UserController extends BaseController {
	@Autowired
	private PostService postService;
	@Autowired
	private FeedsService feedsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private FollowService followService;
	@Autowired
	private FavorService favorService;
	@Autowired
	private NotifyService notifyService;

	/**
	 * 用户主页
	 * @param model
	 * @return
	 */
	@GetMapping("/user")
	public String home(ModelMap model) {
		Pageable pageable = wrapPageable();
		AccountSubject subject = getSubject();

		Page<FeedsVO> page = feedsService.findUserFeeds(pageable, subject.getProfile().getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_FEEDS);
	}

	/**
	 * 我发布的文章
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user", params = "method=posts")
	public String posts(ModelMap model) {
		Pageable pageable = wrapPageable();
		AccountProfile up = getSubject().getProfile();
		Page<PostVO> page = postService.pagingByAuthorId(pageable, up.getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_POSTS);
	}

	/**
	 * 我发表的评论
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user", params = "method=comments")
	public String comments(ModelMap model) {
		Pageable pageable = wrapPageable();
		AccountSubject subject = getSubject();
		Page<CommentVO> page = commentService.paging4Home(pageable, subject.getProfile().getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_COMMENTS);
	}

	/**
	 * 我喜欢过的文章
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user", params = "method=favors")
	public String favors(ModelMap model) {
		Pageable pageable = wrapPageable();
		AccountProfile profile = getSubject().getProfile();
		Page<FavorVO> page = favorService.pagingByOwnId(pageable, profile.getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_FAVORS);
	}

	/**
	 * 我的关注
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user", params = "method=follows")
	public String follows(ModelMap model) {
		Pageable pageable = wrapPageable();
		AccountProfile profile = getSubject().getProfile();
		Page<UserVO> page = followService.follows(pageable, profile.getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_FOLLOWS);
	}

	/**
	 * 我的粉丝
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user", params = "method=fans")
	public String fans(ModelMap model) {
		Pageable pageable = wrapPageable();
		AccountProfile profile = getSubject().getProfile();
		Page<UserVO> page = followService.fans(pageable, profile.getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_FANS);
	}

	/**
	 * 我的通知
	 * @param model
	 * @return
	 */
	@GetMapping(value="/user", params = "method=notifies")
	public String notifies(ModelMap model) {
		Pageable pageable = wrapPageable();
		AccountProfile profile = getSubject().getProfile();
		Page<NotifyVO> page = notifyService.findByOwnId(pageable, profile.getId());
		// 标记已读
		notifyService.readed4Me(profile.getId());

		model.put("page", page);
		initUser(model);

		return view(Views.USER_NOTIFIES);
	}

	private void initUser(ModelMap model) {
		AccountProfile up = getSubject().getProfile();
		UserVO user = userService.get(up.getId());

		model.put("user", user);

		pushBadgesCount();
	}

	private void pushBadgesCount() {
		AccountProfile profile = (AccountProfile) session.getAttribute("profile");
		if (profile != null && profile.getBadgesCount() != null) {
			BadgesCount count = new BadgesCount();
			count.setNotifies(notifyService.unread4Me(profile.getId()));
			profile.setBadgesCount(count);
			session.setAttribute("profile", profile);
		}
	}

}
