package com.musala.musalatestapp.domain.general;

import lombok.Data;

@Data(staticConstructor = "of")
public class PageRequest {
    private final int size;
    private final int page;
}
