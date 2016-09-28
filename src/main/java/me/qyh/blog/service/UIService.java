package me.qyh.blog.service;

import java.util.List;

import me.qyh.blog.bean.ExportReq;
import me.qyh.blog.bean.ImportPageWrapper;
import me.qyh.blog.bean.ImportReq;
import me.qyh.blog.bean.ImportResult;
import me.qyh.blog.entity.Space;
import me.qyh.blog.exception.LogicException;
import me.qyh.blog.pageparam.PageResult;
import me.qyh.blog.pageparam.UserFragementQueryParam;
import me.qyh.blog.pageparam.UserPageQueryParam;
import me.qyh.blog.ui.ExportPage;
import me.qyh.blog.ui.Params;
import me.qyh.blog.ui.RenderedPage;
import me.qyh.blog.ui.fragement.UserFragement;
import me.qyh.blog.ui.page.ErrorPage;
import me.qyh.blog.ui.page.ErrorPage.ErrorCode;
import me.qyh.blog.ui.page.ExpandedPage;
import me.qyh.blog.ui.page.Page;
import me.qyh.blog.ui.page.SysPage;
import me.qyh.blog.ui.page.SysPage.PageTarget;
import me.qyh.blog.ui.page.UserPage;

public interface UIService {

	/**
	 * 插入用户自定义挂件
	 * 
	 * @param userFragement
	 * @throws LogicException
	 */
	void insertUserFragement(UserFragement userFragement) throws LogicException;

	/**
	 * 删除用户自定义挂件
	 * 
	 * @param id
	 * @throws LogicException
	 */
	void deleteUserFragement(Integer id) throws LogicException;

	/**
	 * 分页查询用户自定义挂件
	 * 
	 * @param param
	 * @return
	 */
	PageResult<UserFragement> queryUserFragement(UserFragementQueryParam param);

	/**
	 * 更新自定义挂件
	 * 
	 * @param userFragement
	 */
	void updateUserFragement(UserFragement userFragement) throws LogicException;

	/**
	 * 根据ID查询用户挂件
	 * 
	 * @param id
	 *            挂件ID
	 * @return null如果不存在
	 */
	UserFragement queryUserFragement(Integer id);

	/**
	 * 根据ID查询用户页面
	 * 
	 * @param id
	 * @return
	 */
	UserPage queryUserPage(Integer id);

	/**
	 * 根据alias查询用户页面
	 * 
	 * @param id
	 * @return
	 */
	UserPage queryUserPage(String alias);

	/**
	 * 查询系统页面模板
	 * 
	 * @param space
	 * @param target
	 * @return
	 */
	SysPage querySysPage(Space space, PageTarget target);

	/**
	 * 分页查询用户自定义页面
	 * 
	 * @param param
	 * @return
	 */
	PageResult<UserPage> queryUserPage(UserPageQueryParam param);

	/**
	 * 删除用户自定义页面
	 * 
	 * @param id
	 * @throws LogicException
	 */
	void deleteUserPage(Integer id) throws LogicException;

	/**
	 * 渲染预览页面
	 * 
	 * @param space
	 * @param target
	 * @return
	 * @throws LogicException
	 */
	RenderedPage renderPreviewPage(Space space, PageTarget target) throws LogicException;

	/**
	 * 保存页面模板
	 * 
	 * @param sysPage
	 * @throws LogicException
	 */
	void buildTpl(SysPage sysPage) throws LogicException;

	/**
	 * 保存页面模板
	 * 
	 * @param userPage
	 * @throws LogicException
	 */
	void buildTpl(UserPage userPage) throws LogicException;

	/**
	 * 渲染页面
	 * 
	 * @param space
	 * @param pageTarget
	 * @param params
	 * @return
	 * @throws LogicException
	 */
	RenderedPage renderSysPage(Space space, PageTarget pageTarget, Params params) throws LogicException;

	/**
	 * 渲染用户自定义页面
	 * 
	 * @param alias
	 *            别名
	 * @return 不会为null
	 * @throws LogicException
	 *             如果页面不存在，数据渲染异常等
	 */
	RenderedPage renderUserPage(String alias) throws LogicException;

	/**
	 * 删除系统挂件模板
	 * 
	 * @param space
	 * @param target
	 * @throws LogicException
	 */
	void deleteSysPage(Space space, PageTarget target) throws LogicException;

	/**
	 * 渲染拓展页面
	 * 
	 * @param expandedPage
	 * @param params
	 * @return
	 * @throws LogicException
	 */
	RenderedPage renderExpandedPage(Integer id, Params params) throws LogicException;

	/**
	 * 查询所有的拓展页面
	 * 
	 * @return
	 */
	List<ExpandedPage> queryExpandedPage();

	/**
	 * 还原用户拓展页面
	 * 
	 * @param id
	 * @throws LogicException
	 */
	void deleteExpandedPage(Integer id) throws LogicException;

	/**
	 * 查询用户拓展页面
	 * 
	 * @param id
	 * @return
	 * @throws LogicException
	 */
	ExpandedPage queryExpandedPage(Integer id) throws LogicException;

	/**
	 * 保存/更新拓展页面模板
	 * 
	 * @param page
	 * @throws LogicException
	 */
	void buildTpl(ExpandedPage page) throws LogicException;

	/**
	 * 保存更新错误页面模板
	 * 
	 * @param errorPage
	 * @throws LogicException
	 */
	void buildTpl(ErrorPage errorPage) throws LogicException;

	/**
	 * 删除错误挂件模板
	 * 
	 * @param space
	 * @param target
	 * @throws LogicException
	 */
	void deleteErrorPage(Space space, ErrorCode erroCode) throws LogicException;

	/**
	 * 查询错误页面
	 * 
	 * @param space
	 * @param code
	 * @return
	 */
	ErrorPage queryErrorPage(Space space, ErrorCode code);

	/**
	 * 渲染错误页面
	 * 
	 * @param space
	 * @param code
	 * @return
	 * @throws LogicException
	 */
	RenderedPage renderErrorPage(Space space, ErrorCode code) throws LogicException;

	/**
	 * 导出某个空间下的所有页面模板
	 * <p>
	 * <strong>无法导出拓展页面的模板！</strong>
	 * </p>
	 * 
	 * @param space
	 * @return
	 * @throws LogicException
	 */
	List<ExportPage> export(ExportReq req) throws LogicException;

	/**
	 * 导入空间下的模板
	 * 
	 * @param page
	 * @param req
	 * @return 该空间下以前所有的模板
	 * @throws LogicException
	 */
	ImportResult importTemplate(List<ImportPageWrapper> pages, ImportReq req) throws LogicException;

	/**
	 * 预览页面
	 * 
	 * @param page
	 * @return
	 * @throws LogicException
	 */
	RenderedPage renderPreviewPage(Page page) throws LogicException;

	/**
	 * 通过DATA_TAG标签查询数据
	 * 
	 * @param dataTagStr
	 * @return
	 * @throws LogicException 
	 */
	Object queryData(String dataTagStr) throws LogicException;

}
