package com.example.springboot.controller.request;

import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
//@RequiredArgsConstructor
public class UserPageRequest extends BaseRequest {
    private String name;
    private String phone;
}
