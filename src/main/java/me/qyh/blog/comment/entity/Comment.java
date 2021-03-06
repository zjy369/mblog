/*
 * Copyright 2016 qyh.me
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.qyh.blog.comment.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.google.gson.annotations.Expose;

import me.qyh.blog.core.entity.BaseEntity;
import me.qyh.blog.core.entity.Editor;
import me.qyh.blog.core.message.Message;
import me.qyh.blog.core.util.Validators;

/**
 * 评论
 * 
 * @author Administrator
 *
 */
public class Comment extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Comment parent;// 如果为null,则为评论，否则为回复
	private String parentPath;// 路径，最多支持255个字符(索引原因)
	private String content;
	private List<Integer> parents = new ArrayList<>();
	private Timestamp commentDate;
	private List<Comment> children = new ArrayList<>();
	private CommentStatus status;

	private String website;
	private String nickname;
	@Expose(serialize = false)
	private String email;
	@Expose(serialize = false)
	private String ip;
	private Boolean admin;// 是否是管理员

	private String gravatar;
	private String url;
	private Editor editor;// 编辑器

	private CommentModule commentModule;

	/**
	 * ip是否被禁止评论
	 * 
	 * @since 6.0
	 */
	private boolean ban;

	/**
	 * 评论状态
	 * 
	 * @author Administrator
	 *
	 */
	public enum CommentStatus {
		NORMAL(new Message("comment.status.normal", "正常")), CHECK(new Message("comment.status.check", "审核"));

		private final Message message;

		CommentStatus(Message message) {
			this.message = message;
		}

		public Message getMessage() {
			return message;
		}
	}

	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		if (!"/".equals(parentPath)) {
			Arrays.stream(parentPath.split("/")).filter(path -> !path.isEmpty())
					.forEach(path -> this.parents.add(Integer.parseInt(path)));
		}
		this.parentPath = parentPath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isRoot() {
		return parent == null;
	}

	public List<Integer> getParents() {
		return parents;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	public List<Comment> getChildren() {
		return children;
	}

	public void setChildren(List<Comment> children) {
		this.children = children;
	}

	public CommentStatus getStatus() {
		return status;
	}

	public void setStatus(CommentStatus status) {
		this.status = status;
	}

	public boolean isChecking() {
		return CommentStatus.CHECK.equals(status);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getGravatar() {
		return gravatar;
	}

	public void setGravatar(String gravatar) {
		this.gravatar = gravatar;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (Validators.baseEquals(this, obj)) {
			Comment rhs = (Comment) obj;
			return Objects.equals(this.id, rhs.id);
		}
		return false;
	}

	public boolean matchParent(Comment parent) {
		return this.parent != null && this.parent.equals(parent) && this.commentModule.equals(parent.commentModule);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public CommentModule getCommentModule() {
		return commentModule;
	}

	public void setCommentModule(CommentModule commentModule) {
		this.commentModule = commentModule;
	}

	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
	}
}
