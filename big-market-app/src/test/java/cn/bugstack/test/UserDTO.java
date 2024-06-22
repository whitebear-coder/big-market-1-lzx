package cn.bugstack.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author：linzexu
 * @date: 2024/6/22
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

    private String userId;
    private String userName;
    private Integer userAge;
}
