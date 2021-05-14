package rs.digitize.backend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Sort;
import rs.digitize.backend.entity.PostPreview;
import rs.digitize.backend.service.PostPreviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/previews")
@RequiredArgsConstructor
public class PostPreviewController {
	private final PostPreviewService postPreviewService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllPostPreviews")
	public ResponseEntity<List<PostPreview>> getAll(
			@RequestParam(required = false, name="q") Specification<PostPreview> query,
			@RequestParam(required = false, name = "sort") Sort sort,
			@RequestParam(required = false, name = "page") Pageable pageable) {
		return ResponseEntity.ok(postPreviewService.findAll(query, sort, pageable));
	}
}

