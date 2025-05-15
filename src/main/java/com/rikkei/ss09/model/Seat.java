package com.rikkei.ss09.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Seat {
    private Long id;
    private Long screenRoomId;
    private Double price;
    private String status;
}
