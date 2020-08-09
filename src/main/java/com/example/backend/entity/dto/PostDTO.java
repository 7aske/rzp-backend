package com.example.backend.entity.dto;

import com.example.backend.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	private Long idPost;
	private Long idUser;
	private Long idCategory;
	private Boolean postPublished;
	private LocalDate postDatePosted;
	private Long postViews;
	private String postTitle;
	private String postExcerpt;
	private String postBody;
	private String postSlug;
	private List<Tag> tagList;
}
