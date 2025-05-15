package com.rikkei.ss09.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScreenRoom {
    private Long id;
    private String screenRoomName;
    private Integer totalSeat;
}
