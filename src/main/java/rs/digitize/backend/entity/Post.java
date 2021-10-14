package rs.digitize.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rs.digitize.backend.util.PostUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Blog post
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "post")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Post extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "post_id")
	private Integer id;
	@JoinColumn(name = "user_fk", referencedColumnName = "user_id")
	@ManyToOne
	private User user;
	@JoinColumn(name = "category_fk", referencedColumnName = "category_id")
	@ManyToOne
	private Category category;
	@Column(name = "title")
	private String title;
	@Column(name = "excerpt")
	private String excerpt;
	@Column(name = "body")
	private String body;
	@Column(name = "published")
	private Boolean published;
	@Column(name = "views")
	private Integer views;
	@Column(name = "slug")
	private String slug;
	@Column(name = "date_posted")
	private LocalDate datePosted;
	@Column(name = "image")
	private String image;
	@ManyToMany
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_fk"), inverseJoinColumns = @JoinColumn(name = "tag_fk"))
	private List<Tag> tags;

	@PreUpdate
	@PrePersist
	private void updatePicture() {
		if (body == null) return;
		if (image == null || image.isEmpty())
			image = PostUtil.getFirstImageUrl(body);
	}
}