package com.ssafy.thxstore.store.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DetailStoreResponse {
    private sideInfo sideInfo;
    private baseInfo baseInfo;
}
