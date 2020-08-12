package com.example.backend.entity.dto;

import com.example.backend.entity.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class PostPreviewDTO {
	private Long idPost;
	private Long idUser;
	private String postAuthor;
	private String categoryName;
	private Date postDatePosted;
	private Date postDateUpdated;
	private Long postViews;
	private String postTitle;
	private String postExcerpt;
	private String postSlug;
	private Boolean postPublished;
	private List<Tag> tags;
}
