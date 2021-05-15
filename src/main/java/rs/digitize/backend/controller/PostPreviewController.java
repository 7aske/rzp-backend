package rs.digitize.backend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.digitize.backend.entity.PostPreview;
import rs.digitize.backend.security.annotaions.AllowAuthor;
import rs.digitize.backend.service.PostPreviewService;
import rs.digitize.backend.util.SpecificationUtil;

import java.util.List;

import static rs.digitize.backend.entity.domain.RecordStatus.ACTIVE;

@RestController
@RequestMapping("/previews")
@RequiredArgsConstructor
public class PostPreviewController {
	private final PostPreviewService postPreviewService;

	@AllowAuthor
	@GetMapping("/all")
	@ApiOperation(value = "", nickname = "getAllPostPreviews")
	public ResponseEntity<List<PostPreview>> getAllPostPreviews(@RequestParam(required = false, name = "specification") Specification<PostPreview> specification,
	                                                            @RequestParam(required = false, name = "sort") Sort sort,
	                                                            @RequestParam(required = false, name = "page") Pageable pageable) {
		return ResponseEntity.ok(postPreviewService.findAll(specification, sort, pageable));
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllPostPreviewsNotDeleted")
	public ResponseEntity<List<PostPreview>> getAllPostPreviewsNotDeleted(@RequestParam(name = "q", required = false) Specification<PostPreview> specification,
	                                                                      @RequestParam(name = "page", required = false) Pageable pageable,
	                                                                      @RequestParam(name = "sort", required = false) Sort sort) {
		Specification<PostPreview> spec = SpecificationUtil.combineSpecificationFor(specification, ACTIVE);
		return ResponseEntity.ok(postPreviewService.findAll(spec, sort, pageable));
	}
}

