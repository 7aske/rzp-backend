package rs.digitize.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

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
	@Column(name = "deleted")
	private boolean deleted;
	@Column(name = "published")
	private boolean published;
	@Column(name = "views")
	private Integer views;
	@Column(name = "slug")
	private String slug;
	@Column(name = "date_posted")
	private LocalDate datePosted;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_fk"), inverseJoinColumns = @JoinColumn(name = "tag_fk"))
	private List<Tag> tags;
	
}