package rs.digitize.backend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import rs.digitize.backend.entity.PostPreview;
import rs.digitize.backend.entity.User;
import rs.digitize.backend.entity.domain.RecordStatus;
import rs.digitize.backend.security.annotaions.AllowAuthor;
import rs.digitize.backend.service.PostPreviewService;
import rs.digitize.backend.util.SpecificationUtil;

import java.util.List;

import static rs.digitize.backend.entity.domain.RecordStatus.ACTIVE;
import static rs.digitize.backend.util.SpecificationUtil.combineSpecificationFor;

@RestController
@RequestMapping("/previews")
@RequiredArgsConstructor
public class PostPreviewController {
	private final PostPreviewService postPreviewService;

	@AllowAuthor
	@GetMapping("/all")
	@ApiOperation(value = "", nickname = "getAllPostPreviews")
	public ResponseEntity<List<PostPreview>> getAllPostPreviews(@AuthenticationPrincipal User user,
	                                                            @RequestParam(required = false, name = "specification") Specification<PostPreview> specification,
	                                                            @RequestParam(required = false, name = "sort") Sort sort,
	                                                            @RequestParam(required = false, name = "page") Pageable pageable) {
		if (!user.isAdmin()) {
			specification = combineSpecificationFor(specification, "user.username", user.getUsername());
		}
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

	@PutMapping("/{postId}/record-status")
	@ApiOperation(value = "", nickname = "setRecordStatus")
	public ResponseEntity<PostPreview> setRecordStatus(@PathVariable Integer postId,
	                                                   @RequestParam RecordStatus recordStatus) {
		return ResponseEntity.ok(postPreviewService.setRecordStatus(postId, recordStatus));
	}
}

