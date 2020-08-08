package com.example.backend.entity.dto;

import com.example.backend.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveDTO {
	private Long idUser;
	private Category idCategory;
	private List<PostTranslationSaveDTO> postTranslations;
}
