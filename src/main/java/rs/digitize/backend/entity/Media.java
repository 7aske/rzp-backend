package rs.digitize.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Uploaded image shown on the blog post
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "media")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Media extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "media_id")
	private Integer id;
	@Column(name = "uri")
	private String uri;
	@Column(name = "height")
	private Integer height;
	@Column(name = "width")
	private Integer width;
	@Column(name = "size")
	private Long size;
}