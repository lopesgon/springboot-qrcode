package com.lopesgon.digift.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lopesgon.digift.api.model.GiftCardModel;
import com.lopesgon.digift.api.service.QrService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/barcodes")
@AllArgsConstructor
public class QrController {

  private final QrService qrService;

  @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<byte[]> getQrCode() throws Exception {
    try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
      ImageIO.write(qrService.generateQRCodeImage(GiftCardModel.builder()
          .id(UUID.randomUUID())
          .website(URI.create("https://google.ch"))
          .sellerId(UUID.randomUUID())
          .build()), "png", stream);

      return ResponseEntity.ok(stream.toByteArray());
    }
  }

  @PostMapping(consumes = { MediaType.IMAGE_PNG_VALUE })
  public GiftCardModel postMethodName(@RequestBody byte[] qrCoBufferedImage) throws IOException {
    return qrService.decodeQRCodeImage(qrCoBufferedImage);
  }

}