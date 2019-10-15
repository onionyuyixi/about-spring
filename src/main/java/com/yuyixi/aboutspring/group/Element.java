package com.yuyixi.aboutspring.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangcong
 * @date 2019/8/16 16:46
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Element {

    int year;

    int month;

    int day;

    int num;
}
