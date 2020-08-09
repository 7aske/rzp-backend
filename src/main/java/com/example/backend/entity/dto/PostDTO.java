package com.example.backend.entity.dto;

import com.example.backend.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	private Long idPost;
	private Long idUser;
	private Long idCategory;
	private List<PostTranslationDTO> postTranslations;
	private Boolean postPublished;
	private Boolean postDeleted;
	private LocalDate postDatePosted;
	private Long postViews;

}
