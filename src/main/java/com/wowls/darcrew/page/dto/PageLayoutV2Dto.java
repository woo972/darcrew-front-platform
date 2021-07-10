package com.wowls.darcrew.page.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PageLayoutV2Dto {
    @Builder.Default
    private boolean canaryStatus = false;
    @Builder.Default
    private PageLayoutDto canaryPageLayoutDto = null;
    private PageLayoutDto pageLayoutDto;

}
