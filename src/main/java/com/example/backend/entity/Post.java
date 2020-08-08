package com.example.backend.entity;

import com.example.backend.entity.data.Locale;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_post")
	private Long idPost;

	@JoinColumn(name = "id_user", referencedColumnName = "id_user")
	@ManyToOne
	private User idUser;

	@JoinColumn(name = "id_category", referencedColumnName = "id_category")
	@ManyToOne
	private Category idCategory;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_post")
	@ToString.Exclude
	private List<PostTranslation> postTranslations;

	@Column(name = "post_date_posted")
	private LocalDate postDatePosted;
	@Column(name = "post_deleted")
	private Boolean postDeleted;
	@Column(name = "post_published")
	private Boolean postPublished;
	@Column(name = "post_views")
	private Long postViews;

	private PostTranslation getTranslation(String locale) {
		return getPostTranslations()
				.stream()
				.filter(translation -> translation
						.getLanguage()
						.getLanguageName()
						.equals(locale))
				.findFirst().orElse(null);
	}

	public boolean isLocalizedFor(String locale) {
		return getTranslation(locale) != null;
	}

	public String getPostTitle(String locale) {
		PostTranslation postTranslation = getTranslation(locale);
		if (postTranslation != null) {
			return postTranslation.getPostTitleTranslation();
		}
		return "";
	}

	public String getPostExcerpt(String locale) {
		PostTranslation postTranslation = getTranslation(locale);
		if (postTranslation != null) {
			return postTranslation.getPostExcerptTranslation();
		}
		return "";
	}


	public String getPostBody(String locale) {
		PostTranslation postTranslation = getTranslation(locale);
		if (postTranslation != null) {
			return postTranslation.getPostBodyTranslation();
		}
		return "";
	}


	public String getPostSlug(String locale) {
		PostTranslation postTranslation = getTranslation(locale);
		if (postTranslation != null) {
			return postTranslation.getPostSlugTranslation();
		}
		return "";
	}

}