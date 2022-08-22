package com.lopesgon.demo.qrcodedemo.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.lopesgon.demo.qrcodedemo.model.GiftCardModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class QrService {

    private static Map<EncodeHintType, ?> ENCODE_HINTS = new HashMap<>() {
        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            put(EncodeHintType.CHARACTER_SET, "UTF-8");
        }
    };

    public BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 500, 500, ENCODE_HINTS);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public BufferedImage generateQRCodeImage(GiftCardModel giftCardModel) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(giftCardModel.toString(), BarcodeFormat.QR_CODE, 500, 500,
                ENCODE_HINTS);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public GiftCardModel decodeQRCodeImage(byte[] qrCoBufferedImage) throws IOException {
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(qrCoBufferedImage));
        QRCodeReader reader = new QRCodeReader();
        Result result;
        try {
            result = reader.decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image))),
                    null);
        } catch (NotFoundException | ChecksumException | FormatException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        log.info(result.getText());
        return null;
    }
}
