package com.example.springboot.request;

import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
//@RequiredArgsConstructor
public class UserPageRequest extends BaseRequest {
    private String name;
    private String phone;
}
