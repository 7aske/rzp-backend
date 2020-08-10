package com.example.backend.adapter;

import com.example.backend.entity.Comment;
import com.example.backend.entity.dto.CommentDTO;

public class CommentAdapter {
	public static CommentDTO adapt(Comment comment) {
		if (comment == null) {
			return null;
		}

		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setIdComment(comment.getIdComment());
		commentDTO.setCommentBody(comment.getCommentBody());
		commentDTO.setCommentDatePosted(comment.getCommentDatePosted());
		commentDTO.setIdPost(comment.getIdPost().getIdPost());
		commentDTO.setIdUser(UserDTOAdapter.adapt(comment.getIdUser()));
		return commentDTO;
	}
}
