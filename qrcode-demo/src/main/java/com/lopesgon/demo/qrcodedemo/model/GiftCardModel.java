package com.lopesgon.demo.qrcodedemo.model;

import java.net.URI;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GiftCardModel {
    private URI website;
    private UUID id;
    private UUID sellerId;
}
