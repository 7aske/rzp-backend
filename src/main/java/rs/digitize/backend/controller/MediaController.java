package rs.digitize.backend.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.digitize.backend.entity.Media;
import rs.digitize.backend.service.MediaService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/medias")
@RequiredArgsConstructor
public class MediaController {
	private final MediaService mediaService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllMedias")
	public ResponseEntity<List<Media>> getAllMedias(@RequestParam(name = "q", required = false) Specification<Media> specification,
	                                                @RequestParam(name = "page", required = false) Pageable page,
	                                                @RequestParam(name = "sort", required = false) Sort sort) {
		return ResponseEntity.ok(mediaService.findAll(specification, page, sort));
	}

	@GetMapping("/{mediaId}")
	@ApiOperation(value = "", nickname = "getMediaById")
	public ResponseEntity<Media> getMediaById(@PathVariable Integer mediaId) {
		return ResponseEntity.ok(mediaService.findById(mediaId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "uploadMedia")
	public ResponseEntity<Media> saveMedia(@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
		return ResponseEntity.status(CREATED).body(mediaService.upload(multipartFile));
	}

	@DeleteMapping("/{mediaId}")
	@ApiOperation(value = "", nickname = "deleteMediaById")
	public void deleteMediaById(@PathVariable Integer mediaId) {
		mediaService.deleteById(mediaId);
	}

}

