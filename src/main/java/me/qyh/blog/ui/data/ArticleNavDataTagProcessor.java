package me.qyh.blog.ui.data;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import me.qyh.blog.bean.ArticleNav;
import me.qyh.blog.entity.Article;
import me.qyh.blog.entity.Space;
import me.qyh.blog.exception.LogicException;
import me.qyh.blog.service.ArticleService;
import me.qyh.blog.ui.Params;

public class ArticleNavDataTagProcessor extends DataTagProcessor<ArticleNav> {

	@Autowired
	private ArticleService articleService;

	public ArticleNavDataTagProcessor(String name, String dataName) {
		super(name, dataName);
	}

	@Override
	protected ArticleNav buildPreviewData(Map<String, String> attributes) {
		Article previous = new Article(-1);
		previous.setTitle("预览博客-前一篇");
		Article next = new Article(-2);
		next.setTitle("预览博客-后一篇");
		Space space = new Space();
		space.setAlias("test");
		space.setName("预览");
		previous.setSpace(space);
		next.setSpace(space);

		return new ArticleNav(previous, next);
	}

	@Override
	protected ArticleNav query(Space space, Params params, Map<String, String> attributes) throws LogicException {
		Article article = params.get("article", Article.class);
		if (article == null) {
			String idStr = attributes.get("article");
			if (idStr != null) {
				try {
					Integer id = Integer.parseInt(idStr);
					article = articleService.getArticleForView(id);
				} catch (Exception e) {
				}
			}
		}
		if (article != null && space.getAlias().equals(article.getSpace().getAlias()))
			return articleService.getArticleNav(article);
		return null;
	}

}
