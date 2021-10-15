package rs.digitize.backend.service.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.digitize.backend.entity.Media;
import rs.digitize.backend.entity.domain.MediaType;
import rs.digitize.backend.exception.io.MediaUploadException;
import rs.digitize.backend.repository.MediaRepository;
import rs.digitize.backend.service.MediaService;
import rs.digitize.backend.util.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class MediaServiceImpl implements MediaService {
	private final MediaRepository mediaRepository;

	@Value("${media.upload.path}")
	private String mediaUploadPath;

	@Override
	public List<Media> findAll() {
		return mediaRepository.findAll();
	}

	@Override
	public List<Media> findAll(Specification<Media> specification, Pageable pageable, Sort sort) {
		if (pageable != null) {
			return mediaRepository.findAll(specification, pageable).toList();
		}
		return mediaRepository.findAll(specification, sort == null ? Sort.unsorted() : sort);
	}

	@Override
	public Media findById(Integer mediaId) {
		return mediaRepository.findById(mediaId)
				.orElseThrow(() -> new NoSuchElementException("MediaService.notFound"));
	}

	@Override
	public Media update(Media media) {
		Media existing = findById(media.getId());
		media.setHeight(existing.getHeight());
		media.setSize(existing.getSize());
		media.setWidth(existing.getWidth());
		media.setUri(existing.getUri());
		return mediaRepository.save(media);
	}

	@Override
	public void deleteById(Integer mediaId) {
		Media media = mediaRepository.findById(mediaId)
				.orElseThrow(() -> new NoSuchElementException("media.not-found"));
		Path path = Paths.get(mediaUploadPath, media.getUri());
		IOUtils.deleteFileIfExistsNoExcept(path);
		mediaRepository.deleteById(mediaId);
	}

	@Override
	public Media upload(MultipartFile file) {
		return upload(file, MediaType.POST_IMAGE);
	}

	@Override
	public Media upload(MultipartFile file, MediaType type) {
		if (file == null || file.isEmpty()) throw new MediaUploadException("media.upload.file-empty");

		String filename = getUploadedFilename(file);
		String contentType = file.getContentType();
		Path path = Paths.get(mediaUploadPath,"static/media", filename);
		String pathStr = path.toString();
		String uri = Paths.get("static/media", filename).toString();

		Optional<Media> existing = mediaRepository.findByUri(uri);
		if (existing.isPresent()) return existing.get();

		try {
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));

			File destFile = new File(pathStr);
			destFile.getParentFile().mkdirs();
			ImageIO.write(src, contentType == null ? "png" : contentType.split("/")[1], destFile);

			Media media = new Media();
			media.setHeight(src.getHeight());
			media.setWidth(src.getWidth());
			media.setSize(destFile.length());
			media.setUri(uri);
			media.setType(type);
			return mediaRepository.save(media);
		} catch (IOException e) {
			e.printStackTrace();
			IOUtils.deleteFileIfExistsNoExcept(path);
			throw new MediaUploadException("media.upload.failed");
		}
	}

	private String getUploadedFilename(MultipartFile file) {
		String uuid = UUID.randomUUID().toString();
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return String.format("%s.%s", uuid, ext);
	}
}