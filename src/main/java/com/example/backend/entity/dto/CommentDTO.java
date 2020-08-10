package com.example.backend.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
	private Long idComment;
	private UserCommentDTO idUser;
	private Long idPost;
	private String commentBody;
	private Date commentDatePosted;
}
