package com.ssafy.thxstore.controller.store;

import com.ssafy.thxstore.image.service.ImageService;
import com.ssafy.thxstore.store.domain.Store;
import com.ssafy.thxstore.store.dto.CreateStoreDto;
import com.ssafy.thxstore.store.dto.CreateStoreDtoTest;
import com.ssafy.thxstore.store.service.StoreService;
import com.sun.jersey.multipart.FormDataParam;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/store", produces = MediaTypes.HAL_JSON_VALUE)
public class StoreController {

    private final StoreService storeService;
    private final ImageService imageService;

    //@Consumes({MediaType.MULTIPART_FORM_DATA})
    // root
    @PostMapping
    public ResponseEntity createStore(@ModelAttribute CreateStoreDtoTest createStoreDtoTest){
        //@RequestHeader String authorization,@Valid @RequestBody CreateStoreDto createStoreDto
        //@RequestPart(value = "file") MultipartFile licenseImage, @RequestPart("request") CreateStoreDto createStoreDto){
        // 인증 검사
        //MultipartFile test;
        System.out.println("test");
        String imgProfile = null;
        try {
            imgProfile = imageService.createImage(createStoreDtoTest.getLicenseImg());
        }catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CreateStoreDto createStoreDto = CreateStoreDto.builder()
                .name(createStoreDtoTest.getName())
                .mainAddress(createStoreDtoTest.getMainAddress())
                .subAddress(createStoreDtoTest.getSubAddress())
                .phoneNum(createStoreDtoTest.getPhoneNum())
                .license(createStoreDtoTest.getLicense())
                .licenseImg(imgProfile)
                .build();
        //createStoreDto.setLicenseImg(imgProfile);
        Store store = storeService.createStore(createStoreDto);

        WebMvcLinkBuilder selfLinkBuilder = linkTo(StoreController.class).slash(store.getId());
        URI createUri = selfLinkBuilder.toUri();
        StoreResource storeResource = new StoreResource(store);
        storeResource.add(linkTo(StoreController.class).withRel("create-store"));
        storeResource.add(Link.of("/api/docs/index.html#resources-create-store").withRel("profile"));

        return ResponseEntity.created(createUri).body(storeResource);
    }

    // 스토어 상세 조회
    @GetMapping
    public ResponseEntity detailStore(){
        return ResponseEntity.created(null).body(null);
    }

    //스토어 정보 수정(개인)
    @PatchMapping
    public ResponseEntity patchStore(){
        return ResponseEntity.created(null).body(null);
    }

    // 스토어 정보 수정(불변) 불변 자료만 받아오자
    @PutMapping
    public ResponseEntity putStore(){
        return ResponseEntity.created(null).body(null);
    }

    //스토어 상태 반환
    @GetMapping("/status/")
    public ResponseEntity statusStore(){
        return ResponseEntity.created(null).body(null);
    }
}