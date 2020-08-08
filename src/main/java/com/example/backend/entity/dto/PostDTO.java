package com.example.backend.entity.dto;

import com.example.backend.entity.Category;
import com.example.backend.entity.Post;
import com.example.backend.entity.data.Locale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostDTO {
	private Long idPost;
	private Long idUser;
	private Category idCategory;
	private LocalDate postDatePosted;
	private Boolean postDeleted;
	private Boolean postPublished;
	private Long postViews;

	private String locale;
	private String postTitle;
	private String postExcerpt;
	private String postBody;
	private String postSlug;

	public PostDTO(Post post) {
		this(post, Locale.DEFAULT_LOCALE);
	}

	public PostDTO(Post post, String locale){
		if (!post.isLocalizedFor(locale)){
			locale = Locale.DEFAULT_LOCALE;
		}

		this.idPost = post.getIdPost();
		this.idUser = post.getIdUser().getIdUser();
		this.idCategory = post.getIdCategory();
		this.locale = locale;
		this.postDatePosted = post.getPostDatePosted();
		this.postDeleted = post.getPostDeleted();
		this.postPublished = post.getPostPublished();
		this.postViews = post.getPostViews();

		this.postTitle = post.getPostTitle(locale);
		this.postExcerpt = post.getPostExcerpt(locale);
		this.postBody = post.getPostBody(locale);
		this.postSlug = post.getPostSlug(locale);
	}
}
