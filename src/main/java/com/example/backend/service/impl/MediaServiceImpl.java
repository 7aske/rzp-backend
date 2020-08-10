package com.example.backend.service.impl;

import com.example.backend.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.backend.entity.Media;
import com.example.backend.repository.MediaRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaRepository mediaRepository;

	@Value("${media.upload.path}")
	private String mediaUploadPath;

	@Override
	public List<Media> findAll() {
		return mediaRepository.findAll();
	}

	@Override
	public Media findById(Long idMedia) {
		return mediaRepository.findById(idMedia).orElse(null);
	}

	@Override
	public Media save(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public Media upload(MultipartFile file) throws IOException {
		if (file.isEmpty()) {
			return null;
		}

		BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
		UUID uuid = UUID.randomUUID();
		String filePath = Paths.get(mediaUploadPath, uuid.toString()).toString();

		File destination = new File(filePath);
		destination.mkdirs();
		ImageIO.write(src, "png", destination);

		Media media = new Media();
		media.setMediaFilepath(String.format("static/media/%s", uuid.toString()));

		return mediaRepository.save(media);
	}

	@Override
	public Media update(Media media) {
		return mediaRepository.save(media);
	}

	@Override
	public void delete(Media media) throws Exception {
		deleteAssociatedFile(media);
		mediaRepository.delete(media);
	}

	@Override
	public void deleteById(Long idMedia) throws Exception {
		deleteAssociatedFile(idMedia);
		mediaRepository.deleteById(idMedia);
	}

	private void deleteAssociatedFile(Media media) throws IOException {
		Path path = Paths.get(mediaUploadPath, media.getMediaFilepath());
		Files.deleteIfExists(path);
	}

	private void deleteAssociatedFile(Long idMedia) throws IOException {
		if (!mediaRepository.findById(idMedia).isPresent()) {
			return;
		}
		deleteAssociatedFile(mediaRepository.findById(idMedia).get());
	}

}
